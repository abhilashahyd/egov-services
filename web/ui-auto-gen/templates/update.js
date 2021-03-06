var utilities = require('../utilities/utility');
const getType = utilities.getType;
const getTitleCase = utilities.getTitleCase;
const setLabels = utilities.setLabels;
const getQuery = utilities.getQuery;

let localeFields = {};
let errors = {};
let getFieldsFromInnerObject = function(reference, fields, definition, module, jPath, isArray) {
    if (definition[reference])
        for (let key in definition[reference].properties) {
            if (["id", "tenantId", "auditDetails", "assigner"].indexOf(key) > -1) continue;

            if (definition[reference].properties[key].type == "array") {
                let refSplitArr = definition[reference].properties[key].items.$ref.split("/");
                getFieldsFromInnerObject(refSplitArr[refSplitArr.length - 1], fields, definition, module, (isArray ? (jPath + "[0]") : jPath) + "." + key, true);
            } else if (definition[reference].properties[key].$ref) {
                let refSplitArr = definition[reference].properties[key].$ref.split("/");
                getFieldsFromInnerObject(refSplitArr[refSplitArr.length - 1], fields, definition, module, (isArray ? (jPath + "[0]") : jPath) + "." + key);
            } else {
                localeFields[module + ".create." + key] = getTitleCase(key);
                fields[(isArray ? (jPath + "[0]") : jPath) + "." + key] = {
                    "name": key,
                    "jsonPath": (isArray ? (jPath + "[0]") : jPath) + "." + key,
                    "label": module + ".create." + key,
                    "pattern": definition[reference].properties[key].pattern,
                    "type": definition[reference].properties[key].enum ? "singleValueList" : definition[reference].properties[key].format && ["number", "integer", "double", "long", "float"].indexOf(definition[reference].properties[key].type) == -1 ? getType(definition[reference].properties[key].format) :  getType(definition[reference].properties[key].type),
                    "isRequired": (definition[reference].properties[key].required || (definition[reference].required && definition[reference].required.constructor == Array && definition[reference].required.indexOf(key) > -1) ? true : false),
                    "isDisabled": definition[reference].properties[key].readOnly ? true : false,
                    "defaultValue": definition[reference].properties[key].default,
                    "maxLength": definition[reference].properties[key].maxLength,
                    "minLength": definition[reference].properties[key].minLength,
                    "patternErrorMsg": definition[reference].properties[key].pattern ? (module + ".create.field.message." + key) : ""
                };

                if(fields[(isArray ? (jPath + "[0]") : jPath) + "." + key].type == "text" && definition[reference].properties[key].maxLength && definition[reference].properties[key].maxLength > 256)
                    fields[(isArray ? (jPath + "[0]") : jPath) + "." + key].type = "textarea";
            }
        }
}

let updateTemplate = function(module, numCols, path, config, definition, basePath, uiInfoDef) {
    let specifications = {
        numCols: numCols,
        useTimestamp: true,
        objectName: '',
        groups: [],
        url: path,
        tenantIdRequired: true,
        searchUrl: basePath + uiInfoDef.searchUrl
    };
    let fields = {};
    let ind = 0;
    for(var i=0; i<config["post"].parameters.length; i++) {
        if(config["post"].parameters[i].schema) {
            ind = i;
            break;
        }
    }
    let splitArr = config["post"].parameters[ind].schema.$ref.split("/");
    let properties = definition[splitArr[splitArr.length - 1]].properties;
    let reference;
    for (let key in properties) {
        if (key != "requestInfo") {
            //IF ARRAY
            if(properties[key].type == "array") {
                let propertiesArr = properties[key].items.$ref.split("/");
                specifications.objectName = key;
                reference = propertiesArr[propertiesArr.length - 1];
                isArr = true;
            } else {
                let propertiesArr = properties[key].$ref.split("/");
                specifications.objectName = key;
                reference = propertiesArr[propertiesArr.length - 1];
            }
            break;
        }
    }

    getFieldsFromInnerObject(reference, fields, definition, module, isArr ? (specifications.objectName + "[0]") : specifications.objectName);

    //=======================CUSTOM FILE LOGIC==========================>>
    if (uiInfoDef.externalData && typeof uiInfoDef.externalData == "object" && uiInfoDef.externalData.length) {
        for(var i=0; i<uiInfoDef.externalData.length; i++) {
            if(fields[uiInfoDef.externalData[i].fieldName]) {
                if(fields[uiInfoDef.externalData[i].fieldName].type == "autoCompelete")
                    fields[uiInfoDef.externalData[i].fieldName].autoCompleteUrl = uiInfoDef.externalData[i].url + getQuery(uiInfoDef.externalData[i].url, uiInfoDef.externalData[i].keyPath, uiInfoDef.externalData[i].valPath);
                else
                    fields[uiInfoDef.externalData[i].fieldName].url = uiInfoDef.externalData[i].url + getQuery(uiInfoDef.externalData[i].url, uiInfoDef.externalData[i].keyPath, uiInfoDef.externalData[i].valPath);
            } else {
                errors[uiInfoDef.externalData[i].fieldName] = "Field exists in x-ui-info externalData section but not present in API specifications. REFERENCE PATH: " + uiInfoDef.referencePath;
            }
        }
    }

    if(uiInfoDef.dependents && uiInfoDef.dependents.length) {
        for(let i=0; i<uiInfoDef.dependents.length; i++) {
            if(fields[uiInfoDef.dependents[i].onChangeField]) {
                fields[uiInfoDef.dependents[i].onChangeField].depedants = [];
                for(let key in uiInfoDef.dependents[i].affectedFields) {
                    fields[uiInfoDef.dependents[i].onChangeField].depedants.push({
                        "jsonPath": key,
                        "type": uiInfoDef.dependents[i].affectedFields[key].type,
                        "pattern": uiInfoDef.dependents[i].affectedFields[key].pattern
                    })
                }
            }
        }
    }
    
    if(uiInfoDef.autoFills && uiInfoDef.autoFills.length) {
        for(var i=0; i< uiInfoDef.autoFills.length; i++) {
            if(fields[uiInfoDef.autoFills[i].onChangeField]) {
                fields[uiInfoDef.autoFills[i].onChangeField].type = "textSearch";
                fields[uiInfoDef.autoFills[i].onChangeField].autoCompleteDependancy = {
                    autoCompleteUrl: uiInfoDef.autoFills[i].url,
                    autoFillFields: {}
                };

                for(var j=0; j< uiInfoDef.autoFills[i].affectedFields.length; j++) {
                    fields[uiInfoDef.autoFills[i].onChangeField].autoCompleteDependancy.autoFillFields[uiInfoDef.autoFills[i].affectedFields[j]] = uiInfoDef.autoFills[i].affectJSONPath[j];
                }
            } else {
                errors[uiInfoDef.autoFills[i].onChangeField] = "Field exists in x-ui-info AutoFills section but not present in API specifications. REFERENCE PATH: " + uiInfoDef.referencePath;
            }
        }
    }

    if(uiInfoDef.checkboxes && uiInfoDef.checkboxes.length) {
        for(var i=0; i<uiInfoDef.checkboxes.length; i++) {
            if(fields[uiInfoDef.checkboxes[i]]) {
                fields[uiInfoDef.checkboxes[i]].type = "checkbox";
            } else {
                errors[uiInfoDef.checkboxes[i]] = "Field exists in x-ui-info checkboxes section but not present in API specifications. REFERENCE PATH: " + uiInfoDef.referencePath;   
            }
        }
    }

    if(uiInfoDef.radios && uiInfoDef.radios.length) {
        for(var i=0; i<uiInfoDef.radios.length; i++) {
            if(fields[uiInfoDef.radios[i].jsonPath]) {
                fields[uiInfoDef.radios[i].jsonPath].type = "radio";
                localeFields[module + ".create." + uiInfoDef.radios[i].trueLabel] = getTitleCase(uiInfoDef.radios[i].trueLabel);
                localeFields[module + ".create." + uiInfoDef.radios[i].falseLabel] = getTitleCase(uiInfoDef.radios[i].falseLabel);
                fields[uiInfoDef.radios[i].jsonPath].values = [{
                    label: module + ".create." + uiInfoDef.radios[i].trueLabel,
                    value: true
                }, {
                    label: module + ".create." + uiInfoDef.radios[i].falseLabel,
                    value: false
                }];
            } else {
                errors[uiInfoDef.radios[i].jsonPath] = "Field exists in x-ui-info radios section but not present in API specifications. REFERENCE PATH: " + uiInfoDef.referencePath;   
            }
        }
    }

    for(var key in uiInfoDef.groups) {
        localeFields[module + ".create.group.title." + key] = getTitleCase(key);
        let group = {
            name: key,
            label: module + ".create.group.title." + key,
            fields: []
        };
        for(var i=0; i<uiInfoDef.groups[key].fields.length; i++) {
            if (fields[uiInfoDef.groups[key].fields[i]])
                group.fields.push(fields[uiInfoDef.groups[key].fields[i]]);
            else
                errors[uiInfoDef.groups[key].fields[i]] = "Field exists in x-ui-info groups section but not present in API specifications. REFERENCE PATH: " + uiInfoDef.referencePath;
        }
        specifications.groups.push(group);
    }

    setLabels(localeFields, "./output/" + module);
    if(Object.keys(errors).length) {

        /*console.log("\x1b[31mERROR OCCURED! CANNOT PROCESS YAML.");
        console.log(errors);
        console.log("\x1b[37m");
        process.exit();*/
        return {specifications: specifications, errors: errors};
    }
    //==================================================================>>
    return {specifications: specifications};
}

module.exports = updateTemplate;