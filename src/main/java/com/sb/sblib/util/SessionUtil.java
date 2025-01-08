package com.sb.sblib.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtil {

	public static String getLoginUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
