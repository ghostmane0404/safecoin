// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
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

// Команды для CI
tasks.register("detektAll") {
    dependsOn(subprojects.map { "${it.path}:detekt" })
    group = "verification"
    description = "Run detekt on all modules"
}

tasks.register("lintAll") {
    dependsOn(subprojects.map { "${it.path}:lintDebug" })
    group = "verification"
    description = "Run lint on all modules"
}

tasks.register("qualityChecks") {
    dependsOn("detektAll", "lintAll")
    group = "verification"
    description = "Run all quality checks"
}
