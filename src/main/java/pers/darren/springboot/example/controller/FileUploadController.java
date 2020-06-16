package pers.darren.springboot.example.controller;

import java.io.File;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

	@PostMapping("/uploadMultiFile")
	public String uploadMultiFile(final MultipartFile[] uploadFileArr, final MultipartHttpServletRequest request) {
		if (ArrayUtils.isNotEmpty(uploadFileArr)) {
			for (final MultipartFile multipartFile : uploadFileArr) {
				try {
					final String fileName = multipartFile.getOriginalFilename();
					log.info("multipartFile.getOriginalFilename()>>>" + fileName);
					final byte[] content = multipartFile.getBytes();
					FileUtils.writeByteArrayToFile(new File("/home/darren/Workspaces/array", fileName), content);
				} catch (final Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		final List<MultipartFile> uploadFileList = request.getFiles("uploadFileArr");
		if (CollectionUtils.isNotEmpty(uploadFileList)) {
			for (final MultipartFile multipartFile : uploadFileList) {
				try {
					final String fileName = multipartFile.getOriginalFilename();
					log.info("multipartFile.getOriginalFilename()>>>" + fileName);
					final byte[] content = multipartFile.getBytes();
					FileUtils.writeByteArrayToFile(new File("/home/darren/Workspaces/list", fileName), content);
				} catch (final Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return "SUCCESS";
	}
}