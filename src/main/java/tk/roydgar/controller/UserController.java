package tk.roydgar.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.model.entity.User;
import tk.roydgar.model.entity.temporary.LoginData;
import tk.roydgar.model.service.UserService;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @GetMapping("user/{userId}")
    public @ResponseBody User findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("user/login")
    public @ResponseBody User login(@RequestBody LoginData loginData) {
        return userService.login(loginData);
    }

    @PostMapping("user/register")
    public @ResponseBody User register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("user/confirmation/{userId}/{hash}")
    public @ResponseBody User confirmation(@PathVariable Long userId, @PathVariable String hash) {
        return userService.confirmEmail(userId, hash);
    }

}

