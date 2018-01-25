package com.pikapika.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pikapika.app.dto.FileDto;
import com.pikapika.app.dto.ListImageDto;
import com.pikapika.app.entity.FileEntity;
@Service
public interface FileService {
	/**
	 * 查询所有文件
	 * @return 文件列表
	 * @throws Exception
	 */
	List<FileEntity> searchFiles() throws Exception;
	
	/**
	 * 搜寻对应文件通过用户Id
	 * @param uaccountId 用户Id
	 * @return 文件列表
	 * @throws Exception
	 */
	List<FileEntity> searchFiles(String uaccountId) throws Exception;
	
	/**
	 * 搜寻对应文件通过用户Id和文件类型
	 * @param fileType 文件类型
	 * @param uaccountId 用户Id
	 * @return 文件列表
	 * @throws Exception
	 */
	List<FileEntity> searchFiles(String fileType, String uaccountId) throws Exception;
	
	/**
	 * 搜寻对应收藏图片通过用户Id
	 * @param uaccountId 用户Id
	 * @return 图片列表
	 * @throws Exception
	 */
	List<FileEntity> searchPicCollection(String uaccountId) throws Exception;
	
	/**
	 * 搜寻文件URL通过用户Id和文件类型
	 * @param fileType 文件类型
	 * @param uaccountId 用户Id
	 * @return 文件URL列表
	 * @throws Exception
	 */
	List<ListImageDto> searchFileUrls(String fileType, String uaccountId) throws Exception;
	
	/**
	 * 上传文件
	 * @param file 文件对象
	 * @return file 文件信息
	 * @throws Exception
	 */
	FileDto uploadFile(FileDto file) throws Exception;
	
	/**
	 * 删除文件
	 * @param fileName 文件名
	 * @param uaccountId 用户Id
	 * @throws Exception
	 */
	void deleteFile(String fileName ,String uaccountId) throws Exception;
}
