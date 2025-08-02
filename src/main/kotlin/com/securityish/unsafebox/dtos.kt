package com.securityish.unsafebox

import java.time.LocalDateTime
import java.util.UUID

data class SafeBoxId(val id: UUID, val createdOn: LocalDateTime)

data class SafeBoxValues(val name: String, val password: String)

data class Item(val id: Int, val description: String, val createdOn: LocalDateTime)

data class UserValues(
    val username: String,
    val password: String,
)