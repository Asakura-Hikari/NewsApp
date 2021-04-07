package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //登陆功能后台实现
    private int login() throws FileNotFoundException {
        EditText userName = (EditText)findViewById(R.id.account);
        EditText password = (EditText)findViewById(R.id.pwd);
        int check = 0; //如果check为0，则登陆失败，为1，则是用户账号，为2，则是管理员账号。

        String inputAcc = userName.getText().toString();
        String inputPwd = password.getText().toString();

        try {
            BufferedReader in = new BufferedReader(new FileReader("Account.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                String[] user = str.split(" ");
                if (user[0].charAt(0)=='U'&& user[0].equals(inputAcc) && user[1].equals(inputPwd)){
                    check = 1;
                }else if (user[0].charAt(0)=='A'&&user[0].equals(inputAcc) && user[1].equals(inputPwd)){
                    check = 2;
                }
            }
        }catch (IOException ignored){
        }
        return check;
    }

    //注册功能实现
    private boolean register(){
        EditText userName = (EditText)findViewById();
        EditText password = (EditText)findViewById();
        boolean ckeck = true; //返回true则注册成功，返回false则注册失败。

        String inputAcc = userName.getText().toString();
        String inputPwd = password.getText().toString();

        if (inputAcc.charAt(0)!='U'){
            ckeck = false;
        }else {
            String acc = "\n"+inputAcc+" "+inputPwd;
            try {
                FileWriter writer = new FileWriter("Account.txt", true);
                writer.write(acc);
                writer.close();
            } catch (IOException e) {
                ckeck = false;
                e.printStackTrace();
            }
        }
        return ckeck;
    }
}
