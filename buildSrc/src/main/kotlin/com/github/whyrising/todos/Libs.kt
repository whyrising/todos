package com.github.whyrising.todos

object Libs {
    const val jvmTarget = "1.8"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"

    object Kotlin {
        const val version = "1.6.10"
    }

    object Androidx {
        object Core {
            private const val version = "1.7.0"
            const val coreKtx = "androidx.core:core-ktx:$version"
        }

        object Appcompat {
            private const val version = "1.4.1"
            const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        }

        object Constraint {
            private const val version = "2.1.3"
            const val constrainLayout = "androidx.constraintlayout:constraintlayout:$version"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val coreKtx = "androidx.test:core-ktx:$version"
            const val rules = "androidx.test:rules:$version"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }
    }

    object Material {
        private const val version = "1.5.0"
        const val material = "com.google.android.material:material:$version"
    }

    object Coroutines {
        private const val group = "org.jetbrains.kotlinx"
        private const val version = "1.6.0"

        const val core = "$group:kotlinx-coroutines-core:$version"
        const val android = "$group:kotlinx-coroutines-android:$version"
        const val coroutinesTest = "$group:kotlinx-coroutines-test:$version"
    }

    object Kotest {
        private const val version = "5.1.0"

        const val runner = "io.kotest:kotest-runner-junit5:$version"
        const val assertions = "io.kotest:kotest-assertions-core-jvm:$version"
        const val property = "io.kotest:kotest-property:$version"
    }
}
