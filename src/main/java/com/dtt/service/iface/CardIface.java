package com.dtt.service.iface;

import com.dtt.requestDTO.ApiResponse;

public interface CardIface {

	ApiResponse getPidByIdDocNumber(String idDocNumber);

}
