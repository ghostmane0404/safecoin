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
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("application") {
            id = libs.plugins.pluginConvention.application.get().pluginId
            implementationClass = "ApplicationConventionPlugin"
        }
        register("ui") {
            id = libs.plugins.pluginConvention.ui.get().pluginId
            implementationClass = "UiConventionPlugin"
        }
        register("common") {
            id = libs.plugins.pluginConvention.common.get().pluginId
            implementationClass = "CommonConventionPlugin"
        }
    }
}
