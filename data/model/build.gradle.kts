plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
    alias(libs.plugins.cleanArchitectureAndroidKoin)
    alias(libs.plugins.cleanArchitectureAndroidTest)

}

android {
    namespace = "com.example.androidcleanarchitecture.data.model"
}

dependencies {
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    api(libs.converter.gson)


}
