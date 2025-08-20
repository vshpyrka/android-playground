pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        mavenLocal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()

        val gprLocalUser: String by settings
        val gprLocalKey: String by settings
        val gprUser = System.getenv("GITHUB_USER") ?: gprLocalUser
        val gprPassword = System.getenv("GITHUB_TOKEN") ?: gprLocalKey

        maven {
            name = "android-version-catalog"
            url = uri("https://maven.pkg.github.com/vshpyrka/android-version-catalog")
            credentials {
                username = gprUser
                password = gprPassword
            }
        }
    }

    versionCatalogs {
        create("pluginLibs") {
            from("com.vshpyrka.android:version-catalog-plugins:latest.release")
        }
        create("libs") {
            from("com.vshpyrka.android:version-catalog-libs:latest.release")
        }
        create("testLibs") {
            from("com.vshpyrka.android:version-catalog-test:latest.release")
        }
        create("sdk") {
            from("com.vshpyrka.android:version-catalog-sdk:latest.release")
        }
    }
}

rootProject.name="Android Playground"

include(":app")


include(":android-jni-example")
include(":android-datastore-example")
include(":android-mediaprojection-example")
include(":android-navigation-example")
include(":navigation-feature-info")
include(":navigation-feature-settings")
include(":android-predictive-back-example")
include(":android-shortcuts-example")
include(":android-documents-example")
include(":android-workmanager-example")
include(":android-embedded-activity-example")
include(":android-slidingpanelayout-example")
include(":android-viewmodel-example")
include(":android-resource-types-example")
include(":android-per-app-language-example")
include(":android-custom-view-group-example")
include(":android-wrap-children-group-example")
include(":android-custom-progress-view-example")
include(":android-degree-picker-view-example")
include(":android-picture-in-picture-example")
include(":android-compose-playground")
include(":android-compose-glance-widget-example")

project(":android-jni-example").projectDir = File("app/libs/android-jni-example")
project(":android-datastore-example").projectDir = File("app/libs/android-datastore-example")
project(":android-mediaprojection-example").projectDir = File("app/libs/android-mediaprojection-example")
project(":android-navigation-example").projectDir = File("app/libs/android-navigation-example")
project(":navigation-feature-info").projectDir = File("app/libs/android-navigation-example/libs/navigation-feature-info")
project(":navigation-feature-settings").projectDir = File("app/libs/android-navigation-example/libs/navigation-feature-settings")
project(":android-predictive-back-example").projectDir = File("app/libs/android-predictive-back-example")
project(":android-shortcuts-example").projectDir = File("app/libs/android-shortcuts-example")
project(":android-documents-example").projectDir = File("app/libs/android-documents-example")
project(":android-workmanager-example").projectDir = File("app/libs/android-workmanager-example")
project(":android-embedded-activity-example").projectDir = File("app/libs/android-embedded-activity-example")
project(":android-slidingpanelayout-example").projectDir = File("app/libs/android-slidingpanelayout-example")
project(":android-viewmodel-example").projectDir = File("app/libs/android-viewmodel-example")
project(":android-resource-types-example").projectDir = File("app/libs/android-resource-types-example")
project(":android-per-app-language-example").projectDir = File("app/libs/android-per-app-language-example")
project(":android-custom-view-group-example").projectDir = File("app/libs/android-custom-view-group-example")
project(":android-wrap-children-group-example").projectDir = File("app/libs/android-wrap-children-group-example")
project(":android-custom-progress-view-example").projectDir = File("app/libs/android-custom-progress-view-example")
project(":android-degree-picker-view-example").projectDir = File("app/libs/android-degree-picker-view-example")
project(":android-picture-in-picture-example").projectDir = File("app/libs/android-picture-in-picture-example")
project(":android-compose-playground").projectDir = File("app/libs/android-compose-playground/compose")
project(":android-compose-glance-widget-example").projectDir = File("app/libs/android-compose-glance-widget-example")
