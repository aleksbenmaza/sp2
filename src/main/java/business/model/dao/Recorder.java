package business.model.dao;

import business.model.mapping.Entity;
import business.model.mapping.Token;

import java.util.Set;

/**
 * Created by alexandremasanes on 21/02/2017.
 */
public interface Recorder {

    void save(Entity entity);

//    void save(Token token);
}
