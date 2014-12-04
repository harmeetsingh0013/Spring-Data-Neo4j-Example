/**
 * 
 */
package com.harmeetsingh13.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Harmeet Singh(Taara)
 *
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
	return new Class<?>[]{Neo4jConfig.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
	return new Class<?>[]{ApplicationContextConfig.class};
	}
	@Override
	protected String[] getServletMappings() {
	return new String[]{"/"};
	}
}
