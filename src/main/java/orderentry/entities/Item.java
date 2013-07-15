package orderentry.entities;

import jpa.namingsupport.AbstractEntity;
import jpa.namingsupport.Alias;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ITEMS")
@Alias(name = "ITEM")
public class Item extends AbstractEntity {

    private String name;

    private BigDecimal price;

    @ManyToOne
    private Category category;

    public Item() {
        this(null, null, null);
    }

    public Item(String name, BigDecimal price, Category category) {
        this(null, null, name, price, category);
    }

    public Item(Long id, Long version, String name, BigDecimal price, Category category) {
        super(id, version);
        this.name = name;
        this.price = price;
        this.category = category;
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
