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
class UiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.kotlin.kapt")
                apply("kotlin-parcelize")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureCompose(this)
                defaultConfig.targetSdk = 35
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

            dependencies {
                // Core Android
                add("implementation", libs.findBundle("androidx-core").get())

                // Compose
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findBundle("compose").get())
            }
        }
    }
}
