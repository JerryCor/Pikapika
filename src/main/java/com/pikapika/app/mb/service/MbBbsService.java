package com.pikapika.app.mb.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Service
@Produces(MediaType.APPLICATION_JSON)
public interface MbBbsService {
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/bbs")
	String searchAllBbs(@FormParam("pageNum")String pageNum, @FormParam("pageSize") String pageSize) throws Exception;
}
