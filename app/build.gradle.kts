
plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.choppingblock_home"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.choppingblock_home"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'

    // Gson converter for Retrofit
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // Logging Interceptor for Retrofit
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0'

    // Gson library for parsing JSON
    implementation 'com.google.code.gson:gson:2.8.8'

    // Android dependencies from libs.toml
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Testing dependencies from libs.toml
    testImplementation(libs.junit) // JUnit
    androidTestImplementation(libs.ext.junit) // Ext JUnit for Android
    androidTestImplementation(libs.espresso.core) // Espresso UI testing

}