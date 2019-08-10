package com.example.jwt.demojwt.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
public class RegisterLog {
		public String getMac() {	
			return StringUtils.leftPad(LogVariables.mac,  12, " ");
		}
		
		public String getToken() {
			if(LogVariables.token != null)
				return StringUtils.substring(LogVariables.token, 0, 20);
			else
				return StringUtils.leftPad(" ",  20, " ");
		}
		
		public String getKeyLog()
		{
			if(LogVariables.keyLog != null)
				return LogVariables.keyLog;
			else
				return StringUtils.leftPad("", 10);
		}
		
		public void error(String description, Exception error) {
			log.error(getKeyLog() + " " + description, error);
		}
		
		public void debug(String format, String method, Object... body) {	
			format = getBody(format, body);
			String msg = getKeyLog() + " [REQUEST]  ::> Token: [" + getToken() + "] - Mac [" + getMac() + "] - Method: " + method + " -> " + format;
			log.info(msg);
		}	
		
		public void info(String format, String method, Object... body) {
			format = getBody(format, body);
			String msg = getKeyLog() + " [INFO]     ::> Token: [" + getToken() + "] - Mac [" + getMac() + "] - Method: " + method + " -> " + format;
			log.info(msg);
		}

		private String getBody(String format, Object... body) {
			for(Object obj : body)
			{
				try
				{
					if(obj != null)
						format = format.replaceFirst("\\{}", obj.toString());
					else
						format = format.replaceFirst("\\{}", "null");
				}
				catch(Exception e)
				{
					format = e.getMessage();
				}
			}
			return format;
		}	
}
