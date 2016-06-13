package com.example.mario.enforceoffline;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by victor on 16/6/10.
 * 处理登录信息.
 */
public class LoginActivity extends BaseActivity{
    private EditText accountEdit;

    private EditText passwordEdit;

    private Button  login;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        accountEdit=(EditText)findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account=accountEdit.getText().toString();//读取账号
                String password=passwordEdit.getText().toString();//读取密码

                if(account.equals("admin")&&password.equals("123456")){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Account or Password is invalid",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
