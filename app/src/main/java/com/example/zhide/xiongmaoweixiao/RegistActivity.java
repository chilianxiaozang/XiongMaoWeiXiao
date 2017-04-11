package com.example.zhide.xiongmaoweixiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class RegistActivity extends BaseActivity implements View.OnClickListener{
    EditText regist_userName;
    EditText regist_userPassword;
    EditText regist_userRepeatrPassword;
    EditText regist_userPhonenumber;
    EditText regist_userVerification;
    Button regist_registButton;
    Button   regist_getVerificationButton;
    User user;
    BmobSMS sms;
    String user_VerificationCode;
    Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_activity);
        initUI();
        regist_registButton.setOnClickListener(this);
    }
    protected void initUI(){
        regist_userName = (EditText) findViewById(R.id.regist_username_edittext);
        regist_userPassword = (EditText) findViewById(R.id.regist_userpassword_edittext);
        regist_userRepeatrPassword = (EditText) findViewById(R.id.regist_userrepeat_edittext);
        regist_userPhonenumber = (EditText) findViewById(R.id.regist_userphonenumber_edittext);
        regist_userVerification = (EditText) findViewById(R.id.regist_verification_edittext);
        regist_registButton = (Button) findViewById(R.id.regist_registbutton);
        regist_getVerificationButton = (Button) findViewById(R.id.regist_verification_button);
    }
//    初始化UI组件
    protected void registUser(){
        if(istrue()){
            user = new User();
            user.setFlag("1");
            user.setUsername(regist_userName.getText().toString().trim());
            user.setPassword(regist_userPassword.getText().toString().trim());
            user.setUsermoney(0);
            user.setUserweibi(0);
            user.setUserIntegral(0);
            user.setEmailVerified(false);
            user.signUp(new SaveListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if( e == null){
                        Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        BmobFile bmobFile  = new BmobFile();
                        bmobFile.upload(new UploadFileListener() {
                            @Override
                            public void done(BmobException e) {
                            }
                        });
                        user.loginByAccount(regist_userName.getText().toString().trim(),regist_userPassword.getText().toString().trim(),new LogInListener<User>() {
                            @Override
                            public void done(User user, BmobException e) {
                                if(e == null){
                                    Toast.makeText(RegistActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistActivity.this,MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(RegistActivity.this,"登录失败："+e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        } );
                    }else {
                        Toast.makeText(RegistActivity.this,"注册失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    public void isVerificated(){
        BmobSMS.requestSMSCode(RegistActivity.this, regist_userPhonenumber.getText().toString().trim(), "熊猫微校", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, cn.bmob.sms.exception.BmobException e) {
                if(e == null){
                    user_VerificationCode = integer.toString().trim();
                }else {
                    Toast.makeText(RegistActivity.this,"发送验证码失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean istrue(){
        if( regist_userName.getText().toString().trim().equals("")){
            Toast.makeText(RegistActivity.this,"亲，账号为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if( regist_userPassword.getText().toString().trim().equals("")){
            Toast.makeText(RegistActivity.this,"亲，密码为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(regist_userRepeatrPassword.getText().toString().trim().equals("")){
            Toast.makeText(RegistActivity.this,"亲，重复密码为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(regist_userPhonenumber.getText().toString().trim().equals("")){
            Toast.makeText(RegistActivity.this,"亲，手机号码为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!(regist_userPassword.getText().toString().trim().equals(regist_userRepeatrPassword.getText().toString().trim()))){
            Toast.makeText(RegistActivity.this,"亲，俩个密码不一样",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regist_registbutton:
                if (istrue()){
                    registUser();
                }
                break;
            default:
                break;
        }
    }
}
