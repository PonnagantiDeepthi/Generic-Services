package com.dtt.utils;

import com.dtt.requestDTO.ApiResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AppUtil {
	
	private static UUID uuid;
	
	public static String getUUId() {
		uuid = UUID.randomUUID();
		return uuid.toString();
	}

    //to getcurrent date
    public static Date getCurrentDate() {
        return new Date();
    }

    public static ApiResponse createApiResponse(boolean success, String msg, Object object) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(msg);
        apiResponse.setResult(object);
        apiResponse.setSuccess(success);
        return apiResponse;

    }
    
    public static String getBase64FromByteArr(byte[] bytes){
        Base64.Encoder base64 = Base64.getEncoder();
        return base64.encodeToString(bytes);
    }

	public static String getDate(){
    	SimpleDateFormat smpdate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = new Date();
		return smpdate.format(date);
    }
	
	public static String getTimeStampString(Date date) throws ParseException {
        Optional<Date> oDate = Optional.ofNullable(date);
        Date d = oDate.isPresent() ? oDate.get() : new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return f.format(d);
    }
	

}

