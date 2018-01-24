package com.pikapika.app.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.pikapika.app.entity.FileEntity;

public class FileDto extends FileEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String contentType;
	
	private String resourcePath;
	
	private String base64Code;
	
	private MultipartFile file;
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getBase64Code() {
		return base64Code;
	}

	public void setBase64Code(String base64Code) {
		this.base64Code = base64Code;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
