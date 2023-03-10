plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Build.compileSdk

    defaultConfig {
        applicationId = Build.applicationId
        minSdk = Build.minSdk
        targetSdk = Build.targetSdk
        versionCode = Build.versionCode
        versionName = Build.versionName

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":ble"))

    implementation(Dependencies.KotlinX.coroutines)

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.Navigation.fragment)
    implementation(Dependencies.AndroidX.Navigation.ui)
    implementation(Dependencies.View.constraintLayout)
    implementation(Dependencies.RikkaX.appCompat)
    implementation(Dependencies.RikkaX.material)
    implementation(Dependencies.RikkaX.materialPreference)
    implementation(Dependencies.RikkaX.core)
    implementation(Dependencies.RikkaX.insets)
    implementation(Dependencies.RikkaX.mainSwitchBar)
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)
}

configurations.all {
    exclude("androidx.appcompat", "appcompat")
}