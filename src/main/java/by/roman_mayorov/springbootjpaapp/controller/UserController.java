package by.roman_mayorov.springbootjpaapp.controller;

import by.roman_mayorov.springbootjpaapp.dto.UserDto;
import by.roman_mayorov.springbootjpaapp.entity.User;
import by.roman_mayorov.springbootjpaapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable Long id) {
        if (userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<Optional<User>> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        if (userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(userService.updateUser(userDto,id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "delete/{id}")
    public HttpStatus deleteUser(@PathVariable Long id) {
        if (userService.findUserById(id).isPresent()) {
            userService.deleteUserById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
