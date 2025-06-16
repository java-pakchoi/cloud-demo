sentinel有单独的终端，需要单独配置，请参考sentinel官方文档
# 1. sentinel支持对web、普通函数（@SentinelResource注解）、feign接口（@FeignClient注解）进行流量控制
## 1.1 web流量控制是通过实现BlockExceptionHandler接口，实现流控后处理
## 1.2 普通函数流量控制是通过@SentinelResource注解，注解中fallbackClass参数指定降级类，实现流控后处理
## 1.3 feign接口流量控制是通过@FeignClient注解，注解中fallback属性指定降级类，实现流控后处理

# 2. sentinel支持对Dubbo、SpringCloud等微服务框架进行流量控制
## 2.1 Dubbo流量控制是通过Dubbo的Filter实现，实现流控后处理
## 2.2 SpringCloud流量控制是通过SpringCloud的GatewayFilter实现，实现流控后处理

# 3. sentinel支持对HTTP请求进行流量控制
## 3.1 sentinel-apache-httpclient是sentinel对Apache HttpClient的封装，支持对HTTP请求进行流量控制
## 3.2 sentinel-okhttp是sentinel对OkHttp的封装，支持对HTTP请求进行流量控制
## 3.3 sentinel-reactor-netty是sentinel对Reactor Netty的封装，支持对HTTP请求进行流量控制

# 4. sentinel支持对Redis、Memcached、MySQL等数据源进行流量控制
## 4.1 Redis流量控制是通过Redisson的SentinelCommands实现，实现流控后处理
## 4.2 Memcached流量控制是通过MemcachedClient的addObserver实现，实现流控后处理
## 4.3 MySQL流量控制是通过JDBC实现，实现流控后处理

sentinel的功能大致如下：
流量控制的功能和概念：
1. 熔断：当流量突然增加到一定阈值时，停止对流量的处理，并返回错误或默认值，防止服务因过载而崩溃。
2. 降级：当流量突然增加到一定阈值时，将流量导流到备用服务，以保证核心服务的正常运行。
3. 限流：当流量突然增加到一定阈值时，限制流量，防止服务因过载而崩溃。
4. 热点参数：当流量突然增加到一定阈值时，对热点参数进行降级，以保证核心服务的正常运行。
5. 流量整形：当流量突然增加到一定阈值时，对流量进行整形，以保证核心服务的正常运行。
6. 流量控制规则：通过流量控制规则，对流量进行控制，包括QPS、线程数、调用关系、调用参数等。
7. 流量控制规则管理：通过流量控制规则管理，可以对流量控制规则进行配置、修改、删除、查看等。
8. 系统自适应：当流量突然增加到一定阈值时，系统会自动调整规则，以保证核心服务的正常运行。
9. 实时监控：通过实时监控，可以看到流量控制的实时数据，包括QPS、调用关系、调用参数等。
10. 故障注入：通过故障注入，可以模拟流量控制规则的异常场景，以测试系统的容错能力。
11. 日志统计：通过日志统计，可以看到流量控制的日志数据，包括QPS、调用关系、调用参数等。
