package com.dtt.service.impl;

import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtt.enums.ServiceNames;
import com.dtt.model.Subscriber;
import com.dtt.repo.SubscriberRepoIface;
import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.WalletTransactionDto;
import com.dtt.requestDTO.WalletTransactionListDto;
import com.dtt.service.iface.WalletTransactionIface;
import com.dtt.utils.AppUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.PessimisticLockException;
import jakarta.persistence.QueryTimeoutException;

@Service
public class WalletTransactionImpl implements WalletTransactionIface {

	private static Logger logger = LoggerFactory.getLogger(WalletTransactionImpl.class);

	/** The Constant CLASS. */
	final static String CLASS = "VarifyCredentialsImpl";

	@Autowired
	SubscriberRepoIface subscriberRepoIface;

	@Autowired
	LogModelServiceImpl logModelService;

	@Override
	public ApiResponse addWalletTransaction(
			WalletTransactionDto walletTransactionDto) {
		try {
			Date startTime = AppUtil.getCurrentDate();
			logger.info(CLASS + "request body " + walletTransactionDto);
			Subscriber subscriber = subscriberRepoIface
					.findbyDocumentNumber(walletTransactionDto.getPassportNumber());
			String typeMessage = walletTransactionDto.getType();// + " Data shared Susccessfully";

			if(subscriber != null) {
				Date EndTime = AppUtil.getCurrentDate();
				logModelService.setLogModelDTO(true, subscriber.getSubscriberUid(), null, ServiceNames.WALLET.toString(),
						AppUtil.getUUId(), typeMessage, startTime, EndTime, "false");
				
				return AppUtil.createApiResponse(true, "Log successfully", null);
			}else {
				
				return AppUtil.createApiResponse(false, "subscriber not found", null);
			}
			

//			logModelService.setLogModelDTO(true, tradeLicenseModel.getSubscriberUId(), null,
//					ServiceNames.TRADE_LICENSE.toString(), AppUtil.getUUId(), "Trade License Approved", startTime,
//					EndTime, "false");
		} catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
				| PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
			e.printStackTrace();
			logger.error(CLASS + "addWalletTransaction Exception Something went Wrong, Onboarding Failed "
					+ e.getMessage());
			return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
		} catch (Exception e) {
			e.printStackTrace();
			return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
		}
	}

	@Override
	public ApiResponse addListWalletTransaction(WalletTransactionListDto walletTransactionDto) {
		try {
			//System.out.println(" walletTransactionDto "+walletTransactionDto);
			logger.info(CLASS + "request body of addListWalletTransaction" + walletTransactionDto);
			Date startTime = AppUtil.getCurrentDate();
			// Convert list to JSON
	        ObjectMapper objectMapper = new ObjectMapper();
	        
	        String s = objectMapper.writeValueAsString(walletTransactionDto);
	       
	        JsonNode json = objectMapper.readTree(s);
	        
	     // Access the "walletTransactionDtos" array
            JsonNode jsonList = json.get("walletTransactionDtos");
	        
	        if(jsonList.isArray()) {
	        	for (JsonNode jsonNode : jsonList) {
	        		
	        		Subscriber subscriber = subscriberRepoIface
	    					.findbyDocumentNumber(jsonNode.get("passportNumber").asText());
	        		if(subscriber != null) {
	        			String typeMessage = jsonNode.get("type").asText();
		        		Date EndTime = AppUtil.getCurrentDate();
		    			logModelService.setLogModelDTO(true, subscriber.getSubscriberUid(), null, ServiceNames.WALLET.toString(),
		    					AppUtil.getUUId(), typeMessage, startTime, EndTime, "false");
	        		}
	        	}
	        }
	        return AppUtil.createApiResponse(true, "Log successfully", null);
			
		} catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
				| PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
			e.printStackTrace();
			logger.error(CLASS + "addWalletTransaction Exception Something went Wrong, Onboarding Failed "
					+ e.getMessage());
			return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
		} catch (Exception e) {
			e.printStackTrace();
			return AppUtil.createApiResponse(false, "Something went wrong please. try after sometime", null);
		}
	}

}

