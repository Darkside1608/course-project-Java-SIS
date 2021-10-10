package com.github.siberianintegrationsystems.restApp.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity

public class Session extends BaseEntity {


    @Column
    private String name;

    @Column
    private Double percent;


    @Column
    private Date insertDate;

    public Session() {
    }

    public Session(String name, Double percent) {
        this.name = name;
        this.percent = percent;
        insertDate = new Date();
    }

    public String getName() {
        return name;
    }

    public Double getPercent() {
        return percent;
    }

    public Date getInsertDate() {
        return insertDate;
    }
}