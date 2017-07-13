package org.egov.egf.budget.web.mapper;

import org.egov.common.master.web.contract.BoundaryContract;
import org.egov.common.master.web.contract.DepartmentContract;
import org.egov.egf.budget.domain.model.Budget;
import org.egov.egf.budget.domain.model.BudgetDetail;
import org.egov.egf.budget.domain.model.BudgetDetailSearch;
import org.egov.egf.budget.domain.model.EgfStatus;
import org.egov.egf.budget.web.contract.BudgetContract;
import org.egov.egf.budget.web.contract.BudgetDetailContract;
import org.egov.egf.budget.web.contract.BudgetDetailSearchContract;
import org.egov.egf.master.web.contract.BudgetGroupContract;
import org.egov.egf.master.web.contract.EgfStatusContract;
import org.egov.egf.master.web.contract.FunctionContract;
import org.egov.egf.master.web.contract.FundContract;
import org.egov.egf.master.web.contract.SchemeContract;
import org.egov.egf.master.web.contract.SubSchemeContract;

public class BudgetDetailMapper {

	public BudgetDetail toDomain(BudgetDetailContract contract) {

		BudgetDetail budgetDetail = new BudgetDetail();

		budgetDetail.setId(contract.getId());
		budgetDetail.setBudgetGroup(contract.getBudgetGroup());
		budgetDetail.setBudget(
				Budget.builder().id(contract.getBudget() != null ? contract.getBudget().getId() : null).build());
		budgetDetail.setOriginalAmount(contract.getOriginalAmount());
		budgetDetail.setApprovedAmount(contract.getApprovedAmount());
		budgetDetail.setBudgetAvailable(contract.getBudgetAvailable());
		budgetDetail.setAnticipatoryAmount(contract.getAnticipatoryAmount());
		budgetDetail.setUsingDepartment(contract.getUsingDepartment());
		budgetDetail.setExecutingDepartment(contract.getUsingDepartment());
		budgetDetail.setFunction(contract.getFunction());
		budgetDetail.setScheme(contract.getScheme());
		budgetDetail.setFund(contract.getFund());
		budgetDetail.setSubScheme(contract.getSubScheme());
		budgetDetail.setFunctionary(contract.getFunctionary());
		budgetDetail.setBoundary(contract.getBoundary());
		budgetDetail.setMaterializedPath(contract.getMaterializedPath());
		budgetDetail.setDocumentNumber(contract.getDocumentNumber());
		budgetDetail.setUniqueNo(contract.getUniqueNo());
		budgetDetail.setPlanningPercent(contract.getPlanningPercent());
		budgetDetail.setStatus(
				EgfStatus.builder().id(contract.getStatus() != null ? contract.getStatus().getId() : null).build());
		budgetDetail.setCreatedBy(contract.getCreatedBy());
		budgetDetail.setCreatedDate(contract.getCreatedDate());
		budgetDetail.setLastModifiedBy(contract.getLastModifiedBy());
		budgetDetail.setLastModifiedDate(contract.getLastModifiedDate());
		budgetDetail.setTenantId(contract.getTenantId());

		return budgetDetail;
	}

	public BudgetDetailContract toContract(BudgetDetail budgetDetail) {

		BudgetDetailContract contract = new BudgetDetailContract();

		contract.setId(budgetDetail.getId());
		contract.setBudgetGroup(budgetDetail.getBudgetGroup() != null ? budgetDetail.getBudgetGroup() : null);
		if (budgetDetail.getBudget() != null) {
			BudgetMapper bMapper = new BudgetMapper();
			contract.setBudget(bMapper.toContract(budgetDetail.getBudget()));
		}
		contract.setOriginalAmount(budgetDetail.getOriginalAmount());
		contract.setApprovedAmount(budgetDetail.getApprovedAmount());
		contract.setBudgetAvailable(budgetDetail.getBudgetAvailable());
		contract.setAnticipatoryAmount(budgetDetail.getAnticipatoryAmount());
		contract.setUsingDepartment(budgetDetail.getUsingDepartment());
		contract.setExecutingDepartment(budgetDetail.getExecutingDepartment());
		contract.setFunction(budgetDetail.getFunction());
		contract.setScheme(budgetDetail.getScheme());
		contract.setFund(budgetDetail.getFund());
		contract.setSubScheme(budgetDetail.getSubScheme());
		contract.setFunctionary(budgetDetail.getFunctionary());
		contract.setBoundary(budgetDetail.getBoundary());
		contract.setMaterializedPath(budgetDetail.getMaterializedPath());
		contract.setDocumentNumber(budgetDetail.getDocumentNumber());
		contract.setUniqueNo(budgetDetail.getUniqueNo());
		contract.setPlanningPercent(budgetDetail.getPlanningPercent());
		contract.setStatus(EgfStatusContract.builder()
				.id(budgetDetail.getStatus() != null ? budgetDetail.getStatus().getId() : null).build());
		contract.setCreatedBy(budgetDetail.getCreatedBy());
		contract.setCreatedDate(budgetDetail.getCreatedDate());
		contract.setLastModifiedBy(budgetDetail.getLastModifiedBy());
		contract.setLastModifiedDate(budgetDetail.getLastModifiedDate());
		contract.setTenantId(budgetDetail.getTenantId());

		return contract;
	}

	public BudgetDetailSearch toSearchDomain(BudgetDetailSearchContract contract) {

		BudgetDetailSearch budgetDetailSearch = new BudgetDetailSearch();

		budgetDetailSearch.setId(contract.getId());
		budgetDetailSearch.setBudgetGroup(BudgetGroupContract.builder()
				.id(contract.getBudgetGroup() != null ? contract.getBudgetGroup().getId() : null).build());
		budgetDetailSearch.setBudget(
				Budget.builder().id(contract.getBudget() != null ? contract.getBudget().getId() : null).build());
		budgetDetailSearch.setOriginalAmount(contract.getOriginalAmount());
		budgetDetailSearch.setApprovedAmount(contract.getApprovedAmount());
		budgetDetailSearch.setBudgetAvailable(contract.getBudgetAvailable());
		budgetDetailSearch.setAnticipatoryAmount(contract.getAnticipatoryAmount());
		budgetDetailSearch.setUsingDepartment(DepartmentContract.builder()
				.id(contract.getUsingDepartment() != null ? contract.getUsingDepartment().getId() : null).build());
		budgetDetailSearch.setExecutingDepartment(DepartmentContract.builder()
				.id(contract.getExecutingDepartment() != null ? contract.getExecutingDepartment().getId() : null)
				.build());
		budgetDetailSearch.setFunction(FunctionContract.builder()
				.id(contract.getFunction() != null ? contract.getFunction().getId() : null).build());
		budgetDetailSearch.setScheme(SchemeContract.builder()
				.id(contract.getScheme() != null ? contract.getScheme().getId() : null).build());
		budgetDetailSearch.setFund(
				FundContract.builder().id(contract.getFund() != null ? contract.getFund().getId() : null).build());
		budgetDetailSearch.setSubScheme(SubSchemeContract.builder()
				.id(contract.getSubScheme() != null ? contract.getSubScheme().getId() : null).build());
		budgetDetailSearch.setFunctionary(FunctionContract.builder()
				.id(contract.getFunctionary() != null ? contract.getFunctionary().getId() : null).build());
		budgetDetailSearch.setBoundary(BoundaryContract.builder()
				.id(contract.getBoundary() != null ? contract.getBoundary().getId() : null).build());
		budgetDetailSearch.setMaterializedPath(contract.getMaterializedPath());
		budgetDetailSearch.setDocumentNumber(contract.getDocumentNumber());
		budgetDetailSearch.setUniqueNo(contract.getUniqueNo());
		budgetDetailSearch.setPlanningPercent(contract.getPlanningPercent());
		budgetDetailSearch.setStatus(
				EgfStatus.builder().id(contract.getStatus() != null ? contract.getStatus().getId() : null).build());
		budgetDetailSearch.setCreatedBy(contract.getCreatedBy());
		budgetDetailSearch.setCreatedDate(contract.getCreatedDate());
		budgetDetailSearch.setLastModifiedBy(contract.getLastModifiedBy());
		budgetDetailSearch.setLastModifiedDate(contract.getLastModifiedDate());
		budgetDetailSearch.setTenantId(contract.getTenantId());
		budgetDetailSearch.setPageSize(contract.getPageSize());
		budgetDetailSearch.setOffset(contract.getOffset());

		return budgetDetailSearch;
	}

	public BudgetDetailSearchContract toSearchContract(BudgetDetailSearch budgetDetailSearch) {

		BudgetDetailSearchContract contract = new BudgetDetailSearchContract();

		contract.setId(budgetDetailSearch.getId());
		contract.setBudgetGroup(
				budgetDetailSearch.getBudgetGroup() != null ? budgetDetailSearch.getBudgetGroup() : null);
		contract.setBudget(BudgetContract.builder()
				.id(budgetDetailSearch.getBudget() != null ? budgetDetailSearch.getBudget().getId() : null).build());
		contract.setOriginalAmount(budgetDetailSearch.getOriginalAmount());
		contract.setApprovedAmount(budgetDetailSearch.getApprovedAmount());
		contract.setBudgetAvailable(budgetDetailSearch.getBudgetAvailable());
		contract.setAnticipatoryAmount(budgetDetailSearch.getAnticipatoryAmount());
		contract.setUsingDepartment(
				budgetDetailSearch.getUsingDepartment() != null ? budgetDetailSearch.getUsingDepartment() : null);
		contract.setExecutingDepartment(budgetDetailSearch.getExecutingDepartment() != null
				? budgetDetailSearch.getExecutingDepartment() : null);
		contract.setFunction(budgetDetailSearch.getFunction() != null ? budgetDetailSearch.getFunction() : null);
		contract.setScheme(budgetDetailSearch.getScheme() != null ? budgetDetailSearch.getScheme() : null);
		contract.setFund(budgetDetailSearch.getFund() != null ? budgetDetailSearch.getFund() : null);
		contract.setSubScheme(budgetDetailSearch.getSubScheme() != null ? budgetDetailSearch.getSubScheme() : null);
		contract.setFunctionary(
				budgetDetailSearch.getFunctionary() != null ? budgetDetailSearch.getFunctionary() : null);
		contract.setBoundary(budgetDetailSearch.getBoundary() != null ? budgetDetailSearch.getBoundary() : null);
		contract.setMaterializedPath(budgetDetailSearch.getMaterializedPath());
		contract.setDocumentNumber(budgetDetailSearch.getDocumentNumber());
		contract.setUniqueNo(budgetDetailSearch.getUniqueNo());
		contract.setPlanningPercent(budgetDetailSearch.getPlanningPercent());
		contract.setStatus(EgfStatusContract.builder()
				.id(budgetDetailSearch.getStatus() != null ? budgetDetailSearch.getStatus().getId() : null).build());
		contract.setCreatedBy(budgetDetailSearch.getCreatedBy());
		contract.setCreatedDate(budgetDetailSearch.getCreatedDate());
		contract.setLastModifiedBy(budgetDetailSearch.getLastModifiedBy());
		contract.setLastModifiedDate(budgetDetailSearch.getLastModifiedDate());
		contract.setTenantId(budgetDetailSearch.getTenantId());
		contract.setPageSize(budgetDetailSearch.getPageSize());
		contract.setOffset(budgetDetailSearch.getOffset());

		return contract;
	}

}