package com.pikapika.app.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pikapika.app.dto.FileDto;
import com.pikapika.app.dto.ListImageDto;
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
		if (files != null && !files.isEmpty()) {
			return files;
		}
		return null;
	}

	@Override
	public List<FileEntity> searchFiles(String uaccountId) throws Exception {
		List<FileEntity> files = fileMapper.getFilesByUaccountId(uaccountId);
		if (files != null && !files.isEmpty()) {
			return files;
		}
		return null;
	}

	@Override
	public List<FileEntity> searchFiles(String fileType, String uaccountId) throws Exception {
		List<FileEntity> files = fileMapper.getFilesByKey(fileType, uaccountId);
		if (files != null && !files.isEmpty()) {
			return files;
		}
		return null;
	}

	@Override
	public FileDto uploadFile(FileDto file) throws Exception {
		FileEntity entity = new FileEntity();
		if (PikapikaConstants.PIKAPIKA_IMG.equals(file.getFileType())) {
			file = FileUtil.uploadImg(file.getFile(), file, file.getContentType(), file.getResourcePath());
			BeanUtils.copyProperties(file, entity);
			fileMapper.insertFile(entity);
			return file;
		} else if(PikapikaConstants.PIKAPIKA_IMG_SCRAWL.equals(file.getFileType())){
			file = FileUtil.uploadScrawl(file);
			BeanUtils.copyProperties(file, entity);
			fileMapper.insertFile(entity);
			return file;
		} else if(PikapikaConstants.PIKAPIKA_VIDEO.equals(file.getFileType())){
			file = FileUtil.uploadVideo(file);
			BeanUtils.copyProperties(file, entity);
			fileMapper.insertFile(entity);
			return file;
		} else if(PikapikaConstants.PIKAPIKA_WORD.equals(file.getFileType())){
			file = FileUtil.uploadWord(file);
			BeanUtils.copyProperties(file, entity);
			fileMapper.insertFile(entity);
			return file;
		} else {
			return null;
		}

	}

	@Override
	public void deleteFile(String fileName, String uaccountId) throws Exception {
		fileMapper.deleteFile(fileName, uaccountId);

	}

	@Override
	public List<ListImageDto> searchFileUrls(String fileType, String uaccountId) throws Exception {
		List<ListImageDto> fileUrls = fileMapper.getFileUrlByKey(fileType, uaccountId);
		if (fileUrls != null && !fileUrls.isEmpty()) {
			return fileUrls;
		}
		return null;
	}

	@Override
	public List<FileEntity> searchPicCollection(String uaccountId) throws Exception {
		List<FileEntity> picCollection = fileMapper.getPicCollection(uaccountId);
		if(picCollection != null && !picCollection.isEmpty()){
			return picCollection;
		}
		return null;
	}

}
