package com.dtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.ConsentDTO;
import com.dtt.service.iface.ConsentIface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class ConsentController {
	@Autowired
	private ConsentIface consentIface;

	@PostMapping("/api/add/consent/files")
	public ApiResponse addConsentFiles(@RequestParam(value = "model") String model,
									   @RequestParam(value = "termsAndConditions",required = false) MultipartFile termsAndConditions,
									   @RequestParam(value = "dataPrivacy",required = false) MultipartFile dataPrivacy) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ConsentDTO consentDTO = mapper.readValue(model, ConsentDTO.class);

		return consentIface.addFiles(consentDTO,termsAndConditions,dataPrivacy);
	}

}

