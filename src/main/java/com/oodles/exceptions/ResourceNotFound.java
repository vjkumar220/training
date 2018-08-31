package com.oodles.exceptions;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
public class ResourceNotFound {
	public static Map<String, Object> generateResponse(HttpStatus status, boolean error, String message, Object extendedMessage, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("timestamp", new Date());
			map.put("status", status);
			map.put("error", error);
			map.put("message", message);
			map.put("extendedMessage", extendedMessage);
			map.put("data",responseObj);
			return map;
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("message", e.getMessage());
			return map;
		}
	}
}

