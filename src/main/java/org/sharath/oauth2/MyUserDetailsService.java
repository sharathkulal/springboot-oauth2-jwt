/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.oauth2;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class is called when client requests access/refresh token.
 * you should check if the client is still valid before returning.
 * @author Sharath Kulal
 */
@Service
public class MyUserDetailsService implements UserDetailsService{

    private Logger LOG = Logger.getLogger(MyUserDetailsService.class);
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info(username);
        org.springframework.security.core.userdetails.User user = null;
        if(username.equalsIgnoreCase("mario")) {
            List<GrantedAuthority> lstGrants = new ArrayList<>();
            lstGrants.add(new SimpleGrantedAuthority("jump"));
            lstGrants.add(new SimpleGrantedAuthority("fast"));
            user = new org.springframework.security.core.userdetails.User("mario","mario123", lstGrants);
        }else if(username.equalsIgnoreCase("luigi")) {
            List<GrantedAuthority> lstGrants = new ArrayList<>();
            lstGrants.add(new SimpleGrantedAuthority("jump high"));
            lstGrants.add(new SimpleGrantedAuthority("slow"));
            user = new org.springframework.security.core.userdetails.User("luigi","luigi123", lstGrants);
        }
        return user;
    }
    
}
