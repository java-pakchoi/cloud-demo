# 阶段1：构建阶段
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app
# 1. 复制所有文件（包括父POM和嵌套子模块）
COPY . .

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=test
ENV SPRING_CLOUD_NACOS_SERVER_ADDR=host.docker.internal:8848

# 2. 一次性构建整个项目（自动解析层级依赖）
RUN mvn clean package -Dmaven.test.skip=true

# 阶段2：运行阶段（以gateway为例）
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
# 3. 只提取目标模块的JAR（例如gateway）
COPY --from=builder /app/service-product/target/service-product-*.jar ./product.jar

CMD ["java", "-jar", "product.jar"]