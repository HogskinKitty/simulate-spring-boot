package run.threedog.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import run.threedog.springboot.annotation.ConditionalOnClass;
import run.threedog.springboot.server.JettyWebServer;
import run.threedog.springboot.server.TomcatWebServer;

/**
 * 模拟实现自动配置类
 * <p>
 * 通过一个WebServiceAutoConfiguration的Spring配置类，
 * 定义两个Bean，一个TomcatWebServer，一个JettyWebServer，
 * 不过这两个要生效的前提是符合当前所指定的条件，比如：
 * 1.只有存在"org.apache.catalina.startup.Tomcat"类，那么才有TomcatWebServer这个Bean
 * 2.只有存在"org.eclipse.jetty.server.Server"类，那么才有TomcatWebServer这个Bean
 */
@Configuration
public class WebServiceAutoConfiguration implements AutoConfiguration{

    @Bean
    @ConditionalOnClass("org.apache.catalina.startup.Tomcat")
    public TomcatWebServer tomcatWebServer() {
        return new TomcatWebServer();
    }

    @Bean
    @ConditionalOnClass("org.eclipse.jetty.server.Server")
    public JettyWebServer jettyWebServer() {
        return new JettyWebServer();
    }
}