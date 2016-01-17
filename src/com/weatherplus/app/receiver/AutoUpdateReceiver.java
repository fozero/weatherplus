package com.weatherplus.app.receiver;

import com.weatherplus.app.service.AutoUpdateService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 广播接收器BroadcastReceiver
 * @author SKY
 *
 */
public class AutoUpdateReceiver extends BroadcastReceiver {

	/**
	 * 定时任务执行后会调用onReceive方法
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		//再次去启动 AutoUpdateService服务
		Intent i = new Intent(context, AutoUpdateService.class);
		context.startService(i);
	}

}
