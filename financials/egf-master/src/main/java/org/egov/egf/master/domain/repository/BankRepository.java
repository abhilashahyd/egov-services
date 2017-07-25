package org.egov.egf.master.domain.repository;

import java.util.HashMap;
import java.util.Map;

import org.egov.common.domain.model.Pagination;
import org.egov.egf.master.domain.model.Bank;
import org.egov.egf.master.domain.model.BankSearch;
import org.egov.egf.master.persistence.entity.BankEntity;
import org.egov.egf.master.persistence.queue.MastersQueueRepository;
import org.egov.egf.master.persistence.repository.BankJdbcRepository;
import org.egov.egf.master.web.requests.BankRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankRepository {

	@Autowired
	private BankJdbcRepository bankJdbcRepository;
	@Autowired
	private MastersQueueRepository bankQueueRepository;

	public Bank findById(Bank bank) {
		BankEntity entity = bankJdbcRepository.findById(new BankEntity().toEntity(bank));
		return entity.toDomain();

	}

	@Transactional
	public Bank save(Bank bank) {
		BankEntity entity = bankJdbcRepository.create(new BankEntity().toEntity(bank));
		return entity.toDomain();
	}

	@Transactional
	public Bank update(Bank bank) {
		BankEntity entity = bankJdbcRepository.update(new BankEntity().toEntity(bank));
		return entity.toDomain();
	}

	public void add(BankRequest request) {
		Map<String, Object> message = new HashMap<>();

		if (request.getRequestInfo().getAction().equalsIgnoreCase("create")) {
			message.put("bank_create", request);
		} else {
			message.put("bank_update", request);
		}
		bankQueueRepository.add(message);
	}

	public Pagination<Bank> search(BankSearch domain) {

		return bankJdbcRepository.search(domain);

	}

}