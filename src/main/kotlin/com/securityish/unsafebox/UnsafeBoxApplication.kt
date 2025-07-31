package com.securityish.unsafebox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UnsafeBoxApplication

fun main(args: Array<String>) {
	runApplication<UnsafeBoxApplication>(*args)
}
