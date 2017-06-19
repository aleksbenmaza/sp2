package app.core.business.model.mapping;

import app.core.business.exc.BusinessException;
import app.core.business.model.mapping.person.Expert;
import app.core.business.model.mapping.sinister.Sinister;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by alexandremasanes on 03/04/2017.
 */
@Entity
@Table(name = "sinistres_contrats_experts")
public class Coverage implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sinistre_id", referencedColumnName = "id")
    private Sinister sinister;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contrat_id", referencedColumnName = "id")
    private Contract contract;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "expert_id", referencedColumnName = "id")
    private Expert expert;

    public Coverage(Sinister sinister, Contract contract) {
        if(contract.getVehicle() != sinister.getVehicle() || contract.getVehicle().getId() != sinister.getVehicle().getId())
            throw new BusinessException();
        this.sinister = Objects.requireNonNull(sinister);
        this.contract = Objects.requireNonNull(contract);
        sinister.setCoverage(this);
        contract.addCoverage(this);
    }

    public Coverage(Sinister sinister, Contract contract, Expert expert) {
        this(sinister, contract);
        this.expert = Objects.requireNonNull(expert);
        expert.addCoverage(this);
    }

    public Sinister getSinister() {
        return sinister;
    }

    public Contract getContract() {
        return contract;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        if(expert != null) {
            expert.addCoverage(this);

        } else if(this.expert != null)
            this.expert.removeCoverage(this);

        this.expert = expert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coverage coverage = (Coverage) o;

        if (sinister != null ? !sinister.equals(coverage.sinister) : coverage.sinister != null) return false;
        if (contract != null ? !contract.equals(coverage.contract) : coverage.contract != null) return false;
        return expert != null ? expert.equals(coverage.expert) : coverage.expert == null;
    }

    @Override
    public int hashCode() {
        int result = sinister != null ? sinister.hashCode() : 0;
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (expert != null ? expert.hashCode() : 0);
        return result;
    }

    Coverage() {

    }
}
