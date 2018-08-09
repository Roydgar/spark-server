package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.model.entity.user.User;
import tk.roydgar.model.entity.temporary.LoginData;
import tk.roydgar.model.service.UserService;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @GetMapping("user/{userId}")
    public ResponseEntity<?> findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("user/login/{clientId}}")
    public ResponseEntity<?> login(@RequestBody LoginData loginData, @PathVariable Long clientId) {
        return userService.login(loginData, clientId);
    }

    @PostMapping("user/register/{clientId}")
    public ResponseEntity<?> register(@RequestBody User user, @PathVariable Long clientId) {
        return userService.register(user, clientId);
    }

    @GetMapping("user/confirmation/{userId}/{hash}")
    public ResponseEntity<?> confirmation(@PathVariable Long userId, @PathVariable String hash) {
        return userService.confirmEmail(userId, hash);
    }

}

