package org.example.dessertshopspringboot;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocal(){
        ThreadLocal tl=new ThreadLocal();
        new Thread(()->{
            tl.set("xzx");
            tl.set("yzx");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"blue").start();

        new Thread(()->{
            tl.set("wgz");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"red").start();
    }


}
