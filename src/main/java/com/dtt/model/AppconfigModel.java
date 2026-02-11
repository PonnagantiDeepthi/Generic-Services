package com.dtt.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "app_config")
@NamedQuery(name = "AppconfigModel.findAll", query = "SELECT a FROM  AppconfigModel a")
public class AppconfigModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;

	@Column(name = "os_version")
	private String osVersion;

	@Column(name = "latest_version")
	private String latestVersion;

	@Column(name = "minimum_version")
	private String minimumVersion;

	@Column(name = "updateLink")
	private String updateLink;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}

	public String getMinimumVersion() {
		return minimumVersion;
	}

	public void setMinimumVersion(String minimumVersion) {
		this.minimumVersion = minimumVersion;
	}

	public String getUpdateLink() {
		return updateLink;
	}

	public void setUpdateLink(String updateLink) {
		this.updateLink = updateLink;
	}

	@Override
	public String toString() {
		return "AppconfigModel [Id=" + Id + ", osVersion=" + osVersion + ", latestVersion=" + latestVersion
				+ ", minimumVersion=" + minimumVersion + ", updateLink=" + updateLink + "]";
	}
}

