package com.pikapika.app.entity;

import java.io.Serializable;

public class FileEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer fileId;
	private String fileName;
	private String originalName;
	private String filePath;
	private String fileType;
	private Integer isCollection = Integer.valueOf(0);
	private String uaccountId;
	private String createTime;
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}
	public String getUaccountId() {
		return uaccountId;
	}
	public void setUaccountId(String uaccountId) {
		this.uaccountId = uaccountId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
