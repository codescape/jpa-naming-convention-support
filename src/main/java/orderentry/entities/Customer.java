package orderentry.entities;

import jpa.namingsupport.Alias;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMERS")
@Alias(name = "CUST")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_ID_GEN")
    @SequenceGenerator(name = "CUST_ID_GEN", sequenceName = "CUST_ID_SEQ")
    private Long id;

    @Version
    private Long version;

    private String username;

    public Customer() {
        this(null);
    }

    public Customer(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
