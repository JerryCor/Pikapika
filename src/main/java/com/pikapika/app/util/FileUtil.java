package com.pikapika.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.pikapika.app.dto.FileDto;

import sun.misc.BASE64Decoder;

/**
 * 文件工具
 * @author jerryCor
 *
 */
public class FileUtil {

	/**
	 * 上传图片
	 * @param contentType 图片类型
	 * @param userName 用户名称
	 * @param multipartFile 图片对象
	 * @param resourcePath 资源路径
	 * @return 上传成功图片名称
	 * @throws IOException
	 */
	public static FileDto uploadImg(MultipartFile multipartFile, FileDto fileDto, 
			String contentType, String userName, String resourcePath) throws IOException{
			// 获得图片后缀名称
			String imageType = contentType.substring(contentType.indexOf("/") + 1);
			String imageName = UUID.randomUUID().toString().replaceAll("-", "") + "." + imageType;
			String imgpath = resourcePath + PikapikaConstants.PIKAPIKA_UPLOAD_IMG + userName;
			File file = new File(imgpath);
			if(!file.exists() && !file.isDirectory()){
				file.mkdirs();
			}
			String path = imgpath + "/" + imageName;
			multipartFile.transferTo(new File(path));
			fileDto.setFileName(imageName);
			fileDto.setFilePath(PikapikaConstants.PIKAPIKA_UPLOAD_IMG + userName);
			return fileDto;
			/*InputStream is = multipartFile.getInputStream();
			OutputStream out = new FileOutputStream(new File(
					path));
			int read = 0;
			byte[] bytes = new byte[4096];
			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();*/
	}
	
	/**
	 * 上传涂鸦
	 * @param baseCode 涂鸦code
	 * @param request 请求对象
	 * @return 上传图片成功涂鸦名称
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public static String uploadScrawl(String baseCode, HttpServletRequest request) throws IOException{
			// 获得涂鸦后缀名称
			String imageName = UUID.randomUUID().toString().replaceAll("-", "") + "." + PikapikaConstants.PIKAPIKA_IMG_PNG;
			String imgpath = request.getSession().getServletContext()
					.getRealPath(PikapikaConstants.PIKAPIKA_UPLOAD_SCRAWL );
			File file = new File(imgpath);
			if(!file.exists() && !file.isDirectory()){
				file.mkdirs();
			}
			String path = imgpath + "/"+ imageName;
			BASE64Decoder decorder = new  BASE64Decoder();
			//base64解码
			byte[] b = decorder.decodeBuffer(baseCode);
			for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }
			OutputStream out = new FileOutputStream(new File(path));      
            out.write(b);  
            out.flush();  
            out.close();
			return imageName;
	}
	
	/**
	 * 上传视频
	 * @param originalName 视频原名称
	 * @param multipartFile 视频对象
	 * @param request 请求
	 * @return 上传成功视频名称
	 * @throws IOException
	 */
	public static String uploadVideo(String originalName, MultipartFile multipartFile,
			HttpServletRequest request) throws IOException{
			// 获得视频后缀名称
			String videoType = originalName.substring(originalName.lastIndexOf(".") + 1);
			String videoName = UUID.randomUUID().toString().replaceAll("-", "") + "." + videoType;
			String videopath = request.getSession().getServletContext()
					.getRealPath(PikapikaConstants.PIKAPIKA_UPLOAD_VIDEO );
			File file = new File(videopath);
			if(!file.exists() && !file.isDirectory()){
				file.mkdirs();
			}
			String path = videopath + "/"+ videoName;
			multipartFile.transferTo(new File(path));
			return videoName;
	}
	
	/**
	 * 上传附件
	 * @param originalName 附件原名称
	 * @param multipartFile 附件对象
	 * @param request 请求
	 * @return 上传成功附件名称
	 * @throws IOException
	 */
	public static String uploadFile(String originalName, MultipartFile multipartFile,
			HttpServletRequest request) throws IOException{
			// 获得附件后缀名称
			String fileType = originalName.substring(originalName.lastIndexOf(".") + 1);
			String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileType;
			String filepath = request.getSession().getServletContext()
					.getRealPath(PikapikaConstants.PIKAPIKA_UPLOAD_FILE );
			File file = new File(filepath);
			if(!file.exists() && !file.isDirectory()){
				file.mkdirs();
			}
			String path = filepath + "/"+ fileName;
			multipartFile.transferTo(new File(path));
			return fileName;
	}
	/**
	 * 生成FileDto
	 * @param request 请求信息
	 * @param file 上传文件
	 * @param fileType 文件类型
	 * @return FileDto
	 * @throws MalformedURLException
	 */
	public static FileDto makeFileEntity(HttpServletRequest request, MultipartFile file, String fileType) throws MalformedURLException{
		FileDto dto = new FileDto();
		dto.setContentType(file.getContentType());
		dto.setFileType(fileType);
		dto.setOriginalName(file.getOriginalFilename());
		dto.setResourcePath(request.getServletContext().getResource("/").getPath());
		dto.setFile(file);
		//todo
		dto.setUaccountId("zxj123");
		return dto;
	}
}
