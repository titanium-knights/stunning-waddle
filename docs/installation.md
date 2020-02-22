# Installation
Here's how to install Stunning Waddle:

1. In your root **build.gradle** file, replace the `buildscript` block with this to add Kotlin support to your project:
```groovy
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.1'
        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.60'
    }
}
```
2. In **TeamCode/build.gradle**, add the following:
```groovy
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
repositories {
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/titanium-knights/titanium-knights")
    }
}
dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
}
```
3. In **TeamCode/build.release.gradle**, add this to the `dependencies` block:
```groovy
implementation "io.github.titanium_knights:stunning_waddle:0.1.1"
```
4. Re-sync your Android Studio project with Gradle.
5. Add a Kotlin file to your **java** folder. Inherit from `EventOpMode` to get started with Stunning Waddle.