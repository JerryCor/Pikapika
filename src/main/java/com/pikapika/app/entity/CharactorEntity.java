package com.pikapika.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.pikapika.app.util.LocalDateTimeUtil;

public class CharactorEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer charactorId;
	private String charactorName;
	private String birthday;
	private String gender;
	private String charactorDesc;
	private String displayImg;
	private String topImg;
	private MultipartFile displayPic;
	private MultipartFile topPic;
	private LocalDateTime createTime;
	
	
	public Integer getCharactorId() {
		return charactorId;
	}
	public void setCharactorId(Integer charactorId) {
		this.charactorId = charactorId;
	}
	public String getCharactorName() {
		return charactorName;
	}
	public void setCharactorName(String charactorName) {
		this.charactorName = charactorName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCharactorDesc() {
		return charactorDesc;
	}
	public void setCharactorDesc(String charactorDesc) {
		this.charactorDesc = charactorDesc;
	}
	public MultipartFile getDisplayPic() {
		return displayPic;
	}
	public void setDisplayPic(MultipartFile displayPic) {
		this.displayPic = displayPic;
	}
	public MultipartFile getTopPic() {
		return topPic;
	}
	public void setTopPic(MultipartFile topPic) {
		this.topPic = topPic;
	}
	public String getCreateTime() {
		return LocalDateTimeUtil.getDayByFormat(createTime, "yyyy-MM-dd HH:mm:ss");
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public String getDisplayImg() {
		return displayImg;
	}
	public void setDisplayImg(String displayImg) {
		this.displayImg = displayImg;
	}
	public String getTopImg() {
		return topImg;
	}
	public void setTopImg(String topImg) {
		this.topImg = topImg;
	}
	
	
}
