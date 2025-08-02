package com.securityish.unsafebox.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

enum class Role {
    USER, ADMIN
}

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val username: String,
    val password: String,
    val email: String,
    val createdOn: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER
)
