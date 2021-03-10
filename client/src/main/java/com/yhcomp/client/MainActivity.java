package com.yhcomp.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yhcomp.aosex02_ipc_service.RemoteStub;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RemoteStub mBinder = null;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }
}
