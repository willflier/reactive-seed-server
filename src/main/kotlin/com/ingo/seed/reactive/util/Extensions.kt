package com.ingo.seed.reactive.util

import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.permanentRedirect
import org.springframework.web.reactive.function.server.ServerResponse.seeOther
import java.net.URI
import java.nio.charset.Charset
import java.security.MessageDigest

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


// -------------------------
// Spring WebFlux extensions
// -------------------------

fun ServerRequest.locale(): Locale =
    this.headers().asHttpHeaders().contentLanguage ?: Locale.CHINESE

fun ServerResponse.BodyBuilder.json() = contentType(APPLICATION_JSON_UTF8)

fun ServerResponse.BodyBuilder.xml() = contentType(APPLICATION_XML)

fun ServerResponse.BodyBuilder.html() = contentType(TEXT_HTML)

fun permanentRedirect(uri: String) = permanentRedirect(URI(uri)).build()

// ----------------
// Other extensions
// ----------------
fun String.encodeToMd5(): String? = if (isNullOrEmpty()) null else hex(MessageDigest.getInstance("MD5").digest(toByteArray(Charset.forName("CP1252"))))

fun String.encodeToBase64(): String? = if (isNullOrEmpty()) null else Base64.getEncoder().encodeToString(toByteArray())

fun String.decodeFromBase64(): String? = if (isNullOrEmpty()) null else String(Base64.getDecoder().decode(toByteArray()))

fun String.encrypt(key: String, initVector: String): String? {
    try {
        val encrypted = cipher(key, initVector, Cipher.ENCRYPT_MODE).doFinal(toByteArray())
        return Base64.getEncoder().encodeToString(encrypted)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}

fun String.decrypt(key: String, initVector: String): String? {
    try {
        val encrypted = Base64.getDecoder().decode(toByteArray())
        return String(cipher(key, initVector, Cipher.DECRYPT_MODE).doFinal(encrypted))
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}

private fun cipher(key: String, initVector: String, mode: Int): Cipher {
    val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
    val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")

    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(mode, skeySpec, iv)
    return cipher
}

private fun hex(digested: ByteArray): String {
    val sb = StringBuffer()
    for (i in digested.indices) {
        val v = Integer.toHexString(((digested[i].toInt() and 0xFF) or 0x100))
        sb.append(v.substring(1, 3))
    }
    return sb.toString()
}
