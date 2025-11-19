package com.oscar.ailab.server.service.impl.learner;

import com.oscar.ailab.server.service.learner.JavaExeSequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JavaExeSequenceServiceImpl implements JavaExeSequenceService {

    @Override
    public void testStatic() {
        System.out.println("爸爸的岁数:" + Son.factor);

    }

    @Override
    public void testExtend() {
        new Son();
    }

    @Override
    public void testBook() {
        Book.staticFunction();
    }
}


//@Reader: https://cloud.tencent.com/developer/article/1442965
class Grandpa {
    static {
        System.out.println("Grandpa in static");
    }
    public Grandpa() {
        System.out.println("Grandpa Here");
    }

    //public static int factor = 70;
}
class Father extends Grandpa {
    static {
        System.out.println("Father in static");
    }

    public static int factor = 45;

    public Father() {
        System.out.println("Father Here");
    }
}
class Son extends Father
{
    static {
        System.out.println("Son in static");
    }
    //public static int factor = 27;

    public Son() {
        System.out.println("Son Here");
    }
}

class Book {
    static Book book = new Book();
    static {
        System.out.println("书的静态代码块");
    }

    {
        System.out.println("书的普通代码块");
    }
    Book() {
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }
    public static void staticFunction(){
        System.out.println("书的静态方法");
    }
    int price = 110;
    static int amount = 112;
}
