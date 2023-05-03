package com.company.restaurant.Models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "DINING_TABLES")
public class DiningTables {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    public Long ID;

    @Min(1)
    @Max(8)
    public int chairCount = 1;

    public String section;

    public DiningTables(@Min(1) @Max(8) int chair_count, String section) {
        this.chairCount = chair_count;
        this.section = section;
    }

    public DiningTables() {

    }

    public Long getTable_id() {
        return ID;
    }


    public int getChairCount() {
        return chairCount;
    }

    public void setChairCount(int chair_count) {
        this.chairCount = chair_count;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
