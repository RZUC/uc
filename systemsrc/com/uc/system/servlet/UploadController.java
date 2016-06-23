package com.uc.system.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.uc.system.model.PolicyInfo;

/**
 * @ClassName: UploadController
 * @Description: TODO(上传下载)
 * @author Administrator
 * @date 2016年6月22日 下午10:26:54
 */
@Controller
public class UploadController {

	@RequestMapping(value = "/upload")
	public @ResponseBody String upload(
			@RequestParam(value = "file", required = false) MultipartFile[] files,PolicyInfo info)
			throws Exception {
		System.out.println(info.getLocation());
		for (MultipartFile file : files) {
			System.out.println(file.getName());
			// String path = "d:\\";
			// String fileName = file.getOriginalFilename();
			// System.out.println(path);
			// File targetFile = new File("d:\\", fileName);
			// if (!targetFile.exists()) {
			// targetFile.mkdirs();
			// }
			// // 保存
			// try {
			// file.transferTo(targetFile);
			// targetFile.listFiles();
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			//
		}

		return "上传成功\t";
	}
}
