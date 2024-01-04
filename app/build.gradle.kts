import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val signingProps = Properties()
signingProps.load(FileInputStream(file("../signing.properties")))

android {
    namespace = "com.zhbcompany.retirementcalculator"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zhbcompany.retirementcalculator"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = signingProps["app.alias"] as String
            keyPassword = signingProps["app.keyPassword"] as String
            storeFile = file(signingProps["app.file"] as String)
            storePassword = signingProps["app.storePassword"] as String
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}


dependencies {
    val appCenterSdkVersion = "5.0.3"
    implementation ("com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}")
    implementation ("com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("com.microsoft.appcenter:espresso-test-extension:1.4")
}