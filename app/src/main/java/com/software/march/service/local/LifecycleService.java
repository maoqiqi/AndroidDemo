package com.software.march.service.local;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 服务生命周期
 * @date 2016/12/30
 */
public class LifecycleService extends Service {

    private final String TAG = getClass().getSimpleName();

    private int mStartMode;// indicates how to behave if the service is killed
    private IBinder mBinder /*= new Binder()*/;// interface for clients that bind
    private boolean mAllowRebind;// indicates whether onRebind should be used

    public LifecycleService() {
        Log.e(TAG, TAG + "()");
    }

    // 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
    // 如果服务已在运行，则不会调用此方法。
    @Override
    public void onCreate() {
        // The service is being created
        Log.e(TAG, "onCreate()");
    }

    // 当另一个组件（如 Activity）通过调用 startService() 请求启动服务时，系统将调用此方法。
    // 一旦执行此方法，服务即会启动并可在后台无限期运行。
    // 如果您实现此方法，则在服务工作完成后，需要由您通过调用 stopSelf() 或 stopService() 来停止服务。
    // （如果您只想提供绑定，则无需实现此方法。）
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        Log.e(TAG, "onStartCommand()");
        return mStartMode;
    }

    // 当另一个组件想通过调用 bindService() 与服务绑定（例如执行 RPC）时，系统将调用此方法。
    // 在此方法的实现中，您必须通过返回 IBinder 提供一个接口，供客户端用来与服务进行通信。
    // 请务必实现此方法，但如果您并不希望允许绑定，则应返回 null。
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        Log.e(TAG, "onBind()");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        Log.e(TAG, "onUnbind()");
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
        Log.e(TAG, "onRebind()");
    }

    // 当服务不再使用且将被销毁时，系统将调用此方法。
    // 服务应该实现此方法来清理所有资源，如线程、注册的侦听器、接收器等。
    // 这是服务接收的最后一个调用。
    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        Log.e(TAG, "onDestroy()");
    }
}