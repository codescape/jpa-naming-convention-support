package orderentry.entities;

import jpa.namingsupport.Alias;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ORDERS")
@Alias(name = "ORDR")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDR_ID_GEN")
    @SequenceGenerator(name = "ORDR_ID_GEN", sequenceName = "ORDR_ID_SEQ")
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "ORDR_CUST_ID")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Order() {
        this(null);
    }

    public Order(Customer customer) {
        setCustomer(customer);
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

}
