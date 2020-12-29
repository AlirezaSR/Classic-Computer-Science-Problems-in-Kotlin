package chapter01

import java.util.*
import kotlin.experimental.xor


data class KeyPair (
    val key1: ByteArray,
    val key2: ByteArray
)


// Generate *length* random bytes
private fun randomKey(length: Int): ByteArray {
    val dummy = ByteArray(length)
    val random = Random()
    random.nextBytes(dummy)
    return dummy
}

fun encrypt(original: String): KeyPair {
    val originalBytes = original.toByteArray()
    val dummyKey = randomKey(originalBytes.size)
    val encryptedKey = ByteArray(originalBytes.size)
    for (i in originalBytes.indices) {
        // XOR every byte
        encryptedKey[i] = originalBytes[i] xor dummyKey[i]
    }
    return KeyPair(dummyKey, encryptedKey)
}

fun decrypt(kp: KeyPair): String {
    val decrypted = ByteArray(kp.key1.size)
    for (i in kp.key1.indices) {
        // XOR every byte
        decrypted[i] = kp.key1[i] xor kp.key2[i]
    }
    return String(decrypted)
}


fun main(args: Array<String>) {
    val kp = encrypt("One Time Pad!")
    val result = decrypt(kp)
    println(result)
}


