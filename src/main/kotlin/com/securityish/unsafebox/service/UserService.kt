package com.securityish.unsafebox.service

import com.securityish.unsafebox.RegisterRequest
import com.securityish.unsafebox.UserRepository
import com.securityish.unsafebox.entity.Role
import com.securityish.unsafebox.entity.UserEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun createUser(registerRequest: RegisterRequest): UserEntity {
        val user = UserEntity(
            username = registerRequest.username,
            password = passwordEncoder.encode(registerRequest.password),
            email = registerRequest.email,
            role = Role.USER
        )
        return userRepository.save(user)
    }
}
