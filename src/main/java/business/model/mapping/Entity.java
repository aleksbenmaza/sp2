package app.core.business.model.mapping;

import static java.util.Arrays.copyOf;

import app.core.business.model.mapping.person.RegisteredUser;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = -5075953598452663724L;

	@Generated(GenerationTime.ALWAYS)
	@Column(name = "maj")
	protected Timestamp updatedAt;
	
	public Timestamp updatedAt() {
		return updatedAt;
	}

	protected Entity() {}

	protected static <T extends Entity> T requireNonNull(T entity) {
		return Objects.requireNonNull(entity);
	}

	protected static <T extends Entity> void requireNonNull(T... entities) {

		for(Entity entity : entities)
			if(entity == null)
				throw new NullPointerException();
	}
}
