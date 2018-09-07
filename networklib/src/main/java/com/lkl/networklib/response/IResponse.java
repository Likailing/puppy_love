package com.lkl.networklib.response;

/**
 * Created by Likailing date:2018/9/7 0007 time:15:59
 * Email : 13297970902@163.com
 * Description :
 */

public interface IResponse<T> {
    IResponse<T> parserObject2String(String jsonString);
    IResponse<T> parserObject2Integer(String jsonString);
    IResponse<T> parserObject2Boolean(String jsonString);
    IResponse<T> parserObject2Double(String jsonString);
    IResponse<T> parserObject2Long(String jsonString);

    IResponse<T> parserObject(String jsonString);

    boolean isSuccess();

    String getMessage();

    int getCode();

    T getContent();
}
