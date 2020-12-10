package com.smartphone.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PhoneDTO extends AbstractDTO<PhoneDTO> {
    private String name;
    private String price;
    private Long categoryId;
    private String categoryCode;
}
