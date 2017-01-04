package com.excelib.domain.services.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.excelib.domain.services.intf.Async1Services;
import com.excelib.domain.services.intf.Async2Services;
import com.excelib.domain.services.intf.Async3Services;

/**
 * 描述：
 * 异步服务组件。
 * @author zhouze 2016-10-27
 *
 */
@Service
public class Async1ServicesImpl implements Async1Services{

    @Resource
    public Async2Services async2Services;
 
    @Resource
    public Async3Services async3Services;
    
    /** 缺省的构造器*/
    public Async1ServicesImpl() {
    }

    @Override
    @Async
    public void testAsyncMethodNoReturnA() {
        try {
            //让程序暂停100秒，相当于执行一个很耗时的任务
            
//            this.testAsyncMethodNoReturnB();
            async2Services.testAsyncMethodNoReturnC();
            this.printCResult();
            
            System.out.println("  ------ A");
            Thread.sleep(10000);
            System.out.println("end ------ A");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void testAsyncMethodNoReturnB() {
        try {
            //让程序暂停100秒，相当于执行一个很耗时的任务
            Thread.sleep(5000);
            System.out.println("end ------ B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    private void printCResult() throws InterruptedException, ExecutionException {
        Future<String> future = async3Services.asyncMethodWithReturnType();
        System.out.println(future);
//        while (true) {  ///这里使用了循环判断，等待获取结果信息  
//            if (future.isDone()) {  //判断是否执行完毕  
//                System.out.println("Result from asynchronous process - " + future.get());  
//                break;  
//            }  
//            System.out.println("Continue doing something else. ");  
//            Thread.sleep(1000);  
//        }  
    }
    
    
}
