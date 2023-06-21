package com.team4.backend.util;

import com.team4.backend.model.dto.ResultDtoProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    public static String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }

    public static boolean jwtStatus(HttpServletRequest request) {
        return (boolean) request.getAttribute(ResultDtoProperties.STATUS);
    }

    public static String jwtMessage(HttpServletRequest request) {
        return (String) request.getAttribute(ResultDtoProperties.MESSAGE);
    }
}
