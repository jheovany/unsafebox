package com.securityish.unsafebox.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "safebox")
data class SafeBoxEntity(
    @Id
    val id: UUID,
    val name: String,
    val password: String,
    val createdOn: LocalDateTime,

    @OneToMany(mappedBy = "safeBox", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: List<ItemEntity> = emptyList()
)