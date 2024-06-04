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



    buildTypes {

        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        debug {
            versionNameSuffix = "-DEBUG"
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
        implementation(coil)
        implementation(androidx.navigation.ui.ktx)
        implementation(androidx.navigation.fragment.ktx)
        debugImplementation(chucker.library)
        releaseImplementation(chucker.library.no.op)
        implementation(androidx.core.ktx)
        implementation(androidx.appcompat)
        implementation(material)
        implementation(androidx.activity)
    }

    projects.apply {
        implementation(presentation.products)
        implementation(core.di)
        implementation(core.image)
        implementation(core.common)
    }

}