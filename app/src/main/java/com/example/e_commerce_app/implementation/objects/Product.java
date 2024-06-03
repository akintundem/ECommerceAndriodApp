package com.example.e_commerce_app.implementation.objects;

public class Product {
    private String product_id;
    private String product_name;
    private double discounted_price;
    private double actual_price;
    private double discount_percentage;
    private double rating;
    private int rating_count;
    private String about_product;
    private int number_of_inventory;
    private String img_link;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(double discounted_price) {
        this.discounted_price = discounted_price;
    }

    public double getActual_price() {
        return actual_price;
    }

    public void setActual_price(double actual_price) {
        this.actual_price = actual_price;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public String getAbout_product() {
        return about_product;
    }

    public void setAbout_product(String about_product) {
        this.about_product = about_product;
    }

    public int getNumber_of_inventory() {
        return number_of_inventory;
    }

    public void setNumber_of_inventory(int number_of_inventory) {
        this.number_of_inventory = number_of_inventory;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }
}
