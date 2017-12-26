package com.pikapika.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.pikapika.app.util.LocalDateTimeUtil;

public class BbsEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer bbsId;
	private String bbsTitle;
	private String bbsContent;
	private String uaccountId;
	private LocalDateTime createTime;
	
	public Integer getBbsId() {
		return bbsId;
	}
	public void setBbsId(Integer bbsId) {
		this.bbsId = bbsId;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getUaccountId() {
		return uaccountId;
	}
	public void setUaccountId(String uaccountId) {
		this.uaccountId = uaccountId;
	}
	public String getCreateTime() {
		return LocalDateTimeUtil.getDayByFormat(createTime, "yyyy-MM-dd HH:mm:ss");
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	
	
}
