import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.api.artifacts.VersionCatalogsExtension

/**
 * Convention plugin для UI фичей (wallet, market, trading, etc.)
 * Включает: Android Library + Compose + Hilt + Navigation
 */
class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("dagger.hilt.android.plugin")
                apply("org.jetbrains.kotlin.kapt")
                apply("kotlin-parcelize")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35

                buildFeatures {
                    compose = true
                }
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

            dependencies {
                // Core Android
                add("implementation", libs.findBundle("androidx-core").get())

                // Compose
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findBundle("compose").get())
                add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())

                // Navigation
                add("implementation", libs.findBundle("navigation").get())

                // Hilt
                add("implementation", libs.findBundle("hilt").get())
                add("kapt", libs.findLibrary("google-dagger-hilt-compiler").get())

                // Logging
                add("implementation", libs.findLibrary("timber").get())
            }
        }
    }
}