// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(pluginLibs.plugins.android.application) apply false
    alias(pluginLibs.plugins.android.library) apply false
    alias(pluginLibs.plugins.kotlin.android) apply false
    alias(pluginLibs.plugins.kotlin.jvm) apply false
    alias(pluginLibs.plugins.kotlin.kapt) apply false
    alias(pluginLibs.plugins.navigation.safeargs) apply false
    alias(pluginLibs.plugins.hilt) apply false
    // find latest version number here:
    // https://mvnrepository.com/artifact/com.google.protobuf/protobuf-gradle-plugin
    alias(pluginLibs.plugins.protobuf) apply false
    alias(pluginLibs.plugins.serialization) apply false
}

apply(plugin = "android-reporting")
