package com.github.whyrising.todos

object Libs {
    const val jvmTarget = "1.8"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"

    object Kotlin {
        const val version = "1.6.10"
    }

    object Androidx {
        object Appcompat {
            private const val version = "1.4.1"
            const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        }

        object Constraint {
            private const val version = "2.1.3"
            const val constrainLayout =
                "androidx.constraintlayout:constraintlayout:$version"
        }

        object Core {
            private const val version = "1.7.0"
            const val coreKtx = "androidx.core:core-ktx:$version"
        }

        object Fragment {
            private const val version = "1.4.1"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Legacy {
            private const val version = "1.0.0"
            const val support = "androidx.legacy:legacy-support-v4:$version"
        }

        object Lifecycle {
            private const val version = "2.4.1"

            const val vmKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveData =
                "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtime =
                "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val vmSavedState =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            const val common =
                "androidx.lifecycle:lifecycle-common-java8:$version"

            // Test helpers for LiveData
            const val testLiveData = "androidx.arch.core:core-testing:$version"
        }

        object Navigation {
            const val version = "2.4.1"
            private const val gr = "androidx.navigation"
            const val fragment = "$gr:navigation-fragment-ktx:$version"
            const val ui = "$gr:navigation-ui-ktx:$version"
            const val feature =
                "$gr:navigation-dynamic-features-fragment:$version"
        }

        object RecyclerView {
            private const val version = "1.2.1"
            const val recycler = "androidx.recyclerview:recyclerview:$version"
        }

        object Room {
            const val version = "2.4.2"

            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        object SlidingPaneLayout {
            private const val version = "1.2.0"
            const val spl =
                "androidx.slidingpanelayout:slidingpanelayout:$version"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val coreKtx = "androidx.test:core-ktx:$version"
            const val rules = "androidx.test:rules:$version"
            const val espressoCore =
                "androidx.test.espresso:espresso-core:3.4.0"
        }
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

    object Kotlinx {
        private const val version = "0.17.1"
        const val atomicfu = "org.jetbrains.kotlinx:atomicfu:$version"
    }

    object Ktor {
        const val version = "1.6.7"
        const val clientCore = "io.ktor:ktor-client-core:$version"
        const val engine = "io.ktor:ktor-client-android:$version"
        const val serialization = "io.ktor:ktor-client-serialization:$version"
    }

    object Material {
        private const val version = "1.5.0"
        const val material = "com.google.android.material:material:$version"
    }
}
