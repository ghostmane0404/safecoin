plugins {
    alias(libs.plugins.pluginConvention.common)
    alias(libs.plugins.pluginConvention.ui)
}

android {
    namespace = "com.almazbekov.coreui"

    lint {
        disable.remove("UsingMaterialAndMaterial3Libraries")
    }
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(project(":ui_contracts"))
}
