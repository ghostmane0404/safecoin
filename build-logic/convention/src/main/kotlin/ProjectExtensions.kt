import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.JavaVersion

/**
 * Общая конфигурация для Android модулей
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 24
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        lint {
            lintConfig = file("${rootProject.projectDir}/lint.xml")
            checkDependencies = true
            ignoreTestSources = false
            warningsAsErrors = true
            showAll = true
            checkReleaseBuilds = false
        }

        testOptions {
            animationsDisabled = true
            unitTests {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
            }
        }

        buildTypes {
            getByName("debug") {
                // debug настройки
            }

            getByName("release") {
                isMinifyEnabled = false
                val proguardDir = project.file("proguard")
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
                if (proguardDir.exists()) {
                    proguardDir.listFiles()?.let { files ->
                        proguardFiles(files.toList())
                    }
                }
            }
        }
    }

    configureKotlin()
}

/**
 * Перегрузка для ApplicationExtension
 */
internal fun Project.configureKotlinAndroid(
    applicationExtension: ApplicationExtension,
) {
    configureKotlinAndroid(applicationExtension as CommonExtension<*, *, *, *, *, *>)
}

/**
 * Конфигурация Kotlin для всех модулей
 */
private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=kotlin.ExperimentalStdlibApi",
                "-opt-in=kotlin.ExperimentalUnsignedTypes",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
                "-Xjsr305=strict",
            )
        }
    }
}

/**
 * Конфигурация Compose для UI модулей
 */
internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        lint {
            disable.add("UsingMaterialAndMaterial3Libraries")
        }

        buildTypes {
            getByName("debug") {
                println("Adding Compose compiler report destination for debug build in project ${project.name}")
            }
        }
    }

    // Compose compiler настройки
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            val composeConfigFile = rootProject.file("compose_compiler_config.conf")
            if (composeConfigFile.exists()) {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:stabilityConfigurationPath=" +
                        composeConfigFile.absolutePath,
                )
            }
        }
    }
}
