package pl.playerony.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pl.playerony.BlogXML.exception.XmlException;
import pl.playerony.exception.DatabaseException;
import pl.playerony.exception.InputException;
import pl.playerony.exception.RestException;
import pl.playerony.service.XmlService;

@Controller
@CrossOrigin
@RequestMapping("/xml")
public class XmlController {
	private XmlService xmlService;
	
	public XmlController(XmlService xmlService) {
		this.xmlService = xmlService;
	}
	
	@RequestMapping("/import")
	public @ResponseBody Map<String, Object> importXmlFile(@RequestParam("file") MultipartFile uploadfile, HttpServletRequest request) throws RestException, DatabaseException, XmlException, InputException {
		Map<String, Object> map = new HashMap<>();
		
		if(uploadfile.isEmpty())
			throw new RestException("uploaded file is empty");
		
		try {
			String originalFilename = uploadfile.getOriginalFilename();
			ServletContext context = request.getServletContext();
			String appPath = context.getRealPath("");
			String fullPath = appPath + uploadfile.getOriginalFilename();
			File destinationFile = new File(fullPath);
			uploadfile.transferTo(destinationFile);
			
			xmlService.importXml(fullPath);
			
			map.put("status", "success");
		} catch (IllegalStateException | IOException e) {
			throw new RestException("Some problems by transfering file destination folder", e);
		}
		
		return map;
	}
	
	@RequestMapping("/export")
	public @ResponseBody Map<String, Object> exportXmlFile(@RequestParam("file") MultipartFile exportfile, HttpServletRequest request) throws RestException, DatabaseException, XmlException, InputException {
		Map<String, Object> map = new HashMap<>();
		
		if(exportfile.isEmpty())
			throw new RestException("uploaded file is empty");
		
		try {
			String originalFilename = exportfile.getOriginalFilename();
			ServletContext context = request.getServletContext();
			String appPath = context.getRealPath("");
			String fullPath = appPath + exportfile.getOriginalFilename();
			File destinationFile = new File(fullPath);
			exportfile.transferTo(destinationFile);
			
			xmlService.exportXml(fullPath);
			
			map.put("status", "success");
		} catch (IllegalStateException | IOException e) {
			throw new RestException("Some problems by transfering file destination folder", e);
		}
		
		return map;
	}
	
}
