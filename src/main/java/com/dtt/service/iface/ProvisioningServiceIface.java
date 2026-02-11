package com.dtt.service.iface;

import com.dtt.enums.IdentifierType;
import com.dtt.enums.ResponseType;
import com.dtt.requestDTO.ApiResponse;

public interface ProvisioningServiceIface {

   public ApiResponse getProfileDetailsForProvisioning(IdentifierType identifierType, ResponseType responseType,String identifierValue);

   public ApiResponse getUserProfileDetailsBySuid(String suid);

   public ApiResponse getUAEIDAuthenticatinProfileBySuid(String suid);
}
