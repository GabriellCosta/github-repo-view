package dependencies

internal object Kotlin {
    const val version = "1.3.10"
}

internal object Gradle {
    const val androidGradlePlugin = "3.2.1"
}


object BuildPlugins {

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Gradle.androidGradlePlugin}"

}