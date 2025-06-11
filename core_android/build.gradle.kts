plugins {
    alias(libs.plugins.pluginConvention.common)
}

android {
    namespace = "com.almazbekov.core"
}

dependencies {
    implementation(project(":core"))
    implementation(libs.bundles.koin)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.core.ktx)
}
