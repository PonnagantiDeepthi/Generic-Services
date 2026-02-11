package com.dtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dtt.requestDTO.ApiResponse;
import com.dtt.service.iface.CardIface;
import com.dtt.utils.AppUtil;

@RestController
@CrossOrigin(origins = "*")
public class CardController {
	
	@Autowired
	CardIface cardIface;
	
	@GetMapping("/api/get/card/by/docNum/{idDocNumber}")
	public ApiResponse getPidByIdDocNumber(@PathVariable("idDocNumber") String idDocNumber) {
		try {
			return cardIface.getPidByIdDocNumber(idDocNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return AppUtil.createApiResponse(false, "Something went wrong", null);
		}
	}

}
