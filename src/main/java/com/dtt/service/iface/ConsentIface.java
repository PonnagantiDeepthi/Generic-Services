package com.dtt.service.iface;

import org.springframework.web.multipart.MultipartFile;

import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.ConsentDTO;

public interface ConsentIface {
	
	public ApiResponse addFiles(ConsentDTO consentDTO, MultipartFile termsAndConditions, MultipartFile dataPrivacy);


}
