package app.aiyun.controller;


import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
public class HomeController {

    @Value("${aiyun.base-config}")
    private String baseConfig;

    @Value("${username:}")
    private String userName;

    @Autowired
    private WebApplicationContext applicationContext;

    @GetMapping("/index")
    public Map index() throws NacosException {

        String jdbcUrl = applicationContext.getEnvironment().getProperty("jdbc.url");
        String password = applicationContext.getEnvironment().getProperty("password");

        Map<String,String> map = new HashMap<String, String>();
        map.put("本地配置", baseConfig);
        map.put("datasource", jdbcUrl);
        map.put("用户名", userName);
        map.put("密码", password);
        return map;
    }
}
