/**
 * 
 */
package com.harmeetsingh13.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.neo4j.annotation.GraphId;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@EqualsAndHashCode
public abstract class BaseEntity {

	@GraphId
	@Getter @Setter
	private Long graphId;
}
