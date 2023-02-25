plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.er.reminder"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://episod-reminder.ru/rest/v1\"")
        buildConfigField("String", "TELEGRAM_BOT_URL", "\"https://t.me/milka_production\"")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "ru.er.reminder"
}

dependencies {
    implementation(project(Modules.login))

    baseImpl()
    androidNavigation()
    constraintLayout()
    androidHilt()
    workManager()
    implementation(project(Modules.core))
    implementation(project(Modules.core_ui))
    implementation(project(Modules.utils))
    implementation(project(Modules.data))
    implementation(project(Modules.domain))

}