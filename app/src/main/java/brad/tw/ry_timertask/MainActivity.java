package brad.tw.ry_timertask;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private Task1 task1;
    private TextView mesg;
    private int counter;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesg = findViewById(R.id.mesg);
        handler = new UIHandler();
    }

    @Override
    protected void onStart() {
        super.onStart();

        timer = new Timer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    public void test1(View view) {
        Log.v("brad", "start");
        timer.schedule(new Task1(), 3*1000);
    }
    public void test2(View view) {
        task1 = new Task1();
        timer.schedule(task1, 1*1000, 1*1000);
    }
    public void test3(View view) {
        if (task1 != null){
            task1.cancel();
            task1 = null;
        }
    }
    public void test4(View view) {
        Intent intent = new Intent(this, Page2Activity.class);
        startActivity(intent);
    }

    private class Task1 extends TimerTask {
        @Override
        public void run() {
            //Log.v("brad", "OK");
            counter++;

//            Message message = new Message();
//            Bundle data = new Bundle();
//            data.putInt("counter", counter);
//            message.setData(data);
//            handler.sendMessage(message);

            handler.sendEmptyMessage(0);

        }
    }


    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                mesg.setText("" + counter);
            }


        }
    }

}
