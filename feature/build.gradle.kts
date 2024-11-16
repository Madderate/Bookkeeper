// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
}

val dependenciesLibs = libs
val rootProjectName = rootProject.name
subprojects {
    this.group = "cn.bookkeeper.$rootProjectName"
    this.version = dependenciesLibs.feature.create("$rootProjectName.${this.name}").get().version!!
}
