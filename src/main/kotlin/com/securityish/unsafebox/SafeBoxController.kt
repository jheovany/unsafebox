package com.securityish.unsafebox

import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.UUID

@RestController
@RequestMapping("/safeboxes")
@Tag(name = "SafeBoxes")
class SafeBoxController {
    @PostMapping
    @Operation(
        summary = "Creates a new safebox",
        description = "Creates a new safebox based on a non-empty name and a password."
    )
    fun create(@RequestBody safeBoxValues: SafeBoxValues) : SafeBoxId {
        return SafeBoxId(UUID.randomUUID(), LocalDateTime.now())
    }

    @GetMapping("/{id}/items")
    @Operation(
        summary = "Retrieves the content of a safebox",
        description = "Retrieves the currently stored contents in the safebox identified by the given ID"
    )
    fun items() : List<Item> {
        return listOf(Item(1, "desc 1"))
    }

    @PutMapping("/{id}/items")
    @Operation(
        summary = "Add items to a Safebox",
        description = "Inserts new contents in the safebox identified by the given ID and with the given Basic Auth"
    )
    fun addItems(@RequestBody itemsDescriptions: List<String>) {

    }
}
