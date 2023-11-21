package run.threedog.springboot.server;

public class TomcatWebServer implements WebServer{
    @Override
    public void start() {
        System.out.println("Tomcat启动");
    }
}
