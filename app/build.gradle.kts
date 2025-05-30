plugins {
    alias(libs.plugins.pluginConvention.application)
}

android{
    namespace = "com.almazbekov.safecoin"

    defaultConfig {
        applicationId = "com.almazbekov.safecoin"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

fun dependencies() {
    // Все основные зависимости уже включены в buildlogic.application
    // Добавляйте сюда только специфичные для app зависимости
}