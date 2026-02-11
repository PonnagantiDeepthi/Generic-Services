package com.dtt.service.iface;

import java.text.ParseException;
import java.util.Date;

public interface LogModelServiceIface {
	
	public void setLogModel(Boolean response, String Identifier, String geoLocation, String serviceName,
			String correlationID, String totalTime, Date startTime, Date endTime, String otpStatus, String message)
			throws ParseException;

	public void setLogModelDTO(Boolean response, String Identifier, String geoLocation, String serviceName,
			String correlationID, String totalTime, Date startTime, Date endTime, String otpStatus)
			throws ParseException;

	public void setLogModelFCMToken(Boolean response, String Identifier, String geoLocation, String serviceName,
			String correlationID, String message, Date startTime, Date endTime, String otpStatus) throws ParseException;

}
