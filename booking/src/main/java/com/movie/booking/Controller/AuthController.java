package com.movie.booking.Controller;

import com.movie.booking.Entity.User;
import com.movie.booking.Repository.UserRepository;
import com.movie.booking.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        // 🔥 CHECK DUPLICATE USERNAME
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);

        return "Registered Successfully";
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        Optional<User> userOpt = repo.findByUsername(user.getUsername());

        // 🔥 CHECK USER EXISTS
        if (userOpt.isEmpty()) {
            return "User not found";
        }

        User dbUser = userOpt.get();


        if (encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwt.generateToken(user.getUsername());
        }

        return "Invalid Password";
    }
}