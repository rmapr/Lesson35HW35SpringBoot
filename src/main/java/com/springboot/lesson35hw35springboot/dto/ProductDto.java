package com.springboot.lesson35hw35springboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
//@Builder
@Accessors(chain = true)
public class ProductDto {

    private String name;
    private Double cost;

}
