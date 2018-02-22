/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.oauth2;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * This class could do Db Access to userDetails Service which can do userId/Password Check as the auth token request comes in
 * @author Sharath Kulal
 */
@Component(value = "MyAuthManager")
public class MyAuthManager implements AuthenticationManager{

    private final static Logger LOG = Logger.getLogger(MyAuthManager.class);
    
    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        LOG.info("Authentication:"+a.getName());
        LOG.debug("Password:"+a.getCredentials());

        UsernamePasswordAuthenticationToken upat = null;

        UserDetails userDetails = userDetailsService.loadUserByUsername(a.getPrincipal().toString());
        
        if (a.getPrincipal().equals(userDetails.getUsername()) && a.getCredentials().equals(userDetails.getPassword())) {
            upat = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
        }
        
        return upat;
    }
    
    
}
