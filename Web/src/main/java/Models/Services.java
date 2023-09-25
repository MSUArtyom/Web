package Models;

import javax.persistence.*;

@Entity
@Table(name = "services", schema="webapp")
public class Services
{
    public Services(String name, String type, String description, String tariff_plan) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.tariff_plan = tariff_plan;
    }

    public Services() {    }

    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTariff_plan() {
        return tariff_plan;
    }

    public void setTariff_plan(String tariff_plan) {
        this.tariff_plan = tariff_plan;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Services other = (Services) obj;
        return (this.service_id == other.service_id) &&
                (this.name.equals(other.name)) &&
                (this.type.equals(other.type)) &&
                (this.description.equals(other.description)) &&
                (this.tariff_plan.equals(other.tariff_plan));
    }

    private int service_id;
    private String name;
    private String type;
    private String description;
    private String tariff_plan;

}