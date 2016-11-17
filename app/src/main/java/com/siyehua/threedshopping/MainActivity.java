package com.siyehua.threedshopping;

import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    BitmapView viewPager;
    int[] tmpArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (BitmapView) findViewById(R.id.vp);
        SeekBar progressBar = (SeekBar) findViewById(R.id.pg);

        tmpArr = new int[]{R.drawable._001, R.drawable._002, R.drawable._003, R.drawable._004, R
                .drawable._005, R.drawable._006, R.drawable._007, R.drawable._008, R.drawable
                ._009, R.drawable._010, R.drawable._011, R.drawable._012, R.drawable._013, R
                .drawable._014, R.drawable._015, R.drawable._016, R.drawable._017, R.drawable
                ._018, R.drawable._019, R.drawable._020, R.drawable._021, R.drawable._022, R
                .drawable._023, R.drawable._024, R.drawable._025, R.drawable._026, R.drawable
                ._027, R.drawable._028, R.drawable._029, R.drawable._030, R.drawable._031, R
                .drawable._032};
        viewPager.setImage(tmpArr);

        progressBar.setMax(8 * 50);
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewPager.setDegress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                return false;
            }
        });
    }
}
