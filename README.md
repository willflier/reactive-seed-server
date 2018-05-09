# Reactive Server

This project purpose is to power the Reactive Server Seed.

## Software design

This project software design goal is to demonstrate what a functional web application
developed with Spring Boot 2, Spring WebFlux and Kotlin can look like:
 - Reactive and non-blocking
 - More functional style and less annotation based than typical Spring applications
 - Leverage Kotlin features like [Kotlin extensions](https://kotlinlang.org/docs/reference/extensions.html) and [reified type parameters](https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters) for cleaner code
 - Simple, fast to start, efficient request processing, low memory consumption
 - [Constructor based injection](http://olivergierke.de/2013/11/why-field-injection-is-evil/)
 - Immutable Pojos
 - Cloud Native

### Technologies used

 - Language: [Kotlin](https://kotlin.link/)
 - Framework: [Spring Boot 2.0](https://projects.spring.io/spring-boot/) with [Spring 5 Kotlin support](https://docs.spring.io/spring-framework/docs/5.0.x/spring-framework-reference/kotlin.html) and [Spring WebFlux functional](https://docs.spring.io/spring-framework/docs/5.0.x/spring-framework-reference/reactive-web.html)
 - Engine: [Netty](http://netty.io/) used for client and server
 - Reactive API: [Reactor](http://projectreactor.io/)
 - Persistence : [Spring Data Reactive MongoDB](https://spring.io/blog/2016/11/28/going-reactive-with-spring-data)
 - Build: [Gradle Script Kotlin](https://github.com/gradle/gradle-script-kotlin)
 - Testing: [Junit 5](http://junit.org/)

### TODO

 - Support for Spring Cloud

## Developer guide

### Prerequisite
 - Install [Git](https://git-scm.com/)
 - [Fork](https://github.com/mix-it/mixit#fork-destination-box) and clone [the project](https://github.com/mix-it/mixit)
 - [Install Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### Run the app in dev mod using command line
 - Run `./gradlew bootRun` in another terminal
 - Open `http://localhost:8080/` in your browser
 - If you want to debug the app, add `--debug-jvm` parameter to Gradle command line

### Import and run the project in IDEA
 - Make sure you have at least IntelliJ IDEA `2018.1.x` and IDEA Kotlin plugin `1.1.4+` (menu Tools -> Kotlin -> configure Kotlin Plugin Updates -> make sure "Stable" channel is selected -> check for updates now -> restart IDE after the update)
 - Import it in IDEA as a Gradle project
 - In IntelliJ IDEA, right click on `Application.kt` then `Run ...` or `Debug ...`
 - Open `http://localhost:8080/` in your browser

### Package and run the application from the executable JAR:
```
./gradlew clean build
java -jar build/libs/ingo-seed-reactive-server-0.0.1-SNAPSHOT
```
