package com.software.march.service.remote;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 绑定服务 - 使用 Messenger
 * @date 2016/12/30
 */
public class MessengerService extends Service {

    // 使用 Messenger
    // 如需让服务与远程进程通信，则可使用 Messenger 为您的服务提供接口。利用此方法，您无需使用 AIDL 便可执行进程间通信 (IPC)。
    // 以下是 Messenger 的使用方法摘要：
    // 1.服务实现一个 Handler，由其接收来自客户端的每个调用的回调
    // 2.Handler 用于创建 Messenger 对象（对 Handler 的引用）
    // 3.Messenger 创建一个 IBinder，服务通过 onBind() 使其返回客户端
    // 4.客户端使用 IBinder 将 Messenger（引用服务的 Handler）实例化，然后使用后者将 Message 对象发送给服务
    // 5.服务在其 Handler 中（具体地讲，是在 handleMessage() 方法中）接收每个 Message。
    // 这样，客户端并没有调用服务的“方法”。而客户端传递的“消息”（Message 对象）是服务在其 Handler 中接收的。

    // 请注意，服务就是在 Handler 的 handleMessage() 方法中接收传入的 Message，并根据 what 成员决定下一步操作。

    /**
     * Command to the service to display a message
     */
    public static final int MSG_SAY_HELLO = 1;

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}