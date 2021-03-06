package com.archana.demospringboot16thdec.model;

import com.sun.javafx.beans.IDProperty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BalanceSheet")
public class BalanceSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    private String description;
    @Column
    @NotNull
    @Min(0l)
    private int value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userdetails_userid")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BalanceSheet() {
    }

    public BalanceSheet(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BalanceSheet{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
