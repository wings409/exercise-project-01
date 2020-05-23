package com.company.controller;

import com.company.vo.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * @ClassName FileController
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/23 13:54
 * @Version V1.0
 **/
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

	private String folder = "D:\\JavaFile\\IdeaProjects\\study-project\\exercise-project-01\\exercise-security-demo\\src\\main\\java\\com\\company\\controller";

	@PostMapping
	public FileInfo upload(MultipartFile file) throws IOException {
		log.info(file.getName());
		log.info(file.getOriginalFilename());
		log.info(file.getSize() + "");

		File localFile = new File(folder, System.currentTimeMillis() + ".txt");
		file.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
		     OutputStream outputStream = response.getOutputStream();) {
			response.setContentType("application/x-download");
			//定义下载的文件名
			response.addHeader("Content-Disposition", "attachment;filename-test.text");
			//把文件的输入流copy到输出流，即把文件内容写到输出流。
			IOUtils.copy(inputStream,outputStream);
			outputStream.flush();
		}

	}
}
