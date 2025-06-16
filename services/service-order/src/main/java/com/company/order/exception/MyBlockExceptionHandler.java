package com.company.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

/**
 * sentinel限流异常处理器
 * 当前方式只适用于web请求的流控，在serviceImpl里面是另一种方式兜底流控处理
 * 注意：如果使用了@SentinelResource，则注解的流控处理优先级更高，此处的处理器不会生效
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println("你被限流了");
        writer.flush();
        writer.close();
    }
}
