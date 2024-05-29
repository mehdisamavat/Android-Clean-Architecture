plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
}
android {
    namespace = "com.example.androidcleanarchitecture.model"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}

