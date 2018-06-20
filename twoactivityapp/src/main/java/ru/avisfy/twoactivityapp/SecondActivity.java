package ru.avisfy.twoactivityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button a2btn;
    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        a2btn =(Button) findViewById(R.id.a2btn);
        et = (EditText) findViewById(R.id.et);
        a2btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a2btn:
                String colorCode = et.getText().toString();
                if (colorCode.isEmpty()) {
                    et.setText("EditText is empty.");
                    return;
                }
                Intent intent = new Intent(this,FirstActivity.class);
                intent.putExtra("colorCode", colorCode);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    }
