import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
    alias(libs.plugins.pluginConvention.application)
    alias(libs.plugins.firebase.app.distribution)
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
    appId = "1:1044804823434:android:e46295f9c1b9b572078073" // dev App ID
    groups = "testers"
    releaseNotesFile = "release-notes.txt" // или serviceCredentialsFile
}

fun dependencies() {
    // Все основные зависимости уже включены в buildlogic.application
    // Добавляйте сюда только специфичные для app зависимости
}
