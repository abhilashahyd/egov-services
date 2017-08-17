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
 *         3) This license does not grant any rights to any Long of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.egf.voucher.web.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.egov.common.web.contract.AuditableContract;
import org.egov.egf.master.web.contract.FinancialStatusContract;
import org.egov.egf.master.web.contract.FunctionContract;
import org.egov.egf.master.web.contract.FunctionaryContract;
import org.egov.egf.master.web.contract.FundContract;
import org.egov.egf.master.web.contract.FundsourceContract;
import org.egov.egf.master.web.contract.SchemeContract;
import org.egov.egf.master.web.contract.SubSchemeContract;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonPropertyOrder({ "id", "type", "name", "description", "voucherNumber", "voucherDate", "originalVoucherNumber",
		"refVoucherNumber", "moduleName", "billNumber", "status", "fund", "function", "fundsource", "scheme",
		"subScheme", "functionary", "division", "department", "sourcePath", "budgetCheckRequired",
		"budgetAppropriationNo", "ledgers" })

public class VoucherContract extends AuditableContract {

	@Length(max = 256)
	private String id;

	@Length(max = 50)
	private String type;

	@Length(max = 50)
	private String name;

	@Length(max = 256)
	private String description;

	@Length(max = 50)
	private String voucherNumber;

	@NotNull
	private Date voucherDate;

	@Length(max = 50)
	private String originalVoucherNumber;

	@Length(max = 50)
	private String refVoucherNumber;

	@Length(max = 50)
	private String moduleName;

	@Length(max = 50)
	private String billNumber;

	private FinancialStatusContract status;

	private FundContract fund;

	private FunctionContract function;

	private FundsourceContract fundsource;

	private SchemeContract scheme;

	private SubSchemeContract subScheme;

	private FunctionaryContract functionary;

	private Boundary division;

	private Department department;

	@Length(max = 256)
	private String sourcePath;

	private Boolean budgetCheckRequired = true;

	@Length(max = 50)
	private String budgetAppropriationNo;

	private Set<LedgerContract> ledgers;

	public BigDecimal getTotalAmount() {
		BigDecimal amount = BigDecimal.ZERO;
		for (final LedgerContract detail : ledgers)
			amount = amount.add(detail.getDebitAmount());
		return amount;
	}

}