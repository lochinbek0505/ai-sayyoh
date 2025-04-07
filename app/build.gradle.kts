plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "uz.falconmobile.ai_obida"
    compileSdk = 35

    defaultConfig {
        applicationId = "uz.falconmobile.ai_obida"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    aaptOptions {
        noCompress ("tflite")
        noCompress ("lite")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("androidx.camera:camera-camera2:1.1.0")
    implementation ("androidx.camera:camera-lifecycle:1.1.0")
    implementation ("androidx.camera:camera-view:1.1.0")


    // dependency to allow us to crop square images
    implementation ("com.soundcloud.android:android-crop:1.0.1@aar")
    // TensorFlow lite dependency
    implementation ("org.tensorflow:tensorflow-lite:+")

}