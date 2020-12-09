package com.smartphone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDTO<T> {
    protected Long id;
    protected Timestamp createdDate;
    protected Timestamp modifiedDate;
    protected String createdBy;
    protected String modifiedBy;
}
