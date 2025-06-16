package com.company.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "order") // 自动刷新绑定配置
public class OrderProperties {

    private String value;

    private String database;
}
