package com.adi.tugas4.Model;

import javax.persistence.*;

@Entity
@Table(name="bookcategory")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "bookcategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
