package com.pikapika.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pikapika.app.entity.FileEntity;
public interface FileMapper {
	/**
	 * 查询所有文件
	 * @return 文件列表
	 */
	List<FileEntity> getFiles();
	/**
	 * 获取对应文件通过用户Id
	 * @param uaccountId 用户Id
	 * @return 文件列表
	 */
	List<FileEntity> getFilesByUaccountId(@Param("uaccountId") String uaccountId);
	
	/**
	 * 获取对应文件通过用户Id和文件类型
	 * @param fileType 文件类型
	 * @param uaccountId 用户Id
	 * @return 文件列表
	 */
	List<FileEntity> getFilesByKey(@Param("fileType") String fileType, @Param("uaccountId") String uaccountId);
	/**
	 * 添加文件
	 * @param file
	 */
	void insertFile(FileEntity file);
	/**
	 * 更新文件
	 * @param file
	 */
	/*void updateFile(FileEntity file);*/
	/**
	 * 删除文件通过文件名和用户Id
	 * @param fileName 文件名
	 * @param uaccountId 用户Id
	 */
	void deleteFile(@Param("fileName") String fileName, @Param("uaccountId") String uaccountId);
}
