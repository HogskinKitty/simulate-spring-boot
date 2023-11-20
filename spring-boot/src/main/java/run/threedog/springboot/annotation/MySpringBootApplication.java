package run.threedog.springboot.annotation;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 简单模拟实现SpringBoot启动流程
 */
public class MySpringBootApplication {

    /**
     * run方法具体需要实现的功能
     * <p>
     * 1. 创建一个Spring容器
     * 2. 创建Tomcat对象
     * 3. 生成DispatcherServlet对象，并且和前面创建出来的Spring容器进行绑定
     * 4. 将DispatcherServlet添加到Tomcat中
     * 5. 启动Tomcat
     *
     * @param clazz
     * @param args
     */
    public static void run(Class clazz, String[] args) {

        AnnotationConfigWebApplicationContext applicationContext =
            new AnnotationConfigWebApplicationContext();
        applicationContext.register(clazz);
        applicationContext.refresh();

        startTomcat(applicationContext);
    }

    /**
     * 启动Tomcat
     *
     * @param applicationContext
     */
    public static void startTomcat(WebApplicationContext applicationContext){

        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(8080);

        Engine engine = new StandardEngine();
        engine.setDefaultHost("localhost");

        Host host = new StandardHost();
        host.setName("localhost");

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet(applicationContext));
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }
}
