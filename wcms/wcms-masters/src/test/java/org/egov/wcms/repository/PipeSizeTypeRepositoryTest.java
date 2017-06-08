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

package org.egov.wcms.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.request.User;
import org.egov.wcms.config.ApplicationProperties;
import org.egov.wcms.model.PipeSizeType;
import org.egov.wcms.repository.builder.PipeSizeTypeQueryBuilder;
import org.egov.wcms.repository.rowmapper.PipeSizeTypeRowMapper;
import org.egov.wcms.service.PipeSizeTypeService;
import org.egov.wcms.web.contract.PipeSizeTypeGetRequest;
import org.egov.wcms.web.contract.PipeSizeTypeRequest;
import org.egov.wcms.web.contract.factory.ResponseInfoFactory;
import org.egov.wcms.web.errorhandlers.ErrorHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(PipeSizeTypeRepository.class)
@WebAppConfiguration
public class PipeSizeTypeRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private PipeSizeTypeQueryBuilder pipeSizeQueryBuilder;

    @Mock
    private PipeSizeTypeRowMapper pipeSizeRowMapper;

    @MockBean
    private PipeSizeTypeService pipeSizeService;

    @MockBean
    private ErrorHandler errHandler;

    @MockBean
    private ApplicationProperties applicationProperties;

    @MockBean
    private ResponseInfoFactory responseInfoFactory;

    @InjectMocks
    private PipeSizeTypeRepository pipeSizeRepository;

    @Test
    public void test_Should_Create_PipeSize() throws Exception {
        final PipeSizeTypeRequest pipeSizeRequest = new PipeSizeTypeRequest();
        final RequestInfo requestInfo = new RequestInfo();
        final User user = new User();
        user.setId(1L);
        requestInfo.setUserInfo(user);
        final PipeSizeType pipeSize = new PipeSizeType();
        pipeSize.setCode("10");
        pipeSize.setActive(true);
        // pipeSize.getAuditDeatils().setCreatedBy(1L);
        pipeSize.setSizeInInch(1.2);
        pipeSize.setSizeInMilimeter(10.1);

        pipeSizeRequest.setRequestInfo(requestInfo);
        pipeSizeRequest.setPipeSize(pipeSize);

        assertNotNull(pipeSizeRepository.persistCreatePipeSize(pipeSizeRequest));

    }

    @Test(expected = Exception.class)
    public void test_throwException_Create_PipeSize() throws Exception {
        final PipeSizeTypeRequest pipeSizeRequest = new PipeSizeTypeRequest();
        final RequestInfo requestInfo = new RequestInfo();
        final PipeSizeType pipeSize = new PipeSizeType();
        pipeSize.setCode("10");
        pipeSize.setActive(true);
        // pipeSize.getAuditDeatils().setCreatedBy(1L);
        pipeSize.setSizeInInch(1.2);
        pipeSize.setSizeInMilimeter(10.1);

        pipeSizeRequest.setRequestInfo(requestInfo);
        pipeSizeRequest.setPipeSize(pipeSize);

        assertNotNull(pipeSizeRepository.persistCreatePipeSize(pipeSizeRequest));

    }

    @Test
    public void test_Should_Modify_PipeSize() throws Exception {
        final PipeSizeTypeRequest pipeSizeRequest = new PipeSizeTypeRequest();
        final RequestInfo requestInfo = new RequestInfo();
        final User user = new User();
        user.setId(1L);
        requestInfo.setUserInfo(user);
        final PipeSizeType pipeSize = new PipeSizeType();
        pipeSize.setCode("10");
        pipeSize.setActive(true);
        // pipeSize.getAuditDeatils().setCreatedBy(1L);
        pipeSize.setSizeInInch(1.2);
        pipeSize.setSizeInMilimeter(10.1);

        pipeSizeRequest.setRequestInfo(requestInfo);
        pipeSizeRequest.setPipeSize(pipeSize);

        assertNotNull(pipeSizeRepository.persistModifyPipeSize(pipeSizeRequest));

    }

    @Test(expected = Exception.class)
    public void test_throwException_Modify_PipeSize() throws Exception {
        final PipeSizeTypeRequest pipeSizeRequest = new PipeSizeTypeRequest();
        final RequestInfo requestInfo = new RequestInfo();
        final PipeSizeType pipeSize = new PipeSizeType();
        pipeSize.setCode("10");
        pipeSize.setActive(true);
        // pipeSize.getAuditDeatils().setCreatedBy(1L);
        pipeSize.setSizeInInch(1.2);
        pipeSize.setSizeInMilimeter(10.1);

        pipeSizeRequest.setRequestInfo(requestInfo);
        pipeSizeRequest.setPipeSize(pipeSize);

        assertNotNull(pipeSizeRepository.persistModifyPipeSize(pipeSizeRequest));

    }

    @Test
    public void test_Should_Check_PipeSize() throws Exception {
        final List<Object> preparedStatementValues = new ArrayList<>();
        preparedStatementValues.add(1.22);
        preparedStatementValues.add(1.45);

        assertNotNull(pipeSizeRepository.checkPipeSizeInmmAndCode("ABC", 1.22, "1"));

    }

    /*
     * @Test(expected = Exception.class) public void test_throwException_Check_PipeSize() throws Exception{ final List<Object>
     * preparedStatementValues = new ArrayList<Object>(); preparedStatementValues.add(1.22); preparedStatementValues.add(1.45);
     * when(pipeSizeQueryBuilder.selectPipeSizeInmmAndCodeNotInQuery()).thenThrow(Exception.class);
     * assertNotNull(pipeSizeRepository.checkPipeSizeInmmAndCode("ABC", 1.22, "1")); }
     */

    @Test
    public void test_Should__FindforCriteria() throws Exception {
        final List<Object> preparedStatementValues = new ArrayList<>();
        final PipeSizeTypeGetRequest pipeSizeGetRequest = new PipeSizeTypeGetRequest();
        when(pipeSizeQueryBuilder.getQuery(pipeSizeGetRequest, preparedStatementValues)).thenReturn("query");
        final List<PipeSizeType> pipeSizes = pipeSizeRepository.findForCriteria(pipeSizeGetRequest);

        assertTrue(pipeSizes != null);

    }

    @SuppressWarnings("unchecked")
    @Test(expected = Exception.class)
    public void test_throwException__FindforCriteria() throws Exception {
        final List<Object> preparedStatementValues = new ArrayList<>();
        final PipeSizeTypeGetRequest pipeSizeGetRequest = new PipeSizeTypeGetRequest();
        when(pipeSizeQueryBuilder.getQuery(pipeSizeGetRequest, preparedStatementValues)).thenThrow(Exception.class);
        final List<PipeSizeType> pipeSizes = pipeSizeRepository.findForCriteria(pipeSizeGetRequest);

        assertTrue(pipeSizes != null);

    }

}
