package com.dtt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dtt.model.AppconfigModel;
import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.AppconfigReqDTO;
import com.dtt.service.impl.AppServiceImpl;
import com.dtt.utils.AppUtil;

@RestController
public class AppconfigController {

	@Autowired
	private AppServiceImpl configservice;

	@GetMapping("/api/service/status")
	public String hello() {
		return "AppConfig Service is Up and Running ..";
	}

	@PostMapping("/api/check/update")
	public ApiResponse updateconfig(@RequestBody AppconfigReqDTO dto) {
		return configservice.appUpdateUrl(dto);
	}

	@PostMapping("/api/save")
	public ApiResponse addconfig(@RequestBody AppconfigModel dto) {
		return configservice.saveconfig(dto);
	}

	@GetMapping("/api/get/AppConfig")
	public ApiResponse getAllConfig() {
		return configservice.getAllConfig();
	}
	

    @GetMapping({"/api/endpoints/getall"})
    public ApiResponse getAllEndpoints() {
        Map<String, String> endpointsMap = configservice.getAllEndpointsAsMap();
        return AppUtil.createApiResponse(true, "Successfully received all endpoints", endpointsMap);
    }
}

