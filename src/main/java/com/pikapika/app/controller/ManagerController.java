package com.pikapika.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pikapika.app.entity.CharactorEntity;
import com.pikapika.app.entity.FileEntity;
import com.pikapika.app.service.CharactorService;
import com.pikapika.app.service.FileService;

@Controller
@RequestMapping("/pikapika")
public class ManagerController {
	
	@Autowired
	private CharactorService charactorService;
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 请求船员一览页面
	 * @return 船员一览页面
	 * @throws Exception
	 */
	@RequestMapping(value="charactors", method = RequestMethod.GET)
	public ModelAndView charactors() throws Exception{
		ModelAndView view = new ModelAndView("charactors/charactortoManage");
		List<CharactorEntity> entities = charactorService.searchAllCharactors();
		view.addObject("charactorsInfos", entities);
		view.addObject("charactorsInfo", new CharactorEntity());
		view.addObject("activeModel", "charactors");
		return view;
	}
	
	/**
	 * 请求船员详情页面
	 * @param charactorId
	 * @return 船员详情页面
	 * @throws Exception
	 */
	@RequestMapping(value="charactorsInfo/{charactorId}", method = RequestMethod.GET)
	public ModelAndView charactorsInfo(@PathVariable String charactorId) throws Exception{
		ModelAndView view = new ModelAndView("charactors/charactortoManage");
		CharactorEntity entity = charactorService.searchCharactorById(charactorId);
		if(entity!= null && entity.getCharactorId()!= null){
			view.addObject("charactorsInfo", entity);
			view.addObject("displayFlag", true);
			view.addObject("activeModel", "charactors");
		}else{
			List<CharactorEntity> entities = charactorService.searchAllCharactors();
			view.addObject("charactorsInfos", entities);
			view.addObject("charactorsInfo", new CharactorEntity());
			view.addObject("activeModel", "charactors");
		}
		return view;
	}
	
	/**
	 * 更新船员信息
	 * @param charactorEntity
	 * @param request
	 * @return 更新信息后页面
	 */
	@RequestMapping(value="charactorsInfo", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView charactorsInfo(CharactorEntity charactorEntity, 
			HttpServletRequest request){
		System.out.println(request.getServletContext().getContextPath());
		System.out.println(request.getSession().getServletContext()
				.getRealPath(""));
		ModelAndView view = new ModelAndView("charactors/charactorsInfoSaveSuccess");
		String pathRoot = request.getSession().getServletContext()
				.getRealPath("");
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String uuid2 = UUID.randomUUID().toString().replaceAll("-", "");
		String path = "";
		// 获得文件类型（可以判断如果不是图片，禁止上传）
		String contentType = charactorEntity.getDisplayPic().getContentType();
		String contentType2 = charactorEntity.getTopPic().getContentType();
		if (contentType.equals("image/jpg")||contentType.equals("image/png")||contentType.equals("image/bmp")||contentType.equals("image/jpeg")) {
        	// 获得文件后缀名称
  			String imageName = contentType
  					.substring(contentType.indexOf("/") + 1);
  			path = "/images/" + uuid + "." + imageName;
  			try {
  				charactorEntity.getDisplayPic().transferTo(new File(pathRoot + path));
  				charactorEntity.setDisplayImg(path);
  			} catch (IllegalStateException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			System.out.println(path);
		}
		if (contentType2.equals("image/jpg")||contentType2.equals("image/png")||contentType2.equals("image/bmp")||contentType2.equals("image/jpeg")) {
        	// 获得文件后缀名称
  			String imageName = contentType2
  					.substring(contentType2.indexOf("/") + 1);
  			path = "/images/" + uuid2 + "." + imageName;
  			try {
  				charactorEntity.getTopPic().transferTo(new File(pathRoot + path));
  				charactorEntity.setTopImg(path);
  			} catch (IllegalStateException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			System.out.println(path);
		}
		charactorService.updateForCharactor(charactorEntity);
		view.addObject("charactorsInfo", charactorEntity);
		view.addObject("activeModel", "charactors");
		view.addObject("displayFlag", false);
		return view;
	}
	
	
	
	/**
	 * 请求图片收藏页面
	 * @return 图片收藏页面
	 * @throws Exception
	 */
	@RequestMapping(value="picCollection", method = RequestMethod.GET)
	public ModelAndView picCollection() throws Exception{
		ModelAndView view = new ModelAndView("picCollection/pictoManage");
		List<FileEntity> picCollection = fileService.searchPicCollection("zxj123");
		view.addObject("picCollection", picCollection);
		return view;
	}
}
