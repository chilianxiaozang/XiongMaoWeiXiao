package com.example.zhide.xiongmaoweixiao;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ArrayList<Fragment> fragments;
    private DrawerLayout mDrawerLayout;
    private BottomNavigationBar bottomNavigationBar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initUI();
        initFragment();
        setUIFunction();
    }
    protected void initUI(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
    }
    protected void setUIFunction(){
        //设置左侧划栏的界面
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_myfriend:
                        Toast.makeText(MainActivity.this,"我的好友界面",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_mycollection:
                        Toast.makeText(MainActivity.this,"收藏界面",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_myinformation:
                        Toast.makeText(MainActivity.this,"信息界面",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case  R.id.nav_yonghuguanli:
                        Toast.makeText(MainActivity.this,"用户管理界面",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case  R.id.nav_myword:
                        Toast.makeText(MainActivity.this,"发表界面",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case  R.id.nav_user:
                        Toast.makeText(MainActivity.this,"用户信息界面",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_iphone:
                        Toast.makeText(MainActivity.this,"手机界面",Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
//设置底部导航栏的选项
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.scholl_logo,"校园").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.notification_logo,"资讯").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.chat_logo,"论坛").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){

            @Override
            public void onTabSelected(int position) {
                if(fragments != null){
                    if(position < fragments.size()){
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        Fragment fragment = fragments.get(position);
                        ft.replace(R.id.fragmentbool,fragment);
                        ft.commit();
                    }
                }
            }

            @Override
            public void onTabUnselected(int position) {
                if(fragments != null){
                    if(position < fragments.size()){
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        Fragment fragment = fragments.get(position);
                        ft.remove(fragment);
                        ft.commit();
                    }
                }
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    private void initFragment(){
        fragments = new ArrayList<Fragment>();
        fragments.add(SchoolFragment.newInstance("校园",""));
        fragments.add(InformationFragment.newInstance("信息",""));
        fragments.add(CommentFragment.newInstance("论坛",""));
    }
//    初始化fragments


public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.main_toolbar,menu);
//    inflate菜单栏主选项界面
    return true;
}
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
}
