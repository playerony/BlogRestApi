package pl.playerony.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pl.playerony.BlogXML.exception.XmlException;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(DatabaseException.class)
	public Map<String, Object> handleDatabaseException(DatabaseException e) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "Error[DatabaseException]: " + e.getMessage());
		e.printStackTrace();
		
		return map;
	}
	
	@ExceptionHandler(InputException.class)
	public Map<String, Object> handleInputException(InputException e) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "Error[InputException]: " + e.getMessage());
		e.printStackTrace();
		
		return map;
	}
	
	@ExceptionHandler(SecurityException.class)
	public Map<String, Object> handleSecurityException(SecurityException e) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "Error[SecurityException]: " + e.getMessage());
		e.printStackTrace();
		
		return map;
	}
	
	@ExceptionHandler(XmlException.class)
	public Map<String, Object> handleXmlException(XmlException e) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", "Error[XmlException]: " + e.getMessage());
		e.printStackTrace();
		
		return map;
	}
	
}
