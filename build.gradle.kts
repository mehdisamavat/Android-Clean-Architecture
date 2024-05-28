// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    libs.plugins.apply {
        alias(org.jetbrains.kotlin.jvm) apply false
        alias(jetbrains.kotlin.android) apply false
        alias(android.application) apply false
        alias(kotlinParcelize) apply false
        alias(androidLibrary) apply false
        alias(detekt) apply false
        alias(ksp) apply false
    }
}
