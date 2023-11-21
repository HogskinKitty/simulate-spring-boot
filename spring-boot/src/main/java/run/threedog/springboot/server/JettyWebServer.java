package run.threedog.springboot.server;

public class JettyWebServer implements WebServer {
    @Override
    public void start() {
        System.out.println("Jetty启动");
    }
}
