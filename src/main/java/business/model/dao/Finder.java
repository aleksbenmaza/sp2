package business.model.dao;

import business.model.mapping.Entity;
import business.model.mapping.IdentifiableByIdImpl;

import java.util.List;

/**
 * Created by alexandremasanes on 21/02/2017.
 */
public interface Finder {

    <T extends Entity> List<T>  find(Class<T> entityClass);

    <T extends IdentifiableByIdImpl> List<T> find(Class<T> entityClass, long... ids);

    <T extends IdentifiableByIdImpl> T find(Class<T> entityClass, long id);

    <T extends IdentifiableByIdImpl> boolean exists(Class<T> entityClass, long id);

    <T extends IdentifiableByIdImpl> long getNextId(Class<T> entityClass);
}
