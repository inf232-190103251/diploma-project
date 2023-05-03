package com.company.restaurant.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "food_info")
public class FoodInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "food_quantity")
    private Integer foodQuantity;

    @Column(name = "estimated_time")
    private Integer estimatedTime;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "details" }, allowSetters = true)
    private Food food;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FoodInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFoodQuantity() {
        return this.foodQuantity;
    }

    public FoodInfo foodQuantity(Integer foodQuantity) {
        this.setFoodQuantity(foodQuantity);
        return this;
    }

    public void setFoodQuantity(Integer foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public Integer getEstimatedTime() {
        return this.estimatedTime;
    }

    public FoodInfo estimatedTime(Integer estimatedTime) {
        this.setEstimatedTime(estimatedTime);
        return this;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public FoodInfo food(Food food) {
        this.setFood(food);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodInfo)) {
            return false;
        }
        return id != null && id.equals(((FoodInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodInfo{" +
                "id=" + getId() +
                ", foodQuantity=" + getFoodQuantity() +
                ", estimatedTime=" + getEstimatedTime() +
                "}";
    }
}
