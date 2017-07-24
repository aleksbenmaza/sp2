package business.model.dao;

import business.model.mapping.*;
import business.model.mapping.Token;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by alexandremasanes on 21/02/2017.
 */
public interface DAO extends Finder, Recorder, Remover {

    <T extends IdentifiableByIdImpl> T refresh(IdentifiableByIdImpl entity);

    boolean trackBack(Entity entity);

    List<Make> searchMakes(String name);

    List<Model> searchModels(String name);

    List<Model> searchModels(String modelName, long makeId);

    UserAccount findUserAccount(String emailAddress);

    Vehicle findVehicleByRegistrationNumber(String registrationNumber);

    UserAccount findUserAccount(String email, String hash);

    float computeDeductibleValue(long insuranceId, float damageAmount);

    boolean emailExists(String email);

    Token findToken(String value);

    boolean tokenExists(String token);

    String getHashSalt();

    int getTokenLifetime();
}
