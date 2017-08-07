/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.oauth2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.util.Base64Utils;


/**
 *
 * @author Sharath Kulal
 */
@RunWith(SpringRunner.class)

//@WebAppConfiguration

@AutoConfigureJsonTesters
@WebMvcTest
@Import(DefaultTestConfiguration.class)
public class Oauth2Test {

    @Autowired
    private MockMvc mockMvc;
    
    /**
     * Helper method to log-in a user and return access/refresh token
     * @return access_token
     * @throws Exception 
     */
    private String getAuthToken(boolean refreshToken) throws Exception {
        String username = "clientOne";
        String password = "secret";
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);

        MvcResult result = mockMvc.perform(post("/oauth/token")
                .header("Authorization", authHeader)
                .param("grant_type", "password")
                .param("username", "mario").param("password", "mario123")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("token_type", is("bearer")))
                //.andDo(print())
                .andReturn();
        
        String content = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(content);
        
        String strTokenName;
        if(refreshToken) {
            strTokenName = "refresh_token";
        }else {
            strTokenName = "access_token";
        }
        
        JsonNode tokenNode = actualObj.get(strTokenName);
        String token = tokenNode.asText();
        
        return token;
    }
    
    @Test
    public void test_login_valid() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                post("/oauth/token")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,"Basic " + Base64Utils.encodeToString("clientOne:secret".getBytes()))
                .param("grant_type", "password")
                .param("username", "luigi")
                .param("password", "luigi123")
        );
        resultActions.andExpect(status().isOk());
    }
    
    @Test
    public void test_refreshToken() throws Exception {
        String client = "clientOne";
        String password = "secret";
        String auth = client + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        String refreshToken = getAuthToken(true);
        
        ResultActions raCheckToken = mockMvc.perform(post("/oauth/token")
                .header("Authorization", authHeader)
                .param("grant_type", "refresh_token")
                .param("refresh_token", refreshToken)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        //andDo(print());
        
    }
}
