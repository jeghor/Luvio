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

rootProject.name = "Luvio"
include(":app")
include(":sources")
include(":sources:ui_core")
include(":sources:ui_atoms")
include(":sources:login")
include(":sources:login:api")
include(":sources:onboarding")
include(":sources:onboarding:api")
include(":sources:core")
include(":sources:core:api")
include(":sources:core:impl")
