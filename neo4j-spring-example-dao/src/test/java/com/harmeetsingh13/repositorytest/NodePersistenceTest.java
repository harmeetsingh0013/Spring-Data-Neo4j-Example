/**
 * 
 */
package com.harmeetsingh13.repositorytest;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.harmeetsingh13.config.Neo4jConfig;
import com.harmeetsingh13.entities.Movie;
import com.harmeetsingh13.repository.RepositoryMovie;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional(value = "neo4jTransactionManager")
@ContextConfiguration(classes = Neo4jConfig.class, loader = AnnotationConfigContextLoader.class)
public class NodePersistenceTest {

	@Autowired
	private Neo4jTemplate neo4jTemplate;
	@Autowired
	private RepositoryMovie movieRepo;
	
	@Test
	@Transactional
	public void persistMovie() {
		Movie movie = new Movie();
		movie.setId(13L);
		movie.setTitle("Dark Knight");
		Movie returnMovie = neo4jTemplate.save(movie);
		
		assertThat(returnMovie, notNullValue());
	}
	
	@Test
	public void getObjectFromRepo() {
		Movie movie = movieRepo.findBySchemaPropertyValue("id", 13);
		
		assertThat(movie, notNullValue());
	}
}
