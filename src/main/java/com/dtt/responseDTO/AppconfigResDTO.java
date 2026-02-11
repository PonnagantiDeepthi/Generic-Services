package com.dtt.responseDTO;

public class AppconfigResDTO {
	private String latestVersion;

	private boolean recommendUpgrade;

	private boolean forceUpgrade;

	private String updateLink;

	public String getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}

	public boolean isRecommendUpgrade() {
		return recommendUpgrade;
	}

	public void setRecommendUpgrade(boolean recommendUpgrade) {
		this.recommendUpgrade = recommendUpgrade;
	}

	public boolean isForceUpgrade() {
		return forceUpgrade;
	}

	public void setForceUpgrade(boolean forceUpgrade) {
		this.forceUpgrade = forceUpgrade;
	}

	public String getUpdateLink() {
		return updateLink;
	}

	public void setUpdateLink(String updateLink) {
		this.updateLink = updateLink;
	}

	public AppconfigResDTO(String latestVersion, boolean recommendUpgrade, boolean forceUpgrade, String updateLink) {

		this.latestVersion = latestVersion;
		this.recommendUpgrade = recommendUpgrade;
		this.forceUpgrade = forceUpgrade;
		this.updateLink = updateLink;
	}

	@Override
	public String toString() {
		return "AppconfigResDTO [latestVersion=" + latestVersion + ", recommendUpgrade=" + recommendUpgrade
				+ ", forceUpgrade=" + forceUpgrade + ", updateLink=" + updateLink + "]";
	}

}
