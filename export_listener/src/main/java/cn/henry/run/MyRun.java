package cn.henry.run;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyRun {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContext-listener.xml");
    }
}
