plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
    alias(libs.plugins.cleanArchitectureAndroidKoin)
    alias(libs.plugins.cleanArchitectureAndroidTest)

}

android {
    namespace = "com.example.androidcleanarchitecture.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.recyclerview)


}

