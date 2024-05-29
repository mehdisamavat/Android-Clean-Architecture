// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
        alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
        alias(libs.plugins.jetbrains.kotlin.android) apply false
        alias(libs.plugins.android.application) apply false
        alias(libs.plugins.kotlinParcelize) apply false
        alias(libs.plugins.androidLibrary) apply false
        alias(libs.plugins.detekt) apply false
        alias(libs.plugins.ksp) apply false
}
