import Libraries.api
import Libraries.atImpl
import Libraries.dImpl
import Libraries.impl
import Libraries.kapt
import Libraries.tImpl
import org.gradle.api.artifacts.dsl.DependencyHandler

object Libraries {

    const val impl = "implementation"
    const val kapt = "kapt"
    const val api = "api"
    const val tImpl = "testImplementation"
    const val dImpl = "debugImplementation"
    const val atImpl = "androidTestImplementation"

    object Versions {
        const val dagger = "2.42"
        const val hilt = "1.0.0"
        const val recyclerView = "1.2.1"
        const val recyclerViewSelection = "1.1.0"
        const val workManager = "2.7.1"
        const val navigation = "2.5.0"
        const val junit = "4.13.2"
        const val xJunit = "1.1.3"
        const val espresso = "3.4.0"
        const val xCore = "1.9.0"
        const val appcompat = "1.4.2"
        const val material = "1.6.1"
        const val constraintLayout = "2.1.4"
        const val swipeRefreshLayout = "1.1.0"
        const val lifecycle = "2.5.0"
        const val ktor = "1.6.7"
        const val okHttp = "4.9.3"
        const val coroutines = "1.6.1"
        const val serialization = "1.3.2"
        const val room = "2.4.2"
        const val security = "1.1.0-alpha03"
        const val bindingDelegate = "1.5.6"
        const val leakcanary = "2.9.1"
        const val treeView = "1.2.0"
        const val treeView2 = "1.0.0"
        const val mdReader = "1.1.1"
    }


    object Hilt {
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
        const val hiltWork = "androidx.hilt:hilt-work:${Versions.hilt}"
    }

    object Recycler {
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val recyclerSelection = "androidx.recyclerview:recyclerview-selection:${Versions.recyclerViewSelection}"
    }


    object Navigation {
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val navigationCommon = "androidx.navigation:navigation-common-ktx:${Versions.navigation}"
        const val navigationDynamicFeatures = "androidx.navigation:navigation-dynamic-features-fragment:" +
                Versions.navigation
    }

    object AndroidTest {
        const val junit = "junit:junit:${Versions.junit}"
        const val testJunit = "androidx.test.ext:junit:${Versions.xJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object BaseImplement {
        const val coreKtx = "androidx.core:core-ktx:${Versions.xCore}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object UiElements {
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val process = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
    }

    object Ktor {
        const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val clientOkhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    }

    object OkHttp {
        const val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    }

    object Kotlin {
        const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    }

    object DaoRoom {
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Security {
        const val securityCrypto = "androidx.security:security-crypto-ktx:${Versions.security}"
    }

    object ViewBindingDelegate {
        const val viewBindingPropertyDelegate = "com.github.kirich1409:viewbindingpropertydelegate:" +
                Versions.bindingDelegate
    }

    object Leakcanary {
        const val leakScanner = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    }

    object RecycleTreeView {
        const val recyclerTreeView = "com.github.TellH:RecyclerTreeView:${Versions.treeView}"
    }

    object UpdatedTreeView {
        const val treeView = "io.github.amrdeveloper:treeview:${Versions.treeView2}"
    }

    object MarkDownReader {
        const val markDownReader = "com.github.mukeshsolanki:MarkdownView-Android:${Versions.mdReader}"
    }
}

fun DependencyHandler.baseImpl() {
    add(impl, Libraries.BaseImplement.coreKtx)
    add(impl, Libraries.BaseImplement.appcompat)
    add(impl, Libraries.BaseImplement.material)
    baseAndroidTest()
}


fun DependencyHandler.androidHilt() {
    add(kapt, Libraries.Hilt.hiltCompiler)
    add(impl, Libraries.Hilt.hiltAndroid)
    add(kapt, Libraries.Hilt.hiltAndroidCompiler)
    add(impl, Libraries.Hilt.hiltWork)
}

fun DependencyHandler.recycler() {
    add(impl, Libraries.Recycler.recyclerView)
    add(impl, Libraries.Recycler.recyclerSelection)
}

fun DependencyHandler.androidNavigation() {
    add(impl, Libraries.Navigation.navigationFragment)
    add(impl, Libraries.Navigation.navigationUi)
    add(impl, Libraries.Navigation.navigationCommon)
    add(impl, Libraries.Navigation.navigationDynamicFeatures)
}

fun DependencyHandler.baseAndroidTest() {
    add(tImpl, Libraries.AndroidTest.junit)
    add(atImpl, Libraries.AndroidTest.testJunit)
    add(atImpl, Libraries.AndroidTest.espresso)
}

fun DependencyHandler.constraintLayout() {
    add(impl, Libraries.UiElements.constraintLayout)
}

fun DependencyHandler.swipeRefreshLayout() {
    add(impl, Libraries.UiElements.swipeRefreshLayout)
}

fun DependencyHandler.lifecycleViewModel() {
    add(impl, Libraries.Lifecycle.viewModel)
}

fun DependencyHandler.lifecycleApi() {
    add(api, Libraries.Lifecycle.runtime)
    add(api, Libraries.Lifecycle.process)
}

fun DependencyHandler.ktor() {
    add(impl, Libraries.Ktor.clientCore)
    add(impl, Libraries.Ktor.clientAndroid)
    add(api, Libraries.Ktor.clientOkhttp)
    add(api, Libraries.Ktor.clientSerialization)
}

fun DependencyHandler.okHttp() {
    add(impl, Libraries.OkHttp.okHttp3)
}

fun DependencyHandler.androidKotlin() {
    add(api, Libraries.Kotlin.kotlinCoroutines)
    add(api, Libraries.Kotlin.kotlinSerialization)
}

fun DependencyHandler.daoRoom() {
    add(impl, Libraries.DaoRoom.roomRuntime)
    add(impl, Libraries.DaoRoom.roomKtx)
    add(kapt, Libraries.DaoRoom.roomCompiler)
}

fun DependencyHandler.bindingDelegate() {
    add(impl, Libraries.ViewBindingDelegate.viewBindingPropertyDelegate)
}
