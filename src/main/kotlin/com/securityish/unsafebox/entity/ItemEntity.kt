package com.securityish.unsafebox.entity

import java.time.LocalDateTime
import jakarta.persistence.*

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
