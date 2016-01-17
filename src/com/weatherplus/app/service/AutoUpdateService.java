package com.weatherplus.app.service;
import com.weatherplus.app.receiver.AutoUpdateReceiver;
import com.weatherplus.app.util.HttpCallbackListener;
import com.weatherplus.app.util.HttpUtil;
import com.weatherplus.app.util.Utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
/**
 * 后台自动更新天气服务
 * @author fozero
 */
public class AutoUpdateService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	/**
	 * 会在每次服务启动的时候调用
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		//开启子线程更新天气信息
		new Thread(new Runnable() {
			@Override
			public void run() {
				updateWeather();
				Log.d("AutoUpdateService", "update the weather info after the three hours.");
			}
		}).start();
		
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		int anHour = 3 * 60 * 60 * 1000; // 这是3小时的毫秒数
//		int anHour = 5 * 1000;
		long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
		
		//8小时后执行 广播接收器AutoUpdateReceiver 的 onReceive()方法
		Intent i = new Intent(this, AutoUpdateReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	/**
	* 更新天气信息。
	*/
	private void updateWeather() {
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String weatherCode = prefs.getString("weather_code", "");
		String address = "http://www.weather.com.cn/data/cityinfo/" +weatherCode + ".html";
		
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			@Override
			public void onFinish(String response) {
				//天气数据处理  存储最新的天气信息
				Utility.handleWeatherResponse(AutoUpdateService.this,response);
			}
			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	

}
