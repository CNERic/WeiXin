package com.bj58.hrg.investment.wx.listener.impl.service.end.message;

import com.bj58.hrg.investment.wx.annotation.Param;
import com.bj58.hrg.investment.wx.listener.impl.service.Responsable;

public class AbstractMessage extends Responsable {

    @Param protected String MsgId;        //消息id，64位整型
    
}
