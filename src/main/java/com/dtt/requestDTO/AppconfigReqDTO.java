package com.dtt.requestDTO;

public class AppconfigReqDTO {

	private String osVersion;

	private String appVersion;

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	@Override
	public String toString() {
		return "AppconfigReqDTO [osVersion=" + osVersion + ", appVersion=" + appVersion + "]";
	}

}
