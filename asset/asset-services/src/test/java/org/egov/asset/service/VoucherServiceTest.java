package org.egov.asset.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.egov.asset.config.ApplicationProperties;
import org.egov.asset.contract.RevaluationRequest;
import org.egov.asset.contract.RevaluationResponse;
import org.egov.asset.contract.VoucherRequest;
import org.egov.asset.model.AccountDetailKey;
import org.egov.asset.model.Asset;
import org.egov.asset.model.AuditDetails;
import org.egov.asset.model.Department;
import org.egov.asset.model.Function;
import org.egov.asset.model.Fund;
import org.egov.asset.model.Revaluation;
import org.egov.asset.model.Voucher;
import org.egov.asset.model.VouchercreateAccountCodeDetails;
import org.egov.asset.model.enums.RevaluationStatus;
import org.egov.asset.model.enums.TypeOfChangeEnum;
import org.egov.asset.model.enums.VoucherType;
import org.egov.asset.util.FileUtils;
import org.egov.common.contract.request.RequestInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class VoucherServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private ObjectMapper objectMapper;

	@Mock
	private ApplicationProperties applicationProperties;

	@Mock
	private VouchercreateAccountCodeDetails vouchercreateAccountCodeDetails;

	@InjectMocks
	private VoucherService voucherService;

	@Test
	public void test_shuould_create_VoucherRequest_For_Reevalaution() {

		RevaluationRequest revaluationRequest = getRevaluationRequest();
		revaluationRequest.getRevaluation().setFund(Long.valueOf("3"));

		Asset asset = get_Asset();
		List<VouchercreateAccountCodeDetails> accountCodeDetails = getVouchercreateAccountCodeDetails();

		when(applicationProperties.getReevaluationVoucherName()).thenReturn("Asset Reevaluation Voucher");
		when(applicationProperties.getReevaluationVoucherDescription())
				.thenReturn("Creating Voucher for Asset Reevaluation");

		VoucherRequest generatedVoucherRequest = voucherService.createVoucherRequestForReevalaution(revaluationRequest,
				asset, accountCodeDetails);

		Fund fund = get_Fund(revaluationRequest);
		Voucher voucher = getVoucher(asset, fund);
		List<Voucher> vouchers = new ArrayList<>();
		vouchers.add(voucher);

		VoucherRequest expectedVoucherRequest = new VoucherRequest();
		expectedVoucherRequest.setRequestInfo(new RequestInfo());
		expectedVoucherRequest.setVouchers(vouchers);

		System.out.println(generatedVoucherRequest + "\n");
		System.err.println(expectedVoucherRequest);

		assertEquals(expectedVoucherRequest.getVouchers(), generatedVoucherRequest.getVouchers());

	}

	private Voucher getVoucher(Asset asset, Fund fund) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Voucher voucher = new Voucher();
		voucher.setType(VoucherType.JOURNALVOUCHER.toString());
		voucher.setVoucherDate(sdf.format(new Date()));
		voucher.setLedgers(getVouchercreateAccountCodeDetails());
		voucher.setDepartment(asset.getDepartment().getId());
		voucher.setName(applicationProperties.getReevaluationVoucherName());
		voucher.setDescription(applicationProperties.getReevaluationVoucherDescription());
		voucher.setFund(fund);

		return voucher;
	}

	private List<VouchercreateAccountCodeDetails> getVouchercreateAccountCodeDetails() {
		VouchercreateAccountCodeDetails vouchercreateAccountCodeDetails = new VouchercreateAccountCodeDetails();

		vouchercreateAccountCodeDetails.setCreditAmount(Double.valueOf("0"));
		vouchercreateAccountCodeDetails.setDebitAmount(Double.valueOf("10"));
		vouchercreateAccountCodeDetails.setGlcode("generalLedger6Code");
		Function function = new Function();
		function.setId(Long.valueOf("124"));

		vouchercreateAccountCodeDetails.setFunction(function);

		List<VouchercreateAccountCodeDetails> accountCodeDetails = new ArrayList<>();
		accountCodeDetails.add(vouchercreateAccountCodeDetails);

		return accountCodeDetails;
	}

	private Fund get_Fund(RevaluationRequest revaluationRequest) {
		Fund fund = new Fund();
		fund.setId(revaluationRequest.getRevaluation().getFund());
		return fund;
	}

	private Asset get_Asset() {
		Asset asset = new Asset();
		asset.setId(Long.valueOf("12"));
		Department department = new Department();
		department.setId(Long.valueOf("2"));
		asset.setDepartment(department);
		return asset;
	}

	private RevaluationRequest getRevaluationRequest() {
		RevaluationRequest revaluationRequest = new RevaluationRequest();
		revaluationRequest.setRequestInfo(new RequestInfo());
		Revaluation revaluation = new Revaluation();
		revaluation.setTenantId("ap.kurnool");
		revaluation.setAssetId(Long.valueOf("12"));
		revaluation.setCurrentCapitalizedValue(Double.valueOf("100.58"));
		revaluation.setTypeOfChange(TypeOfChangeEnum.INCREASED);
		revaluation.setRevaluationAmount(Double.valueOf("10"));
		revaluation.setValueAfterRevaluation(Double.valueOf("90.68"));
		revaluation.setRevaluationDate(Long.valueOf("1496430744825"));
		revaluation.setReevaluatedBy("5");
		revaluation.setReasonForRevaluation("reasonForRevaluation");
		revaluation.setFixedAssetsWrittenOffAccount(Long.valueOf("1"));
		revaluation.setFunction(Long.valueOf("124"));
		revaluation.setFund(Long.valueOf("3"));
		revaluation.setScheme(Long.valueOf("4"));
		revaluation.setSubScheme(Long.valueOf("5"));
		revaluation.setComments("coments");
		revaluation.setStatus(RevaluationStatus.ACTIVE);

		AuditDetails auditDetails = new AuditDetails();
		auditDetails.setCreatedBy("5");
		auditDetails.setCreatedDate(Long.valueOf("1495978422356"));
		auditDetails.setLastModifiedBy("5");
		auditDetails.setLastModifiedDate(Long.valueOf("1495978422356"));
		revaluation.setAuditDetails(auditDetails);

		revaluationRequest.setRevaluation(revaluation);
		return revaluationRequest;

	}
	// private RevaluationResponse getRevaluation(String filePath) throws
	// IOException {
	// String empJson = new FileUtils().getFileContents(filePath);
	// return new ObjectMapper().readValue(empJson, RevaluationResponse.class);
	// }

}