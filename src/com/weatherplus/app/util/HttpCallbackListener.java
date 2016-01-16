package com.weatherplus.app.util;
/**
 * 回调函数接口
 * @author fozero
 */
public interface HttpCallbackListener {

	void onFinish(String response);
	void onError(Exception e);
}
