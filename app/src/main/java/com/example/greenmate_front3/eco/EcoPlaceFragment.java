package com.example.greenmate_front3.eco;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        outimage = (ImageView) view.findViewById(R.id.outImg);
        areaURL = (TextView) view.findViewById(R.id.areaURL);

        //다른 지역 준비중 안내
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("안내");
        builder.setMessage("현재 다음 지역의 지역별 배출요령만 제공되고 있습니다. \n(안동, 경주, 제주)");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // Yes 버튼 및 이벤트 생성
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        // 도 항목 선택 시
        stateSpi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ArrayAdapter<CharSequence> city_empty = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_empty, R.layout.color_spinner);
                    city_empty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_empty);
                }
                if(position == 1){
                    // 서울
                    ArrayAdapter<CharSequence> city_seoul = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_seoul, R.layout.color_spinner);
                    city_seoul.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_seoul);
                }
                if(position == 2){
                    // 부산
                    ArrayAdapter<CharSequence> city_busan = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_busan, R.layout.color_spinner);
                    city_busan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_busan);
                }
                if(position == 3){
                    // 인천
                    ArrayAdapter<CharSequence> city_incheon = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_incheon, R.layout.color_spinner);
                    city_incheon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_incheon);
                }
                if(position == 4){
                    // 대구
                    ArrayAdapter<CharSequence> city_daegu = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_daegu, R.layout.color_spinner);
                    city_daegu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_daegu);
                }
                if(position == 5){
                    // 대전
                    ArrayAdapter<CharSequence> city_daejeon = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_daejeon, R.layout.color_spinner);
                    city_daejeon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_daejeon);
                }
                if(position == 6){
                    // 광주
                    ArrayAdapter<CharSequence> city_gwangju = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_gwangju, R.layout.color_spinner);
                    city_gwangju.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_gwangju);
                }
                if(position == 7){
                    // 울산
                    ArrayAdapter<CharSequence> city_ulsan = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_ulsan, R.layout.color_spinner);
                    city_ulsan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_ulsan);
                }
                if(position == 8){
                    // 세종
                    ArrayAdapter<CharSequence> city_sejong = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_sejong, R.layout.color_spinner);
                    city_sejong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_sejong);
                }
                if(position == 9){
                    // 경기도
                    ArrayAdapter<CharSequence> city_gg = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_gg, R.layout.color_spinner);
                    city_gg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_gg);
                }
                if(position == 10){
                    // 강원도
                    ArrayAdapter<CharSequence> city_gw = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_gw, R.layout.color_spinner);
                    city_gw.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_gw);
                }
                if(position == 11){
                    // 충북
                    ArrayAdapter<CharSequence> city_cb = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_cb, R.layout.color_spinner);
                    city_cb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_cb);
                }
                if(position == 12){
                    // 충남
                    ArrayAdapter<CharSequence> city_cn = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_cn, R.layout.color_spinner);
                    city_cn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_cn);
                }
                if(position == 13){
                    // 전북
                    ArrayAdapter<CharSequence> city_jb = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_jb, R.layout.color_spinner);
                    city_jb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_jb);
                }if(position == 14){
                    // 전남
                    ArrayAdapter<CharSequence> city_jn = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_jn, R.layout.color_spinner);
                    city_jn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_jn);
                }
                if(position == 15){
                    // 경북
                    ArrayAdapter<CharSequence> city_gb = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_gb, R.layout.color_spinner);
                    city_gb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_gb);
                }
                if(position == 16){
                    // 경남
                    ArrayAdapter<CharSequence> city_gn = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_gn, R.layout.color_spinner);
                    city_gn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_gn);
                }
                if(position == 17){
                    // 제주
                    ArrayAdapter<CharSequence> city_jeju = ArrayAdapter.createFromResource(getActivity(),
                            R.array.city_jeju, R.layout.color_spinner);
                    city_jeju.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    citySpi.setAdapter(city_jeju);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                stateSpi.setSelection(15);

            }
        });
        // 시 항목 선택 시
        citySpi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4){
                    //outimage.setBackgroundResource(R.drawable.anuout);
                    //areaURL.setText("https://www.andong.go.kr/portal/contents.do?mId=0607000000");
                    // 지도로 가기 버튼 가시화
                    //mapBtn.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                citySpi.setSelection(4);
            }
        });


        // 검색 버튼
        searchBtn = view.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"검색 버튼 선택",Toast.LENGTH_SHORT).show();

                if((stateSpi.getSelectedItemId() == 15) && (citySpi.getSelectedItemId() == 4)){
                    outimage.setBackgroundResource(R.drawable.out_andong);
                    areaURL.setText("https://www.andong.go.kr/portal/contents.do?mId=0607000000");
                }
                if((stateSpi.getSelectedItemId() == 15) && (citySpi.getSelectedItemId() == 2)){
                    outimage.setBackgroundResource(R.drawable.out_gyeongju);
                    areaURL.setText("https://gyeongju.go.kr/open_content/ko/page.do?step=258&parm_bod_uid=180687&pageOrder=0&srchEnable=1&pageNo=1&srchKeyword=&srchSDate=&pageRef=0&srchBgpUid=-1&mnu_uid=1602&parm_mnu_uid=0&pagePrvNxt=1&srchEDate=&srchVoteType=-1&srchColumn=&");
                }
                if((stateSpi.getSelectedItemId() == 17) && (citySpi.getSelectedItemId() == 1)){
                    outimage.setBackgroundResource(R.drawable.out_seogwipo);
                    areaURL.setText("https://www.seogwipo.go.kr/group/clean/life/archives.htm?act=view&seq=124312507");
                }
                if((stateSpi.getSelectedItemId() == 17) && (citySpi.getSelectedItemId() == 2)){
                    outimage.setBackgroundResource(R.drawable.out_jeju);
                    areaURL.setText("https://www.jejusi.go.kr/field/eco/weekwaste.do");
                }
            }
        });

        // 지도로 가기 버튼
        mapBtn = view.findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("안내");
                builder.setMessage("현재 안동지역만 클린하우스 위치만 제공되고 있습니다. \n안동지역 클린하우스 위치를 보시겠습니까?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                // Yes 버튼 및 이벤트 생성
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundlems = new Bundle(); // 번들을 통해 값 전달
                        bundlems.putString("state",stateSpi.getSelectedItem().toString());//번들에 넘길 값 저장
                        bundlems.putString("city",citySpi.getSelectedItem().toString());//번들에 넘길 값 저장
                        ecoPlaceMapFragment.setArguments(bundlems);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ecoPlaceMapFragment).addToBackStack(null).commit();
                        Toast.makeText(getActivity(),"지도",Toast.LENGTH_SHORT).show();
                    }
                });

                //No 버튼 및 이벤트 생성
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Pass
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        return view;
    }
}
