package app.core.business.model.mapping.person.insuree;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import app.core.business.model.mapping.ToBeChecked;
import app.core.business.model.mapping.Contract;
import app.core.business.model.mapping.UserAccount;
import app.core.business.model.mapping.person.Admin;
import app.core.business.model.mapping.person.RegisteredUser;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_id", referencedColumnName = "id")
	private Admin admin;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Contract> contracts;

	public Customer() {
		contracts = new HashSet<Contract>();
	}

	public Customer(Admin admin) {
		this();
		this.admin = admin;
		admin.addCustomer(this);
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
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
		return new HashSet<Contract>(contracts);
	}

	public boolean addContract(Contract contract) {
		return contracts.add(contract);
	}
}