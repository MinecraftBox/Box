package dev.minecraftbox.utils.file

import com.google.gson.GsonBuilder

val gson = GsonBuilder()
    .serializeNulls()
    .create()!!

inline fun <reified T> convert(json: String) = gson.fromJson<T>(json, T::class.java)!!