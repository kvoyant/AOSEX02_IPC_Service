package com.yhcomp.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yhcomp.aosex02_ipc_service.RemoteStub;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RemoteStub mBinder = null;
    TextView text;
    private String Tag = "MainActivity(client) ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e("asdd", Tag + "onCreate() Bundle :" + savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.packagenametxt);

        Button startbuttion = (Button)this.findViewById(R.id.startbutton);
        Button getbutton = (Button)this.findViewById(R.id.getbutton);
        Button stopbutton = (Button)this.findViewById(R.id.stopbutton);

        startbuttion.setOnClickListener(this);
        getbutton.setOnClickListener(this);
        stopbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.e("asdd", Tag + "onClick() view.getId() :" + view.getId());

        switch(view.getId()) {
            case R.id.startbutton: {
                Toast.makeText(this, "원격 서비스 실행", Toast.LENGTH_LONG).show();

                Log.e("asdd", Tag + "   - bindService를 통한 원격 서비스 연결 시도");

                Intent intent = new Intent();
                ComponentName name = new ComponentName(
                        "com.yhcomp.aosex02_ipc_service",//패키지명
                        "com.yhcomp.aosex02_ipc_service.MyService" //원격 서비스 클래스명
                );

                intent.setComponent(name);

                bindService(intent, srvConn, Context.BIND_AUTO_CREATE);
                break;
            }

            case R.id.getbutton : {
                Toast.makeText(this, "패키지명 가져오기", Toast.LENGTH_SHORT).show();

                String packageName = null;

                try {
                    packageName = mBinder.getServerPackageName();
                } catch (RemoteException e) {
                    Log.e("asdd", Tag + "   - 패키지명 가져오기 RemoteException :" + e.toString());
                    e.printStackTrace();
                }
                Log.e("asdd", Tag + "   - 패키지명 가져오기 :" + packageName);
            }
        }

    }

    ServiceConnection srvConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("asdd", Tag + "onServiceConnected() ~ 연결");
            Log.e("asdd", Tag + "   - componentName :" + componentName);
            Log.e("asdd", Tag + "   - iBinder() :" + iBinder);

            mBinder = RemoteStub.Stub.asInterface(iBinder);

            Log.e("asdd", Tag + "onServiceConnected() ~ 성공!");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("asdd", Tag + "onServiceDisconnected() ~ ");
            Log.e("asdd", Tag + "   - componentName :" + componentName);

        }
    };
}
