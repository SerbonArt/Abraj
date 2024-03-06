plugins {
    id("com.android.application")
}

android {
    namespace = "com.studiobrend.abraj"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.studiobrend.abraj"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.preference:preference:1.2.1")
    implementation("com.google.android.material:material:1.9.0") // Ažurirali na verziju 1.8.0
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.readystatesoftware.sqliteasset:sqliteassethelper:2.0.1")
    implementation(files("libs/prayer_time_library.jar"))
    implementation("androidx.core:core:1.10.1")
    implementation("androidx.fragment:fragment:1.6.1")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.jakewharton.timber:timber:4.7.1")






    testImplementation("junit:junit:4.13.2")


    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}