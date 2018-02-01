package com.pikapika.app.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pikapika.app.dto.FileDto;
import com.pikapika.app.entity.FileEntity;
import com.pikapika.app.service.FileService;
import com.pikapika.app.util.CodeUtil;
import com.pikapika.app.util.FileUtil;
import com.pikapika.app.util.PikapikaConstants;
/**
 * 收藏图片管理
 * @author xjzhuc
 *
 */
@Controller
@RequestMapping("/pikapika")
public class PicCollectionController {
	
	private static Logger logger = LoggerFactory.getLogger(PicCollectionController.class);
	
	
	@Autowired
	private FileService fileService;
	
	
	/**
	 * 请求图片收藏页面
	 * @return 图片收藏页面
	 * @throws Exception
	 */
	@RequestMapping(value="picCollection", method = RequestMethod.GET)
	public ModelAndView picCollection() throws Exception{
		ModelAndView view = new ModelAndView("picCollection/pictoManage");
		PageHelper.startPage(PikapikaConstants.INT_NUB_1, PikapikaConstants.INT_NUB_8);
		List<FileEntity> picCollection = fileService.searchPicCollection("zxj123");
		PageInfo<FileEntity> pageInfo = new PageInfo<>(picCollection);
		view.addObject("picCollection", picCollection);
		view.addObject("pageInfo", pageInfo);
		return view;
	}
	
	/**
	 * 请求图片收藏页面(下一页)
	 * @return 图片收藏页面
	 * @throws Exception
	 */
	@RequestMapping(value="picCollection/{pageNub}", method = RequestMethod.GET)
	public ModelAndView pageNext(@PathVariable String pageNub) throws Exception{
		ModelAndView view = new ModelAndView("picCollection/pictoManage");
		if(pageNub!=null && CodeUtil.isNub(pageNub)){
			if(pageNub.length() > PikapikaConstants.INT_NUB_2){
				PageHelper.startPage((PikapikaConstants.INT_NUB_2 * PikapikaConstants.INT_NUB_10) 
						, PikapikaConstants.INT_NUB_8);
			}else{
				PageHelper.startPage(Integer.valueOf(pageNub), PikapikaConstants.INT_NUB_8);
			}
			List<FileEntity> picCollection = fileService.searchPicCollection("zxj123");
			PageInfo<FileEntity> pageInfo = new PageInfo<>(picCollection);
			view.addObject("picCollection", picCollection);
			view.addObject("pageInfo", pageInfo);
			return view;
		}
		return new ModelAndView("redirect:/pikapika/picCollection");
	}
	
	/**
	 * 上传收藏图片
	 * 
	 * @param action
	 *            请求方法
	 * @param uploadImg
	 *            图片对象
	 * @param request
	 *            请求对象
	 * @return 上传结果
	 */
	@RequestMapping(value = "picCollection/uploadimage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestParam("file") MultipartFile file ,HttpServletRequest request) {
		logger.info("开始添加图片......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Boolean> result = new ConcurrentHashMap<>();
		if(file!= null && file.getContentType() != null){
			try {
				logger.info("开始向服务器添加图片......");
				FileDto dto = FileUtil.makeFileEntity(request, file, null, PikapikaConstants.PIKAPIKA_IMG_COLLECTION);
				dto = fileService.uploadFile(dto);
				if(dto!= null){
					result.put("flag", PikapikaConstants.BOOLEAN_TRUE);
					jsonResult = mapper.writeValueAsString(result);
					return jsonResult;
				}
				result.put("flag", PikapikaConstants.BOOLEAN_FALSE);
				jsonResult = mapper.writeValueAsString(result);
			} catch (Exception e) {
				logger.info("向服务器添加图片失败......");
				logger.info(e.getMessage());
			}
		}
		/*
		 * 另一种接受上传文件的方式
		 * MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;
	        Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
	        for(MultipartFile file1 :files.values()){
	        	System.out.println(file1.getOriginalFilename());
	        }
        */
		return jsonResult;
	}
	
	/**
	 * 删除指定图片
	 * @return 图片收藏页面
	 * @throws Exception
	 */
	@RequestMapping(value="picCollection/delFactory/{delId}", method = RequestMethod.GET)
	public ModelAndView delImg(@PathVariable String delId) throws Exception{
		if(delId!= null){
			String[] ids = delId.split(",");
			fileService.deleteFile(ids, "zhuxj123");
		}
		/*if(pageNub!=null && CodeUtil.isNub(pageNub)){
			if(pageNub.length() > PikapikaConstants.INT_NUB_2){
				PageHelper.startPage((PikapikaConstants.INT_NUB_2 * PikapikaConstants.INT_NUB_10) 
						, PikapikaConstants.INT_NUB_8);
			}else{
				PageHelper.startPage(Integer.valueOf(pageNub), PikapikaConstants.INT_NUB_8);
			}
			List<FileEntity> picCollection = fileService.searchPicCollection("zxj123");
			PageInfo<FileEntity> pageInfo = new PageInfo<>(picCollection);
			view.addObject("picCollection", picCollection);
			view.addObject("pageInfo", pageInfo);
			return view;
		}*/
		return new ModelAndView("redirect:/pikapika/picCollection");
	}
}
