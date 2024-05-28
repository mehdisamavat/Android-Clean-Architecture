plugins {
    `kotlin-dsl`
}

group = "com.example.android_clean_architecture.buildlogic"


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {

        register("androidApplication") {
            id = "clean.architecture.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "clean.architecture.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("jvmLibrary") {
            id = "clean.architecture.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("androidKoin") {
            id = "clean.architecture.android.koin"
            implementationClass = "AndroidKoinConventionPlugin"
        }

        register("androidTest") {
            id = "clean.architecture.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }

        register("androidDetekt") {
            id = "clean.architecture.android.detekt"
            implementationClass = "AndroidDetektConventionPlugin"
        }

    }
}
