package dependencies

internal object Versions {

    const val okhttp = "3.11.0"
    const val retrofit = "2.4.0"
    const val supportLibrary = "1.0.0"
    const val jUnit4 = "4.12"
    const val assertJ = "2.9.1"
    const val mockitoKotlin = "2.0.0-RC3"
    const val mockitoDexMaker = "2.19.0"
    const val androidJUnit = "1.0.2"
    const val espressoCore = "3.0.2"
    const val roboletric = "4.0"
    const val kodeinDI = "5.3.0"
    const val timber = "4.7.1"
    const val livedata ="1.1.1"

}

object Dependencies {

    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${PluginsVersions.kotlin}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    val appCompat = "androidx.appcompat:appcompat:${Versions.supportLibrary}"
    val cardView = "androidx.cardview:cardview:${Versions.supportLibrary}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.supportLibrary}"
    val materialDesign = "com.google.android.material:material:${Versions.supportLibrary}"

    val kodein = "org.kodein.di:kodein-di-generic-jvm:${Versions.kodeinDI}"
    val kodeinConf = "org.kodein.di:kodein-di-conf-jvm:${Versions.kodeinDI}"
    val kodeinAndroid = "org.kodein.di:kodein-di-framework-android-x:${Versions.kodeinDI}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val livedataCore = "android.arch.lifecycle:livedata-core:${Versions.livedata}"
    val lifecycleExtension = "android.arch.lifecycle:extensions:${Versions.livedata}"

}

object TestDependencies {

    val jUnit = "junit:junit:${Versions.jUnit4}"
    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${PluginsVersions.kotlin}"
    val assertJ = "org.assertj:assertj-core:${Versions.assertJ}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoDexMaker = "com.linkedin.dexmaker:dexmaker-mockito:${Versions.mockitoDexMaker}"
    val androidTestRunner = "com.android.support.test:runner:${Versions.androidJUnit}"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espressoCore}"
    val roboletric = "org.robolectric:robolectric:${Versions.roboletric}"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}
