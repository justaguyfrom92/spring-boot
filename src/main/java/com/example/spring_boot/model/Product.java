package com.example.spring_boot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product
{    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    //private String imageUrl;
    private String category;
    private boolean inStock;

    public Product() {
    }

    public Product(Long id, String name, String description, double price, 
                   /**String imageUrl,**/ String category, boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        //this.imageUrl = imageUrl;
        this.category = category;
        this.inStock = inStock;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price;}

    //public String getImageUrl() { return imageUrl; }
    //public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock;}

}