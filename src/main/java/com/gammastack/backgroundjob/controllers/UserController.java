package com.gammastack.backgroundjob.controllers;

import com.gammastack.backgroundjob.entities.User;
import com.gammastack.backgroundjob.repositries.UserRepository;
import com.gammastack.backgroundjob.services.UserJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserJob userJob;

  @PostMapping("/register")
  public String registerUser(@RequestBody User user) {
    User savedUser = userRepository.save(user);
    userJob.sendWelcomeEmail();
    userJob.sendVerificationEmail();
    userJob.sendReviewReminderEmail();
    return savedUser.toString();
  }

  @GetMapping("/{id}")
  public String getUserById(@PathVariable int id) {
    User user = userRepository.findById(id);
    return user.toString();
  }

  @GetMapping("/count")
  public int getUserCount() {
    return (int) userRepository.count();
  }
}