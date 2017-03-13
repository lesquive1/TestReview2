package com.luis.testreview2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView MyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REGISTRAR
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("my_action");
        registerReceiver(MyServiceBroadcastRec, intentFilter);


        MyTextView = (TextView) findViewById(R.id.textView);

        Button SendButton = (Button) findViewById(R.id.button);
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MyService.class);

                Bundle misDatos = new Bundle();
                misDatos.putString("comando","Test 1");
                intent.putExtras(misDatos);

                startService(intent);

                //MyTextView.setText("Hola!");

            }
        });

    }

    // BROADCAST
    private final BroadcastReceiver MyServiceBroadcastRec = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //String accion = intent.getAction();

            Bundle misDatos = intent.getExtras();
            String comando = misDatos.getString("mensaje");

            MyTextView.setText(comando);


        }
    };
}
