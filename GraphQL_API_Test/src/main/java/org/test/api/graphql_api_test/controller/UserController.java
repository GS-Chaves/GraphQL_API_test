package org.test.api.graphql_api_test.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.test.api.graphql_api_test.dto.UserDTO;
import org.test.api.graphql_api_test.graphql.UserSubscription;
import org.test.api.graphql_api_test.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserSubscription userSubscription;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userSubscription = new UserSubscription();
    }

    @QueryMapping
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }

    @QueryMapping
    public UserDTO getUser(@Argument Long id) {
        return userService.getUser(id);
    }

    @MutationMapping
    public UserDTO createUser(@Argument String name, @Argument String email, @Argument String password) {
        UserDTO userDTO = new UserDTO(null, name, email, password);
        userService.createUser(userDTO);
        userSubscription.publish(userDTO);
        return userDTO;
    }
}
