package org.dkv.dkvback.config.property

import org.dkv.dkvback.model.Module
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@ConfigurationProperties
data class ModuleProperties(
    val modules: List<Module> = ArrayList()
)