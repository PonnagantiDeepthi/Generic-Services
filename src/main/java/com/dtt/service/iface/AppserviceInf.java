package com.dtt.service.iface;

import java.util.Map;

import com.dtt.model.AppconfigModel;
import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.AppconfigReqDTO;

public interface AppserviceInf {
	
	ApiResponse appUpdateUrl(AppconfigReqDTO appconfigReqDTO);

	ApiResponse saveconfig(AppconfigModel paramAppconfigModel);

	ApiResponse getAllConfig();
	
	Map<String, String> getAllEndpointsAsMap();

}
