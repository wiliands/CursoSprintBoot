package br.com.springboot.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.springboot.data.vo.v1.UploadFileResponseVO;
import br.com.springboot.services.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "File Endpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {
	
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileStorageService service;
	
	@ApiOperation(value = "Upload File")
	@PostMapping("/uploadFile")
	public UploadFileResponseVO uploadFile(@RequestParam(name = "file") MultipartFile file) {
		return armazenarArquivo(file);
	}
	
	@ApiOperation(value = "Upload Multiple File")
	@PostMapping("/uploadMultipleFile")
	public List<UploadFileResponseVO> uploadMultipleFile(@RequestParam(name = "files") MultipartFile[] files) {
		return Arrays.asList(files)
					 .stream()
					 .map(file -> armazenarArquivo(file))
					 .collect(Collectors.toList());
	}
	
	@ApiOperation(value = "Download File by File Name")
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = service.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			log.info("Could not determine file type!");
		}
		
		if(contentType == null) {
			contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		}
		
		return ResponseEntity.ok()
							 .contentType(MediaType.parseMediaType(contentType))
							 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
							 .body(resource);
	}
	
	private UploadFileResponseVO armazenarArquivo(MultipartFile file) {
		String fileName = service.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder
								 .fromCurrentContextPath()
								 .path("/api/file/v1/downloadFile/")
								 .path(fileName)
								 .toUriString();
		
		return new UploadFileResponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
}
