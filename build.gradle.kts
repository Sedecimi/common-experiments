
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
//    id("org.jetbrains.kotlinx.benchmark") version "0.4.4"
    application
}

group = "org.sedecimi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

fun KotlinNativeTarget.defaultConfig() {
    binaries {
        executable {
            entryPoint = "main"
        }
    }
}

kotlin {
    jvm()
    macosX64 {
        defaultConfig()
    }
    macosArm64 {
        defaultConfig()
    }
    linuxX64 {
        defaultConfig()
    }
    mingwX64 {
        defaultConfig()
    }
    /*
    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" && isArm64 -> macosArm64("native")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("native")
        hostOs == "Linux" && isArm64 -> linuxArm64("native")
        hostOs == "Linux" && !isArm64 -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }
*/

//    nativeTarget.apply {
//        binaries {
//            executable {
//                entryPoint = "main"
//            }
//        }
//    }

//    configurations.matching { it.name != "kotlinCompilerPluginClasspath" }.all {
//        resolutionStrategy.eachDependency {
//            val version = requested.version
//            if (requested.group == "org.jetbrains.kotlinx" &&
//                requested.name.startsWith("kotlinx-coroutines") &&
//                version != null && !version.contains("native-mt")
//            ) {
//                useVersion("$version-native-mt")
//            }
//        }
//    }
    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
//                implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.4")
            }
        }
    }
}

application {
    mainClass.set("JvmMain")
}
/*
Не смог заставить работать

benchmark {
    targets {
        createJmhGenerationRuntimeConfiguration("jvmBenchmark", "1.21")
//        register("jvm") {
//            jmhVersion = "1.21"
//        }
        // This one matches source set name, e.g. 'jvmMain', 'jvmTest', etc
        // and register the corresponding compilation (here the 'benchmark' compilation declared in the 'jvm' target)
        register("jvmBenchmark") {
//            jmhVersion = "1.21"
        }
    }
    configurations {
        val main by getting {
            iterations = 5 // number of iterations
            iterationTime = 300
            iterationTimeUnit = "ms"
        }
    }
}
*/
