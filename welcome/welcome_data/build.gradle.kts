plugins {
    alias(libs.plugins.pluginConvention.common)
}

android {
    namespace = "com.almazbekov.welcome.data"
}

dependencies {
    implementation(project(":welcome:welcome_domain"))
    implementation(project(":core"))
}
