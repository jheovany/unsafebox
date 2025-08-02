package com.securityish.unsafebox.service

import com.securityish.unsafebox.UserRepository
import com.securityish.unsafebox.UserValues
import com.securityish.unsafebox.model.UserEntity
import com.securityish.unsafebox.security.JwtUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils
) {
    fun registerUser(user: UserValues): String {
        if (userRepository.existsByUsername(user.username)) {
            return "Username already exists"
        }
        val user = UserEntity(
            username = user.username.trim(),
            password = passwordEncoder.encode(user.password.trim())
        )
        userRepository.save(user)
        return "User registered successfully"
    }

    fun loginUser(user: UserValues): String {
        val auth = UsernamePasswordAuthenticationToken(user.username, user.password)
        authenticationManager.authenticate(auth)
        val token = jwtUtils.generateToken(user.username)
        return token
    }
}
