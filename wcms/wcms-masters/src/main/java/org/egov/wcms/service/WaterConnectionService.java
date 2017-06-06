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
package org.egov.wcms.service;

import org.egov.wcms.model.Connection;
import org.egov.wcms.producers.WaterTransactionProducer;
import org.egov.wcms.repository.WaterConnectionRepository;
import org.egov.wcms.util.AckConsumerNoGenerator;
import org.egov.wcms.web.contract.WaterConnectionReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WaterConnectionService {
	
    public static final Logger logger = LoggerFactory.getLogger(WaterConnectionService.class);
    
    @Autowired
    private WaterTransactionProducer waterTransactionProducer;
    
    @Autowired
    private AckConsumerNoGenerator ackConsumerNoGenerator;
    
    @Autowired
    private WaterConnectionRepository waterConnectionRepository;
    
    public Connection createWaterConnection(final String topic, final String key, final WaterConnectionReq waterConnectionRequest){
        final ObjectMapper mapper = new ObjectMapper();
        String waterConnectionValue = null;
        try {
            logger.info("WaterConnectionService request::" + waterConnectionRequest);
            waterConnectionValue = mapper.writeValueAsString(waterConnectionRequest);
            logger.info("waterConnectionValue::" + waterConnectionValue);
        } catch (final JsonProcessingException e) {
        	logger.error("Exception while stringifying water coonection object", e);
        }
        try {
        	waterTransactionProducer.sendMessage(topic, key, waterConnectionValue);
        } catch (final Exception e) {
            logger.error("Producer failed to post request to kafka queue", e);
            waterConnectionRequest.getConnection().setAcknowledgementNumber("0000000000");
            return waterConnectionRequest.getConnection();
        }
        waterConnectionRequest.getConnection().setAcknowledgementNumber(ackConsumerNoGenerator.getAckNo());
        
        return waterConnectionRequest.getConnection();
    }
    
    public Connection create(WaterConnectionReq waterConnectionRequest){
    	logger.info("Service API entry for create New Connection");
    	try{
    	waterConnectionRequest = waterConnectionRepository.persistConnection(waterConnectionRequest);
    	}catch(Exception e){
    		logger.error("Persisting failed due to db exception", e);
    	}
    	return waterConnectionRequest.getConnection();
    }

}