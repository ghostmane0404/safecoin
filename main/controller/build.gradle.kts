plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(project(":udf"))
    implementation(project(":welcome:welcome_domain"))
}
