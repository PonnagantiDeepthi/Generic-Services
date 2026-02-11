package com.dtt.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dtt.enums.LogMessageType;
import com.dtt.enums.TransactionType;
import com.dtt.requestDTO.LogModelDTO;
import com.dtt.service.iface.LogModelServiceIface;
import com.dtt.utils.AppUtil;
import com.dtt.utils.KafkaSender;
import com.fasterxml.jackson.databind.ObjectMapper;

import ug.daes.DAESService;
import ug.daes.Result;

@Service
public class LogModelServiceImpl implements LogModelServiceIface {

	private static Logger logger = LoggerFactory.getLogger(LogModelServiceImpl.class);

	/** The Constant CLASS. */
	final static String CLASS = "LogModelServiceImpl";
	
	@Autowired
	KafkaSender mqSender;
	
	@Autowired
	MessageSource messageSource;

	@Override
	public void setLogModel(Boolean response, String Identifier, String geoLocation, String serviceName,
			String correlationID, String totalTime, Date startTime, Date endTime, String otpStatus,String message)
			throws ParseException {
		LogModelDTO logModel = new LogModelDTO();
		logModel.setIdentifier(Identifier);
		logModel.setCorrelationID(correlationID);
		logModel.setTransactionID(correlationID);
		logModel.setTimestamp(null);
		logModel.setStartTime(AppUtil.getTimeStampString(startTime));
		logModel.setEndTime(AppUtil.getTimeStampString(endTime));
		logModel.setServiceName(serviceName);
		logModel.setLogMessage(message);
		logModel.setTransactionType(TransactionType.OTHER.toString());
		logModel.setGeoLocation(geoLocation);
		logModel.seteSealUsed(false);
		logModel.setSignatureType(null);
		logModel.setCallStack(otpStatus);
		if (response) {
			logModel.setLogMessageType(LogMessageType.SUCCESS.toString());
		} else {
			logModel.setLogMessageType(LogMessageType.FAILURE.toString());
		}
		logModel.setChecksum(null);

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(logModel);
			Result checksumResult = DAESService.addChecksumToTransaction(json);
			String push = new String(checksumResult.getResponse());
			LogModelDTO log = objectMapper.readValue(push, LogModelDTO.class);
			mqSender.send(log);
			logger.info(CLASS + " setLogModel log {}",logModel );
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(CLASS + " setLogModel Exception  {]", e.getMessage() );
		}
	}
	
	@Override
	public void setLogModelDTO(Boolean response, String Identifier, String geoLocation, String serviceName,
			String correlationID, String message, Date startTime, Date endTime, String otpStatus)
			throws ParseException {
		LogModelDTO logModel = new LogModelDTO();
		logModel.setIdentifier(Identifier);
		logModel.setCorrelationID(null);
		logModel.setTransactionID(null);
		logModel.setTimestamp(null);
		logModel.setStartTime(AppUtil.getTimeStampString(startTime));
		logModel.setEndTime(AppUtil.getTimeStampString(endTime));
		logModel.setServiceName(serviceName);
		logModel.setLogMessage(message);
		logModel.setTransactionType(TransactionType.BUSINESS.toString());
		logModel.setGeoLocation(geoLocation);
		logModel.seteSealUsed(false);
		logModel.setSignatureType(null);
		logModel.setCallStack(otpStatus);
		if (response) {
			logModel.setLogMessageType(LogMessageType.SUCCESS.toString());
		} else {
			logModel.setLogMessageType(LogMessageType.FAILURE.toString());
		}
		logModel.setChecksum(null);

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(logModel);
			Result checksumResult = DAESService.addChecksumToTransaction(json);
			String push = new String(checksumResult.getResponse());
			LogModelDTO log = objectMapper.readValue(push, LogModelDTO.class);
			mqSender.send(log);
			logger.info(CLASS + " setLogModel log {}",logModel );
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(CLASS + " setLogModel Exception  {]", e.getMessage() );
		}
	}

//	@Override
//	public void setLogModelNiraApi(NiraApiLogDto niraApiLogDto) throws ParseException {
//		try {
//			LogModelDTO logModel = new LogModelDTO();
//			logModel.setIdentifier(niraApiLogDto.getSubscriberUniqueId());
//			logModel.setCorrelationID(AppUtil.getUUId());
//			logModel.setTransactionID(AppUtil.getUUId());
//			logModel.setTimestamp(null);
//			logModel.setStartTime(AppUtil.getTimeStamping());
//			logModel.setEndTime(AppUtil.getTimeStamping());
//			logModel.setServiceName(niraApiLogDto.getServiceName());
//			logModel.setLogMessage(niraApiLogDto.getLogMessage());
//			logModel.setLogMessageType(LogMessageType.FAILURE.toString());
//			logModel.setTransactionType(TransactionType.BUSINESS.toString());
//			logModel.setGeoLocation(null);
//			logModel.seteSealUsed(false);
//			logModel.setSignatureType(null);
//			logModel.setCallStack(null);
//			logModel.setChecksum(null);
//			logModel.setServiceProviderAppName(null);
//			logModel.setServiceProviderName(null);
//			logModel.setTransactionSubType(null);
//			logModel.setSubTransactionID(null);
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			String json = objectMapper.writeValueAsString(logModel);
//			Result checksumResult = DAESService.addChecksumToTransaction(json);
//			String push = new String(checksumResult.getResponse());
//			LogModelDTO log = objectMapper.readValue(push, LogModelDTO.class);
//			mqSender.send(log);
//			logger.info(CLASS + "setLogModelNiraApi log {}", log );
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(CLASS + " setLogModelNiraApi Exception {}",e.getMessage() );
//		}
//
//	}
	
	@Override
	public void setLogModelFCMToken(Boolean response, String Identifier, String geoLocation, String serviceName,
			String correlationID, String message, Date startTime, Date endTime, String otpStatus)
			throws ParseException {
		LogModelDTO logModel = new LogModelDTO();
		logModel.setIdentifier(Identifier);
		logModel.setCorrelationID(correlationID);
		logModel.setTransactionID(correlationID);
		logModel.setTimestamp(null);
		logModel.setStartTime(AppUtil.getTimeStampString(startTime));
		logModel.setEndTime(AppUtil.getTimeStampString(endTime));
		logModel.setServiceName(serviceName);
		logModel.setLogMessage(message);
		logModel.setTransactionType(TransactionType.BUSINESS.toString());
		logModel.setGeoLocation(geoLocation);
		logModel.seteSealUsed(false);
		logModel.setSignatureType(null);
		logModel.setCallStack(otpStatus);
		if (response) {
			logModel.setLogMessageType(LogMessageType.SUCCESS.toString());
		} else {
			logModel.setLogMessageType(LogMessageType.FAILURE.toString());
		}
		logModel.setChecksum(null);

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(logModel);
			Result checksumResult = DAESService.addChecksumToTransaction(json);
			String push = new String(checksumResult.getResponse());
			LogModelDTO log = objectMapper.readValue(push, LogModelDTO.class);
			mqSender.send(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
