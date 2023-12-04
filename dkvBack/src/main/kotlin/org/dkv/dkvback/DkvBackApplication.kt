package org.dkv.dkvback

import org.dkv.dkvback.config.property.ModuleProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ModuleProperties::class)
class DkvBackApplication

fun main(args: Array<String>) {
    runApplication<DkvBackApplication>(*args)
}
