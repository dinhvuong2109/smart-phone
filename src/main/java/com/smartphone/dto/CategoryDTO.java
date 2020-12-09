package com.smartphone.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO extends AbstractDTO<CategoryDTO>{
    private String name;
    private String code;
}
