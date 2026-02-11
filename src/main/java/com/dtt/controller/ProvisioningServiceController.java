package com.dtt.controller;


import com.dtt.enums.IdentifierType;
import com.dtt.enums.ResponseType;
import com.dtt.requestDTO.ApiResponse;
import com.dtt.service.iface.ProvisioningServiceIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvisioningServiceController {

    @Autowired
    ProvisioningServiceIface provisioningServiceIface;

    @GetMapping("/api/get/details/{identifierType}/{responseType}/{identifierValue}")
    public ApiResponse getProfileDetailsForProvisioning(

            @PathVariable IdentifierType identifierType,
            @PathVariable ResponseType responseType,
            @PathVariable String identifierValue
    ) {
        return provisioningServiceIface.getProfileDetailsForProvisioning(
                identifierType,
                responseType,
                identifierValue
        );
    }


    @GetMapping("/api/get/user/profile/by/suid/{suid}")
    public ApiResponse getUserProfileBySuid(@PathVariable String suid){
        return provisioningServiceIface.getUserProfileDetailsBySuid(suid);
    }

    @GetMapping("/api/get/uaeid/auth/profile/by/suid/{suid}")
    public ApiResponse getAuthProfileBySuid(@PathVariable String suid){
        return provisioningServiceIface.getUAEIDAuthenticatinProfileBySuid(suid);
    }
}
