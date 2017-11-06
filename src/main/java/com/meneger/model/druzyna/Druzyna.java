package com.meneger.model.druzyna;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DRUZYNY")
public class Druzyna {
    public static final int NAME_MAX_LENGTH = 30;

    @Id
    @NotNull
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "NAZWA", length = NAME_MAX_LENGTH, unique = true)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
