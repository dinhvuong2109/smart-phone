package com.smartphone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "images")
public class ImageEntity extends BaseEntity{
    private String fileName;
    private String filePath;
    private String fileType;
    private String fileSize;

//    @OneToOne(mappedBy = "phoneImage")
//    private PhoneEntity phone;
}
