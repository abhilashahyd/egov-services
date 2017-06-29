package org.egov.demand.web.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.response.ResponseInfo;
import org.egov.demand.TestConfiguration;
import org.egov.demand.model.GlCodeMaster;
import org.egov.demand.model.GlCodeMasterCriteria;
import org.egov.demand.service.GlCodeMasterService;
import org.egov.demand.util.FileUtils;
import org.egov.demand.web.contract.GlCodeMasterResponse;
import org.egov.demand.web.contract.factory.ResponseFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GlCodeMasterController.class)
@Import(TestConfiguration.class)
public class GlCodeMasterControllerTest {

	/*@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ResponseFactory responseFactory;
	@MockBean
	private GlCodeMasterService glCodeMasterService;
	
	
	@Test
	public void test_Should_Search_GlCodeMaster() throws Exception {
		List<GlCodeMaster> glCodeMaster = new ArrayList<>();
		glCodeMaster.add(getGlCodeMaster());

		GlCodeMasterResponse glCodeMasterResponse = new GlCodeMasterResponse();
		glCodeMasterResponse.setGlCodeMasters(glCodeMaster);
		glCodeMasterResponse.setResponseInfo(new ResponseInfo());

		System.out.println("glCodeMasterResponse:::::"+glCodeMasterResponse);
		when(glCodeMasterService.getGlCodes(Matchers.any(GlCodeMasterCriteria.class), Matchers.any(RequestInfo.class)))
				.thenReturn(glCodeMasterResponse);
		System.out.println(":::::json:::::::"+content().json(getFileContents("glCodeMasterSearchResponse.json")));
		mockMvc.perform(post("/glcodemasters/_search").param("tenantId", "ap.kurnool")
				.param("service","string").contentType(MediaType.APPLICATION_JSON)
				.content(getFileContents("requestinfowrapper.json"))).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(getFileContents("glCodeMasterSearchResponse.json")));
	}
	
	private String getFileContents(String fileName) throws IOException {
		return new FileUtils().getFileContents(fileName);
	}
	
	private GlCodeMaster getGlCodeMaster(){
		GlCodeMaster glCodeMaster=new GlCodeMaster();
		
		glCodeMaster.setId("12");
		glCodeMaster.setService("string");
		glCodeMaster.setTaxHead("string");
		glCodeMaster.setTenantId("ap.kurnool");
		glCodeMaster.setGlCode("string");
		glCodeMaster.setFromDate(0L);
		glCodeMaster.setToDate(0L);
		
		return glCodeMaster;
	}*/
}