package com.company.restaurant.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Code {

    @NotNull
    private String code;

    public Code(@JsonProperty("code") String code) {
        this.code = code;
    }

    public Code() {
    }

    public String getCode() {
        return code;
    }
}
