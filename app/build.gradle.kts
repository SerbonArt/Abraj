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
        vectorDrawables.useSupportLibrary = true
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
    implementation("io.reactivex.rxjava3:rxjava:3.1.3")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.jakewharton.threetenabp:threetenabp:1.3.1")
    implementation("com.google.android.gms:play-services-location:21.1.0")
    implementation(files("libs/prayer_time_library.jar"))
    implementation("androidx.preference:preference:1.2.1")
    implementation("com.jakewharton.timber:timber:4.7.1")
    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
    implementation("com.readystatesoftware.systembartint:systembartint:1.0.3")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}