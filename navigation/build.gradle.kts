plugins {
    alias(libs.plugins.pluginConvention.common)
    alias(libs.plugins.pluginConvention.ui)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.almazbekov.navigation"

    lint {
        disable.remove("UsingMaterialAndMaterial3Libraries")
    }
}

dependencies {
    implementation(libs.bundles.navigation)
    implementation(libs.androidx.navigation3.runtime.android)
}
