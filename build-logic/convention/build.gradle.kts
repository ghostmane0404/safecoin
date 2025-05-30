plugins {
    `kotlin-dsl`
}

group = "buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("application") {
            id = "buildlogic.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("domain") {
            id = "buildlogic.domain"
            implementationClass = "DomainConventionPlugin"
        }
        register("ui") {
            id = "buildlogic.ui"
            implementationClass = "UiConventionPlugin"
        }
        register("data") {
            id = "buildlogic.data"
            implementationClass = "DataConventionPlugin"
        }
        register("navigation") {
            id = "buildlogic.navigation"
            implementationClass = "NavigationConventionPlugin"
        }
    }
}