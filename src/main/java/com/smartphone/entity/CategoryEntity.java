package com.smartphone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    private String name;
    private String code;
    @OneToMany(mappedBy = "category")
    private final List<PhoneEntity> phoneList = new ArrayList<>();
}
