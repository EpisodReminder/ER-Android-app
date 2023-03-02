package ru.er.core

import android.content.res.AssetManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.InputStream


suspend inline fun <reified T> AssetManager.decode(json: Json, path: String): T = withContext(
    Dispatchers.IO) {
    val string = open(path).use(InputStream::readBytes).toString(Charsets.UTF_8)
    json.decodeFromString(string)
}