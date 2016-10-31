package com.excelib.domain.services.intf;

import java.util.concurrent.Future;

public interface Async3Services {

    /** 有返回值的异步方法*/
    public Future<String> asyncMethodWithReturnType() ;

}
