pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SafeCoin"
include(":app")
include(":udf")
include(":udfcompose")
include(":core")
include(":coreui")
include(":ui_contracts")
include(":bottom_tab")
include(":navigation")
include(":welcome:welcome_ui")
include(":main:controller")
include(":welcome:welcome_domain")
include(":welcome:welcome_data")
include(":core_android")
include(":welcome:welcome_controller")
