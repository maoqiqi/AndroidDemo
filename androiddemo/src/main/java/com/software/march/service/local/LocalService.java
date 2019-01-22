package com.software.march.service.local;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 绑定服务 - 扩展 Binder 类
 * @date 2016/12/30
 */
public class LocalService extends Service {

    // 扩展 Binder 类
    // 如果您的服务仅供本地应用使用，不需要跨进程工作，则可以实现自有 Binder 类，让您的客户端通过该类直接访问服务中的公共方法。
    // 注：此方法只有在客户端和服务位于同一应用和进程内这一最常见的情况下方才有效。
    // 例如，对于需要将 Activity 绑定到在后台播放音乐的自有服务的音乐应用，此方法非常有效。

    // 以下是具体的设置方法：
    // 1.在您的服务中，创建一个可满足下列任一要求的 Binder 实例：
    // 1) 包含客户端可调用的公共方法
    // 2) 返回当前 Service 实例，其中包含客户端可调用的公共方法
    // 3) 或返回由服务承载的其他类的实例，其中包含客户端可调用的公共方法
    // 2.从 onBind() 回调方法返回此 Binder 实例。
    // 3.在客户端中，从 onServiceConnected() 回调方法接收 Binder，并使用提供的方法调用绑定服务。
    // 注：之所以要求服务和客户端必须在同一应用内，是为了便于客户端转换返回的对象和正确调用其 API。
    // 服务和客户端还必须在同一进程内，因为此方法不执行任何跨进程编组。

    // LocalBinder 为客户端提供 getService() 方法，以检索 LocalService 的当前实例。
    // 这样，客户端便可调用服务中的公共方法。 例如，客户端可调用服务中的 getRandomNumber()。

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {

        // Return this instance of LocalService so clients can call public methods
        public LocalService getService() {
            // 返回LocalService实例, 客户端可以调用
            return LocalService.this;
        }
    }
}