/**
 * 
 */
package com.harmeetsingh13.config;

import javax.annotation.Resource;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.aspects.config.Neo4jAspectConfiguration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.JtaTransactionManagerFactoryBean;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:properties/db.properties")
@ComponentScan(basePackages = "com.harmeetsingh13.entities")
@EnableNeo4jRepositories(basePackages = "com.harmeetsingh13.repository")
public class Neo4jConfig extends Neo4jAspectConfiguration{

	@Resource
	private Environment env; 
	
	public Neo4jConfig() {
		setBasePackage("com.harmeetsingh13.entities");
	}
	
	@Bean(name = "graphDatabaseService")
	public GraphDatabaseService getGraphDatabaseService(){
		GraphDatabaseFactory databaseService = new GraphDatabaseFactory();
		return databaseService.newEmbeddedDatabase(env.getProperty("db.store.directory"));
	}
	
	@Autowired
	@Bean(name = "jtaTransactionManagerFactoryBean")
	public JtaTransactionManagerFactoryBean neo4jTransactionManagerFactoryBean
		(GraphDatabaseService graphDatabaseService) throws Exception {
		JtaTransactionManagerFactoryBean factoryBean = 
				new JtaTransactionManagerFactoryBean(graphDatabaseService);
		return factoryBean;
	}
	
	@Autowired
	@Bean(name= {"neo4jTransactionManager"})
	public PlatformTransactionManager getTransactionManager
		(JtaTransactionManagerFactoryBean jtaTransactionManagerFactoryBean) throws Exception {
		ChainedTransactionManager transactionManager = new ChainedTransactionManager(jtaTransactionManagerFactoryBean.getObject());
		return transactionManager;
	}
}
