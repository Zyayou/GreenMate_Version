package com.example.greenmate_front3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OutputAreaActivity extends AppCompatActivity {

    Button goplace;
    Spinner spinner1;
    Spinner spinner2;
    ImageView outimage;
    TextView are_URL;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outputarea);

        goplace = (Button) findViewById(R.id.goplace);
        spinner1 = (Spinner) findViewById(R.id.arer_state);
        spinner2 = (Spinner) findViewById(R.id.area_city);
        outimage = (ImageView) findViewById(R.id.outimage);
        are_URL = (TextView) findViewById(R.id.are_URL);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(intent);
            }
        });

        // 도 항목
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.state, R.layout.color_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        // 시 항목
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.city_gb, R.layout.color_spinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);

        // 지도로 가기
        goplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arer_state = spinner1.getSelectedItem().toString();        //text값을 String으로 변환하여 가져오기
                String area_city = spinner2.getSelectedItem().toString();  //getText가 Editable로 가져오기 때문에 String으로 변환해준다

                //LoginResultActivity로 값을 전달하고 이동하기 위한 작업
                Intent intent = new Intent(getApplicationContext(),PlacegarageActivity.class );

                intent.putExtra("arer_state", arer_state);    //값 전달
                intent.putExtra("area_city", area_city); //값 전달

                startActivity(intent); //다음 activity로 넘어가기
            }
        });

        // 도 항목 선택 시
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner1.setSelection(0);

            }
        });
        // 시 항목 선택 시
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4){
                    outimage.setBackgroundResource(R.drawable.anuout);
                    are_URL.setText("https://www.andong.go.kr/portal/contents.do?mId=0607000000");
                    // 지도로 가기 버튼 가시화
                    goplace.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner2.setSelection(0);
            }
        });

    }

}
