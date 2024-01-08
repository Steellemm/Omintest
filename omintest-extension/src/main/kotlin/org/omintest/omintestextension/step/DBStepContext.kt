package org.omintest.omintestextension.step

import org.omintest.api.DataBaseStepContext

class DBStepContext(
    values: Map<String, Any>,
    private val url: String,
    private val user: String,
    private val password: String
) : DataBaseStepContext, SpringStepContext(values) {
    override fun getUrl(): String {
        return url
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUser(): String {
        return user
    }
}