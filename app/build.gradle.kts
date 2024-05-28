plugins {
    alias(libs.plugins.cleanArchitectureAndroidApplication)
    alias(libs.plugins.cleanArchitectureAndroidKoin)
    alias(libs.plugins.cleanArchitectureAndroidTest)
}

android {
    namespace = libs.versions.applicationId.get()
    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding.isEnabled = true

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    libs.apply {
        implementation(material)
        implementation(androidx.appcompat)
        implementation(androidx.constraintlayout)
        implementation(koin.android)
        implementation(koin.core)
        debugImplementation(leakCanary)
        debugImplementation(chucker.library)
        releaseImplementation(chucker.library.no.op)
    }

}