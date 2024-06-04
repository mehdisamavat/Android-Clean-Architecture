plugins {
    alias(libs.plugins.cleanArchitectureAndroidLib)
    alias(libs.plugins.cleanArchitectureAndroidKoin)

}

android {
    namespace = "com.example.androidcleanarchitecture.core.network"
}

dependencies {

    implementation(libs.androidx.annotation.jvm)
    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.data.model)

    api(libs.converter.gson)
    implementation(libs.retrofit)
    implementation(projects.core.common)
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)

}


