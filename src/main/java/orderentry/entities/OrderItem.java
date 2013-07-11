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
    @SequenceGenerator(name = "ORIT_ID_GEN", sequenceName = "ORIT_ID_SEQ")
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "ORIT_ORDR_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ORIT_ITEM_ID")
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
