package com.xela.notelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    private Handler h;
    private Runnable r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, NoteScreen.class);
                startActivity(intent);
                //autologin();
            }
        };
        h = new Handler();

        h.postDelayed(r,2000);

    }

    //@Override
    //protected void onDestroy() {
    //    super.onDestroy();
    //   if (h != null && r != null){
    //        h.removeCallbacks(r);
    //    }
    //}
}