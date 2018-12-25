/**
 *
 */
package com.ydb.service.imp;

import com.ydb.entity.Person;
import com.ydb.entity.ResourecesUrl;
import com.ydb.service.IRbacService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 权限验证服务
 * @date:2018/12/21
 */
@Component("rbacService")
public class RbacServiceImpl implements IRbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof Person) {
            // 读取用户所拥有权限的所有URL
            Set<ResourecesUrl> urls = ((Person) principal).getUrls();
            for (ResourecesUrl url : urls) {
                if (antPathMatcher.match("/gdpi"+url.getResourecesUrl(), request.getRequestURI())&url.getResourecesUrlMethod().equals(request.getMethod().toUpperCase())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
