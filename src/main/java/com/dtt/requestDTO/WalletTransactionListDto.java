package com.dtt.requestDTO;

import java.io.Serializable;
import java.util.List;

public class WalletTransactionListDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<WalletTransactionDto> walletTransactionDtos;

	public List<WalletTransactionDto> getWalletTransactionDtos() {
		return walletTransactionDtos;
	}

	public void setWalletTransactionDtos(List<WalletTransactionDto> walletTransactionDtos) {
		this.walletTransactionDtos = walletTransactionDtos;
	}

	@Override
	public String toString() {
		return "WalletTransactionListDto [walletTransactionDtos=" + walletTransactionDtos + "]";
	}
	

}

