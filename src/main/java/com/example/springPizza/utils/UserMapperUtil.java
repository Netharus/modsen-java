package com.example.springPizza.utils;


import com.example.springPizza.models.enums.Role;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class UserMapperUtil {
    @Named("mapRoleToString")
    public String mapRoleToString(Role role) {
        return role.name();
    }
}
