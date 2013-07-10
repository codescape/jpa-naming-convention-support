package orderentry.entities;

import jpa.namingsupport.AbstractEntity;
import jpa.namingsupport.Alias;

import javax.persistence.*;

@SuppressWarnings("UnusedDeclaration")
@Entity
@Table(name = "CATEGORIES")
@Alias(name = "CTGR")
public class Category extends AbstractEntity {

    @Basic(optional = false)
    private String name;

    @Basic
    private String description;

    public Category() {
        this(null, null);
    }

    public Category(String name, String description) {
        this(null, null, name, description);
    }

    public Category(Long id, Long version, String name, String description) {
        super(id, version);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
