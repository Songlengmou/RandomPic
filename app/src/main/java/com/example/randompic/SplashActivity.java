package com.example.randompic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Song
 */
public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.tv_number)
    TextView number;
    @BindView(R.id.rl_splash)
    RelativeLayout splash;

    AlphaAnimation animation;
    MyCount my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        number.getBackground().setAlpha(50);

        //随机图片
        int array[] = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6};
        Random rnd = new Random();
        int index = rnd.nextInt(5);
        Log.e("随机", index + "");
        splash.setBackground(getResources().getDrawable(array[index]));

        countDown();
    }

    @OnClick(R.id.tv_number)
    void onViewClick() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        my.cancel();
        finish();
    }

    /**
     * 设置的倒计时时间
     */
    private void countDown() {
//        //背景透明度
//        animation = new AlphaAnimation(0.5f, 1.0f);
//        //透明度时间
//        animation.setDuration(3000);
        my = new MyCount(3000, 1000);
        my.start();
        splash.setAnimation(animation);
    }

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            number.setText("跳过" + millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {

        }
    }

    @Override
    public void onBackPressed() {
    }
}
