package com.bj58.hrg.investment.wx.listener.impl.process;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.common.Exec;
import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.Service;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;

public class AsyncThreadMode extends ThreadsMode {

    private final Logger log = Logger.getLogger(AsyncThreadMode.class);
    
    @Override
    public String process(final Context context) {
        
        Exec.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                
                try {
                    AsyncThreadMode.this.routeToNext(Service.class, "root", context);
                } catch (Exception e) {
                    log.error("任务处理出错", e);
                }
                return null;
            }
            
        });
        return null; //异步模式忽略返回
    }

}
