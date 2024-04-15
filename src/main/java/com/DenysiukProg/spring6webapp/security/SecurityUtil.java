package com.DenysiukProg.spring6webapp.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * Utility class for accessing security-related information.
 */
public class SecurityUtil {
    /**
     * Retrieves the username of the currently authenticated user from the session.
     *
     * @return The username of the currently authenticated user, or null if the user is not authenticated.
     */
    public static String getSessionUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return authentication.getName();
        }
        return null;
    }

}
