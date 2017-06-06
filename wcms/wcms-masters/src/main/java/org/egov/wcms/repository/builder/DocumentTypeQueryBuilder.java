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

package org.egov.wcms.repository.builder;

import java.util.List;

import org.egov.wcms.config.ApplicationProperties;
import org.egov.wcms.web.contract.DocumentTypeGetReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeQueryBuilder {

    @Autowired
    private ApplicationProperties applicationProperties;


    private static final Logger logger = LoggerFactory.getLogger(DocumentTypeQueryBuilder.class);

    private static final String BASE_QUERY = "SELECT document.id as document_id, document.code as document_code,"
            + " document.name as document_name, document.description as document_description,document.active as document_active, document.tenantId as document_tenantId "
            + " FROM egwtr_document_type document ";

    @SuppressWarnings("rawtypes")
    public String getQuery(DocumentTypeGetReq documentTypeGetRequest, List preparedStatementValues) {
        StringBuilder selectQuery = new StringBuilder(BASE_QUERY);

        addWhereClause(selectQuery, preparedStatementValues, documentTypeGetRequest);
        addOrderByClause(selectQuery, documentTypeGetRequest);
        addPagingClause(selectQuery, preparedStatementValues, documentTypeGetRequest);

        logger.debug("Query : " + selectQuery);
        return selectQuery.toString();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void addWhereClause(StringBuilder selectQuery, List preparedStatementValues,
                                DocumentTypeGetReq documentTypeGetRequest) {

        if (documentTypeGetRequest.getId() == null && documentTypeGetRequest.getName() == null && documentTypeGetRequest.getActive() == null
                && documentTypeGetRequest.getTenantId() == null)
            return;

        selectQuery.append(" WHERE");
        boolean isAppendAndClause = false;

        if (documentTypeGetRequest.getTenantId() != null) {
            isAppendAndClause = true;
            selectQuery.append(" document.tenantId = ?");
            preparedStatementValues.add(documentTypeGetRequest.getTenantId());
        }

        if (documentTypeGetRequest.getId() != null) {
            isAppendAndClause = addAndClauseIfRequired(isAppendAndClause, selectQuery);
            selectQuery.append(" document.id IN " + getIdQuery(documentTypeGetRequest.getId()));
        }


        if (documentTypeGetRequest.getName() != null) {
            isAppendAndClause = addAndClauseIfRequired(isAppendAndClause, selectQuery);
            selectQuery.append(" document.name = ?");
            preparedStatementValues.add(documentTypeGetRequest.getName());
        }

        if (documentTypeGetRequest.getCode() != null) {
            isAppendAndClause = addAndClauseIfRequired(isAppendAndClause, selectQuery);
            selectQuery.append(" document.code = ?");
            preparedStatementValues.add(documentTypeGetRequest.getCode());
        }

        if (documentTypeGetRequest.getActive() != null) {
            isAppendAndClause = addAndClauseIfRequired(isAppendAndClause, selectQuery);
            selectQuery.append(" document.active = ?");
            preparedStatementValues.add(documentTypeGetRequest.getActive());
        }
    }

    private void addOrderByClause(StringBuilder selectQuery, DocumentTypeGetReq documentTypeGetRequest) {
        String sortBy = (documentTypeGetRequest.getSortBy() == null ? "document.id"
                : "document." + documentTypeGetRequest.getSortBy());
        String sortOrder = (documentTypeGetRequest.getSortOrder() == null ? "DESC" : documentTypeGetRequest.getSortOrder());
        selectQuery.append(" ORDER BY " + sortBy + " " + sortOrder);
    }


    /**
     * This method is always called at the beginning of the method so that and
     * is prepended before the field's predicate is handled.
     *
     * @param appendAndClauseFlag
     * @param queryString
     * @return boolean indicates if the next predicate should append an "AND"
     */
    private boolean addAndClauseIfRequired(boolean appendAndClauseFlag, StringBuilder queryString) {
        if (appendAndClauseFlag)
            queryString.append(" AND");

        return true;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void addPagingClause(StringBuilder selectQuery, List preparedStatementValues,
                                 DocumentTypeGetReq documentTypeGetRequest) {
        // handle limit(also called pageSize) here
        selectQuery.append(" LIMIT ?");
        long pageSize = Integer.parseInt(applicationProperties.wcmsSearchPageSizeDefault());
        if (documentTypeGetRequest.getPageSize() != null)
            pageSize = documentTypeGetRequest.getPageSize();
        preparedStatementValues.add(pageSize); // Set limit to pageSize

        // handle offset here
        selectQuery.append(" OFFSET ?");
        int pageNumber = 0; // Default pageNo is zero meaning first page
        if (documentTypeGetRequest.getPageNumber() != null)
            pageNumber = documentTypeGetRequest.getPageNumber() - 1;
        preparedStatementValues.add(pageNumber * pageSize); // Set offset to
        // pageNo * pageSize
    }

    private static String getIdQuery(List<Long> idList) {
        StringBuilder query = new StringBuilder("(");
        if (idList.size() >= 1) {
            query.append(idList.get(0).toString());
            for (int i = 1; i < idList.size(); i++) {
                query.append(", " + idList.get(i));
            }
        }
        return query.append(")").toString();
    }


    public static String insertDocumentTypeQuery() {
        return "INSERT INTO egwtr_document_type(code,name,description,active,createdby,lastmodifiedby,createddate,lastmodifieddate,tenantid) values "
                + "(?,?,?,?,?,?,?,?,?)";
    }

    public static String updateDocumentTypeQuery() {
        return "UPDATE egwtr_document_type SET name = ?,description = ?,"
                + "active = ?,lastmodifiedby = ?,lastmodifieddate = ? where code = ?";
    }
    public static String selectDocumentTypeByNameAndCodeQuery() {
        return " select code FROM egwtr_document_type where name = ? and tenantId = ?";
    }


    public static String selectDocumentTypeByNameAndCodeNotInQuery() {
        return " select code from egwtr_document_type where name = ? and tenantId = ? and code != ? ";
    }

}

