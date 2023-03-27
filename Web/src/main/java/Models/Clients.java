package Models;

import javax.persistence.*;

@Entity
@Table(name = "clients", schema="webbbb")
public class Clients
{
    public Clients(String name, String emails, String phone_numbers, String addresses) {
        this.name = name;
        this.emails = emails;
        this.phone_numbers = phone_numbers;
        this.addresses = addresses;
    }

    public Clients() {    }

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_numbers() {
        return phone_numbers;
    }

    public void setPhone_numbers(String phone_numbers) {
        this.phone_numbers = phone_numbers;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Clients other = (Clients) obj;
        return (this.client_id == other.client_id) &&
                (this.name.equals(other.name)) &&
                (this.phone_numbers.equals(other.phone_numbers)) &&
                (this.addresses.equals(other.addresses)) &&
                (this.emails.equals(other.emails));
    }

    private int client_id;
    private String name;
    private String emails;
    private String phone_numbers;
    private String addresses;

}

