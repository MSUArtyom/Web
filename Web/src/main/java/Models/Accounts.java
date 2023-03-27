package Models;

import javax.persistence.*;

@Entity
@Table(name = "accounts", schema="webbbb")
public class Accounts {

    public Accounts() {}

    public Accounts(Clients client_id, Float balance, Float max_debt, Integer max_term) {
        this.client_id = client_id;
        this.balance = balance;
        this.max_debt = max_debt;
        this.max_term = max_term;
    }

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getMax_debt() {
        return max_debt;
    }

    public void setMax_debt(Float max_debt) {
        this.max_debt = max_debt;
    }

    public Integer getMax_term() {
        return max_term;
    }

    public void setMax_term(Integer max_term) {
        this.max_term = max_term;
    }

    private int account_id;

    @ManyToOne(targetEntity=Clients.class)
    @JoinColumn(name = "client_id")
    public Clients getClient_id() {
        return client_id;
    }

    public void setClient_id(Clients client_id) {
        this.client_id = client_id;
    }

    private Clients client_id;
    private Float balance;
    private Float max_debt;
    private Integer max_term;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts other = (Accounts) o;
        return account_id == other.account_id &&
                Float.compare(other.balance, balance) == 0 &&
                Float.compare(other.max_debt, max_debt) == 0 &&
                max_term.equals(other.max_term) &&
                client_id.equals(other.client_id);
    }

}
