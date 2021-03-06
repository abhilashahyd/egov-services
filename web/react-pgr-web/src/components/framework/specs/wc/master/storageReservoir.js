var dat = {
	"wc.create": {
		"numCols": 12/3,
		"url": "/wcms/masters/storagereservoirs/_create",
		"tenantIdRequired": true,
		"idJsonPath": "StorageReservoirs[0].code",
		"useTimestamp": true,
		"objectName": "StorageReservoirs",
		"groups": [
			{
				"label": "wc.create.storageReservoir.title",
				"name": "createStorageReservoir",
				"fields": [
						{
							"name": "name",
							"jsonPath": "StorageReservoirs[0].name",
							"label": "wc.create.groups.fields.storageReservoirName",
							"pattern": "^.{0,40}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of name is 40"
						},
						{
							"name": "reservoirType",
							"jsonPath": "StorageReservoirs[0].reservoirType",
							"label": "wc.create.groups.fields.reservoirType",
							"pattern": "",
							"type": "singleValueList",
							"url": "/wcms/masters/master/_getreservoirtypes?|$..key|$..object",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "locationName",
							"jsonPath": "StorageReservoirs[0].location",
							"label": "wc.create.groups.fields.location",
							"pattern": "^.{3,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of Location is 100"
						},
						{
							"name": "noOfMainDistributionLines",
							"jsonPath": "StorageReservoirs[0].noOfMainDistributionLines",
							"label": "wc.create.groups.fields.numberOfMainDistributionLine",
							"pattern": "^.{1,6}$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of DistributionLine is 6"
						},
						{
							"name": "noOfConnection",
							"jsonPath": "StorageReservoirs[0].noOfConnection",
							"label": "wc.create.groups.fields.numberOfConnectionFromReservoir",
							"pattern": "^.{1,6}$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of connection is 6"
						},
						{
							"name": "noOfSubLines",
							"jsonPath": "StorageReservoirs[0].noOfSubLines",
							"label": "wc.create.groups.fields.numberOfSubLines",
							"pattern": "^.{1,6}$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of subline is 6"
						},
						{
							"name": "capacity",
							"jsonPath": "StorageReservoirs[0].capacity",
							"label": "wc.create.groups.fields.storageCapacityofReservoir(in MLD)",
							"pattern": "^.{1,8}$",
							"type": "number",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of capacity is 8"
						}

				]
			}
		]
	},
	"wc.search": {
		"numCols": 12/3,
		"url": "/wcms/masters/storagereservoirs/_search",
		"tenantIdRequired": true,

		"useTimestamp": true,
		"objectName": "StorageReservoirs",
		"groups": [
			{
				"label": "wc.search.storageReservoir.title",
				"name": "searchStorageReservoir",
				"fields": [
						{
							"name": "name",
							"jsonPath": "name",
							"label": "wc.create.groups.fields.storageReservoirName",
							"pattern": "^.{3,100}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of name is 100"
						},
						{
							"name": "reservoirType",
							"jsonPath": "reservoirType",
							"label": "wc.create.groups.fields.reservoirType",
							"pattern": "",
							"type": "singleValueList",
							"url": "/wcms/masters/master/_getreservoirtypes?|$..key|$..object",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		],
		"result": {
			"header": [{label: "wc.create.groups.fields.storageReservoirName"},{label: "wc.create.groups.fields.reservoirType"}, {label: "wc.create.groups.fields.location"},
			{label: "wc.create.groups.fields.storageCapacityofReservoir(in MLD)"},{label: "wc.create.groups.fields.numberOfSubLines"},{label: "wc.create.groups.fields.numberOfMainDistributionLine"},{label: "wc.create.groups.fields.numberOfConnectionFromReservoir"}],
			"values": ["name", "reservoirType", "location","capacity","noOfSubLines","noOfMainDistributionLines","noOfConnection"],
			"resultPath": "StorageReservoirs",
			"rowClickUrlUpdate": "/update/wc/storageReservoir/{id}",
			"rowClickUrlView": "/view/wc/storageReservoir/{id}"
			}
	},
	"wc.view": {
		"numCols": 12/3,
		"url": "/wcms/masters/storagereservoirs/_search?ids={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "StorageReservoirs",
		"groups": [
			{
				"label": "wc.view.storageReservoir.title",
				"name": "viewStorageReservoir",
				"fields": [
						{
							"name": "name",
							"jsonPath": "StorageReservoirs[0].name",
							"label": "wc.create.groups.fields.storageReservoirName",
							"pattern": "^.{0,40}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of name is 40"
						},
						{
							"name": "reservoirType",
							"jsonPath": "StorageReservoirs[0].reservoirType",
							"label": "wc.create.groups.fields.reservoirType",
							"pattern": "",
							"type": "singleValueList",
							"url": "/wcms/masters/master/_getreservoirtypes?|$..key|$..object",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "locationName",
							"jsonPath": "StorageReservoirs[0].location",
							"label": "wc.create.groups.fields.location",
							"pattern": "^.{3,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of Location is 100"
						},
						{
							"name": "noOfMainDistributionLines",
							"jsonPath": "StorageReservoirs[0].noOfMainDistributionLines",
							"label": "wc.create.groups.fields.numberOfMainDistributionLine",
							"pattern": "^.{1,124}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of DistributionLine is 6"
						},
						{
							"name": "noOfConnection",
							"jsonPath": "StorageReservoirs[0].noOfConnection",
							"label": "wc.create.groups.fields.numberOfConnectionFromReservoir",
							"pattern": "^.{1,124}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of connection is 6"
						},
						{
							"name": "noOfSubLines",
							"jsonPath": "StorageReservoirs[0].noOfSubLines",
							"label": "wc.create.groups.fields.numberOfSubLines",
							"pattern": "^.{1,124}$",
							"type": "text",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of subline is 6"
						},
						{
							"name": "capacity",
							"jsonPath": "StorageReservoirs[0].capacity",
							"label": "wc.create.groups.fields.storageCapacityofReservoir(in MLD)",
							"pattern": "^.{1,8}$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of capacity is 8"
						}

				]
			}
		]
	},
	"wc.update" : {
		"numCols": 12/3,
		"searchUrl": "/wcms/masters/storagereservoirs/_search?ids={id}",
		"url":"/wcms/masters/storagereservoirs/_update",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "StorageReservoirs",
		"groups": [
			{
				"label": "wc.update.storageReservoir.title",
				"name": "updateStorageReservoir",
				"fields": [
						{
							"name": "name",
							"jsonPath": "StorageReservoirs[0].name",
							"label": "wc.create.groups.fields.storageReservoirName",
							"pattern": "^.{0,40}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of name is 40"
						},
						{
							"name": "reservoirType",
							"jsonPath": "StorageReservoirs[0].reservoirType",
							"label": "wc.create.groups.fields.reservoirType",
							"pattern": "",
							"type": "singleValueList",
							"url": "/wcms/masters/master/_getreservoirtypes?|$..key|$..object",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						},
						{
							"name": "locationName",
							"jsonPath": "StorageReservoirs[0].location",
							"label": "wc.create.groups.fields.location",
							"pattern": "^.{0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of Location is 100"
						},
						{
							"name": "noOfMainDistributionLines",
							"jsonPath": "StorageReservoirs[0].noOfMainDistributionLines",
							"label": "wc.create.groups.fields.numberOfMainDistributionLine",
							"pattern": "^.{1,6}$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of DistributionLine is 6"
						},
						{
							"name": "noOfConnection",
							"jsonPath": "StorageReservoirs[0].noOfConnection",
							"label": "wc.create.groups.fields.numberOfConnectionFromReservoir",
							"pattern": "^.{1,6}$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of connection is 6"
						},
						{
							"name": "noOfSubLines",
							"jsonPath": "StorageReservoirs[0].noOfSubLines",
							"label": "wc.create.groups.fields.numberOfSubLines",
							"pattern": "^.{1,6}$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of subline is 6"
						},
						{
							"name": "capacity",
							"jsonPath": "StorageReservoirs[0].capacity",
							"label": "wc.create.groups.fields.storageCapacityofReservoir(in MLD)",
							"pattern": "^.{1,8}$",
							"type": "number",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Maximum length of capacity is 8"
						}

				]
			}
		]
	}
}

export default dat;
