package com.software.march.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.software.march.bean.PersonBean;
import com.software.march.service.remote.IRemoteService;

public class RemoteServiceCallActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private Button btnBindServiceWithAidl;
    private Button btnUnbindServiceWithAidl;
    private Button btnGetPid;
    private Button btnPersonById;

    private IRemoteService mIRemoteService;
    private boolean mRemoteServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_service_call);

        btnBindServiceWithAidl = (Button) findViewById(R.id.btn_bind_service_with_aidl);
        btnUnbindServiceWithAidl = (Button) findViewById(R.id.btn_unbind_service_with_aidl);
        btnGetPid = (Button) findViewById(R.id.btn_get_pid);
        btnPersonById = (Button) findViewById(R.id.btn_person_by_id);

        btnBindServiceWithAidl.setOnClickListener(this);
        btnUnbindServiceWithAidl.setOnClickListener(this);
        btnGetPid.setOnClickListener(this);
        btnPersonById.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

    private void bindServiceWithAIDL() {
        // Bind to RemoteService
        if (!mRemoteServiceBound) {
            Intent intent = new Intent("com.software.march.REMOTE_SERVICE");
            intent.setPackage("com.software.march");
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
        Log.e(TAG, TAG + " getPid():" + Process.myPid());
        try {
            mIRemoteService.getPid();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        // com.software.march.client E/MainActivity: MainActivity getPid():2668
        // com.software.march E/RemoteService: RemoteService getPid():29203
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
        if (mIRemoteService != null) {
            unbindService(mRemoteServiceConnection);
            mIRemoteService = null;
            mRemoteServiceBound = false;
        }
    }
}