# 🛡️ PII Masker - Spring Boot Starter

[![Maven Central](https://img.shields.io/maven-central/v/io.github.ketansinghrajput/pii-masker-spring-boot-starter.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.ketansinghrajput/pii-masker-spring-boot-starter/1.0.1)
[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A lightweight, zero-configuration Spring Boot Starter library that automatically intercepts and masks Personally Identifiable Information (PII) in your application logs and JSON responses. 

Data privacy is not an afterthought. Stop accidentally leaking sensitive user data into your centralized logging systems (ELK, Splunk, Datadog) with a single plug-and-play dependency.

## 🚀 Why use PII Masker?

* **Zero Code Changes:** You don't need to rewrite your existing `log.info()` or `log.debug()` statements. 
* **True Plug-and-Play:** Built as a Spring Boot Auto-Configuration module. Just drop it in the classpath.
* **Optimized for India & Global Standards:** Natively supports masking for Indian Aadhaar numbers, global Credit Cards, Indian Phone Numbers, and Emails.

## 📦 Installation

This library is published on Maven Central. Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.ketansinghrajput</groupId>
    <artifactId>pii-masker-spring-boot-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```

For Gradle users, add this to your `build.gradle`:
```groovy
implementation 'io.github.ketansinghrajput:pii-masker-spring-boot-starter:1.0.1'
```

## 🛠️ How It Works (Automatic Masking)

Behind the scenes, `PiiMaskingUtil` uses highly optimized regex patterns to detect and mask sensitive fields before the log is ever written.

**Supported PII Types & Masking Formats:**
* **Aadhaar Card:** `XXXX-XXXX-1234`
* **Credit Card:** `XXXX-XXXX-XXXX-1234`
* **Phone Number:** `XXXXXX3210`
* **Email Address:** `s****@gmail.com`

**Your existing code:**
```java
String userEmail = "sensei@gmail.com";
String userPhone = "9876543210";
log.info("User registered with email: {} and phone: {}", userEmail, userPhone);
```

**Standard Log Output (Vulnerable):**
```text
[INFO] User registered with email: sensei@gmail.com and phone: 9876543210
```

**PII Masker Output (Secured):**
```text
[INFO] User registered with email: s****@gmail.com and phone: XXXXXX3210
```

## 🧠 Core Technologies Used
* **Java 17 / Spring Boot 3.5.13**
* **Logback / SLF4J**
* **Jackson Databind** (For JSON payload masking)
* **Regex / Pattern Matching**
* **Maven Central Publishing** (Sonatype)

## 🤝 Contributing
Found a bug or want to add support for masking SSNs/PAN numbers? Pull requests are welcome!
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License
Distributed under the Apache License 2.0.
