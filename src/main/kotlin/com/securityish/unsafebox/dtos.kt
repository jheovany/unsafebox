package com.securityish.unsafebox

import java.time.LocalDateTime
import java.util.UUID

data class SafeBoxId(val id: UUID, val createdOn: LocalDateTime)

data class SafeBoxValues(val name: String, val password: String)

data class Item(val id: Int, val description: String, val createdOn: LocalDateTime)

data class LoginRequest(
    val username: String,
    val password: String
)

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String
)

data class JwtResponse(
    val token: String,
    val type: String = "Bearer",
    val username: String,
    val email: String
)

data class MessageResponse(
    val message: String
)