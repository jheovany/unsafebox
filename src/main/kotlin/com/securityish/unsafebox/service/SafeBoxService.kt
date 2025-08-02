package com.securityish.unsafebox.service

import com.securityish.unsafebox.Item
import com.securityish.unsafebox.ItemRepository
import com.securityish.unsafebox.SafeBoxId
import com.securityish.unsafebox.SafeBoxRepository
import com.securityish.unsafebox.SafeBoxValues
import com.securityish.unsafebox.entity.ItemEntity
import com.securityish.unsafebox.entity.SafeBoxEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class SafeBoxService(
    private val safeBoxRepository: SafeBoxRepository,
    private val itemRepository: ItemRepository
) {
    fun createSafeBox(safeBoxValues: SafeBoxValues): SafeBoxId {
        val safeBox = SafeBoxEntity(
            id = UUID.randomUUID(),
            name = safeBoxValues.name,
            password = safeBoxValues.password,
            createdOn = LocalDateTime.now()
        )
        val safeBoxEntity = safeBoxRepository.save(safeBox)
        return SafeBoxId(safeBoxEntity.id, safeBoxEntity.createdOn)
    }

    fun addItemToSafeBox(safeBoxId: UUID, descriptions: List<String>): Int {
        val safeBox = safeBoxRepository.findById(safeBoxId)
            .orElseThrow { IllegalArgumentException("SafeBox not found") }

        val items = mutableListOf<ItemEntity>()

        for (desc in descriptions) {
            val item = ItemEntity(
                safeBox = safeBox,
                description = desc,
                createdOn = LocalDateTime.now()
            )
            items.add(item)
        }

        itemRepository.saveAll(items)

        return items.size
    }

    fun listItems(safeBoxId: UUID): List<Item> {
        val items = itemRepository.findAllBySafeBoxId(safeBoxId)
        return items.map {
            it ->
            Item(it.id, it.description, it.createdOn)
        }
    }
}