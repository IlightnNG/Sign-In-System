plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    implementation ("com.google.zxing:core:3.4.1")
    implementation ("com.google.zxing:javase:3.4.1")
}

tasks.test {
    useJUnitPlatform()
}