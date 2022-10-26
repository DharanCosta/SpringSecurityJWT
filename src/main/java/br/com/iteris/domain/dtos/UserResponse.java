package br.com.iteris.domain.dtos;

import br.com.iteris.domain.entities.*;
import lombok.*;


import java.util.*;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private List<Role> roleList;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.roleList = user.getRoles();
    }
}
