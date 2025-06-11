import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
    alias(libs.plugins.pluginConvention.application)
    alias(libs.plugins.firebase.app.distribution)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.almazbekov.safecoin"

    defaultConfig {
        applicationId = "com.almazbekov.safecoin"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

firebaseAppDistribution {
    appId = "1:1044804823434:android:e46295f9c1b9b572078073"
    groups = "testers"
    releaseNotesFile = "release-notes.txt"
}

dependencies {
    implementation(libs.androidx.splashScreen)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.navigation3.ui.android)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.material3.android)
    implementation(libs.bundles.koin)

    implementation(project(":coreui"))
    implementation(project(":core"))
    implementation(project(":core_android"))
    implementation(project(":ui_contracts"))
    implementation(project(":navigation"))
    implementation(project(":bottom_tab"))
    implementation(project(":udf"))
    implementation(project(":udfcompose"))
    implementation(project(":main:controller"))
    implementation(project(":welcome:welcome_data"))
    implementation(project(":welcome:welcome_domain"))
    implementation(project(":welcome:welcome_ui"))
    implementation(project(":welcome:welcome_controller"))
}
