// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.8.20"
        classpath("com.android.tools.build:gradle:8.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        // Dodajte ostale potrebne classpath zavisnosti ovde
    }
}

plugins {
    // Možete izostaviti verziju i primenu plugina ovde ako već koristite buildscript blok za konfiguraciju
}

allprojects {
    repositories {
        google()
        mavenCentral()
        // Dodajte ostale repozitorijume ako je potrebno
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
