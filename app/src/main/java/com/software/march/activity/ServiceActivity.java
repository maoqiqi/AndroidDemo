package com.software.march.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.software.march.R;
import com.software.march.utils.SPUtils;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLifecycle;
    private Button btnBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SPUtils.getThemeRes(this));
        setContentView(R.layout.activity_service);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLifecycle = (Button) findViewById(R.id.btn_lifecycle);
        btnBindService = (Button) findViewById(R.id.btn_bind_service);

        btnLifecycle.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
    }

    // 服务
    // Service 是一个可以在后台执行长时间运行操作而不提供用户界面的应用组件。
    // 服务可由其他应用组件启动，而且即使用户切换到其他应用，服务仍将在后台继续运行。
    // 此外，组件可以绑定到服务，以与之进行交互，甚至是执行进程间通信 (IPC)。
    // 例如，服务可以处理网络事务、播放音乐，执行文件 I/O 或与内容提供程序交互，而所有这一切均可在后台进行。

    // 服务基本上分为两种形式：
    // 1.启动:
    // 当应用组件（如 Activity）通过调用 startService() 启动服务时，服务即处于“启动”状态。
    // 一旦启动，服务即可在后台无限期运行，即使启动服务的组件已被销毁也不受影响。
    // 已启动的服务通常是执行单一操作，而且不会将结果返回给调用方。
    // 例如，它可能通过网络下载或上传文件。 操作完成后，服务会自行停止运行。
    // 2.绑定:
    // 当应用组件通过调用 bindService() 绑定到服务时，服务即处于“绑定”状态。
    // 绑定服务提供了一个客户端-服务器接口，允许组件与服务进行交互、发送请求、获取结果，
    // 甚至是利用进程间通信 (IPC) 跨进程执行这些操作。 仅当与另一个应用组件绑定时，绑定服务才会运行。
    // 多个组件可以同时绑定到该服务，但全部取消绑定后，该服务即会被销毁。

    // * 服务可以同时以这两种方式运行，也就是说，它既可以是启动服务（以无限期运行），也允许绑定。
    // 问题只是在于您是否实现了一组回调方法：onStartCommand()（允许组件启动服务）和 onBind()（允许绑定服务）。
    // * 无论应用是处于启动状态还是绑定状态，抑或处于启动并且绑定状态，
    // 任何应用组件均可像使用 Activity 那样通过调用 Intent 来使用服务（即使此服务来自另一应用）。
    // 不过，您可以通过清单文件将服务声明为私有服务，并阻止其他应用访问。
    // 注意：服务在其托管进程的主线程中运行，它既不创建自己的线程，也不在单独的进程中运行（除非另行指定）。
    // 这意味着，如果服务将执行任何 CPU 密集型工作或阻止性操作（例如 MP3 播放或联网），
    // 则应在服务内创建新线程来完成这项工作。通过使用单独的线程，可以降低发生“应用无响应”(ANR) 错误的风险，
    // 而应用的主线程仍可继续专注于运行用户与 Activity 之间的交互。

    // 基础知识
    // 要创建服务，您必须创建 Service 的子类（或使用它的一个现有子类）。
    // 在实现中，您需要重写一些回调方法，以处理服务生命周期的某些关键方面并提供一种机制将组件绑定到服务（如适用）。

    // 创建启动服务
    // 1.启动服务由另一个组件通过调用 startService() 启动，这会导致调用服务的 onStartCommand() 方法。
    // 2.服务启动之后，其生命周期即独立于启动它的组件，并且可以在后台无限期地运行，即使启动服务的组件已被销毁也不受影响。
    // 因此，服务应通过调用 stopSelf() 结束工作来自行停止运行，或者由另一个组件通过调用 stopService() 来停止它。
    // 3.应用组件（如 Activity）可以通过调用 startService() 方法并传递 Intent 对象（指定服务并包含待使用服务的所有数据）
    // 来启动服务。服务通过 onStartCommand() 方法接收此 Intent。
    // 4.例如，假设某 Activity 需要将一些数据保存到在线数据库中。
    // 该 Activity 可以启动一个协同服务，并通过向 startService() 传递一个 Intent，为该服务提供要保存的数据。
    // 服务通过 onStartCommand() 接收 Intent，连接到互联网并执行数据库事务。事务完成之后，服务会自行停止运行并随即被销毁。
    // 注意：默认情况下，服务与服务声明所在的应用运行于同一进程，而且运行于该应用的主线程中。
    // 因此，如果服务在用户与来自同一应用的 Activity 进行交互时执行密集型或阻止性操作，则会降低 Activity 性能。
    // 为了避免影响应用性能，您应在服务内启动新线程。

    // 创建绑定服务
    // 绑定服务允许应用组件通过调用 bindService() 与其绑定，以便创建长期连接（通常不允许组件通过调用 startService() 来启动它）。
    // 如需与 Activity 和其他应用组件中的服务进行交互，或者需要通过进程间通信 (IPC) 向其他应用公开某些应用功能，则应创建绑定服务。
    // 要创建绑定服务，必须实现 onBind() 回调方法以返回 IBinder，用于定义与服务通信的接口。
    // 然后，其他应用组件可以调用 bindService() 来检索该接口，并开始对服务调用方法。
    // 服务只用于与其绑定的应用组件，因此如果没有组件绑定到服务，则系统会销毁服务（您不必按通过 onStartCommand() 启动的服务那样来停止绑定服务）。
    // 要创建绑定服务，首先必须定义指定客户端如何与服务通信的接口。
    // 服务与客户端之间的这个接口必须是 IBinder 的实现，并且服务必须从 onBind() 回调方法返回它。
    // 一旦客户端收到 IBinder，即可开始通过该接口与服务进行交互。
    // 多个客户端可以同时绑定到服务。客户端完成与服务的交互后，会调用 unbindService() 取消绑定。一旦没有客户端绑定到该服务，系统就会销毁它。
    // 有多种方法实现绑定服务，其实现比启动服务更为复杂，因此绑定服务将在有关绑定服务的单独文档中专门讨论。
    // 如果组件是通过调用 bindService() 来创建服务（且未调用 onStartCommand()，则服务只会在该组件与其绑定时运行。
    // 一旦该服务与所有客户端之间的绑定全部取消，系统便会销毁它。

    // 仅当内存过低且必须回收系统资源以供具有用户焦点的 Activity 使用时，Android 系统才会强制停止服务。
    // 如果将服务绑定到具有用户焦点的 Activity，则它不太可能会终止；
    // 如果将服务声明为在前台运行（稍后讨论），则它几乎永远不会终止。
    // 或者，如果服务已启动并要长时间运行，则系统会随着时间的推移降低服务在后台任务列表中的位置，
    // 而服务也将随之变得非常容易被终止；如果服务是启动服务，则您必须将其设计为能够妥善处理系统对它的重启。
    // 如果系统终止服务，那么一旦资源变得再次可用，系统便会重启服务（不过这还取决于从 onStartCommand() 返回的值，
    // 本文稍后会对此加以讨论）。如需了解有关系统会在何时销毁服务的详细信息，请参阅进程和线程文档。

    // 使用清单文件声明服务
    // android:name 属性是唯一必需的属性，用于指定服务的类名。
    // 应用一旦发布，即不应更改此类名，如若不然，可能会存在因依赖显式 Intent 启动或绑定服务而破坏代码的风险
    // （请阅读博客文章Things That Cannot Change[不能更改的内容]）。

    // 为了确保应用的安全性，请始终使用显式 Intent 启动或绑定 Service，且不要为服务声明 Intent 过滤器。
    // 启动哪个服务存在一定的不确定性，而如果对这种不确定性的考量非常有必要，
    // 则可为服务提供 Intent 过滤器并从 Intent 中排除相应的组件名称，
    // 但随后必须使用 setPackage() 方法设置 Intent 的软件包，这样可以充分消除目标服务的不确定性。
    // 此外，还可以通过添加 android:exported 属性并将其设置为 "false"，确保服务仅适用于您的应用。
    // 这可以有效阻止其他应用启动您的服务，即便在使用显式 Intent 时也如此。

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lifecycle:
                startActivity(new Intent(this, ServiceLifecycleActivity.class));
                break;
            case R.id.btn_bind_service:
                startActivity(new Intent(this, BindServiceActivity.class));
                break;
        }
    }
}