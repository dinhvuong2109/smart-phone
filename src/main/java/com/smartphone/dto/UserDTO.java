package com.smartphone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends AbstractDTO<UserDTO> {
    private String userName;
    private String password;
    private String fullName;
    private int status;
    private List<Long> roleIds;
    private List<String> roleCodes;
}
