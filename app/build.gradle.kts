
plugins {
    alias(pluginLibs.plugins.android.application)
    alias(pluginLibs.plugins.kotlin.android)
    alias(pluginLibs.plugins.kotlin.kapt)
    alias(pluginLibs.plugins.navigation.safeargs)
    alias(pluginLibs.plugins.hilt)
}

android {
    compileSdk = sdk.versions.compileSdk.get().toInt()
    ndkVersion = "25.2.9519653"

    namespace = "com.vshpyrka.playground"

    defaultConfig {
        applicationId = "com.vshpyrka.playground"
        minSdk = sdk.versions.minSdk.get().toInt()
        targetSdk = sdk.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            enableAndroidTestCoverage = true
            isPseudoLocalesEnabled = true
        }
    }

    androidResources {
        generateLocaleConfig = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        animationsDisabled = true
        unitTests {
            isIncludeAndroidResources = true

            all { test ->
                with(test) {
                    testLogging {
                        events = setOf(
                            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                            org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
                            org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR,
                        )
                    }
                }
            }
        }
    }

    packaging { resources.excludes.add("META-INF/versions/9/previous-compilation-data.bin") }

    lint {
        baseline = file("lint-baseline.xml")
    }
}

kapt {
    // https://kotlinlang.org/docs/reference/kapt.html#non-existent-type-correction
    correctErrorTypes = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":navigation-feature-info"))
    implementation(project(":navigation-feature-settings"))
    implementation(project(":android-jni-example"))
    implementation(project(":android-datastore-example"))
    implementation(project(":android-mediaprojection-example"))
    implementation(project(":android-navigation-example"))
    implementation(project(":android-predictive-back-example"))
    implementation(project(":android-shortcuts-example"))
    implementation(project(":android-documents-example"))
    implementation(project(":android-workmanager-example"))
    implementation(project(":android-embedded-activity-example"))
    implementation(project(":android-slidingpanelayout-example"))
    implementation(project(":android-viewmodel-example"))
    implementation(project(":android-resource-types-example"))
    implementation(project(":android-per-app-language-example"))
    implementation(project(":android-custom-view-group-example"))
    implementation(project(":android-wrap-children-group-example"))
    implementation(project(":android-custom-progress-view-example"))
    implementation(project(":android-degree-picker-view-example"))
    implementation(project(":android-picture-in-picture-example"))
    implementation(project(":android-compose-playground"))
    implementation(project(":android-compose-glance-widget-example"))

    implementation(libs.coroutines)
    implementation(libs.coroutines.android)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)

    implementation(libs.constraintlayout)
    implementation(libs.work.manager.runtime.ktx)

    implementation(libs.material)
    implementation(libs.transitions)

    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.extensions)

    implementation(libs.startup)

    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)

    implementation(libs.security.crypto)

    implementation(libs.window)
    implementation(libs.swiperefreshlayout)
    implementation(libs.paging.runtime)

    implementation(libs.shortcuts)

    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)

    debugImplementation(testLibs.fragment.testing.manifest)

    testImplementation(testLibs.coroutines.test)
    testImplementation(testLibs.core.testing)
    testImplementation(testLibs.android.junit.ktx)
    testImplementation(testLibs.truth)
    testImplementation(testLibs.mockk)
    testImplementation(testLibs.paging.common)

    androidTestImplementation(testLibs.fragment.testing)
    androidTestImplementation(testLibs.coroutines.test)
    androidTestImplementation(testLibs.core.ktx)
    androidTestImplementation(testLibs.navigation.testing)
    androidTestImplementation(testLibs.espresso)
    androidTestImplementation(testLibs.espresso.device)
    androidTestImplementation(testLibs.android.junit.ktx)
    androidTestImplementation(testLibs.espresso.contrib) {
        exclude(group = "org.checkerframework", module = "checker")
        exclude(module = "protobuf-lite")
    }
    androidTestImplementation(testLibs.espresso.intents)
    androidTestImplementation(testLibs.test.runner)
    androidTestImplementation(testLibs.test.rules)
    androidTestImplementation(testLibs.uiautomator)
    androidTestImplementation(testLibs.truth)

}
