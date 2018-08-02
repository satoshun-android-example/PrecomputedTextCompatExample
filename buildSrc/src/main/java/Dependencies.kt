object Vers {
  val compile_sdk = 28
  val min_sdk = 28
  val target_sdk = 28
  val agp = "3.2.0-beta05"
//  val agp = "3.1.2"

  val kotlin = "1.2.60"
  val couroutine = "0.24.0"
  val ktlint = "0.21.0"
}

object Libs {
  val android_plugin = "com.android.tools.build:gradle:${Vers.agp}"
  val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin}"
  val dokka_plugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.16"

  val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"
  val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Vers.couroutine}"
  val ui_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Vers.couroutine}"

  val ktx = "androidx.core:core-ktx:1.0.0-alpha1"

  val appcompat = "androidx.appcompat:appcompat:1.0.0-beta01"
  val recyclerview = "androidx.recyclerview:recyclerview:1.0.0-beta01"
  val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha1"
  val cardview = "androidx.cardview:cardview:1.0.0-beta01"

  val viewmodel = "android.arch.lifecycle:viewmodel:1.1.1"
  val livedata = "android.arch.lifecycle:livedata:1.1.1"

  val junit = "junit:junit:4.12"
  val support_test = "com.android.support.test:runner:1.0.1"
  val espresso = "com.android.support.test.espresso:espresso-core:3.0.1"
  val arch_test = "android.arch.core:core-testing:1.1.1"

  val truth = "com.google.truth:truth:0.39"
  val mockito_kotlin = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  val multidex = "com.android.support:multidex:1.0.3"
}
