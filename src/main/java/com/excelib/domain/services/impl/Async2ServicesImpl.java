package com.excelib.domain.services.impl;



import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.excelib.domain.services.intf.Async2Services;

@Service
public class Async2ServicesImpl implements Async2Services{

    /** 缺省的构造器*/
    public Async2ServicesImpl() {
    }

    @Override
    @Async
    public void testAsyncMethodNoReturnC() {
        try {
            //让程序暂停100秒，相当于执行一个很耗时的任务
            Thread.sleep(5000);
            System.out.println("end ------ C");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    
    
}
