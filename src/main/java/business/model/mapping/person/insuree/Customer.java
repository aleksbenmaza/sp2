package business.model.mapping.person.insuree;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import business.model.mapping.ToBeChecked;
import business.model.mapping.Contract;
import business.model.mapping.UserAccount;
import business.model.mapping.person.Manager;
import business.model.mapping.person.RegisteredUser;

@Entity
@Table(name = "clients")
public class Customer extends Insuree implements RegisteredUser, ToBeChecked {

	public static final long serialVersionUID = -4842054876473218101L;

	@Column(name = "carte_identite")
	private String idCard;

	@Column
	private String sepa;

	@Enumerated
	@Column(name = "statut")
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_id", referencedColumnName = "id")
	private Manager admin;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Contract> contracts;

	public Customer() {
		contracts = new HashSet<Contract>();
	}

	public Customer(Manager admin) {
		this();
		this.admin = admin;
		admin.addCustomer(this);
	}

	public Manager getAdmin() {
		return admin;
	}

	public void setAdmin(Manager admin) {
		this.admin = admin;
		if(admin != null)
			admin.addCustomer(this);
	}

	@Override
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public UserAccount getUserAccount() {
		return userAccount;
	}
	
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getSepa() {
		return sepa;
	}

	public void setSepa(String sepa) {
		this.sepa = sepa;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	public Set<Contract> getContracts() {
		return(contracts);
	}

	public boolean addContract(Contract contract) {
		return contracts.add(contract);
	}
}