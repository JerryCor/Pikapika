package com.pikapika.app.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pikapika.app.dto.FileDto;
import com.pikapika.app.entity.FileEntity;
import com.pikapika.app.mapper.FileMapper;
import com.pikapika.app.service.FileService;
import com.pikapika.app.util.FileUtil;
import com.pikapika.app.util.PikapikaConstants;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;
	@Override
	public List<FileEntity> searchFiles() throws Exception {
		List<FileEntity> files = fileMapper.getFiles();
		if(files != null 
				&& !files.isEmpty()){
			return files;
		}
		return null;
	}

	@Override
	public List<FileEntity> searchFiles(String uaccountId) throws Exception {
		List<FileEntity> files = fileMapper.getFilesByUaccountId(uaccountId);
		if(files != null 
				&& !files.isEmpty()){
			return files;
		}
		return null;
	}

	@Override
	public List<FileEntity> searchFiles(String fileType, String uaccountId) throws Exception {
		List<FileEntity> files = fileMapper.getFilesByKey(fileType, uaccountId);
		if(files != null 
				&& !files.isEmpty()){
			return files;
		}
		return null;
	}

	@Override
	public FileDto uploadFile(FileDto file) throws Exception {
		if(PikapikaConstants.PIKAPIKA_IMG.equals(file.getFileType())){
			FileEntity entity = new FileEntity();
			file = FileUtil.uploadImg(file.getFile(), file, file.getContentType(), file.getUaccountId(), file.getResourcePath());
			BeanUtils.copyProperties(file, entity);
			fileMapper.insertFile(entity);
			return file;
		}else{
			return null;
		}
		
	}

	@Override
	public void deleteFile(String fileName, String uaccountId) throws Exception {
		fileMapper.deleteFile(fileName, uaccountId);
		
	}
	

}
