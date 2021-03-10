package com.yhcomp.aosex02_ipc_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        Toast.makeText(this, "[RemoteServie] onBind 함수 호출", Toast.LENGTH_LONG).show();

        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "[RemoteServie] onCrate 함수 호출", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "[RemoteServie] onDestroy 함수 호출", Toast.LENGTH_LONG).show();
    }

    RemoteStub.Stub mBinder = new RemoteStub.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getServerPackageName() throws RemoteException {
            return MyService.this.getPackageName();
        }
    };

}
