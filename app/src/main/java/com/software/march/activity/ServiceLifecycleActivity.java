package com.software.march.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.software.march.R;
import com.software.march.service.local.LifecycleService;
import com.software.march.utils.SPUtils;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Service生命周期
 * @date 2016/12/14
 */
public class ServiceLifecycleActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private Intent intent;
    private boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_service_lifecycle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // 管理绑定服务的生命周期
    // 服务的生命周期比 Activity 的生命周期要简单得多。但是，密切关注如何创建和销毁服务反而更加重要，因为服务可以在用户没有意识到的情况下运行于后台。

    // 服务生命周期（从创建到销毁）可以遵循两条不同的路径：
    // 1.启动服务
    // 该服务在其他组件调用 startService() 时创建，然后无限期运行，且必须通过调用 stopSelf() 来自行停止运行。
    // 此外，其他组件也可以通过调用 stopService() 来停止服务。服务停止后，系统会将其销毁。
    // 2.绑定服务
    // 该服务在另一个组件（客户端）调用 bindService() 时创建。然后，客户端通过 IBinder 接口与服务进行通信。
    // 客户端可以通过调用 unbindService() 关闭连接。多个客户端可以绑定到相同服务，而且当所有绑定全部取消后，系统即会销毁该服务。 （服务不必自行停止运行。）

    // 这两条路径并非完全独立。也就是说，您可以绑定到已经使用 startService() 启动的服务。
    // 例如，可以通过使用 Intent（标识要播放的音乐）调用 startService() 来启动后台音乐服务。
    // 随后，可能在用户需要稍加控制播放器或获取有关当前播放歌曲的信息时，Activity 可以通过调用 bindService() 绑定到服务。
    // 在这种情况下，除非所有客户端均取消绑定，否则 stopService() 或 stopSelf() 不会实际停止服务。

    // 当服务与所有客户端之间的绑定全部取消时，Android 系统便会销毁服务（除非还使用 onStartCommand() 启动了该服务）。
    // 因此，如果您的服务是纯粹的绑定服务，则无需对其生命周期进行管理 — Android 系统会根据它是否绑定到任何客户端代您管理。
    // 不过，如果您选择实现 onStartCommand() 回调方法，则您必须显式停止服务，因为系统现在已将服务视为已启动。
    // 在此情况下，服务将一直运行到其通过 stopSelf() 自行停止，或其他组件调用 stopService() 为止，无论其是否绑定到任何客户端。
    // 此外，如果您的服务已启动并接受绑定，则当系统调用您的 onUnbind() 方法时，如果您想在客户端下一次绑定到服务时接收 onRebind() 调用，则可选择返回 true。
    // onRebind() 返回空值，但客户端仍在其 onServiceConnected() 回调中接收 IBinder。

    // 实现生命周期回调
    // 与 Activity 类似，服务也拥有生命周期回调方法，您可以实现这些方法来监控服务状态的变化并适时执行工作。
    // 注：与 Activity 生命周期回调方法不同，您不需要调用这些回调方法的超类实现。

    // 通过实现这些方法，您可以监控服务生命周期的两个嵌套循环：
    // 1.服务的整个生命周期从调用 onCreate() 开始起，到 onDestroy() 返回时结束。
    // 与 Activity 类似，服务也在 onCreate() 中完成初始设置，并在 onDestroy() 中释放所有剩余资源。
    // 例如，音乐播放服务可以在 onCreate() 中创建用于播放音乐的线程，然后在 onDestroy() 中停止该线程。
    // 无论服务是通过 startService() 还是 bindService() 创建，都会为所有服务调用 onCreate() 和 onDestroy() 方法。
    // 2.服务的有效生命周期从调用 onStartCommand() 或 onBind() 方法开始。
    // 每种方法均有 {Intent 对象，该对象分别传递到 startService() 或 bindService()。
    // 对于启动服务，有效生命周期与整个生命周期同时结束（即便是在 onStartCommand() 返回之后，服务仍然处于活动状态）。
    // 对于绑定服务，有效生命周期在 onUnbind() 返回时结束。
    // 注：尽管启动服务是通过调用 stopSelf() 或 stopService() 来停止，但是该服务并无相应的回调（没有 onStop() 回调）。
    // 因此，除非服务绑定到客户端，否则在服务停止时，系统会将其销毁 — onDestroy() 是接收到的唯一回调。

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_service_lifecycle, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start_service:
                startService();
                break;
            case R.id.stop_service:
                stopService();
                break;
            case R.id.bind_service:
                bindService();
                break;
            case R.id.unbind_service:
                unbindService();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 1.启动服务
    // LifecycleService: LifecycleService()
    // LifecycleService: onCreate()
    // LifecycleService: onStartCommand()
    // 2.停止服务
    // LifecycleService: onDestroy()
    // 3.绑定服务
    // LifecycleService: LifecycleService()
    // LifecycleService: onCreate()
    // LifecycleService: onBind()
    // ServiceLifecycleActivity: onServiceConnected()【onBind返回值不为空才会调用】
    // 4.解绑服务
    // LifecycleService: onUnbind()
    // LifecycleService: onDestroy()

    // 启动服务
    private void startService() {
        if (intent == null) {
            intent = new Intent(this, LifecycleService.class);
            startService(intent);
            Toast.makeText(this, "启动服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经启动了", Toast.LENGTH_SHORT).show();
        }
    }

    // 停止服务
    private void stopService() {
        if (intent != null) {
            stopService(intent);
            intent = null;
            Toast.makeText(this, "停止服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还没有启动", Toast.LENGTH_SHORT).show();
        }
    }

    // 绑定服务
    private void bindService() {
        if (!mBound) {
            Intent intent = new Intent(this, LifecycleService.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            mBound = true;
            Toast.makeText(this, "绑定服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已经绑定了", Toast.LENGTH_SHORT).show();
        }
    }

    // 解绑服务
    private void unbindService() {
        if (mBound) {
            unbindService(mConnection);
            mConnection = null;
            mBound = false;
            Toast.makeText(this, "解绑服务", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还没有绑定", Toast.LENGTH_SHORT).show();
        }
    }

    // 创建连接对象
    private ServiceConnection mConnection = new ServiceConnection() {

        // Called when the connection with the service is established
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e(TAG, "onServiceConnected()");
            mBound = true;
        }

        // Called when the connection with the service disconnects unexpectedly
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceDisconnected()");
            mConnection = null;
            mBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 停止服务
        if (intent != null) {
            stopService(intent);
            intent = null;
        }

        // 解绑服务(Activity销毁,绑定服务必须解绑)
        if (mBound) {
            unbindService(mConnection);
            mConnection = null;
            mBound = false;
        }
    }
}