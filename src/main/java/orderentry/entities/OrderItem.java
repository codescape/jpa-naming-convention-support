package orderentry.entities;

import jpa.namingsupport.Alias;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_ITEMS")
@Alias(name = "ORIT")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORIT_ID_GEN")
    @SequenceGenerator(name = "ORIT_ID_GEN", sequenceName = "ORIT_ID_SEQ", allocationSize = 1)
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

    private int quantity;

    public OrderItem() {
        this(null, 0);
    }

    public OrderItem(Item item, int quantity) {
        setItem(item);
        setQuantity(quantity);
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
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
