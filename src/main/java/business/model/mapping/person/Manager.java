package business.model.mapping.person;

import business.model.mapping.UserAccount;
import business.model.mapping.person.insuree.Customer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="administrateurs")
public class Manager extends Person implements RegisteredUser {

	public static final long serialVersionUID = 6723623575574622116L;

	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Customer> customers;

	{
		customers = new HashSet<Customer>();
	}

	@Override
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public boolean addCustomer(Customer customer) {
		return false;
	}

	public boolean removeCustomer(Customer customer) {
		return false;
	}
}
