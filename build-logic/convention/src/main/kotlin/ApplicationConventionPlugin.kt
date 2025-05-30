import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.api.artifacts.VersionCatalogsExtension

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
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
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true
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
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findBundle("compose").get())
                add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())
                add("implementation", libs.findLibrary("kotlinx-datetime").get())
                add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:2.0.4")
                add("testImplementation", libs.findBundle("testing").get())
                // Test dependencies
                add("androidTestImplementation", platform(bom))
            }
        }
    }
}
