package Models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions", schema="webbbb")
public class Transactions {

    public Transactions() {}

    public Transactions(Accounts account_id, Contracts contract_id, Float value, Timestamp time) {
        this.account_id = account_id;
        this.contract_id = contract_id;
        this.value = value;
        this.time = time;
    }

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    private int transaction_id;

    @ManyToOne(targetEntity=Accounts.class)
    @JoinColumn(name = "account_id")
    public Accounts getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Accounts account_id) {
        this.account_id = account_id;
    }

    @ManyToOne(targetEntity=Contracts.class)
    @JoinColumn(name = "contract_id")
    public Contracts getContract_id() {
        return contract_id;
    }

    public void setContract_id(Contracts contract_id) {
        this.contract_id = contract_id;
    }

    private Accounts account_id;
    private Contracts contract_id;
    private Float value;
    private Timestamp time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions other = (Transactions) o;
        return transaction_id == other.transaction_id &&
                time.equals(other.time) &&
                Float.compare(other.value, value) == 0 &&
                contract_id.equals(other.contract_id) &&
                account_id.equals(other.account_id);
    }

}