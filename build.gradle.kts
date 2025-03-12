plugins {
    id("org.gradle.java")
}

group = "kylooh.github.io"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.threeten:threeten-extra:1.8.0")
}