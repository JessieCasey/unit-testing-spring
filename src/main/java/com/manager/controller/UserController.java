package com.manager.controller;

import com.manager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        log.info("[Get] Request to method 'getUserById'");
        try {
            return ResponseEntity.ok().body(userService.getUserById(id));
        } catch (Exception e) {
            log.error("Error in method 'getUserById': " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
