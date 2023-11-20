package run.threedog.user;

import run.threedog.springboot.annotation.MySpringBootApplication;
import run.threedog.springboot.annotation.SpringBootApplication;

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        MySpringBootApplication.run(MyApplication.class, args);
    }
}
