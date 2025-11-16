package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "furniture")
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Название не должно быть пустым")
    @Size(min = 2, max = 40, message = "Название должно быть длиной от 2х до 40 символов")
    private String name;

    @NotBlank(message = "Тип не может быть пустым")
    @Size(min = 2, max = 40, message = "Тип должен быть длиной от 2х до 40 символов")
    private String type;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть больше 0")
    private Double price;
    


    public Furniture()    {
    }

    public Furniture(Integer id, String name, String type, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }   
    public String getName() {
        return name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }   




}
