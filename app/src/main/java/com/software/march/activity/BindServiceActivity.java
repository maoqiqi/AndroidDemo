package com.software.march.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.bean.PersonBean;
import com.software.march.service.local.LocalService;
import com.software.march.service.remote.IRemoteService;
import com.software.march.service.remote.MessengerService;
import com.software.march.service.remote.RemoteService;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description 绑定服务
 * @date 2016/12/30
 */
public class BindServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBindServiceWithBinder;
    private Button btnUnbindServiceWithBinder;
    private Button btnGetRandomNumber;
    private Button btnBindServiceWithMessenger;
    private Button btnUnbindServiceWithMessenger;
    private Button btnSayHello;
    private Button btnBindServiceWithAidl;
    private Button btnUnbindServiceWithAidl;
    private Button btnGetPid;
    private Button btnGetPersonById;

    private LocalService mLocalService;
    private boolean mLocalServiceBound = false;

    private Messenger mMessenger;
    private boolean mMessengerServiceBound = false;

    private IRemoteService mIRemoteService;
    private boolean mRemoteServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_bind_service);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBindServiceWithBinder = (Button) findViewById(R.id.btn_bind_service_with_binder);
        btnUnbindServiceWithBinder = (Button) findViewById(R.id.btn_unbind_service_with_binder);
        btnGetRandomNumber = (Button) findViewById(R.id.btn_get_random_number);
        btnBindServiceWithMessenger = (Button) findViewById(R.id.btn_bind_service_with_messenger);
        btnUnbindServiceWithMessenger = (Button) findViewById(R.id.btn_unbind_service_with_messenger);
        btnSayHello = (Button) findViewById(R.id.btn_say_hello);
        btnBindServiceWithAidl = (Button) findViewById(R.id.btn_bind_service_with_aidl);
        btnUnbindServiceWithAidl = (Button) findViewById(R.id.btn_unbind_service_with_aidl);
        btnGetPid = (Button) findViewById(R.id.btn_get_pid);
        btnGetPersonById = (Button) findViewById(R.id.btn_person_by_id);

        btnBindServiceWithBinder.setOnClickListener(this);
        btnUnbindServiceWithBinder.setOnClickListener(this);
        btnGetRandomNumber.setOnClickListener(this);
        btnBindServiceWithMessenger.setOnClickListener(this);
        btnUnbindServiceWithMessenger.setOnClickListener(this);
        btnSayHello.setOnClickListener(this);
        btnBindServiceWithAidl.setOnClickListener(this);
        btnUnbindServiceWithAidl.setOnClickListener(this);
        btnGetPid.setOnClickListener(this);
        btnGetPersonById.setOnClickListener(this);
    }

    // 绑定服务
    // 绑定服务是客户端-服务器接口中的服务器。
    // 绑定服务可让组件（例如 Activity）绑定到服务、发送请求、接收响应，甚至执行进程间通信 (IPC)。
    // 绑定服务通常只在为其他应用组件服务时处于活动状态，不会无限期在后台运行。

    // 基础知识
    // 绑定服务是 Service 类的实现，可让其他应用与其绑定和交互。要提供服务绑定，您必须实现 onBind() 回调方法。
    // 该方法返回的 IBinder 对象定义了客户端用来与服务进行交互的编程接口。
    // 客户端可通过调用 bindService() 绑定到服务。调用时，它必须提供 ServiceConnection 的实现，后者会监控与服务的连接。
    // bindService() 方法会立即无值返回，但当 Android 系统创建客户端与服务之间的连接时，
    // 会对 ServiceConnection 调用 onServiceConnected()，向客户端传递用来与服务通信的 IBinder。
    // 多个客户端可同时连接到一个服务。
    // 不过，只有在第一个客户端绑定时，系统才会调用服务的 onBind() 方法来检索 IBinder。
    // 系统随后无需再次调用 onBind()，便可将同一 IBinder 传递至任何其他绑定的客户端。
    // 当最后一个客户端取消与服务的绑定时，系统会将服务销毁（除非 startService() 也启动了该服务）。
    // 当您实现绑定服务时，最重要的环节是定义您的 onBind() 回调方法返回的接口。
    // 您可以通过几种不同的方法定义服务的 IBinder 接口，下文对这些方法逐一做了阐述。

    // 创建绑定服务
    // 创建提供绑定的服务时，您必须提供 IBinder，用以提供客户端用来与服务进行交互的编程接口。
    // 您可以通过三种方法定义接口：
    // 1.扩展 Binder 类
    // 如果服务是供您的自有应用专用，并且在与客户端相同的进程中运行（常见情况）。则应通过扩展 Binder 类并从 onBind() 返回它的一个实例来创建接口。
    // 客户端收到 Binder 后，可利用它直接访问 Binder 实现中乃至 Service 中可用的公共方法。
    // 如果服务只是您的自有应用的后台工作线程，则优先采用这种方法。不以这种方式创建接口的唯一原因是，您的服务被其他应用或不同的进程占用。
    // 2.使用 Messenger
    // 如需让接口跨不同的进程工作，则可使用 Messenger 为服务创建接口。服务可以这种方式定义对应于不同类型 Message 对象的 Handler。
    // 此 Handler 是 Messenger 的基础，后者随后可与客户端分享一个 IBinder， 从而让客户端能利用 Message 对象向服务发送命令。
    // 此外，客户端还可定义自有 Messenger，以便服务回传消息。
    // 这是执行进程间通信 (IPC) 的最简单方法，因为 Messenger 会在单一线程中创建包含所有请求的队列，这样您就不必对服务进行线程安全设计。
    // 3.使用 AIDL
    // AIDL（Android 接口定义语言）执行所有将对象分解成原语的工作，操作系统可以识别这些原语并将它们编组到各进程中，以执行 IPC。
    // 之前采用 Messenger 的方法实际上是以 AIDL 作为其底层结构。
    // 如上所述，Messenger 会在单一线程中创建包含所有客户端请求的队列，以便服务一次接收一个请求。
    // 不过，如果您想让服务同时处理多个请求，则可直接使用 AIDL。
    // 在此情况下，您的服务必须具备多线程处理能力，并采用线程安全式设计。
    // 如需直接使用 AIDL，您必须创建一个定义编程接口的 .aidl 文件。
    // Android SDK 工具利用该文件生成一个实现接口并处理 IPC 的抽象类，您随后可在服务内对其进行扩展。
    // 注：大多数应用“都不会”使用 AIDL 来创建绑定服务，因为它可能要求具备多线程处理能力，并可能导致实现的复杂性增加。
    // 因此，AIDL 并不适合大多数应用，本文也不会阐述如何将其用于您的服务。

    // 绑定到服务
    // 应用组件（客户端）可通过调用 bindService() 绑定到服务。Android 系统随后调用服务的 onBind() 方法，该方法返回用于与服务交互的 IBinder。
    // 绑定是异步的。bindService() 会立即返回，“不会”使 IBinder 返回客户端。
    // 要接收 IBinder，客户端必须创建一个 ServiceConnection 实例，并将其传递给 bindService()。
    // ServiceConnection 包括一个回调方法，系统通过调用它来传递 IBinder。
    // 注：只有 Activity、服务和内容提供程序可以绑定到服务 — 您无法从广播接收器绑定到服务。
    // 因此，要想从您的客户端绑定到服务，您必须：
    // 1.实现 ServiceConnection。
    // 您的实现必须重写两个回调方法：
    // onServiceConnected()：系统会调用该方法以传递服务的　onBind() 方法返回的 IBinder。
    // onServiceDisconnected()：Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。当客户端取消绑定时，系统“不会”调用该方法。
    // 2.调用 bindService()，传递 ServiceConnection 实现。
    // 3.当系统调用您的 onServiceConnected() 回调方法时，您可以使用接口定义的方法开始调用服务。
    // 4.要断开与服务的连接，请调用 unbindService()。
    // 如果应用在客户端仍绑定到服务时销毁客户端，则销毁会导致客户端取消绑定。 更好的做法是在客户端与服务交互完成后立即取消绑定客户端。 这样可以关闭空闲服务。

    // bindService(Intent service, ServiceConnection conn, int flags)
    // 第一个参数是一个 Intent，用于显式命名要绑定的服务（但 Intent 可能是隐式的）
    // 第二个参数是 ServiceConnection 对象
    // 第三个参数是一个指示绑定选项的标志。它通常应该是 BIND_AUTO_CREATE，以便创建尚未激活的服务。
    // 其他可能的值为 BIND_DEBUG_UNBIND 和 BIND_NOT_FOREGROUND，或 0（表示无）。

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bind_service_with_binder:
                bindServiceWithBinder();
                break;
            case R.id.btn_unbind_service_with_binder:
                unbindServiceWithBinder();
                break;
            case R.id.btn_get_random_number:
                getRandomNumber();
                break;
            case R.id.btn_bind_service_with_messenger:
                bindServiceWithMessenger();
                break;
            case R.id.btn_unbind_service_with_messenger:
                unbindServiceWithMessenger();
                break;
            case R.id.btn_say_hello:
                sayHello();
                break;
            case R.id.btn_bind_service_with_aidl:
                bindServiceWithAIDL();
                break;
            case R.id.btn_unbind_service_with_aidl:
                unbindServiceWithAIDL();
                break;
            case R.id.btn_get_pid:
                getPid();
                break;
            case R.id.btn_person_by_id:
                getPersonById();
                break;
        }
    }

    private void bindServiceWithBinder() {
        // Bind to LocalService
        if (!mLocalServiceBound) {
            Intent intent = new Intent(this, LocalService.class);
            bindService(intent, mLocalServiceConnection, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "扩展 Binder 类-绑定服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经绑定了", Toast.LENGTH_SHORT).show();
        }
    }

    private void unbindServiceWithBinder() {
        // Unbind from the service
        if (mLocalServiceBound) {
            unbindService(mLocalServiceConnection);
            mLocalService = null;
            mLocalServiceBound = false;
            Toast.makeText(this, "扩展 Binder 类-解绑服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还没有绑定", Toast.LENGTH_SHORT).show();
        }
    }

    // 点击按钮时，调用LocalService 的 getRandomNumber() ：
    private void getRandomNumber() {
        if (!mLocalServiceBound) {
            Toast.makeText(this, "还没有绑定", Toast.LENGTH_SHORT).show();
            return;
        }
        // Call a method from the LocalService.
        // However, if this call were something that might hang, then this request should
        // occur in a separate thread to avoid slowing down the activity performance.
        int num = mLocalService.getRandomNumber();
        Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mLocalServiceConnection = new ServiceConnection() {

        // Called when the connection with the service is established
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance.
            // Because we have bound to an explicit service that is running in our own process,
            // we can cast its IBinder to a concrete class and directly access it.
            LocalService.LocalBinder binder = (LocalService.LocalBinder) iBinder;
            mLocalService = binder.getService();
            mLocalServiceBound = true;
        }

        // Called when the connection with the service disconnects unexpectedly
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mLocalService = null;
            mLocalServiceBound = false;
        }
    };

    private void bindServiceWithMessenger() {
        // Bind to the service
        if (!mMessengerServiceBound) {
            Intent intent = new Intent(this, MessengerService.class);
            bindService(intent, mMessengerServiceConnection, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "使用 Messenger-绑定服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经绑定了", Toast.LENGTH_SHORT).show();
        }
    }

    private void unbindServiceWithMessenger() {
        // Unbind from the service
        if (mMessengerServiceBound) {
            unbindService(mMessengerServiceConnection);
            mMessenger = null;
            mMessengerServiceBound = false;
            Toast.makeText(this, "使用 Messenger-解绑服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还没有绑定", Toast.LENGTH_SHORT).show();
        }
    }

    public void sayHello() {
        if (!mMessengerServiceBound) {
            Toast.makeText(this, "还没有绑定", Toast.LENGTH_SHORT).show();
            return;
        }
        // Create and send a message to the service, using a supported 'what' value
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class for interacting with the main interface of the service.
     */
    private ServiceConnection mMessengerServiceConnection = new ServiceConnection() {

        // Called when the connection with the service is established
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // This is called when the connection with the service has been established,
            // giving us the object we can use to interact with the service.
            // We are communicating with the service using a Messenger,
            // so here we get a client-side representation of that from the raw IBinder object.
            mMessenger = new Messenger(iBinder);
            mMessengerServiceBound = true;
        }

        // Called when the connection with the service disconnects unexpectedly
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            // This is called when the connection with the service has been unexpectedly disconnected -- that is,
            // its process crashed.
            mMessenger = null;
            mMessengerServiceBound = false;
        }
    };

    // 请注意，此示例并未说明服务如何对客户端作出响应。 如果您想让服务作出响应，则还需要在客户端中创建一个 Messenger。
    // 然后，当客户端收到 onServiceConnected() 回调时，会向服务发送一条 Message，并在其 send() 方法的 replyTo 参数中包含客户端的 Messenger。

    // 如需查看如何提供双向消息传递的示例，请参阅 MessengerService.java（服务）和 MessengerServiceActivities.java（客户端）示例。

    private void bindServiceWithAIDL() {
        // Bind to RemoteService
        if (!mRemoteServiceBound) {
            Intent intent = new Intent(this, RemoteService.class);
            bindService(intent, mRemoteServiceConnection, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "使用 AIDL-绑定服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经绑定了", Toast.LENGTH_SHORT).show();
        }
    }

    private void unbindServiceWithAIDL() {
        // Unbind from the service
        if (mRemoteServiceBound) {
            unbindService(mRemoteServiceConnection);
            mIRemoteService = null;
            mRemoteServiceBound = false;
            Toast.makeText(this, "使用 AIDL-解绑服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还没有绑定", Toast.LENGTH_SHORT).show();
        }
    }

    private void getPid() {
        if (!mRemoteServiceBound) {
            Toast.makeText(this, "还没有绑定", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "BindServiceActivity getPid():" + Process.myPid(), Toast.LENGTH_SHORT).show();
        try {
            mIRemoteService.getPid();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void getPersonById() {
        if (!mRemoteServiceBound) {
            Toast.makeText(this, "还没有绑定", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            PersonBean bean = mIRemoteService.getPersonById(1);
            Toast.makeText(this, bean.getUserName() + "--" + bean.getNickName() + "--" + bean.getAge(), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection mRemoteServiceConnection = new ServiceConnection() {

        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            mIRemoteService = IRemoteService.Stub.asInterface(service);
            mRemoteServiceBound = true;
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            mIRemoteService = null;
            mRemoteServiceBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocalServiceBound) {
            unbindService(mLocalServiceConnection);
            mLocalService = null;
            mLocalServiceBound = false;
        }
        if (mMessengerServiceBound) {
            unbindService(mMessengerServiceConnection);
            mMessenger = null;
            mMessengerServiceBound = false;
        }
        if (mIRemoteService != null) {
            unbindService(mRemoteServiceConnection);
            mIRemoteService = null;
            mRemoteServiceBound = false;
        }
    }
}