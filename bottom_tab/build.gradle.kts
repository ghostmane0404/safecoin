plugins {
    alias(libs.plugins.pluginConvention.common)
    alias(libs.plugins.pluginConvention.ui)
}

android {
    namespace = "com.almazbekov.bottom.tab"

    lint {
        disable.remove("UsingMaterialAndMaterial3Libraries")
    }
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)
    implementation(project(":navigation"))
    implementation(project(":coreui"))
}
