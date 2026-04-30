package com.movie.booking.Controller;

import com.movie.booking.Entity.User;
import com.movie.booking.Repository.UserRepository;
import com.movie.booking.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwt;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return "Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = repo.findByUsername(user.getUsername()).orElseThrow();

        if (encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwt.generateToken(user.getUsername());
        }
        return "Invalid Login";
    }
}