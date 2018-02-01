package com.pikapika.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.pikapika.app.dto.FileDto;

import sun.misc.BASE64Decoder;

/**
 * 文件工具
 * 
 * @author jerryCor
 *
 */
public class FileUtil {

	/**
	 * 上传图片
	 * 
	 * @param contentType
	 *            图片类型
	 * @param fileDto
	 *            图片信息
	 * @param multipartFile
	 *            图片对象
	 * @param resourcePath
	 *            资源路径
	 * @return 上传成功的图片对象
	 * @throws IOException
	 */
	public static FileDto uploadImg(MultipartFile multipartFile, FileDto fileDto, String contentType, 
			String resourcePath) throws IOException {
		// 获得图片后缀名称
		String imageType = contentType.substring(contentType.indexOf("/") + 1);
		String imageName = UUID.randomUUID().toString().replaceAll("-", "") + "." + imageType;
		String imgpath = resourcePath + PikapikaConstants.PIKAPIKA_UPLOAD_IMG;
		File file = new File(imgpath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		String path = imgpath + "/" + imageName;
		multipartFile.transferTo(new File(path));
		fileDto.setFileName(imageName);
		fileDto.setFilePath(PikapikaConstants.PIKAPIKA_UPLOAD_IMG);
		return fileDto;
		/*
		 * InputStream is = multipartFile.getInputStream(); OutputStream out =
		 * new FileOutputStream(new File( path)); int read = 0; byte[] bytes =
		 * new byte[4096]; while ((read = is.read(bytes)) != -1) {
		 * out.write(bytes, 0, read); } out.flush(); out.close();
		 */
	}

	/**
	 * 上传涂鸦
	 * 
	 * @param fileDto
	 *            涂鸦信息
	 * @return 上传成功的涂鸦对象
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public static FileDto uploadScrawl(FileDto fileDto) throws IOException {
		// 获得涂鸦后缀名称
		String imageName = UUID.randomUUID().toString().replaceAll("-", "") + "." + PikapikaConstants.PIKAPIKA_IMG_PNG;
		String imgpath = fileDto.getResourcePath() + PikapikaConstants.PIKAPIKA_UPLOAD_SCRAWL;
		File file = new File(imgpath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		String path = imgpath + "/" + imageName;
		BASE64Decoder decorder = new BASE64Decoder();
		// base64解码
		byte[] b = decorder.decodeBuffer(fileDto.getBase64Code());
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		OutputStream out = new FileOutputStream(new File(path));
		out.write(b);
		out.flush();
		out.close();
		fileDto.setFileName(imageName);
		fileDto.setOriginalName(imageName);
		fileDto.setFilePath(PikapikaConstants.PIKAPIKA_UPLOAD_SCRAWL);
		return fileDto;
	}

	/**
	 * 上传视频
	 * 
	 * @param fileDto
	 *            视频信息
	 * @return 上传成功的视频对象
	 * @throws IOException
	 */
	public static FileDto uploadVideo(FileDto fileDto) throws IOException {
		// 获得视频后缀名称
		String videoType = fileDto.getOriginalName().substring(fileDto.getOriginalName().lastIndexOf(".") + 1);
		String videoName = UUID.randomUUID().toString().replaceAll("-", "") + "." + videoType;
		String videopath = fileDto.getResourcePath() + PikapikaConstants.PIKAPIKA_UPLOAD_VIDEO;
		File file = new File(videopath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		String path = videopath + "/" + videoName;
		fileDto.getFile().transferTo(new File(path));
		fileDto.setFileName(videoName);
		fileDto.setFilePath(PikapikaConstants.PIKAPIKA_UPLOAD_VIDEO);
		return fileDto;
	}

	/**
	 * 上传附件
	 * 
	 * @param fileDto
	 *            附件信息
	 * @return 上传成功的附件对象
	 * @throws IOException
	 */
	public static FileDto uploadWord(FileDto fileDto) throws IOException {
		// 获得附件后缀名称
		String fileType = fileDto.getOriginalName().substring(fileDto.getOriginalName().lastIndexOf(".") + 1);
		String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileType;
		String filepath = fileDto.getResourcePath() + PikapikaConstants.PIKAPIKA_UPLOAD_FILE;
		File file = new File(filepath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		String path = filepath + "/" + fileName;
		fileDto.getFile().transferTo(new File(path));
		fileDto.setFileName(fileName);
		fileDto.setFilePath(PikapikaConstants.PIKAPIKA_UPLOAD_FILE);
		return fileDto;
	}
	
	/**
	 * 上传收藏图片
	 * 
	 * @param fileDto
	 *            图片信息
	 * @return 上传成功的图片对象
	 * @throws IOException
	 */
	public static FileDto uploadColImg(FileDto fileDto) throws IOException {
		// 获得图片后缀名称
		String imageType = fileDto.getContentType().substring(fileDto.getContentType().indexOf("/") + 1);
		String imageName = UUID.randomUUID().toString().replaceAll("-", "") + "." + imageType;
		String imgpath = fileDto.getResourcePath() + PikapikaConstants.PIKAPIKA_UPLOAD_COLIMG;
		File file = new File(imgpath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		String path = imgpath + "/" + imageName;
		fileDto.getFile().transferTo(new File(path));
		fileDto.setFileName(imageName);
		fileDto.setFilePath(PikapikaConstants.PIKAPIKA_UPLOAD_COLIMG);
		return fileDto;
	}

	/**
	 * 生成FileDto
	 * 
	 * @param request
	 *            请求信息
	 * @param file
	 *            上传文件
	 * @param fileType
	 *            文件类型
	 * @return FileDto
	 * @throws MalformedURLException
	 */
	public static FileDto makeFileEntity(HttpServletRequest request, MultipartFile file, String base64Code,
			String fileType) throws MalformedURLException {
		FileDto dto = new FileDto();
		if (file != null) {
			dto.setContentType(file.getContentType());
			dto.setOriginalName(file.getOriginalFilename());
			dto.setFile(file);
		}
		dto.setFileType(fileType);
		dto.setResourcePath(request.getServletContext().getResource("/").getPath());
		if (base64Code != null) {
			dto.setBase64Code(base64Code);
		}
		// todo
		dto.setUaccountId("zxj123");
		return dto;
	}

	/**
	 * 生成md5值
	 * 
	 * @param inputStream
	 *            IO流
	 * @return md5值
	 * @throws Exception
	 */
	public static String makeMD5(InputStream inputStream) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = inputStream.read(buffer, 0, 1024)) != -1) {
			md.update(buffer, 0, length);
		}
		BigInteger bigInt = new BigInteger(1, md.digest());
		return bigInt.toString(16);
	}
}
