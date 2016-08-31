package com.bj58.hrg.investment.wx.listener.impl.process;

import org.apache.log4j.Logger;

import com.bj58.hrg.investment.wx.dto.Context;
import com.bj58.hrg.investment.wx.listener.ThreadsMode;

public class SyncThreadMode extends ThreadsMode {

    private final Logger log = Logger.getLogger(SyncThreadMode.class);
    
    @Override
    public String process(Context context) {
        
        try {
            
            return super.process(context);
        } catch (Exception e) {
            log.error("任务处理出错", e);
        }
        return null;
    }

}
