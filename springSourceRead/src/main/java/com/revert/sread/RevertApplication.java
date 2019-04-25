package com.revert.sread;

import com.revert.sread.bean.Student;
import com.revert.sread.bean.Tea;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = {"com.revert.sread"})
@EnableScheduling
public class RevertApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 3000,
                                                                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                                                                        new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });



        //定位资源
        String sourcesPath = "spring-bean.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(sourcesPath);
//        //赋值
//        Student student = (Student) classPathXmlApplicationContext.getBean("student1");
//        Tea tea = (Tea) classPathXmlApplicationContext.getBean("TT");
//        System.out.println(student);
//        System.out.println(tea);

        String scan = "com.revert.sread.bean";
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(scan);
        Student student = (Student) annotationConfigApplicationContext.getBean("student");
        Object tea = null;
        try{
            tea = annotationConfigApplicationContext.getBean("tea");
        }catch (Exception e){
            System.out.println("spring 容器中没有 tea");
        }
        System.out.println(student);
        System.out.println(tea);
    }
}
