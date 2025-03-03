package org.test.api.graphql_api_test.service;

import org.springframework.stereotype.Service;
import org.test.api.graphql_api_test.dto.UserDTO;
import org.test.api.graphql_api_test.entity.UserEntity;
import org.test.api.graphql_api_test.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> listUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO getUser(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        assert user != null;
        return new UserDTO(user);
    }

    public void createUser(UserDTO userDTO) {
        UserEntity user = new UserEntity(userDTO);
        userRepository.save(user);
    }
}
