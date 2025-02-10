package by.roman_mayorov.springbootjpaapp.service;

import by.roman_mayorov.springbootjpaapp.dto.UserDto;
import by.roman_mayorov.springbootjpaapp.entity.User;
import by.roman_mayorov.springbootjpaapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserDto userDto) {
    if (findUserByUsername(userDto.getUsername()).isPresent()) {
        throw new RuntimeException("Такой пользователь уже существует");
    }
        return userRepository.save(User.builder()
                        .username(userDto.getUsername())
                        .firstname(userDto.getFirstname())
                        .lastname(userDto.getLastname())
                        .build());
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(UserDto userDto, Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setUsername(userDto.getUsername());
            user.get().setFirstname(userDto.getFirstname());
            user.get().setLastname(userDto.getLastname());
        }
        return user;
    }

    public void deleteUserById(Long id) {
       userRepository.deleteById(id);
    }
}
