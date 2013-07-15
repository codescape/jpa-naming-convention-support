package orderentry.entities;

import jpa.namingsupport.AbstractEntity;
import jpa.namingsupport.Alias;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Alias(name = "ORDR")
public class Order extends AbstractEntity {

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Order() {
        this(null);
    }

    public Order(Customer customer) {
        this(null, null, customer);
    }

    public Order(Long id, Long version, Customer customer) {
        super(id, version);
        this.customer = customer;
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
