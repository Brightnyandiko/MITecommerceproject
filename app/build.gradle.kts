import org.jetbrains.kotlin.cli.jvm.main

//plugins {
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
//    id("com.google.gms.google-services")
//}
//
//android {
//    namespace = "com.bright.ecommerce"
//    compileSdk = 34
//
//    defaultConfig {
//        applicationId = "com.bright.ecommerce"
//        minSdk = 24
//        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary = true
//        }
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    buildFeatures {
//        compose = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.4.3"
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
//}
//
//dependencies {
//
//    implementation("androidx.core:core-ktx:1.12.0")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
//    implementation("androidx.activity:activity-compose:1.8.0")
//    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.navigation:navigation-runtime-ktx:2.7.4")
//    implementation("androidx.compose.ui:ui-android:1.5.4")
//    implementation("androidx.compose.material3:material3:1.1.2")
//    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
//    implementation("androidx.navigation:navigation-compose:2.7.4")
//
//    val compose_ui_version = "1.5.4"
//    implementation ("androidx.compose.material:material-icons-extended:$compose_ui_version")
//    implementation ("io.coil-kt:coil-compose:2.3.0")
//    implementation ("androidx.compose.runtime:runtime-livedata:$compose_ui_version")
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
//
//    //firebase components
//    implementation("com.google.firebase:firebase-inappmessaging-ktx:20.4.0")
//    implementation("com.google.firebase:firebase-auth:22.2.0")
//    implementation("com.google.firebase:firebase-database:20.3.0")
//    implementation("com.google.firebase:firebase-storage:20.3.0")
//    implementation("io.coil-kt:coil-compose:2.3.0")
//    implementation("com.google.firebase:firebase-firestore:24.9.1")
//
//    // Lifecycle components
//    val lifecycleVersion = "2.6.2"
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
//    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
//    implementation ("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
//
//    // Kotlin Coroutines
//    val coroutinesVersion = "1.7.1"
//    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
//    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
//
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
//}

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("com.google.gms.google-services")
    id ("kotlin-parcelize")


}





android {
    namespace = "com.bright.ecommerce"
    compileSdk  = 34

    defaultConfig {
        applicationId = "com.bright.ecommerce"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"),
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
//    sourceSets {
//         main {
//            assets {
//                srcDirs ("src/main/assets")
//            }
//        }
//    }

    sourceSets["main"].java {
        srcDir("src/main/res/assets")
    }
}

dependencies {


    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("androidx.compose.material3:material3:1.1.2")
    val compose_ui_version = "1.5.4"
    implementation ("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.activity:activity-compose:1.7.1")
    implementation ("androidx.compose.ui:ui:$compose_ui_version")
    implementation ("androidx.compose.ui:ui-tooling-preview:$compose_ui_version")
    implementation ("androidx.compose.material:material:1.4.3")
    implementation ("com.google.firebase:firebase-auth-ktx:22.2.0")
    implementation ("com.google.firebase:firebase-firestore-ktx:24.9.1")
    implementation ("com.google.firebase:firebase-storage-ktx:20.3.0")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$compose_ui_version")
    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_ui_version")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:$compose_ui_version")

    val nav_version = ("2.5.3")

    implementation ("androidx.navigation:navigation-compose:$nav_version")

    implementation ("androidx.compose.material:material-icons-extended:$compose_ui_version")
    implementation ("io.coil-kt:coil-compose:2.3.0")
    implementation ("androidx.compose.runtime:runtime-livedata:$compose_ui_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")


    // Lifecycle components
    val lifecycleVersion  = ("2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")


    // Kotlin Coroutines
    val coroutinesVersion = ("1.7.1")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")


}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}