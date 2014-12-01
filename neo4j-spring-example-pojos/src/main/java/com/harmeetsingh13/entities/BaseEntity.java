/**
 * 
 */
package com.harmeetsingh13.entities;

import lombok.EqualsAndHashCode;

import org.springframework.data.neo4j.annotation.GraphId;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@EqualsAndHashCode
public abstract class BaseEntity {

	@GraphId
	private Long graphId;
}
