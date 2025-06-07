# app/proguard-rules.pro

# ==================== ОБЩИЕ ПРАВИЛА ====================

# Сохранить информацию о номерах строк для stack traces
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Сохранить аннотации
-keepattributes *Annotation*

# ==================== KOTLIN ====================

# Kotlin Coroutines
-dontwarn kotlinx.coroutines.**

# Kotlin Serialization (если будете использовать)
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

# ==================== ANDROID ====================

# ViewModels
-keep class * extends androidx.lifecycle.ViewModel { *; }

# Compose
-dontwarn androidx.compose.**

# ==================== КРИПТОГРАФИЯ (ВАЖНО!) ====================

# Сохранить все криптографические классы
-keep class java.security.** { *; }
-keep class javax.crypto.** { *; }

# Сохранить модели данных вашего приложения
-keep class com.almazbekov.safecoin.data.model.** { *; }

# ==================== БЕЗОПАСНОСТЬ ====================

# Удалить логи в продакшне
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# Удалить Timber логи (если будете использовать)
-assumenosideeffects class timber.log.Timber* {
    public static *** plant(...);
    public static *** d(...);
    public static *** w(...);
    public static *** i(...);
    public static *** e(...);
    public static *** v(...);
}