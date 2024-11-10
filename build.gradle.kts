// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version libs.versions.agp apply false
    id("com.android.library") version libs.versions.agp apply false
}
repositories {
    google()
    mavenCentral()
    gradlePluginPortal() // This makes the plugin work
}