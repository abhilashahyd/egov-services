package org.egov.egf.instrument.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.domain.model.Pagination;
import org.egov.egf.instrument.domain.model.Instrument;
import org.egov.egf.instrument.domain.model.InstrumentSearch;
import org.egov.egf.instrument.persistence.entity.InstrumentEntity;
import org.egov.egf.instrument.persistence.queue.repository.InstrumentQueueRepository;
import org.egov.egf.instrument.persistence.repository.InstrumentJdbcRepository;
import org.egov.egf.instrument.web.mapper.InstrumentMapper;
import org.egov.egf.instrument.web.requests.InstrumentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstrumentRepository {

	private InstrumentJdbcRepository instrumentJdbcRepository;

	private InstrumentQueueRepository instrumentQueueRepository;

	private String persistThroughKafka;

	@Autowired
	public InstrumentRepository(InstrumentJdbcRepository instrumentJdbcRepository,
			InstrumentQueueRepository instrumentQueueRepository,
			@Value("${persist.through.kafka}") String persistThroughKafka) {
		this.instrumentJdbcRepository = instrumentJdbcRepository;
		this.instrumentQueueRepository = instrumentQueueRepository;
		this.persistThroughKafka = persistThroughKafka;

	}

	public Instrument findById(Instrument instrument) {
		InstrumentEntity entity = instrumentJdbcRepository.findById(new InstrumentEntity().toEntity(instrument));
		if (entity != null)
			return entity.toDomain();

		return null;

	}

	@Transactional
	public List<Instrument> save(List<Instrument> instruments, RequestInfo requestInfo) {

		InstrumentMapper mapper = new InstrumentMapper();

		if (persistThroughKafka != null && !persistThroughKafka.isEmpty()
				&& persistThroughKafka.equalsIgnoreCase("yes")) {

			InstrumentRequest request = new InstrumentRequest();
			request.setRequestInfo(requestInfo);
			request.setInstruments(new ArrayList<>());

			for (Instrument iac : instruments) {

				request.getInstruments().add(mapper.toContract(iac));

			}

			instrumentQueueRepository.addToQue(request);

			return instruments;
		} else {

			List<Instrument> resultList = new ArrayList<Instrument>();

			for (Instrument iac : instruments) {

				resultList.add(save(iac));
			}

			InstrumentRequest request = new InstrumentRequest();
			request.setRequestInfo(requestInfo);
			request.setInstruments(new ArrayList<>());

			for (Instrument iac : resultList) {

				request.getInstruments().add(mapper.toContract(iac));

			}

			instrumentQueueRepository.addToSearchQue(request);

			return resultList;
		}

	}

	@Transactional
	public List<Instrument> update(List<Instrument> instruments, RequestInfo requestInfo) {

		InstrumentMapper mapper = new InstrumentMapper();

		if (persistThroughKafka != null && !persistThroughKafka.isEmpty()
				&& persistThroughKafka.equalsIgnoreCase("yes")) {

			InstrumentRequest request = new InstrumentRequest();
			request.setRequestInfo(requestInfo);
			request.setInstruments(new ArrayList<>());

			for (Instrument iac : instruments) {

				request.getInstruments().add(mapper.toContract(iac));

			}

			instrumentQueueRepository.addToQue(request);

			return instruments;
		} else {

			List<Instrument> resultList = new ArrayList<Instrument>();

			for (Instrument iac : instruments) {

				resultList.add(update(iac));
			}

			InstrumentRequest request = new InstrumentRequest();
			request.setRequestInfo(requestInfo);
			request.setInstruments(new ArrayList<>());

			for (Instrument iac : resultList) {

				request.getInstruments().add(mapper.toContract(iac));

			}

			instrumentQueueRepository.addToSearchQue(request);

			return resultList;
		}

	}

	@Transactional
	public Instrument save(Instrument instrument) {
		InstrumentEntity entity = instrumentJdbcRepository.create(new InstrumentEntity().toEntity(instrument));
		return entity.toDomain();
	}

	@Transactional
	public Instrument update(Instrument instrument) {
		InstrumentEntity entity = instrumentJdbcRepository.update(new InstrumentEntity().toEntity(instrument));
		return entity.toDomain();
	}

	public Pagination<Instrument> search(InstrumentSearch domain) {

		// if() {
		// InstrumentSearchContract instrumentSearchContract = new
		// InstrumentSearchContract();
		// ModelMapper mapper = new ModelMapper();
		// mapper.map(domain,instrumentSearchContract );
		// Pagination<Instrument> instruments =
		// instrumentESRepository.search(instrumentSearchContract);
		// return instruments;
		// }

		return instrumentJdbcRepository.search(domain);

	}

}