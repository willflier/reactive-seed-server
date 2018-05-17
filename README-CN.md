# Reactive Server

这个种子项目的目的是为Reactive Server提供基础支撑。

## 设计

这个项目的设计目标是展示一个使用了 Spring Boot 2，Spring WebFlux 和 Kotlin 的基础性的 Rest API 服务器

如下所示：
  - 支持响应式和非阻塞
  - 与典型的Spring应用程序相比，功能更强大，注释更少
  - 利用Kotlin特性，如 [Kotlin extensions](https://kotlinlang.org/docs/reference/extensions.html) 和 [reified type parameters](https://kotlinlang.org/docs/reference/inline-functions.html＃reified-type-parameters) 以获得更简洁的代码
  - 简单，快速启动，高效的请求处理，低内存消耗
  - [基于构造函数的依赖注入](http://olivergierke.de/2013/11/why-field-injection-is-evil/)
  - 不可变的Pojos
  - 原生云支持

### 技术使用

 - 语言: [Kotlin](https://kotlin.link/)
 - 框架: [Spring Boot 2.0](https://projects.spring.io/spring-boot/) 和 [Spring 5 Kotlin support](https://docs.spring.io/spring-framework/docs/5.0.x/spring-framework-reference/kotlin.html) 以及 [Spring WebFlux functional](https://docs.spring.io/spring-framework/docs/5.0.x/spring-framework-reference/reactive-web.html)
 - 引擎: [Netty](http://netty.io/) 用作客户端以及服务端
 - 响应式 API: [Reactor](http://projectreactor.io/)
 - 持久层 : [Spring Data Reactive MongoDB](https://spring.io/blog/2016/11/28/going-reactive-with-spring-data)
 - 构建: [Gradle Script Kotlin](https://github.com/gradle/gradle-script-kotlin)
 - 测试: [Junit 5](http://junit.org/)

### TODO

 - 支持 Spring Cloud
 - 支持 Docker
 - 支持 Reactive Security

## 开发者指南

### 先决条件
 - 安装 [Git](https://git-scm.com/)
 - [Fork](https://git.yingegou.com/open/ingo-seed-reactive-server/forks/new) 以及 clone [这个项目](https://git.yingegou.com/open/ingo-seed-reactive-server.git)
 - [安装 Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### 使用命令行在 ev 模式下运行
 - 在另外一个终端中运行 `./gradlew bootRun`
 - 在你的浏览器中打开 `http://localhost:8080/`
 - 如果想要调试应用, 请将 `--debug-jvm` 参数添加到 Gradle 命令行中

### 在IDEA中导入及运行
 - 确保至少有IntelliJ IDEA`2018.1.x` 以及 IDEA Kotlin 插件`1.1.4 +`（菜单工具 -> Kotlin -> 配置Kotlin插件更新 -> 确保选择“稳定”通道 -> 检查现在更新 -> 更新后重新启动IDE）
 - 作为Gradle项目在IDEA中导入
 - 在IntelliJ IDEA中，右键点击 `Application.kt`，然后在`Run ...` 或 `Debug ...`
 - 在浏览器中打开 `http://localhost:8080/`

### 打包并从可执行JAR包中运行：
```
./gradlew clean build
java -jar build/libs/ingo-seed-reactive-server-0.0.1-SNAPSHOT
```
