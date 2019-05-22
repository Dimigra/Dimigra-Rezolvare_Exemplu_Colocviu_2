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

    private my_ButtonClickListener my_buttonClickListener = new my_ButtonClickListener();
    private class my_ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.button1:
                    // TODO
                    Log.e(Constants.TAG, "[GUI] Button1 pressed");
                    break;

                case R.id.button2:
                    // TODO
                    Log.e(Constants.TAG, "[GUI] Button2 pressed");
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
        // TODO
        super.onDestroy();
    }
}
