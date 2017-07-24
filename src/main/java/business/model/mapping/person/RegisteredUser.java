package business.model.mapping.person;



import business.model.mapping.ToBeChecked;
import business.model.mapping.UserAccount;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public interface RegisteredUser {

	void setUserAccount(UserAccount userAccount);
	
	UserAccount getUserAccount();

	static <T extends RegisteredUser> T requireNonNull(T toBeChecked) {
		return Objects.requireNonNull(toBeChecked);
	}
}
