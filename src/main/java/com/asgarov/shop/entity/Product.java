package com.asgarov.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Product extends AbstractPersistable<Long> {

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String imageName;

    @Column
    private String description;

}
