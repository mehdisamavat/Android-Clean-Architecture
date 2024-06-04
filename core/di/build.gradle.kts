plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
    alias(libs.plugins.cleanArchitectureAndroidKoin)
}
android {
    namespace = "com.example.androidcleanarchitecture.core.di"
}

dependencies {
    implementation(projects.data.product)
    implementation(projects.domain.product)
    implementation(projects.core.network)
    implementation(projects.presentation.products)

}
