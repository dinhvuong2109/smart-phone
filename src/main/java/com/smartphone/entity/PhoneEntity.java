package com.smartphone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class PhoneEntity extends BaseEntity {
    private String name;
    private Long price;

//    @OneToOne
//    @JoinColumn(name = "image_id", referencedColumnName = "id")
//    private ImageEntity phoneImage;

    //private String image;
    //    private boolean status;
//    private String screen;
//    private String operatingSystem;
//    private String behindCamera;
//    private String frontCamera;
//    private String cpu;
//    private String ram;
//    private String internalMemory;
//    private String batteryCapacity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
