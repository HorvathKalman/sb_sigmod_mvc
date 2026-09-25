package pti.sb_sigmod_mvc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("authors")
public class AuthorSaveModel {
    @Id
    @Column
    private Integer id;
    @Column
    private String name;
    @Transient
    private Integer counter;

    public AuthorSaveModel(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.counter = 0;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
