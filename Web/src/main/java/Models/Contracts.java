package Models;

import javax.persistence.*;

@Entity
@Table(name = "contracts", schema="webapp")
public class Contracts {

    public Contracts() {}

    public Contracts(Accounts account_id, Services service_id, String time_period) {
        this.account_id = account_id;
        this.service_id = service_id;
        this.time_period = time_period;
    }

    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public String getTime_period() {
        return time_period;
    }

    public void setTime_period(String time_period) {
        this.time_period = time_period;
    }

    private int contract_id;

    @ManyToOne(targetEntity=Accounts.class)
    @JoinColumn(name = "account_id")
    public Accounts getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Accounts account_id) {
        this.account_id = account_id;
    }

    @ManyToOne(targetEntity=Services.class)
    @JoinColumn(name = "service_id")
    public Services getService_id() {
        return service_id;
    }

    public void setService_id(Services service_id) {
        this.service_id = service_id;
    }

    private Accounts account_id;
    private Services service_id;
    private String time_period;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contracts other = (Contracts) o;
        return contract_id == other.contract_id &&
                time_period.equals(other.time_period) &&
                service_id.equals(other.service_id) &&
                account_id.equals(other.account_id);
    }

}