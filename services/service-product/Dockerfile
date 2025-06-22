# 阶段 1: 构建镜像
FROM maven:3.9.6-openjdk-17-slim as builder
# 设置工作目录
WORKDIR /app
# 复制 pom.xml 和 src 目录到工作目录
COPY pom.xml .

COPY src ./src

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=test
ENV SPRING_CLOUD_NACOS_SERVER_ADDR=host.docker.internal:8848

# 构建项目
RUN mvn clean package -Dmaven.test.skip=true

# 阶段 2: 运行时镜像
FROM eclipse-temurin:17-jre-jammy
# 设置工作目录
WORKDIR /app

# 复制 CI 阶段构建好的 JAR 包到容器内
# 注意：这里的 'app.jar' 是容器内 JAR 的名称
# 'target/*.jar' 是 Docker 构建上下文中的路径，它会在CI流水线中被解析为实际的JAR文件
COPY --from=builder /app/target/*.jar gateway.jar

# 声明端口
EXPOSE 8080

# 定义应用程序启动命令
ENTRYPOINT ["java", "-jar", "gateway.jar"]