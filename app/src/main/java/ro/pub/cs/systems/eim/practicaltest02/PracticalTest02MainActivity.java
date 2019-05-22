package ro.pub.cs.systems.eim.practicaltest02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PracticalTest02MainActivity extends AppCompatActivity {

    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;
    EditText text_debug;

    Spinner spinner1;

    Button button1;
    Button button2;

    ServerThread serverTh = null;
    ClientThread clientTh = null;

    private my_ButtonClickListener my_buttonClickListener = new my_ButtonClickListener();
    private class my_ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {

            Log.e(Constants.TAG, "[GUI] -------------------------");
            String port;
            String address;
            String city;
            String typeInfo;

            switch (view.getId()) {
                    // Server
                case R.id.button1:
                    Log.e(Constants.TAG, "[GUI] Button1 pressed");

                    port = text1.getText().toString();
                    if (port == null || port.isEmpty()) {
                        Log.e(Constants.TAG, "[GUI] ERROR: Port empty");
                        return;
                    }

                    serverTh = new ServerThread(Integer.parseInt(port));
                    if (serverTh.getServerSocket() == null) {
                        Log.e(Constants.TAG, "[GUI] ERROR: Server initialization failed");
                        return;
                    }

                    serverTh.start();
                    Log.e(Constants.TAG, "[GUI] Server started on port " + port);

                    break;

                    // Client
                case R.id.button2:
                    Log.e(Constants.TAG, "[GUI] Button2 pressed");

                    address = text2.getText().toString();
                    port = text3.getText().toString();
                    city = text4.getText().toString();
                    typeInfo = spinner1.getSelectedItem().toString();

                    if (address == null || address.isEmpty()) {
                        Log.e(Constants.TAG, "[GUI] ERROR: Address empty");
                        return;
                    }
                    if (port == null || port.isEmpty()) {
                        Log.e(Constants.TAG, "[GUI] ERROR: Port empty");
                        return;
                    }
                    if (city == null || city.isEmpty()) {
                        Log.e(Constants.TAG, "[GUI] ERROR: City empty");
                        return;
                    }


                    clientTh = new ClientThread(
                            address, Integer.parseInt(port), city, typeInfo, text_debug
                    );
                    clientTh.start();
                    Log.e(Constants.TAG, "[GUI] Client started on port-" + port +
                            "  address-" + address +
                            "  city-" + city +
                            "  type info-" + typeInfo);

                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        text1 = (EditText)findViewById(R.id.text1);
        text2 = (EditText)findViewById(R.id.text2);
        text3 = (EditText)findViewById(R.id.text3);
        text4 = (EditText)findViewById(R.id.text4);
        spinner1 = (Spinner)findViewById(R.id.spinner);
        text_debug = (EditText)findViewById(R.id.text_debug);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(my_buttonClickListener);

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(my_buttonClickListener);
    }

    @Override
    protected void onDestroy() {
        if (serverTh != null) {
            serverTh.stopThread();
        }
        super.onDestroy();
    }
}
