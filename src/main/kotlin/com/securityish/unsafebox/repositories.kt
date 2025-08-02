package com.securityish.unsafebox

import com.securityish.unsafebox.model.ItemEntity
import com.securityish.unsafebox.model.SafeBoxEntity
import com.securityish.unsafebox.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface SafeBoxRepository : JpaRepository<SafeBoxEntity, UUID>

interface ItemRepository : JpaRepository<ItemEntity, Int> {
    fun findAllBySafeBoxId(safeBoxId: UUID): List<ItemEntity>
}

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): Optional<UserEntity>
    fun existsByUsername(username: String): Boolean
}
