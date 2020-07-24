package com.adi.tugas4.Model;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price,year;
    private String title;
    private String publisher;
    private String writer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookcategory")
    private  BookCategory bookcategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public BookCategory getBookcategory() {
        return bookcategory;
    }

    public void setBookcategory(BookCategory bookcategory) {
        this.bookcategory = bookcategory;
    }
}
