/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.oauth2;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Sharath Kulal
 */
@TestConfiguration
@Import({AuthorizationServerConfiguration.class, OAuth2SecurityConfiguration.class})
@ComponentScan(basePackages = {"org.sharath.oauth2"})
public class DefaultTestConfiguration {

}
