package com.securityish.unsafebox.controller

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/test")
@SecurityRequirement(name = "BearerAuth")
class TestController {
    @GetMapping("/public")
    fun publicAccess(): String {
        return "Contenido público"
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun userAccess(principal: Principal): String {
        return "Contenido para usuarios autenticados. Usuario: ${principal.name}"
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun adminAccess(principal: Principal): String {
        return "Contenido solo para administradores. Admin: ${principal.name}"
    }
}