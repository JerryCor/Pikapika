package com.pikapika.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pikapika.app.dto.FileDto;
import com.pikapika.app.dto.ListImageDto;
import com.pikapika.app.service.FileService;
import com.pikapika.app.service.StringRedisService;
import com.pikapika.app.ueditor.ActionEnter;
import com.pikapika.app.util.CodeUtil;
import com.pikapika.app.util.FileUtil;
import com.pikapika.app.util.PikapikaConstants;

@Controller
@RequestMapping("/pikapika")
public class UeditorController {

	private static Logger logger = LoggerFactory.getLogger(UeditorController.class);

	@Autowired
	private FileService fileService;
	
/*	@Autowired
	private RedisService redisService;*/
	
	@Autowired
	private StringRedisService stringRedisService;

	/**
	 * 请求ueditor配置
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "ueditor", method = RequestMethod.GET)
	public void config(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "action", required = false) String action) {
		// response.setContentType("application/json");
		logger.info("正在配置ueditor......");
		response.setHeader("Content-Type", "text/html");
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		try {
			String exec = new ActionEnter(request, rootPath, "zxj123").exec();
			if (action != null && !action.isEmpty() && "listimage".equals(action)) {
				rootPath = rootPath.replace("\\", "/");
				exec = exec.replaceAll(rootPath, "/");
			}
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("配置ueditor完成......");
	}

	/**
	 * 获取最近上传图片
	 * 
	 * @param request
	 *            请求对象
	 * @param start
	 *            页码
	 * @param size
	 *            显示条数
	 * @return json字符串
	 */
	@RequestMapping(value = "listimage", method = RequestMethod.GET)
	@ResponseBody
	public String listimage(HttpServletRequest request, @RequestParam("start") String start,
			@RequestParam("size") String size) {
		logger.info("开始获取用户图片列表......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = new ConcurrentHashMap<>();
		List<ListImageDto> urlsList = null;
		if (CodeUtil.isNub(start) && CodeUtil.isNub(size)) {
			try {
				PageHelper.startPage(Integer.valueOf(start), Integer.valueOf(size));
				urlsList = fileService.searchFileUrls(PikapikaConstants.PIKAPIKA_IMG, "zxj123");
				PageInfo<ListImageDto> dtos = new PageInfo<>(urlsList);
				result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
				result.put("list", dtos.getList());
				result.put("start", Integer.valueOf(size));
				result.put("total", dtos.getTotal());
				jsonResult = mapper.writeValueAsString(result);
				logger.info("获取用户图片列表成功......");
				return jsonResult;
			} catch (Exception e) {
				logger.info("获取用户图片列表失败......");
				logger.info(e.getMessage());
			}
		}
		return jsonResult;
	}

	/**
	 * 上传图片
	 * 
	 * @param action
	 *            请求方法
	 * @param uploadImg
	 *            图片对象
	 * @param request
	 *            请求对象
	 * @return 上传结果
	 */
	@RequestMapping(value = "uploadimage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestParam("upfile") MultipartFile uploadImg, HttpServletRequest request) {
		logger.info("开始添加图片......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		if (uploadImg != null) {
			try {
				String fileKey = FileUtil.makeMD5(uploadImg.getInputStream());
				logger.info("生成图片的MD5值： "+fileKey);
				logger.info("向redis服务器查询是否存在相同MD5值的文件......");
				String fileValue = stringRedisService.get(fileKey);
				if(fileValue!= null && !fileValue.isEmpty()){
					logger.info("检查到存在MD5值图片，直接替换已存在的图片......");
					return fileValue;
				}
				if (uploadImg.getContentType() != null) {
					logger.info("未检查到存在MD5值图片，开始向服务器上传......");
					FileDto dto = FileUtil.makeFileEntity(request, uploadImg, null, PikapikaConstants.PIKAPIKA_IMG);
					dto = fileService.uploadFile(dto);
					result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
					result.put("url", dto.getFilePath() + dto.getFileName());
					result.put("title", dto.getOriginalName());
					result.put("original", dto.getOriginalName());
					jsonResult = mapper.writeValueAsString(result);
					logger.info("添加图片成功......");
					logger.info("向redis服务器添加以图片MD5值为KEY的相关信息......");
					stringRedisService.set(fileKey, jsonResult);
					logger.info("向redis服务器添加图片信息成功......");
				}
			} catch (Exception e) {
				logger.error("添加图片失败......");
				logger.error(e.getMessage());
			}
		}
		return jsonResult;
	}

	/*
	 * @RequestMapping(value="uploadsnap", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String uploadImage(@RequestParam("upfile") String
	 * uploadImg, HttpServletRequest request) { logger.info("开始添加图片......");
	 * String jsonResult = ""; ObjectMapper mapper = new ObjectMapper();
	 * Map<String, String> result = new ConcurrentHashMap<>(); return
	 * jsonResult; }
	 */

	/**
	 * 上传涂鸦
	 * 
	 * @param data
	 *            涂鸦data
	 * @param request
	 *            请求对象
	 * @return 上传结果
	 */
	@RequestMapping(value = "uploadscrawl", method = RequestMethod.POST)
	@ResponseBody
	public String uploadscrawl(@RequestParam("upfile") String data, HttpServletRequest request) {
		logger.info("开始添加涂鸦......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		try {
			FileDto dto = FileUtil.makeFileEntity(request, null, data, PikapikaConstants.PIKAPIKA_IMG_SCRAWL);
			dto = fileService.uploadFile(dto);
			result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
			result.put("url", dto.getFilePath() + dto.getFileName());
			result.put("title", dto.getOriginalName());
			result.put("original", dto.getOriginalName());
			jsonResult = mapper.writeValueAsString(result);
			logger.info("添加涂鸦成功......");
		} catch (Exception e) {
			logger.error("添加涂鸦失败......");
			logger.error(e.getMessage());
		}
		return jsonResult;
	}

	/**
	 * 上传视频
	 * 
	 * @param data
	 *            视频data
	 * @param request
	 *            请求对象
	 * @return 上传结果
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadvideo", method = RequestMethod.POST)
	@ResponseBody
	public String uploadvideo(@RequestParam("upfile") MultipartFile data, HttpServletRequest request)
			throws IOException {
		logger.info("开始添加视频......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		if (data != null) {
			if (data.getOriginalFilename() != null) {
				try {
					FileDto dto = FileUtil.makeFileEntity(request, data, null, PikapikaConstants.PIKAPIKA_VIDEO);
					dto = fileService.uploadFile(dto);
					result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
					result.put("url", dto.getFilePath() + dto.getFileName());
					result.put("title", data.getOriginalFilename());
					result.put("original", data.getOriginalFilename());
					jsonResult = mapper.writeValueAsString(result);
					logger.info("添加视频成功......");
				} catch (Exception e) {
					logger.error("添加视频失败......");
					logger.error(e.getMessage());
				}
			}
		}
		return jsonResult;
	}

	/**
	 * 上传附件
	 * 
	 * @param data
	 *            附件data
	 * @param request
	 *            请求对象
	 * @return 上传结果
	 * @throws IOException
	 */
	@RequestMapping(value = "uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadfile(@RequestParam("upfile") MultipartFile data, HttpServletRequest request)
			throws IOException {
		logger.info("开始添加附件......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		if (data != null) {
			if (data.getOriginalFilename() != null) {
				try {
					FileDto dto = FileUtil.makeFileEntity(request, data, null, PikapikaConstants.PIKAPIKA_WORD);
					dto = fileService.uploadFile(dto);
					result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
					result.put("url", dto.getFilePath() + dto.getFileName());
					result.put("title", dto.getOriginalName());
					result.put("original", dto.getOriginalName());
					jsonResult = mapper.writeValueAsString(result);
					logger.info("添加附件成功......");
				} catch (Exception e) {
					logger.error("添加附件失败......");
					logger.error(e.getMessage());
				}
			}
		}
		return jsonResult;
	}

	/*
	 * @RequestMapping(value="deleteTemplate", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public String uploadfile(@RequestParam("templateId") String
	 * templateId, HttpServletRequest request) throws IOException {
	 * logger.info("开始添加附件......"); String jsonResult = ""; ObjectMapper mapper
	 * = new ObjectMapper(); Map<String, String> result = new
	 * ConcurrentHashMap<>(); return jsonResult; }
	 */
}
