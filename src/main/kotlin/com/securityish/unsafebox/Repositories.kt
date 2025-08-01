package com.securityish.unsafebox

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SafeBoxRepository : JpaRepository<SafeBoxEntity, UUID>

interface ItemRepository : JpaRepository<ItemEntity, Int> {
    fun findAllBySafeBoxId(safeBoxId: UUID): List<ItemEntity>
}