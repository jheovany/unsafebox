package com.securityish.unsafebox

import java.time.LocalDateTime
import java.util.UUID
import jakarta.persistence.*

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

@Entity
@Table(name = "item")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "safebox_id", nullable = false)
    val safeBox: SafeBoxEntity,

    val description: String,

    val createdOn: LocalDateTime
)
