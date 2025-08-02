package com.securityish.unsafebox.controller

import com.securityish.unsafebox.Item
import com.securityish.unsafebox.SafeBoxId
import com.securityish.unsafebox.service.SafeBoxService
import com.securityish.unsafebox.SafeBoxValues
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/safeboxes")
@Tag(name = "SafeBoxes")
class SafeBoxController(private val safeBoxService: SafeBoxService) {
    @PostMapping
    @Operation(
        summary = "Creates a new safebox",
        description = "Creates a new safebox based on a non-empty name and a password."
    )
    fun create(@RequestBody safeBoxValues: SafeBoxValues): SafeBoxId {
        return safeBoxService.createSafeBox(safeBoxValues)
    }

    @GetMapping("/{id}/items")
    @Operation(
        summary = "Retrieves the content of a safebox",
        description = "Retrieves the currently stored contents in " +
                "the safebox identified by the given ID"
    )
    fun items(@PathVariable("id") safeBoxId: UUID): List<Item> {
        return safeBoxService.listItems(safeBoxId)
    }

    @PutMapping("/{id}/items")
    @Operation(
        summary = "Add items to a Safebox",
        description = "Inserts new contents in the safebox identified by " +
                "the given ID and with the given Basic Auth"
    )
    fun addItems(
        @PathVariable("id") safeBoxId: UUID,
        @RequestBody itemsDescriptions: List<String>
    ): Int {
        return safeBoxService.addItemToSafeBox(safeBoxId, itemsDescriptions)
    }
}