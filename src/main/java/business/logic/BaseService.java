package business.logic;

import business.model.dao.DAO;
import business.model.mapping.Entity;
import helper.MessageHelper;

import javax.inject.Inject;


/**
 * Created by alexandremasanes on 26/02/2017.
 */
public abstract class BaseService<T extends Entity> {

    @Inject
    protected DAO dao;

    @Inject
    private MessageHelper messageHelper;

    public void refresh(T entity) {
        dao.refresh(entity);
    }

    public void save(T entity) { dao.save(entity);}

    protected String getMessage(String code, Object... vars) {
        return messageHelper.getMessage(code, vars);
    }

}
