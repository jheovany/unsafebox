package com.securityish.unsafebox

import com.securityish.unsafebox.entity.ItemEntity
import com.securityish.unsafebox.entity.SafeBoxEntity
import com.securityish.unsafebox.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SafeBoxRepository : JpaRepository<SafeBoxEntity, UUID>

interface ItemRepository : JpaRepository<ItemEntity, Int> {
    fun findAllBySafeBoxId(safeBoxId: UUID): List<ItemEntity>
}

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}
