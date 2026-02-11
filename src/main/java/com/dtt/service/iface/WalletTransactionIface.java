package com.dtt.service.iface;

import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.WalletTransactionDto;
import com.dtt.requestDTO.WalletTransactionListDto;

public interface WalletTransactionIface {
	
ApiResponse addWalletTransaction(WalletTransactionDto walletTransactionDto);
	
	ApiResponse addListWalletTransaction(WalletTransactionListDto walletTransactionDto);

}
