plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
    alias(libs.plugins.cleanArchitectureAndroidKoin)
}

android {
    namespace = "com.example.androidcleanarchitecture.core.image"
}

dependencies {
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.coil)
}

