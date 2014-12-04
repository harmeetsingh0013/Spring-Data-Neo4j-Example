/**
 * 
 */
package com.harmeetsingh13.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Configuration
@EnableWebMvc
@Import(value={ThymeleafConfig.class})
@ComponentScan(value={"com.harmeetsingh13.controller", "com.harmeetsingh13.service.impl"})
public class ApplicationContextConfig extends WebMvcConfigurerAdapter{

}
