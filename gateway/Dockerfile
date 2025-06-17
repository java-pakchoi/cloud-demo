# Dockerfile (请将此文件放置在每个服务的根目录下，例如 gateway/Dockerfile)
# 阶段 1: 构建 (如果你需要多阶段构建来减小最终镜像大小，这里是构建阶段)
# 通常 Spring Boot 应用可以直接跳过这个构建阶段，直接从JAR创建运行时镜像
# 但为了更好的实践，这里可以演示一个简单的多阶段构建
# 使用一个包含JDK的镜像来构建
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /app
# 复制 Maven pom 文件以利用Docker层缓存
# 注意：这需要你在Dockerfile的构建上下文中包含完整的pom.xml结构，
# 对于聚合项目，可能需要复制整个项目到容器内进行构建
# 更常见的做法是在CI流水线中在宿主机构建好JAR，然后直接COPY JAR
# 这里我们假设JAR已经在宿主机构建好，直接复制
# COPY .mvn .mvn
# COPY mvnw .
# COPY pom.xml .
# COPY src src
# RUN ./mvnw clean package -Dmaven.test.skip=true

# 阶段 2: 运行时镜像
# 使用一个更轻量级的JRE镜像来运行应用程序
# 仅包含JRE，镜像更小
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 复制 CI 阶段构建好的 JAR 包到容器内
# 注意：这里的 'app.jar' 是容器内 JAR 的名称
# 'target/*.jar' 是 Docker 构建上下文中的路径，它会在CI流水线中被解析为实际的JAR文件
COPY target/*.jar gateway.jar

# 暴露应用程序监听的端口 (请根据你的服务实际监听的端口修改)
# Gateway 服务可能监听 8080
# Order 服务可能监听 8081
# Product 服务可能监听 8082
# 默认示例，你可能需要为每个服务修改此端口
EXPOSE 8080

# 定义应用程序启动命令
ENTRYPOINT ["java", "-jar", "gateway.jar"]

# 可选：如果你希望传入JVM参数，可以使用 CMD
# ENTRYPOINT ["java"]
# CMD ["-jar", "app.jar"]