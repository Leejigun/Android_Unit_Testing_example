package com.openit.jglee.android_unit_test_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
* 2017년 11월 02일
* 부스트 캠프의 안드로이드 스튜디오 테스트 학습 프로젝트
* 자세한 사항은 각각의 테스팅 파일에 주석 처리
*
* */
public class MainActivity extends AppCompatActivity {

    TextView txtView;
    Button btnClick;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.hellowTtext);
        btnClick = (Button) findViewById(R.id.btnClick);

     /* RxJava로 구성된 부분

        RxView.clicks(btnClick)
                .map(event -> count++)
                .subscribe(value -> {
                    txtView.setText("count:"+value.toString());
                }, throwable -> {
                    Log.e(TAG, "Error: " + throwable.getMessage());
                    throwable.printStackTrace();
                });

    */
     // 일반 코드로 풀어 쓴 내용
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                txtView.setText("count:"+Integer.toString(count));
            }
        });


    }
}
