package com.example.carddemo;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView result_text0; // 第一个页面的
    TextView result_text;
    Button roll_button2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//       从布局文件中获取名叫roll_button的按钮对象的引用
        Button rollButton = findViewById(R.id.roll_button);

//        给按钮rollButton设置点击监听器，一旦用户点击按钮，就触发监听器的onClick方法

        rollButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                /**
                 * 1、随机积分
                 */
                Random rand = new Random();
                int randNumber = rand.nextInt(100)+1;

//              从布局文件中获取名为result_text文本视图的引用
                TextView resultText = (TextView)findViewById(R.id.result_text);

//              将获取到的随机数设置到resultText的text属性上，注意要将随机数转换为字符串
                resultText.setText(String.valueOf(randNumber));


                /**
                 *  2、随机姓名按钮设置
                 */

                String name =  creatName();
                TextView result_text0 = (TextView)findViewById(R.id.result_text0);
                result_text0.setText(name);
            }
        }  );



/**
 * 跳转界面
 * */
        result_text0 = findViewById(R.id.result_text0);
        result_text = findViewById(R.id.result_text);
        roll_button2 = findViewById(R.id.roll_button2);

        roll_button2.setOnClickListener(new View.OnClickListener() {//绑定点击事件，用intent携带数据并跳转的方式
            @Override
            public void onClick(View v) {
                String Name = result_text0.getText().toString();//EditText输入的数据
                String Number = result_text.getText().toString();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);//初始化intent并设置打开的页面
                intent.putExtra("Name",Name);//设置绑定数据
                intent.putExtra("Number",Number);
                startActivity(intent);//启动页面

            }
        });




        /**
         * 开启子线程 提示选人
         * */
        new Thread(){
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "请选人!!!", Toast.LENGTH_LONG).show();
                    Looper.loop();
                } catch (Exception e) {

                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "选人失败", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }.start();
    }




/**
 * 随机生成姓名
 * */
    public static String creatName ( ) {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(0,"刘备");
        map.put(1,"曹操");
        map.put(2,"孙权");
        map.put(3,"关羽");
        map.put(4,"张飞");
        map.put(5,"孙悟空");
        map.put(6,"猪八戒");
        map.put(7,"沙和尚");
        map.put(8,"唐僧");
        map.put(9,"白龙马");
        Random rand = new Random();
        int randNumber = rand.nextInt(9)+1;
        String name = map.get(randNumber);
        return name;
    }











}

