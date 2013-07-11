package orderentry.entities;

import jpa.namingsupport.Alias;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ITEMS")
@Alias(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_GEN")
    @SequenceGenerator(name = "ITEM_ID_GEN", sequenceName = "ITEM_ID_SEQ")
    private Long id;

    @Version
    private Long version;

    private String name;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "ITEM_CTGR_ID")
    private Category category;

    public Item() {
        this(null, null, null);
    }

    public Item(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
