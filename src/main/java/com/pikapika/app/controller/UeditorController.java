package com.pikapika.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.pikapika.app.util.FileUtil;
import com.pikapika.app.util.PikapikaConstants;



@Controller
@RequestMapping("/pikapika")
public class UeditorController {
	
	private static Logger logger =LoggerFactory.getLogger(UeditorController.class);
	
	
	/**
	 * 请求ueditor配置
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="ueditor", method = RequestMethod.GET)
	public void config(HttpServletRequest request, HttpServletResponse response) {  
        //response.setContentType("application/json");  
		logger.info("正在配置ueditor......");
        response.setHeader("Content-Type" , "text/html");
        String rootPath = request.getSession().getServletContext()  
                .getRealPath("/");  
        try {  
            String exec = new ActionEnter(request, rootPath).exec();  
            PrintWriter writer = response.getWriter();  
            writer.write(exec);  
            writer.flush();  
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        logger.info("配置ueditor完成......");
    }
	/**
	 * 上传图片
	 * @param action 请求方法
	 * @param uploadImg 图片对象
	 * @param request 请求对象
	 * @return 上传结果
	 */
	@RequestMapping(value="uploadimage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestParam("upfile") MultipartFile uploadImg, 
			HttpServletRequest request) {  
		logger.info("开始添加图片......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		if(uploadImg!= null){
			if(uploadImg.getContentType()!= null){
				try {
					String contentType = uploadImg.getContentType();
					String imageName = FileUtil.uploadImg(contentType, uploadImg, request);
					result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
					result.put("url", PikapikaConstants.PIKAPIKA_UPLOAD_IMG + imageName);
					result.put("title", imageName);
					result.put("original", uploadImg.getOriginalFilename());
					jsonResult = mapper.writeValueAsString(result);
					logger.info("添加图片成功......");
				} catch (IOException e) {
					logger.error("添加图片失败......");
					logger.error(e.getMessage());
				}
			}
		}
		return jsonResult;
    }
	
	@RequestMapping(value="uploadsnap", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestParam("upfile") String uploadImg, 
			HttpServletRequest request) {  
		logger.info("开始添加图片......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		return jsonResult;
    }
	
	/**
	 * 上传涂鸦
	 * @param data 涂鸦data
	 * @param request  请求对象
	 * @return 上传结果
	 * @throws IOException
	 */
	@RequestMapping(value="uploadscrawl", method = RequestMethod.POST)
	@ResponseBody
	public String uploadscrawl(@RequestParam("upfile") String data, 
			HttpServletRequest request) throws IOException {  
		logger.info("开始添加涂鸦......");
		String jsonResult = "";
		String imageName = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		try {
			imageName = FileUtil.uploadScrawl(data, request);
			result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
			result.put("url", PikapikaConstants.PIKAPIKA_UPLOAD_SCRAWL + imageName);
			result.put("title", imageName);
			result.put("original", imageName);
			jsonResult = mapper.writeValueAsString(result);
			logger.info("添加涂鸦成功......");
		} catch (IOException e) {
			logger.error("添加涂鸦失败......");
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 上传视频
	 * @param data 视频data
	 * @param request  请求对象
	 * @return 上传结果
	 * @throws IOException
	 */
	@RequestMapping(value="uploadvideo", method = RequestMethod.POST)
	@ResponseBody
	public String uploadvideo(@RequestParam("upfile") MultipartFile data, 
			HttpServletRequest request) throws IOException {  
		logger.info("开始添加视频......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		if(data!= null){
			if(data.getOriginalFilename()!= null){
				try {
					String videoName = FileUtil.uploadVideo(data.getOriginalFilename(), data, request);
					result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
					result.put("url", PikapikaConstants.PIKAPIKA_UPLOAD_VIDEO + videoName);
					result.put("title", data.getOriginalFilename());
					result.put("original", data.getOriginalFilename());
					jsonResult = mapper.writeValueAsString(result);
					logger.info("添加视频成功......");
				} catch (IOException e) {
					logger.error("添加视频失败......");
					logger.error(e.getMessage());
				}
			}
		}
		return jsonResult;
	}
	
	/**
	 * 上传附件
	 * @param data 附件data
	 * @param request  请求对象
	 * @return 上传结果
	 * @throws IOException
	 */
	@RequestMapping(value="uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadfile(@RequestParam("upfile") MultipartFile data, 
			HttpServletRequest request) throws IOException {  
		logger.info("开始添加附件......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		if(data!= null){
			if(data.getOriginalFilename()!= null){
				try {
					String fileName = FileUtil.uploadFile(data.getOriginalFilename(), data, request);
					result.put("state", PikapikaConstants.PIKAPIKA_SUCCESS_UPPER);
					result.put("url", PikapikaConstants.PIKAPIKA_UPLOAD_FILE + fileName);
					result.put("title", data.getOriginalFilename());
					result.put("original", data.getOriginalFilename());
					jsonResult = mapper.writeValueAsString(result);
					logger.info("添加附件成功......");
				} catch (IOException e) {
					logger.error("添加附件失败......");
					logger.error(e.getMessage());
				}
			}
		}
		return jsonResult;
	}
	
	@RequestMapping(value="deleteTemplate", method = RequestMethod.GET)
	@ResponseBody
	public String uploadfile(@RequestParam("templateId") String templateId, 
			HttpServletRequest request) throws IOException {  
		logger.info("开始添加附件......");
		String jsonResult = "";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> result = new ConcurrentHashMap<>();
		return jsonResult;
	}
	
}
