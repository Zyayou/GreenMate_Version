package com.example.greenmate_front3.eco;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.greenmate_front3.PlacegarageActivity;
import com.example.greenmate_front3.R;

public class EcoPlaceFragment extends Fragment{

    private Button searchBtn, mapBtn;
    private Spinner stateSpi, citySpi;
    ImageView outimage;
    TextView areaURL;
    EcoPlaceMapFragment ecoPlaceMapFragment;
    public EcoPlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_ecoplace, container, false);

        ecoPlaceMapFragment = new EcoPlaceMapFragment();

        stateSpi = (Spinner) view.findViewById(R.id.areaState);
        citySpi = (Spinner) view.findViewById(R.id.areaCity);
        outimage = (ImageView) view.findViewById(R.id.outimage);
        areaURL = (TextView) view.findViewById(R.id.areaURL);

/*
        // 도 항목
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.state, R.layout.color_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpi.setAdapter(adapter);

        // 시 항목
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.city_gb, R.layout.color_spinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpi.setAdapter(adapter1);
        // 도 항목 선택 시
        stateSpi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                stateSpi.setSelection(0);

            }
        });
        // 시 항목 선택 시
        citySpi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4){
                    outimage.setBackgroundResource(R.drawable.anuout);
                    areaURL.setText("https://www.andong.go.kr/portal/contents.do?mId=0607000000");
                    // 지도로 가기 버튼 가시화
                    //mapBtn.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                citySpi.setSelection(0);
            }
        });
 */
        /*
        // 검색 버튼
        searchBtn = view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"검색 버튼 선택",Toast.LENGTH_SHORT).show();
                if(citySpi.getSelectedItemId() == 4){
                    outimage.setBackgroundResource(R.drawable.anuout);
                    areaURL.setText("https://www.andong.go.kr/portal/contents.do?mId=0607000000");
                    // 지도로 가기 버튼 가시화
                    //mapBtn.setVisibility(View.VISIBLE);
                }
            }
        });
*/
        // 지도로 가기 버튼
        mapBtn = view.findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ecoPlaceMapFragment).addToBackStack(null).commit();
                //Toast.makeText(getActivity(),"지역별배출요령",Toast.LENGTH_SHORT).show();
            }
        });

        // 임시
        searchBtn = view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ecoPlaceMapFragment).addToBackStack(null).commit();
                Toast.makeText(getActivity(),"지역별배출요령",Toast.LENGTH_SHORT).show();
/*
                //기존 테스트
                String area_state = "경상북도";   //가져온 Extars 중에서 꺼내기
                String area_city = "안동시";

                //LoginResultActivity로 값을 전달하고 이동하기 위한 작업
                Intent intent = new Intent(getActivity(), PlacegarageActivity.class );

                intent.putExtra("arer_state", area_state);    //값 전달
                intent.putExtra("area_city", area_city); //값 전달

                startActivity(intent); //다음 activity로 넘어가기
*/
            }
        });//임시

        return view;
    }
}
