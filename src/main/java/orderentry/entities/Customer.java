package orderentry.entities;

import jpa.namingsupport.AbstractEntity;
import jpa.namingsupport.Alias;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
@Alias(name = "CUST")
public class Customer extends AbstractEntity {

    private String username;

    public Customer() {
        this(null);
    }

    public Customer(String username) {
        this(null, null, username);
    }

    public Customer(Long id, Long version, String username) {
        super(id, version);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
