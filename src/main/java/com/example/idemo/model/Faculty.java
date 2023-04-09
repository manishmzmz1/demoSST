package com.example.idemo.model;

import javax.persistence.*;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Faculty() {
    }

    public Faculty(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
