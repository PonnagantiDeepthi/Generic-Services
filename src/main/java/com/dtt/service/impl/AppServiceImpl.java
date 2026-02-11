package com.dtt.service.impl;

import org.springframework.stereotype.Service;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import jakarta.persistence.PessimisticLockException;
import jakarta.persistence.QueryTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;

import com.dtt.model.ApiEndpoint;
import com.dtt.model.AppconfigModel;
import com.dtt.repo.ApiEndpointRepository;
import com.dtt.repo.AppConfigRepo;
import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.AppconfigReqDTO;
import com.dtt.responseDTO.AppconfigResDTO;
import com.dtt.service.iface.AppserviceInf;
import com.dtt.utils.AppUtil;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class AppServiceImpl implements AppserviceInf {

	@Autowired
	AppConfigRepo appconfigRepository;
	
	@Autowired
    ApiEndpointRepository apiEndpointRepository;

	// @Override
//	public ApiResponse appUpdateUrlOLD(AppconfigReqDTO appconfigDTO) {
//
//		try {
//
//			if (appconfigDTO.getOsVersion() != null || appconfigDTO.getAppVersion() != null) {
//				int appVersion = Integer.parseInt(appconfigDTO.getAppVersion().replaceAll("[.]", ""));
//
//				AppconfigModel appConfigModel = appconfigRepository.getAppConfig(appconfigDTO.getOsVersion());
//				
//				System.out.println("appConfigModel "+appConfigModel);
//				int minVersion = Integer.parseInt(appConfigModel.getMinimumVersion().replaceAll("[.]", ""));
//				int latestVersion = Integer.parseInt(appConfigModel.getLatestVersion().replaceAll("[.]", ""));
//
//				if (appVersion < minVersion) {
//					return new ApiResponse(true, "update Required", new AppconfigResDTO(
//							appConfigModel.getLatestVersion(), false, true, appConfigModel.getUpdateLink()));
//				} else if (appVersion < latestVersion && appVersion >= minVersion) {
//					return new ApiResponse(true, "", new AppconfigResDTO(appConfigModel.getLatestVersion(), true, false,
//							appConfigModel.getUpdateLink()));
//				} else {
//					return new ApiResponse(true, "",
//							new AppconfigResDTO(appConfigModel.getLatestVersion(), false, false, null));
//				}
//			}
//
//		} catch (Exception e) {
//			return new ApiResponse(false, e.getMessage(), null);
//		}
//		return null;
//	}

	@Override
	public ApiResponse appUpdateUrl(AppconfigReqDTO appconfigDTO) {
		try {

			if (appconfigDTO.getOsVersion() != null || appconfigDTO.getAppVersion() != null) {

				AppconfigModel appConfigModel = appconfigRepository.getAppConfig(appconfigDTO.getOsVersion());

				// Check update conditions
				if (compareVersions(appconfigDTO.getAppVersion(), appConfigModel.getMinimumVersion()) < 0) {
					return AppUtil.createApiResponse(true,
							"Update required: Your app version is below the minimum supported version.",
							new AppconfigResDTO(appConfigModel.getLatestVersion(), false, true,
									appConfigModel.getUpdateLink()));
				} else if (compareVersions(appconfigDTO.getAppVersion(), appConfigModel.getLatestVersion()) < 0) {
					return AppUtil.createApiResponse(true, "Update recommended: A newer version is available.",
							new AppconfigResDTO(appConfigModel.getLatestVersion(), true, false,
									appConfigModel.getUpdateLink()));
				} else {
					return AppUtil.createApiResponse(true, "No update required: Your app is up to date.",
							new AppconfigResDTO(appConfigModel.getLatestVersion(), false, false, null));
				}

			} else {
				System.out.println("appconfigDTO :: " + appconfigDTO);
				return AppUtil.createApiResponse(false, "Object can't be null or empty", null);
			}
		} catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
				| PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException ex) {
			ex.printStackTrace();
			return AppUtil.createApiResponse(false, "Something went wrong. Please try after sometime.", null);

		} catch (Exception e) {
			return AppUtil.createApiResponse(false, e.getMessage(), null);
		}
	}

	// Method to compare version strings using Stream API
	public static int compareVersions(String version1, String version2) {
		// Split the version strings into parts
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");

		// Find the maximum length of the two version arrays
		int maxLength = Math.max(v1.length, v2.length);

		// Use Stream API to compare each version segment
		return IntStream.range(0, maxLength).map(i -> Integer.compare(i < v1.length ? Integer.parseInt(v1[i]) : 0, // Parse
																													// or
																													// default
																													// to
																													// 0
				i < v2.length ? Integer.parseInt(v2[i]) : 0 // Parse or default to 0
		)).filter(result -> result != 0) // Filter out equal segments
				.findFirst() // Return the first non-zero comparison result
				.orElse(0); // Default to 0 if all segments are equal
	}

//	public static int compareVersions(String version1, String version2) {
//        String[] v1 = version1.split("\\.");
//        String[] v2 = version2.split("\\.");
//
//        int maxLength = Math.max(v1.length, v2.length);
//        for (int i = 0; i < maxLength; i++) {
//            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
//            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
//
//            if (num1 < num2) {
//                return -1; // version1 is less than version2
//            } else if (num1 > num2) {
//                return 1; // version1 is greater than version2
//            }
//        }
//        return 0; // version1 equals version2
//    }

	@Override
	public ApiResponse saveconfig(AppconfigModel dto) {
		try {
			if (dto != null) {
				if ((dto.getLatestVersion() == null) || (dto.getLatestVersion().equals(""))) {
					return AppUtil.createApiResponse(false, "Appversion cannot be empty", null);
				}
				if ((dto.getMinimumVersion() == null) || (dto.getMinimumVersion().equals(""))) {
					return AppUtil.createApiResponse(false, "MinVersion cannot be empty", null);
				}
				if ((dto.getOsVersion() == null) || (dto.getOsVersion().equals(""))) {
					return AppUtil.createApiResponse(false, "OsVersion cannot be empty", null);
				}
				AppconfigModel savedAppConfig = appconfigRepository.save(dto);
				if (savedAppConfig != null) {
					return AppUtil.createApiResponse(true, "Saved successfully", null);
				}
				return AppUtil.createApiResponse(false, "Saving Failed", null);
			}
			return AppUtil.createApiResponse(false, "cannot be empty", null);
		} catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
				| PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException ex) {
			ex.printStackTrace();
			return AppUtil.createApiResponse(false, "Something went wrong. Please try after sometime.", null);

		} catch (Exception e) {
			return AppUtil.createApiResponse(false, "Something went wrong. Please try after sometime.", null);
		}
	}

	@Override
	public ApiResponse getAllConfig() {
		try {
			List<AppconfigModel> model = appconfigRepository.findAll();
			if (model.size() != 0) {
				return AppUtil.createApiResponse(true, "App Config lists", model);
			}
			return AppUtil.createApiResponse(false, "No records found", null);
		} catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException
				| PessimisticLockException | QueryTimeoutException | SQLGrammarException | GenericJDBCException ex) {
			ex.printStackTrace();
			return AppUtil.createApiResponse(false, "Something went wrong. Please try after sometime.", null);

		} catch (Exception e) {
			return AppUtil.createApiResponse(false, "Something went wrong. Please try after sometime.", null);
		}
	}

	@Override
	public Map<String, String> getAllEndpointsAsMap() {
        List<ApiEndpoint> endpoints = apiEndpointRepository.findAll();
        Map<String, String> resultMap = new HashMap();

        for(ApiEndpoint endpoint : endpoints) {
            resultMap.put(endpoint.getKeyName(), endpoint.getValue());
        }

        return resultMap;
    }

}
