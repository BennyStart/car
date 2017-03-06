package com.ben.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/file")
public class FileController {

	/**
	 * 上传
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	public @ResponseBody String upload(HttpServletRequest request) throws Exception {

		String mappedPath = request.getSession().getServletContext().getRealPath("upload");
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					String path = mappedPath + "/" + file.getOriginalFilename();
					// 上传
					file.transferTo(new File(path));
				}
			}
		}
		return "true";
	}

	/**
	 * 下载
	 */
	@RequestMapping("/download")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session, @RequestParam String textname)
			throws IOException {
		byte[] body = null;
		ServletContext servletContext = session.getServletContext();
		/// files/abc.txt：所要下载文件的地址
		InputStream in = servletContext.getResourceAsStream("/upload/" + textname);
		body = new byte[in.available()];
		in.read(body);

		HttpHeaders headers = new HttpHeaders();
		// 响应头的名字和响应头的值
		headers.add("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(textname, "UTF-8"));

		HttpStatus statusCode = HttpStatus.OK;

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}

}
