package org.egov.works.services.web.controller;

import org.egov.works.services.domain.service.DocumentDetailsService;
import org.egov.works.services.exception.CustomBindException;
import org.egov.works.services.web.contract.DocumentDetailRequest;
import org.egov.works.services.web.contract.DocumentDetailResponse;
import org.egov.works.services.web.contract.RequestInfo;
import org.egov.works.services.web.contract.ResponseInfo;
import org.egov.works.services.web.model.DocumentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/documentdetails")
public class DocumentDetailController {

    @Autowired
    private DocumentDetailsService documentDetailsService;


    @PostMapping
    @RequestMapping("/_create")
    public ResponseEntity<?> createDocuments(@Valid @RequestBody DocumentDetailRequest documentDetailRequest,
                                             BindingResult errors) throws Exception {

        RequestInfo requestInfo = documentDetailRequest.getRequestInfo();

        if (errors.hasErrors()) {
            throw new CustomBindException(errors, requestInfo);
        }

        final List<DocumentDetail> documents = documentDetailsService.createDocuments(documentDetailRequest);
        DocumentDetailResponse documentDetailResponse = new DocumentDetailResponse();
        //prepare response info
        documentDetailResponse.setResponseInfo(new ResponseInfo());
        documentDetailResponse.setDocumentDetails(documents);
        return new ResponseEntity<>(documentDetailResponse, HttpStatus.OK);

    }

   /* @PostMapping
    @RequestMapping("/_update")
    public ResponseEntity<?> updateDocuments(@Valid @RequestBody DocumentDetailRequest documentDetailRequest,
                                             BindingResult errors) throws Exception {

        RequestInfo requestInfo = documentDetailRequest.getRequestInfo();

        if (errors.hasErrors()) {
            throw new CustomBindException(errors, requestInfo);
        }

        final List<DocumentDetail> documents = documentDetailsService.updateDocuments(documentDetailRequest);
        DocumentDetailResponse documentDetailResponse = new DocumentDetailResponse();
        //prepare response info
        documentDetailResponse.setResponseInfo(new ResponseInfo());
        documentDetailResponse.setDocumentDetails(documents);
        return new ResponseEntity<>(documentDetailResponse, HttpStatus.OK);

    }*/
}