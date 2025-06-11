plugins {
    alias(libs.plugins.pluginConvention.common)
    alias(libs.plugins.pluginConvention.ui)
}

android {
    namespace = "com.almazbekov.welcome.ui"

    lint {
        disable.remove("UsingMaterialAndMaterial3Libraries")
    }
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin)
    implementation(project(":ui_contracts"))
    implementation(project(":udf"))
    implementation(project(":coreui"))
    implementation(project(":core_android"))
    implementation(project(":udfcompose"))
    implementation(project(":navigation"))
    implementation(project(":welcome:welcome_controller"))
}
