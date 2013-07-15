package orderentry.entities;

import jpa.namingsupport.AbstractEntity;
import jpa.namingsupport.Alias;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEMS")
@Alias(name = "ORIT")
public class OrderItem extends AbstractEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

    private int quantity;

    public OrderItem() {
        this(null, 0);
    }

    public OrderItem(Item item, int quantity) {
        this(null, null, item, quantity);
    }

    public OrderItem(Long id, Long version, Item item, int quantity) {
        super(id, version);
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
