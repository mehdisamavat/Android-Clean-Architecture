plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
    alias(libs.plugins.cleanArchitectureAndroidKoin)
    alias(libs.plugins.cleanArchitectureAndroidTest)

}

android {
    namespace = "com.example.androidcleanarchitecture.data.product"
}

dependencies {
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.kotlinx.coroutines.android)
    api(projects.data.model)
}
