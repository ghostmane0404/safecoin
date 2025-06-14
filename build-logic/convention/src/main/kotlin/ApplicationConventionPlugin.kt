import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.api.artifacts.VersionCatalogsExtension

@Suppress("LongMethod")
class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                    isCoreLibraryDesugaringEnabled = true
                }
                buildFeatures {
                    compose = true
                    buildConfig = true
                }

                flavorDimensions += "environment"

                productFlavors {
                    create("dev") {
                        dimension = "environment"
                        applicationId = "com.almazbekov.safecoin"

                        // API настройки для разработки
                        buildConfigField("String", "API_BASE_URL", "\"https://api.sandbox.coinapi.io/v1/\"")

                        // Название приложения
                        resValue("string", "app_name", "SafeCoin Dev")

                        // Манифест плейсхолдеры
                        manifestPlaceholders["enableCrashReporting"] = false
                    }

                    create("prod") {
                        dimension = "environment"
                        applicationId = "com.safecoin.io"
                        // Продакшн API
                        buildConfigField("String", "API_BASE_URL", "\"https://rest.coinapi.io/v1/\"")

                        resValue("string", "app_name", "SafeCoin")
                        manifestPlaceholders["enableCrashReporting"] = true
                    }
                }

                signingConfigs {
                    getByName("debug") {
                    }
                }

                buildTypes {
                    getByName("debug") {
                        isDebuggable = true
                        signingConfig = signingConfigs.getByName("debug")
                        // Дополнительные debug настройки
                        buildConfigField("boolean", "IS_DEBUG", "true")
                    }
                    getByName("release") {
                        signingConfig = signingConfigs.getByName("debug")
                        isMinifyEnabled = true
                        isShrinkResources = true
                        isDebuggable = false

                        buildConfigField("boolean", "IS_DEBUG", "false")

                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

            dependencies {
                // Core dependencies
                add("implementation", libs.findBundle("androidx-core").get())

                // Compose dependencies
                val composeBom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(composeBom))
                add("implementation", libs.findBundle("compose").get())
                add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())
                add("implementation", libs.findLibrary("kotlinx-datetime").get())
                add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:2.0.4")
                add("testImplementation", libs.findBundle("testing").get())
                // Test dependencies
                add("androidTestImplementation", platform(composeBom))
                add("implementation", platform(libs.findLibrary("firebase-bom").get()))
                add("implementation", libs.findLibrary("firebase-analytics").get())
                add("implementation", libs.findLibrary("firebase-crashlytics").get())
            }
        }
    }
}
