// build.gradle.kts (Root)

plugins {
    // Definisati plugine na nivou root projekta ako je potrebno.
    // Na primer: plugin za verzionisanje, plugin za statičku analizu koda itd.
    // Primetite: Nema potrebe za definisanjem Android ili Kotlin plugin-a ovde.
}

// Globalne konfiguracije koje se primenjuju na sve module/projekte
allprojects {
    repositories {
        // Svi repozitorijumi su već definisani u settings.gradle.kts kroz dependencyResolutionManagement,
        // tako da ova sekcija može ostati prazna ili se može ukloniti.
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
