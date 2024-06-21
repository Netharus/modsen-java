package com.example.springPizza.mappers;

import com.example.springPizza.database.models.User;
import com.example.springPizza.mappers.dtos.UserLoginRequest;
import com.example.springPizza.mappers.dtos.UserRequest;
import com.example.springPizza.mappers.dtos.UserResponse;
import com.example.springPizza.utils.UserMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = UserMapperUtil.class
)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toModel(UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toModel(UserLoginRequest userLoginRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateModel(UserRequest userRequest, @MappingTarget User user);

    @Mapping(target = "role", qualifiedByName = "mapRoleToString", source = "role")
    UserResponse toResponse(User user);

    @Mapping(target = "role", qualifiedByName = "mapRoleToString", source = "role")
    List<UserResponse> toResponses(List<User> users);
}
