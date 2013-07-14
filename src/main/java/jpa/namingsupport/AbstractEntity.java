package jpa.namingsupport;

import javax.persistence.*;

@SuppressWarnings("UnusedDeclaration")
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CTGR_ID_GEN")
    @SequenceGenerator(name = "CTGR_ID_GEN", sequenceName = "CTGR_ID_SEQ", allocationSize = 1)
    private Long id;

    @Version
    private Long version;

    protected AbstractEntity(Long id, Long version) {
        this.id = id;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

}
