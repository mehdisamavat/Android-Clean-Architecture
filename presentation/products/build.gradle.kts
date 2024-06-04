plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
    alias(libs.plugins.cleanArchitectureAndroidKoin)
    alias(libs.plugins.cleanArchitectureAndroidTest)
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "com.example.androidcleanarchitecture.presentation.products"
    viewBinding.isEnabled = true

}

dependencies {
    implementation(projects.domain.product)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(projects.core.common)
    implementation(projects.core.image)

}

