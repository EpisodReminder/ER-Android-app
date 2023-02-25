package ru.er.data.auth

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.er.domain.session.LoginInformationDeviceRepository
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInformationDataSource @Inject constructor(
    @ApplicationContext context: Context
) : LoginInformationDeviceRepository {


    private val masterKeyAlias: MasterKey by lazy {
        val spec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()

        MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()
    }

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = safeCreateSharedPrefs(context)
    }

    override fun getLogin(): String? = sharedPreferences.getString(LOGIN, null)

    override fun getPassword(): String? = sharedPreferences.getString(PASSWORD, null)

    override fun putLogin(login: String) = sharedPreferences.edit().putString(LOGIN, login).apply()

    override fun putPassword(password: String) =
        sharedPreferences.edit().putString(PASSWORD, password).apply()

    override fun removeLogin() = sharedPreferences.edit().remove(LOGIN).apply()

    override fun removePassword() = sharedPreferences.edit().remove(PASSWORD).apply()

    private fun safeCreateSharedPrefs(context: Context): SharedPreferences {
        return try {
            createSharedPrefs(context)
        } catch (expected: RuntimeException) {
            val dir = File(context.filesDir?.parent + "/shared_prefs/")
            File(dir, "$SHARED_PREFERENCES_NAME.xml").delete()

            createSharedPrefs(context)
        }
    }

    private fun createSharedPrefs(context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            SHARED_PREFERENCES_NAME,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    companion object {
        const val LOGIN = "LOGIN"
        const val PASSWORD = "PASSWORD"
        private const val SHARED_PREFERENCES_NAME = "EncryptedAuthInformation"
    }
}