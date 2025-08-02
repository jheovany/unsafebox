package com.securityish.unsafebox.controller

import com.securityish.unsafebox.UserValues
import com.securityish.unsafebox.security.JwtUtils
import com.securityish.unsafebox.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService,
    private val jwtUtils: JwtUtils
) {
    @PostMapping("/register")
    fun register(@RequestBody user: UserValues): String {
        userService.registerUser(user)
        return "User registered"
    }

    @PostMapping("/login")
    fun login(@RequestBody user: UserValues): String {
        return userService.loginUser(user)
    }
}
