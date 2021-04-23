package com.example.carddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    TextView name,number;
    private AlertDialog.Builder builder;

    public String  nu = "";

    public int randNumber = 0;

    public TextView mCountNumber;
    public Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);



        /** 绑定开始抽奖按钮*/
        mCountNumber = (TextView) findViewById(R.id.count_number);
        mStart = (Button) findViewById(R.id.roll_button2);





        //获取intent数据
        Intent intent = getIntent();
        String na = intent.getStringExtra("Name");
        nu = intent.getStringExtra("Number");

        //TextView显示数据
        name.setText(na);
        number.setText(nu);

        /**
         抽卡按钮绑定
          */
        Button button_middle =  findViewById(R.id.btn_middle);
        Button button_left =  findViewById(R.id.btn_left);
        Button button_right = findViewById(R.id.btn_right);

        button_middle.setOnClickListener(this);
        button_left.setOnClickListener(this);
        button_right.setOnClickListener(this);



        /**
         * 计时
         *Message：Handler接收和处理的消息对象。
         * 2个整型数值：轻量级存储int类型的数据。
         * 1个Object：任意对象。
         * replyTo：线程通信时使用。
         * what：用户自定义的消息码，让接收者识别消息
         **/
        Message message = handler.obtainMessage();
        message.arg1 = 0;
        message.arg2 = 1;
        message.what = 88888;
        message.obj = 10000;
        handler.sendMessageDelayed(message, 1000); //指定多少毫秒后发送消息    需要进行UI操作时，就创建一个Message对象，并通过Handler将这条消息发送出去。

    }


    /**
     判断点击了哪张
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_middle:
                show( );
                v.setVisibility(View.INVISIBLE);  //不显示,但保留所占的空间
                addnumber ( );
                break;
            case R.id.btn_left:
                show( );
                v.setVisibility(View.INVISIBLE);  //不显示,但保留所占的空间
                addnumber ( );

                break;
            case R.id.btn_right:
                show( );
                v.setVisibility(View.INVISIBLE);  //不显示,但保留所占的空间
                addnumber ( );
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }


    /** 抽卡后更新分数*/
    public void addnumber ( ) {
        int first = Integer.parseInt(nu);
        int second = randNumber;
        int sum = first+second;
        String s = String.valueOf(sum);
        number.setText(s);

    }




    /**
     提示框
     */
    public void show(    ) {

        Random rand = new Random();
        randNumber = rand.nextInt(10)+1;

        String str  = String.valueOf(randNumber);
        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("恭喜中奖了,中奖分数");
        builder.setMessage("                                      " + str + "分");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //ToDo: 你想做的事情
                Toast.makeText(SecondActivity.this, "抽奖结束", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SecondActivity.this,MainActivity.class);//初始化intent并设置打开的页面
                startActivity(intent);//启动页面
            }
        });
        builder.create().show();
    }



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {  //触发回调方法handlerMessage()
            super.handleMessage(msg);
            switch (msg.what){
                case 88888:
                    int value = (int) msg.obj;
                    mCountNumber.setText(String.valueOf(value / 1000));  //在主线程中更新UI界面
                    msg = Message.obtain();//重新获取消息
                    msg.arg1 = 0;
                    msg.arg2 = 1;
                    msg.what = 88888;
                    msg.obj = value - 1000;
                    if (value > 0){
                        sendMessageDelayed(msg, 1000);
                    }
                    break;
            }
        }
    };




}