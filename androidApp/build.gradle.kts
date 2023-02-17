plugins {
    id("com.android.application")
    kotlin("android")
    id("com.apollographql.apollo").version("2.5.10")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.kmm_apollo.android"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc02"
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("androidx.work:work-runtime-ktx:2.7.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")

    // Compose
    implementation("androidx.compose.compiler:compiler:1.1.0-rc02")
    implementation("androidx.compose.ui:ui:1.0.5")
    implementation("androidx.compose.ui:ui-graphics:1.0.5")
    implementation("androidx.compose.ui:ui-tooling:1.0.5")
    implementation("androidx.compose.foundation:foundation-layout:1.0.5")
    implementation("androidx.compose.material:material:1.0.5")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.5")
    implementation("androidx.navigation:navigation-compose:2.4.0-rc01")
    implementation("androidx.paging:paging-compose:1.0.0-alpha14")

    //Coil
    implementation("io.coil-kt:coil-compose:1.3.2")

    implementation("com.apollographql.apollo:apollo-runtime-kotlin:2.5.10")
    // Koin main features for Android
    implementation("io.insert-koin:koin-android:3.1.4")
    // No more koin-android-viewmodel, koin-android-scope, koin-android-fragment

    // Java Compatibility
    implementation("io.insert-koin:koin-android-compat:3.1.4")
    // Jetpack WorkManager
    implementation("io.insert-koin:koin-androidx-workmanager:3.1.4")
    // Navigation Graph
    implementation("io.insert-koin:koin-androidx-navigation:3.1.4")
    // Jetpack Compose
    implementation("io.insert-koin:koin-androidx-compose:3.1.4")
}
