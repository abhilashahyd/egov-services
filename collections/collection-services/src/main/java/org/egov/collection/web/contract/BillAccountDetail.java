package org.egov.collection.web.contract;


import java.math.BigDecimal;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class BillAccountDetail   {
  
	private String glcode;

	private Integer order;

	private String accountDescription;

	private Boolean isActualDemand;
    
	private String id;

	private String tenantId;

	private String billDetail;
	
	private BigDecimal creditAmount;

	private BigDecimal debitAmount;
	
	private Purpose purpose;
	  
}
