package com.excelib.domain.services.impl;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.excelib.domain.services.intf.Async3Services;

@Service
public class Async3ServicesImpl implements Async3Services{

    public Async3ServicesImpl() {
    }

    @Override
    @Async  
    public Future<String> asyncMethodWithReturnType() {  
        System.out.println("Execute method asynchronously - "   + Thread.currentThread().getName());  
        try {  
            Thread.sleep(5000);  
            return new AsyncResult<String>("hello world !!!!");  
        } catch (InterruptedException e) {  
        }  
       
        return null;  
    }  

}
