pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Dodajte bilo koje dodatne repozitorijume koji su vam potrebni
    }
}

rootProject.name = "Abraj"
include(":app")
