package com.pikapika.app.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具
 * @author jerryCor
 *
 */
public class FileUtil {

	/**
	 * 上传图片
	 * @param contentType 图片类型
	 * @param multipartFile 图片对象
	 * @param request 请求
	 * @return 上传成功图片名称
	 * @throws IOException
	 */
	public static String uploadImg(String contentType, MultipartFile multipartFile,
			HttpServletRequest request) throws IOException{
			// 获得文件后缀名称
			String imageType = contentType.substring(contentType.indexOf("/") + 1);
			String imageName = UUID.randomUUID().toString().replaceAll("-", "") + "." + imageType;
			String imgpath = request.getSession().getServletContext()
					.getRealPath(PikapikaConstants.PIKAPIKA_UPLOAD_IMG );
			File file = new File(imgpath);
			if(!file.exists() && !file.isDirectory()){
				file.mkdirs();
			}
			String path = imgpath + "/"+ imageName;
			multipartFile.transferTo(new File(path));
			return imageName;
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
}
