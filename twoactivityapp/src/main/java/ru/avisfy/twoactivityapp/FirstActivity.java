package ru.avisfy.twoactivityapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private ImageView iv;
    private TextView tv1;
    private TextView tv2;
    private int BgColor = 0;
    private int TextColor = 0;
    private  Uri selectedImage = null;
    static final int GALLERY_REQUEST = 1;
    static final int SACTIVITY_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        iv = (ImageView)findViewById(R.id.iv);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                BgColor = getResources().getColor(R.color.colorBg1);
                TextColor = getResources().getColor(R.color.colorTv1);
                break;
            case R.id.btn2:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivityForResult(intent, SACTIVITY_REQUEST);
                break;
            case R.id.btn3:
                BgColor = getResources().getColor( R.color.colorBg2);
                TextColor = getResources().getColor(R.color.colorTv2);
                break;
            case R.id.iv:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            default:
                break;
        }
        tv1.setText("Text1");
        if (BgColor !=0){
            tv1.setBackgroundColor(BgColor);
            tv2.setBackgroundColor(BgColor);
        }
        if(TextColor !=0){
            tv1.setTextColor(TextColor);
            tv2.setTextColor(TextColor);
        }
        //tv1.setText(Integer.valueOf(TextColor).toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("BgColor", BgColor);
        outState.putInt("TextColor", TextColor);
        if (selectedImage != null){
            outState.putString("SelectedImage", selectedImage.toString());
        } else  outState.putString("SelectedImage", Uri.EMPTY.toString());

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        BgColor = savedInstanceState.getInt("BgColor");
        TextColor = savedInstanceState.getInt("TextColor");
        String selectedImageStr = savedInstanceState.getString("SelectedImage");

        if (selectedImageStr != ""){
            selectedImage = Uri.parse(selectedImageStr);
            iv.setImageURI(null);
            iv.setImageURI(selectedImage);
        }
        if (BgColor !=0){
            tv1.setBackgroundColor(BgColor);
            tv2.setBackgroundColor(BgColor);
        }
        if(TextColor !=0){
            tv1.setTextColor(TextColor);
            tv2.setTextColor(TextColor);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    selectedImage = data.getData();
                    iv.setImageURI(null);
                    iv.setImageURI(selectedImage);
                }
                break;
            case SACTIVITY_REQUEST:
                if (resultCode == RESULT_OK){
                    if (data == null) {return;}
                    String colorCode = data.getStringExtra("colorCode");
                    try {
                        BgColor = Color.parseColor(colorCode);
                    }catch(IllegalArgumentException e)
                    {
                        tv1.setText("Error color code on activity2");
                        return;
                    }
                    tv1.setBackgroundColor(BgColor);
                    tv2.setBackgroundColor(BgColor);
                }
        }



    }
}
