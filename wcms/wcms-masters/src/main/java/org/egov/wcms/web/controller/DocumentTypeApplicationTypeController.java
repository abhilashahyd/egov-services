/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.wcms.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.response.ResponseInfo;
import org.egov.wcms.config.ApplicationProperties;
import org.egov.wcms.model.DocumentTypeApplicationType;
import org.egov.wcms.service.DocumentTypeApplicationTypeService;
import org.egov.wcms.util.ValidatorUtils;
import org.egov.wcms.web.contract.DocumentTypeApplicationTypeGetRequest;
import org.egov.wcms.web.contract.DocumentTypeApplicationTypeReq;
import org.egov.wcms.web.contract.DocumentTypeApplicationTypeRes;
import org.egov.wcms.web.contract.RequestInfoWrapper;
import org.egov.wcms.web.contract.factory.ResponseInfoFactory;
import org.egov.wcms.web.errorhandlers.ErrorHandler;
import org.egov.wcms.web.errorhandlers.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/documenttypes-applicationtypes")
public class DocumentTypeApplicationTypeController {

    @Autowired
    private DocumentTypeApplicationTypeService docTypeAppTypeService;

    @Autowired
    private ErrorHandler errHandler;

    @Autowired
    private ResponseInfoFactory responseInfoFactory;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private ValidatorUtils validatorUtils;

    @PostMapping(value = "/_create")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid final DocumentTypeApplicationTypeReq documentNameRequest,
            final BindingResult errors) {
        if (errors.hasErrors()) {
            final ErrorResponse errRes = validatorUtils.populateErrors(errors);
            return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
        }
        log.info("documentNameRequest::" + documentNameRequest);

        final List<ErrorResponse> errorResponses = validatorUtils.validateDocumentApplicationRequest(documentNameRequest, false);
        if (!errorResponses.isEmpty())
            return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);

        final List<DocumentTypeApplicationType> docTypesAppTypes = docTypeAppTypeService.pushCreateToQueue(
                applicationProperties.getCreateDocumentTypeApplicationTypeTopicName(),
                "documenttypeapplicationtype-create", documentNameRequest);

        return getSuccessResponse(docTypesAppTypes, "Created", documentNameRequest.getRequestInfo());

    }

    @PostMapping(value = "/_update")
    @ResponseBody
    public ResponseEntity<?> update(
            @RequestBody @Valid final DocumentTypeApplicationTypeReq documentTypeApplicationTypeReq,
            final BindingResult errors) {
        if (errors.hasErrors()) {
            final ErrorResponse errRes = validatorUtils.populateErrors(errors);
            return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
        }
        log.info("documentTypeApplicationTypeRequest::" + documentTypeApplicationTypeReq);
        final List<ErrorResponse> errorResponses = validatorUtils
                .validateDocumentApplicationRequest(documentTypeApplicationTypeReq, true);
        if (!errorResponses.isEmpty())
            return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);

        final List<DocumentTypeApplicationType> documentTypeApplicaTypes = docTypeAppTypeService.pushUpdateToQueue(
                applicationProperties.getUpdateDocumentTypeApplicationTypeTopicName(),
                "documenttypeapplicationtype-update", documentTypeApplicationTypeReq);

        return getSuccessResponse(documentTypeApplicaTypes, null, documentTypeApplicationTypeReq.getRequestInfo());
    }

    @PostMapping("/_search")
    @ResponseBody
    public ResponseEntity<?> search(
            @ModelAttribute @Valid final DocumentTypeApplicationTypeGetRequest docTypeAppTypeGetRequest,
            final BindingResult modelAttributeBindingResult,
            @RequestBody @Valid final RequestInfoWrapper requestInfoWrapper,
            final BindingResult requestBodyBindingResult) {
        final RequestInfo requestInfo = requestInfoWrapper.getRequestInfo();

        if (modelAttributeBindingResult.hasErrors())
            return errHandler.getErrorResponseEntityForMissingParameters(modelAttributeBindingResult, requestInfo);

        if (requestBodyBindingResult.hasErrors())
            return errHandler.getErrorResponseEntityForMissingRequestInfo(requestBodyBindingResult, requestInfo);

        List<DocumentTypeApplicationType> docNameList = null;
        try {
            docNameList = docTypeAppTypeService.getDocumentAndApplicationTypes(docTypeAppTypeGetRequest);
        } catch (final Exception exception) {
            log.error("Error while processing request " + docTypeAppTypeGetRequest, exception);
            return errHandler.getResponseEntityForUnexpectedErrors(requestInfo);
        }

        return getSuccessResponse(docNameList, null, requestInfo);

    }

    private ResponseEntity<?> getSuccessResponse(final List<DocumentTypeApplicationType> docTypeAppTypeList,
            final String mode, final RequestInfo requestInfo) {
        final DocumentTypeApplicationTypeRes docNameResponse = new DocumentTypeApplicationTypeRes();
        docNameResponse.setDocumentTypeApplicationTypes(docTypeAppTypeList);
        final ResponseInfo responseInfo = responseInfoFactory.createResponseInfoFromRequestInfo(requestInfo, true);
        if (StringUtils.isNotBlank(mode))
            responseInfo.setStatus(HttpStatus.CREATED.toString());
        else
            responseInfo.setStatus(HttpStatus.OK.toString());
        docNameResponse.setResponseInfo(responseInfo);
        return new ResponseEntity<>(docNameResponse, HttpStatus.OK);

    }

}
