package com.yhcomp.aosex02_ipc_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private String Tag = "MyService(Server) ";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("asdd", Tag + "onBind() intent :" + intent);

        Toast.makeText(this, "[RemoteServie] onBind 함수 호출", Toast.LENGTH_LONG).show();

        Log.e("asdd", Tag + "   - return mBinder :" + mBinder);
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("asdd", Tag + "onCreate()");

        Toast.makeText(this, "[RemoteServie] onCrate 함수 호출", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("asdd", Tag + "onDestroy()");

        Toast.makeText(this, "[RemoteServie] onDestroy 함수 호출", Toast.LENGTH_LONG).show();
    }

    RemoteStub.Stub mBinder = new RemoteStub.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.e("asdd", Tag + "RemoteStub.Stub() basicTypes()");

        }

        @Override
        public String getServerPackageName() throws RemoteException {
            String pkgNm = MyService.this.getPackageName();

            Log.e("asdd", Tag + "RemoteStub.Stub() getServerPackageName() :" + pkgNm);

            return pkgNm;
        }
    };

}
