enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.com/") }
    }
}

rootProject.name = "Android_Clean_Architecture"
include(":app")

include(":data:product")
include(":data:model")

include(":domain:product")
include(":domain:model")
include(":core:image")
include(":core:common")
include(":core:network")
include(":presentation:products")
include(":core:di")
