/**
 * 
 */
package com.harmeetsingh13.config;

import javax.annotation.Resource;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
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
@ComponentScan(basePackages={"com.harmeetsingh13.entities", "com.harmeetsingh13.maintainrelationship"})
@PropertySource(value="classpath:properties/db.properties")
@EnableNeo4jRepositories(basePackages = "com.harmeetsingh13.repository")
public class Neo4jConfig extends Neo4jAspectConfiguration{

	@Resource
	private Environment env; 
	
	public Neo4jConfig() {
		setBasePackage("com.harmeetsingh13.entities");
	}
	
	@Bean(name="graphDatabaseService")
	public GraphDatabaseService getGraphDatabaseService(){
		GraphDatabaseFactory databaseService = new GraphDatabaseFactory();
		return databaseService.newEmbeddedDatabase(env.getProperty("db.store.directory"));
	}
	
	private JtaTransactionManagerFactoryBean neo4jTransactionManagerFactoryBean() throws Exception {
		JtaTransactionManagerFactoryBean factoryBean = 
				new JtaTransactionManagerFactoryBean(getGraphDatabaseService());
		return factoryBean;
	}
	
	/*@Override
	@Bean(name= {"transactionManager"})
	@Resource(name="graphDatabaseService")
	public PlatformTransactionManager neo4jTransactionManager(GraphDatabaseService graphDatabaseService) {
		JtaTransactionManagerFactoryBean factoryBean = new JtaTransactionManagerFactoryBean(graphDatabaseService);
		return factoryBean.getObject();
	}*/
	
	@Override
	@Bean(name= {"transactionManager"})
	public PlatformTransactionManager neo4jTransactionManager() throws Exception {
		ChainedTransactionManager transactionManager = new ChainedTransactionManager(neo4jTransactionManagerFactoryBean().getObject());
		return transactionManager;
	}
}
