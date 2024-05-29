plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
    alias(libs.plugins.cleanArchitectureAndroidKoin)
    alias(libs.plugins.cleanArchitectureAndroidTest)

}
android {
    namespace = "com.example.androidcleanarchitecture.domain.product"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.data.product)
    api(projects.domain.model)
}
