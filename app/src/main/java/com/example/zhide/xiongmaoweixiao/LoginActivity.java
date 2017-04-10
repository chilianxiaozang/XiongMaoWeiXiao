package com.example.zhide.xiongmaoweixiao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private  Button login_LoginButton;
    private Button login_RegistButton;
    private EditText userAccount;
    private EditText userPassword;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initUI();
        pref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        String account_1 = pref.getString("account","");
        String password_1 = pref.getString("password","");
        userAccount.setText(account_1);
        userPassword.setText(password_1);
        login_LoginButton.setOnClickListener(this);
        login_RegistButton.setOnClickListener(this);
    }
    protected void initUI(){
        userAccount = (EditText) findViewById(R.id.login_account_edittext);
        userPassword = (EditText)  findViewById(R.id.login_password_edittext);
        login_LoginButton = (Button) findViewById(R.id.login_button);
        login_RegistButton = (Button) findViewById(R.id.regist_button);
    }

    public boolean isOk(){
        if(userAccount.getText().toString().trim().equals("")){
            Toast.makeText(LoginActivity.this,"账户不能为空",Toast.LENGTH_SHORT).show();
            return false;
        } else if (userPassword.getText().toString().trim().equals("")){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                if(isOk()){
                    User user = new User();
                    user.loginByAccount(userAccount.getText().toString().trim(),
                            userPassword.getText().toString().trim(), new LogInListener<User>() {
                                @Override
                                public void done(User user, BmobException e) {
                                    if(e == null){
                                        String useraccount = userAccount.getText().toString().trim();
                                        String userpassword = userPassword.getText().toString().trim();
                                        editor =pref.edit();
                                        editor.putBoolean("remeber_password",true);
                                        editor.putString("account",useraccount);
                                        editor.putString("password",userpassword);
                                        editor.apply();
                                        //       缓存用户的帐号密码
                                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    } else {
                                        Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                break;
            case R.id.regist_button:
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
                break;
            default:
                break;
        }
    }
//      判断用户输入的信息是否有误

}
