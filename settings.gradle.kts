pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// We can do like this `implementation(projects.core)`
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "BusbyCryptoTracker"
include(":app")
include(":crypto")
include(":core")
