package app.core.business.model.mapping;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Created by alexandremasanes on 27/02/2017.
 */
@MappedSuperclass
public abstract class IdentifiableByIdImpl extends Entity implements IdentifiableById {

    @Id @GeneratedValue
    private long id;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
