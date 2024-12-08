plugins {
    alias(libs.plugins.android.application) apply false // Android клиент
    alias(libs.plugins.kotlin.android) apply false // Kotlin для Android
    id("org.springframework.boot") version "3.1.4" apply false // Spring Boot
    id("io.spring.dependency-management") version "1.1.3" apply false // Spring Dependency Management
    kotlin("jvm") version "1.9.0" apply false // Kotlin для Spring Boot
}
