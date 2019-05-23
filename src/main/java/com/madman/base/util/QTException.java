package com.madman.base.util;

import java.util.HashMap;
import java.util.Map;

public class QTException extends Exception {

    private static final long   serialVersionUID = 1L;
    private              String respCode         = "9999";
    private              String respMsg          = "系统繁忙，请稍后重试[NT]";
    private              String respMsgOut       = "系统繁忙，请稍后重试[NT]";

    public QTException() {
        this.respMsg = "系统繁忙，请稍后重试[NT]";
    }

    public QTException(String errCode, String respMsg, String respMsgOut) {
        super(respMsg);
        this.respCode = errCode;
        this.respMsg = respMsg;
        this.respMsgOut = respMsgOut;
    }

    public QTException(String errCode, String respMsg) {
        super(respMsg);
        this.respCode = errCode;
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public static final void setResultMapContenxt(Map<String, Object> items) {
        if (!items.containsKey("result")) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("message", items.get("MSG_TEXT"));
            result.put("resultCode", items.get("MSG_CODE"));
            items.put("result", result);
        }

        if (!items.containsKey("resultBean")) {
            Map<String, Object> resultBean = new HashMap<String, Object>();
            items.put("resultBean", resultBean);
        }

        if (!items.containsKey("summary")) {
            Map<String, Object> resultBean = new HashMap<String, Object>();
            items.put("summary", resultBean);
        }
    }

    /**
     * 描述：获取属性值.<br/>
     * 创建人：madman <br/>
     * 返回类型：@return respMsgOut .<br/>
     */
    public String getRespMsgOut() {
        return respMsgOut;
    }

    /**
     * 创建人：madman <br/>
     * 创建时间：2017年4月21日 上午11:24:24 <br/>
     * 参数: @param respMsgOut 设置值. <br/>
     */
    public void setRespMsgOut(String respMsgOut) {
        this.respMsgOut = respMsgOut;
    }

    @Override
    public String getMessage() {
        StringBuffer br = new StringBuffer();
        if (EmptyChecker.isNotEmpty(respCode)) {
            br.append("返回码:" + respCode).append("; ");
        }
        if (EmptyChecker.isNotEmpty(respMsg)) {
            br.append("返回内容:" + respMsg).append("; ");
        }
        if (EmptyChecker.isNotEmpty(respCode)) {
            br.append("对外返回描述:" + respMsgOut);
        }
        return br.toString();
    }

}
