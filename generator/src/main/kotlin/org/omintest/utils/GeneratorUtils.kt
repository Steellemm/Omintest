package org.omintest.utils

import com.google.gson.Gson

val gson = Gson()

inline fun <reified T> Map<String, Any?>?.getDataClass(): T = gson.fromJson(gson.toJson(this), T::class.java) ?:
T::class.java.getConstructor().newInstance()