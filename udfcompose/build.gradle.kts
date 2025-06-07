plugins {
    alias(libs.plugins.pluginConvention.common)
    alias(libs.plugins.pluginConvention.ui)
}

android {
    namespace = "com.almazbekov.udfcompose"

    lint {
        disable.remove("UsingMaterialAndMaterial3Libraries")
    }
}

dependencies {
    implementation(project(":udf"))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.kotlinx.coroutines)
}
