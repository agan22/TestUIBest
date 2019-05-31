package com.example.testuibest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText inputText;
    Button send;
    MsgAdapter adapter;
    List<Msg> msgList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText=findViewById(R.id.input_text);
        send=findViewById(R.id.send);
        //数据源初始化
        initMsgs();
        //实例化RecyclerView控件
        recyclerView=findViewById(R.id.msg_recycler_view);
        //创建MsgAdapter适配器
        adapter=new MsgAdapter(msgList);
        //为RecyclerView控件设置适配器
        recyclerView.setAdapter(adapter);
        //为RecyclerView控件设置布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //事件处理
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=inputText.getText().toString();
                if(!content.equals("")){
                    //创建一条消息,放入数据源
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    //把消息插入到适配器
                    adapter.notifyItemInserted(msgList.size());
                    //滚动屏幕到最后一行
                    recyclerView.smoothScrollToPosition(msgList.size()-1);
                    //清空界面
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1=new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("hi,Who's that?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("This is Tom,Nice to meet you!",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
