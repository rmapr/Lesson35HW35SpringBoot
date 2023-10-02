package com.springboot.lesson35hw35springboot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDto {
    private String name;
    private List<Integer> products;
}
