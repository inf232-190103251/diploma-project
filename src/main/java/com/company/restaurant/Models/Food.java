package com.company.restaurant.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FOOD")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    public Long ID;

    @Column(nullable = false, unique = true)
    public String name;

    @Column(columnDefinition = "text")
    public String description;

    public long price = 0;

    public String sizes = "M";

    public String imageUrl;

    public String category;

    @OneToMany(mappedBy = "food",fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"food"}, allowSetters = true)
    private Set<FoodInfo> details = new HashSet<>();


    public Food(String name, String description, long price, String sizes, String imageUrl, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.sizes = sizes;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String size) {
        this.sizes = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image_url) {
        this.imageUrl = image_url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getID() {
        return ID;
    }

    public Set<FoodInfo> getDetails() {
        return this.details;
    }

    public void setDetails(Set<FoodInfo> foodInfos) {
        if (this.details != null) {
            this.details.forEach(i -> i.setFood(null));
        }
        if (foodInfos != null) {
            foodInfos.forEach(i -> i.setFood(this));
        }
        this.details = foodInfos;
    }

    public Integer getEstimatedTimeByQuantity(Integer quantity) {
        for (FoodInfo foodInfo : details) {
            if (foodInfo.getFoodQuantity().equals(quantity)) {
                return foodInfo.getEstimatedTime();
            }
        }
        return 0;
    }

    public Food details(Set<FoodInfo> foodInfos) {
        this.setDetails(foodInfos);
        return this;
    }

    public Food addDetails(FoodInfo foodInfo) {
        this.details.add(foodInfo);
        foodInfo.setFood(this);
        return this;
    }

    public Food removeDetails(FoodInfo foodInfo) {
        this.details.remove(foodInfo);
        foodInfo.setFood(null);
        return this;
    }


    @Override
    public String toString() {
        return "Food{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", size='" + sizes + '\'' +
                ", image_url='" + imageUrl + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

}
