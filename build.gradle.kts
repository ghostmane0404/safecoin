// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
}

detekt {
    source.setFrom(rootProject.projectDir)
    config.setFrom("$rootDir/config/detekt/detekt.yml")
    baseline = file("$rootDir/config/detekt/baseline.xml")
    autoCorrect = true
    parallel = true
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html {
            required.set(true)
            outputLocation.set(file("$rootDir/build/reports/detekt/detekt.html"))
        }
        xml {
            required.set(true)
            outputLocation.set(file("$rootDir/build/reports/detekt/detekt.xml"))
        }
    }

    setExcludes(
        listOf(
            "**/build/**",
            "**/generated/**",
            "**/test/**",
            "**/androidTest/**",
            "**/.gradle/**",
        ),
    )
}
