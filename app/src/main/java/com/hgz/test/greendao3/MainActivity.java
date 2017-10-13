package com.hgz.test.greendao3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hgz.test.greendao3.adapter.MyAdapter;
import com.hgz.test.greendao3.entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_name2;
    private Button btn_insert;
    private Button btn_delete;
    private Button btn_update;
    private ListView listview;
    private List<User> list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //调用初始化控件的方法
        initVie();
        list = GreenDaoManager.getInstance().getDaoSession().getUserDao().queryBuilder().build().list();
        myAdapter = new MyAdapter(this, list);
        listview.setAdapter(myAdapter);
        //增删改三个按钮的点击事件监听
        btn_insert.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
    }

    //初始化控件的方法
    private void initVie() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_name2 = (EditText) findViewById(R.id.et_name2);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        listview = (ListView) findViewById(R.id.list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                //调用增加数据的方法
                insert(et_name.getText().toString().trim());
                break;
            case R.id.btn_delete:
                //调用删除数据的方法
                delete(et_name.getText().toString().trim());
                break;
            case R.id.btn_update:
                //调用修改数据的方法
                update(et_name.getText().toString().trim(), et_name2.getText().toString().trim());
                break;
        }
    }

    //增加数据的方法
    private void insert(String name) {
        UserDao userDao = GreenDaoManager.getInstance().getDaoSession().getUserDao();
        User unique = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
        User user = new User(null, name);
        if (unique == null) {
            if (TextUtils.isEmpty(et_name.getText().toString().trim())) {
                Toast.makeText(this, "请输入要添加的信息", Toast.LENGTH_SHORT).show();
            } else {
                userDao.insert(user);
                et_name.setText("");
                this.list.clear();
                this.list.addAll(userDao.queryBuilder().build().list());
                myAdapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(this, "数据库中已有这个数据", Toast.LENGTH_SHORT).show();
        }
    }

    //删除数据的方法
    private void delete(String name) {
        UserDao userDao = GreenDaoManager.getInstance().getDaoSession().getUserDao();
        List<User> list2 = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().list();
        if (list2.size() != 0) {
            for (User user : list2) {
                et_name.setText("");
                userDao.deleteByKey(user.getId());
                list.remove(user);
            }
            myAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "没有查询到该姓名", Toast.LENGTH_SHORT).show();
        }

    }

    //修改数据的方法
    private void update(String oldname, String newname) {
        UserDao userDao = GreenDaoManager.getInstance().getDaoSession().getUserDao();
        List<User> list3 = userDao.queryBuilder().where(UserDao.Properties.Name.eq(oldname)).build().list();
        if (list3.size() != 0) {
            for (User user : list3) {
                if (!user.getName().equals(et_name.getText().toString().trim())) {
                    Toast.makeText(this, "没有查询到该姓名", Toast.LENGTH_SHORT).show();
                    break;
                }
                et_name.setText("");
                et_name2.setText("");
                user.setName(newname);
                userDao.update(user);

            }
            myAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "没有查询到该姓名", Toast.LENGTH_SHORT).show();
        }
    }
}
