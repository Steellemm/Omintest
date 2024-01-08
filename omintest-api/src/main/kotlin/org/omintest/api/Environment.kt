package org.omintest.api

import org.omintest.api.model.SpringBootServiceInfo

interface Environment {

    fun getServiceInfo(name: String): SpringBootServiceInfo
}