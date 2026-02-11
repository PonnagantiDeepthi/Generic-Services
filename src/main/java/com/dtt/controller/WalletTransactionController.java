package com.dtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.WalletTransactionDto;
import com.dtt.requestDTO.WalletTransactionListDto;
import com.dtt.service.iface.WalletTransactionIface;
import com.dtt.utils.AppUtil;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class WalletTransactionController {
	
	@Autowired
	WalletTransactionIface varifyCredentialsIface;
	
	@PostMapping("/api/save-wallet-transaction-log") 
	public ApiResponse addWalletTransaction(@RequestBody WalletTransactionDto walletTransactionDto) {
		return varifyCredentialsIface.addWalletTransaction(walletTransactionDto);
	}
	
	@PostMapping("/api/save-list-of-wallet-transaction-log") 
	public ApiResponse addListWalletTransaction(@RequestBody WalletTransactionListDto walletTransactionDto) {
		//System.out.println("WalletTransactionDto "+walletTransactionDto);
		return varifyCredentialsIface.addListWalletTransaction(walletTransactionDto);
	}
	
	@GetMapping("/wallet-service-status")
	public ApiResponse getServiceStatus() {
		//System.out.println("WalletTransactionDto "+walletTransactionDto);
		return AppUtil.createApiResponse(true, "Service is running", null);
	}

}

