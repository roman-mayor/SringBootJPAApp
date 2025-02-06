package by.roman_mayorov.springbootjpaapp.service;

import by.roman_mayorov.springbootjpaapp.dto.UserDto;
import by.roman_mayorov.springbootjpaapp.entity.User;
import by.roman_mayorov.springbootjpaapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void createUserTest() {
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());

        User user1 = userService.createUser(new UserDto());

        Assertions.assertEquals(new User(), user1);
    }

    @Test
    void getAllUsersTest() {
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<User> users = userService.getAllUsers();

        Assertions.assertEquals(new ArrayList<User>(), users);
    }

    @Test
    void findUserByIdTest(){
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));

        Optional<User> user = userService.findUserById(1L);

        Assertions.assertTrue(user.isPresent());
    }

    @Test
    void updateUserTest() {
        UserDto userDto = new UserDto();
        userDto.setUsername("username");
        userDto.setFirstname("firstname");
        userDto.setLastname("lastname");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));

        Optional<User> user1 = userService.updateUser(userDto, 1L);

        Assertions.assertTrue(user1.isPresent());
        Assertions.assertEquals(userDto.getUsername(), user1.get().getUsername());
        Assertions.assertEquals(userDto.getFirstname(), user1.get().getFirstname());
        Assertions.assertEquals(userDto.getLastname(), user1.get().getLastname());
    }

    @Test
    void deleteUserByIdTest() {

        userService.deleteUserById(1L);

        Optional<User> user = userService.findUserById(1L);

        Assertions.assertFalse(user.isPresent());
    }
}