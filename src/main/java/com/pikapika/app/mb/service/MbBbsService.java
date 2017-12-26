package com.pikapika.app.mb.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.stereotype.Service;

@Service
@Produces(MediaType.APPLICATION_JSON)
public interface MbBbsService {
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/bbs")
	String searchAllBbs(@FormParam("pageNum")String pageNum, @FormParam("pageSize") String pageSize) throws Exception;
	
	@POST
    @Consumes({ MediaType.MULTIPART_FORM_DATA})
	@Path("/bbs")
	public String savePost(List<Attachment> files);
	
	@POST
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/email")
	public String sendMail(@FormParam("toEmail")String toEmail) throws Exception;
}
