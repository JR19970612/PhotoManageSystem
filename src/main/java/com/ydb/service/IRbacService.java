/**
 * 
 */
package com.ydb.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhailiang
 *
 */
public interface IRbacService {
	
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
