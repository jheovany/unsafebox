package com.securityish.unsafebox.controller

import com.securityish.unsafebox.JwtResponse
import com.securityish.unsafebox.LoginRequest
import com.securityish.unsafebox.MessageResponse
import com.securityish.unsafebox.RegisterRequest
import com.securityish.unsafebox.util.JwtUtil
import com.securityish.unsafebox.service.UserPrincipal
import com.securityish.unsafebox.service.CustomUserDetailsService
import com.securityish.unsafebox.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val userService: UserService,
    private val jwtUtil: JwtUtil
) {
    @PostMapping("/login")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.username,
                    loginRequest.password
                )
            )

            val userDetails = userDetailsService.loadUserByUsername(loginRequest.username) as UserPrincipal
            val jwt = jwtUtil.generateToken(userDetails)

            ResponseEntity.ok(
                JwtResponse(
                    token = jwt,
                    username = userDetails.username,
                    email = userDetails.getUser().email
                )
            )
        } catch (e: BadCredentialsException) {
            ResponseEntity.badRequest().body(MessageResponse("Error: Credenciales inválidas!"))
        }
    }

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody registerRequest: RegisterRequest): ResponseEntity<MessageResponse> {
        if (userService.existsByUsername(registerRequest.username)) {
            return ResponseEntity.badRequest()
                .body(MessageResponse("Error: El nombre de usuario ya está en uso!"))
        }

        if (userService.existsByEmail(registerRequest.email)) {
            return ResponseEntity.badRequest()
                .body(MessageResponse("Error: El email ya está en uso!"))
        }

        userService.createUser(registerRequest)

        return ResponseEntity.ok(MessageResponse("Usuario registrado exitosamente!"))
    }
}
