FROM maven:3.9.6-eclipse-temurin-17 as builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -Dmaven.test.skip=true

# 阶段 2: 运行时镜像
# 使用一个更轻量级的JRE镜像来运行应用程序
# 仅包含JRE，镜像更小
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# 复制 CI 阶段构建好的 JAR 包到容器内
# 注意：这里的 'app.jar' 是容器内 JAR 的名称
# 'target/*.jar' 是 Docker 构建上下文中的路径，它会在CI流水线中被解析为实际的JAR文件
COPY --from=builder /app/target/*.jar gateway.jar

# 声明端口
EXPOSE 8080

# 定义应用程序启动命令
ENTRYPOINT ["java", "-jar", "gateway.jar"]