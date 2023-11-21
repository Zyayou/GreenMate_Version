package com.example.greenmate_front3.eco;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.greenmate_front3.CleanHouseRequest;
import com.example.greenmate_front3.R;
import com.example.greenmate_front3.activity.EditActivity;
import com.ramotion.foldingcell.FoldingCell;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class EcoDetailFragment extends Fragment {
    private FoldingCell foldingCell;
    private TextView categoryView;
    private CheckBox ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12, ch13, ch14, ch15, ch16;
    private Button catagoryBtn, closeBtn;
    private LinearLayout cellTitleView;
    EcoDetailViewFragment ecoDetailViewFragment;
    SearchView searchView_Detail;


    // 검색시 같은 이름이 있는 아이템이 담길 리스트
    private ArrayList<Items_Detail> search_list = new ArrayList<>();
    // recyclerView에 추가할 아이템 리스트
    private ArrayList<Items_Detail> list = new ArrayList<>();
    // 어댑터
    TextAdapter_Detail adapter;


    public EcoDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_ecodetail, container, false);

        foldingCell = (FoldingCell) view.findViewById(R.id.foldingCell);
        cellTitleView = view.findViewById(R.id.cellTitleView);
        searchView_Detail = (SearchView) view.findViewById(R.id.searchView_detail);
        cellTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
                Toast.makeText(getActivity(),"카테고리 열기",Toast.LENGTH_SHORT).show();
            }
        });

        categoryView = (TextView) view.findViewById(R.id.categoryView);
        ch1 = (CheckBox) view.findViewById(R.id.checkBox1);
        ch2 = (CheckBox) view.findViewById(R.id.checkBox2);
        ch3 = (CheckBox) view.findViewById(R.id.checkBox3);
        ch4 = (CheckBox) view.findViewById(R.id.checkBox4);
        ch5 = (CheckBox) view.findViewById(R.id.checkBox5);
        ch6 = (CheckBox) view.findViewById(R.id.checkBox6);
        ch7 = (CheckBox) view.findViewById(R.id.checkBox7);
        ch8 = (CheckBox) view.findViewById(R.id.checkBox8);
        ch9 = (CheckBox) view.findViewById(R.id.checkBox9);
        ch10 = (CheckBox) view.findViewById(R.id.checkBox10);
        ch11 = (CheckBox) view.findViewById(R.id.checkBox11);
        ch12 = (CheckBox) view.findViewById(R.id.checkBox12);
        ch13 = (CheckBox) view.findViewById(R.id.checkBox13);
        ch14 = (CheckBox) view.findViewById(R.id.checkBox14);
        ch15 = (CheckBox) view.findViewById(R.id.checkBox15);
        ch16 = (CheckBox) view.findViewById(R.id.checkBox16);
        catagoryBtn = (Button) view.findViewById(R.id.catagoryBtn);
        catagoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                if(ch1.isChecked()) result += ch1.getText()+", ";
                if(ch2.isChecked()) result += ch2.getText()+", ";
                if(ch3.isChecked()) result += ch3.getText()+", ";
                if(ch4.isChecked()) result += ch4.getText()+", ";
                if(ch5.isChecked()) result += ch5.getText()+", ";
                if(ch6.isChecked()) result += ch6.getText()+", ";
                if(ch7.isChecked()) result += ch7.getText()+", ";
                if(ch8.isChecked()) result += ch8.getText()+", ";
                if(ch9.isChecked()) result += ch9.getText()+", ";
                if(ch10.isChecked()) result += ch10.getText()+", ";
                if(ch11.isChecked()) result += ch11.getText()+", ";
                if(ch12.isChecked()) result += ch12.getText()+", ";
                if(ch13.isChecked()) result += ch13.getText()+", ";
                if(ch14.isChecked()) result += ch14.getText()+", ";
                if(ch15.isChecked()) result += ch15.getText()+", ";
                if(ch16.isChecked()) result += ch16.getText()+" ";
                if(result == "") result = "적용된 카테고리가 없습니다.";

                // 선택된 카테고리를 어댑터에 전달
                Set<String> selectedCategories = new HashSet<>();
                if (ch1.isChecked()) selectedCategories.add(ch1.getText().toString());
                if (ch2.isChecked()) selectedCategories.add(ch2.getText().toString());
                if(ch3.isChecked()) selectedCategories.add(ch3.getText().toString());
                if(ch4.isChecked()) selectedCategories.add(ch4.getText().toString());
                if(ch5.isChecked()) selectedCategories.add(ch5.getText().toString());
                if(ch6.isChecked()) selectedCategories.add(ch6.getText().toString());
                if(ch7.isChecked()) selectedCategories.add(ch7.getText().toString());
                if(ch8.isChecked()) selectedCategories.add(ch8.getText().toString());
                if(ch9.isChecked()) selectedCategories.add(ch9.getText().toString());
                if(ch10.isChecked()) selectedCategories.add(ch10.getText().toString());
                if(ch11.isChecked()) selectedCategories.add(ch11.getText().toString());
                if(ch12.isChecked()) selectedCategories.add(ch12.getText().toString());
                if(ch13.isChecked()) selectedCategories.add(ch13.getText().toString());
                if(ch14.isChecked()) selectedCategories.add(ch14.getText().toString());
                if(ch15.isChecked()) selectedCategories.add(ch15.getText().toString());
                if(ch16.isChecked()) selectedCategories.add(ch16.getText().toString());

                adapter.setSelectedCategories(selectedCategories);

                categoryView.setText(result);
                Toast.makeText(getActivity(),"카테고리를 적용합니다.",Toast.LENGTH_SHORT).show();

            }
        });

        closeBtn = (Button) view.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
                Toast.makeText(getActivity(),"카테고리 닫기",Toast.LENGTH_SHORT).show();
            }
        });

        ecoDetailViewFragment = new EcoDetailViewFragment();

        // recyclerView, adapter 연결
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TextAdapter_Detail(getActivity(), list);
        recyclerView.setAdapter(adapter);

        // 전체 목록 표시 (검색어가 비어있을 때)
        //adapter.setItems(list);
        // 데이터를 가져오는 부분은 한 번만 수행되도록 수정
        if (list.isEmpty()) {
            loadData();
        }

        // 검색
        searchView_Detail.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // 입력받은 문자열 처리
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 입력란의 문자열이 바뀔 때 처리
                search_list.clear(); // 검색 결과 리스트 초기화

                if (s.equals("")) {
                    adapter.setItems(list);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        Items_Detail items_detail = list.get(i);
                        // 데이터와 비교
                        if (items_detail.rec_title.toLowerCase().contains(s.toLowerCase()) ||
                                items_detail.rec_category.toLowerCase().contains(s.toLowerCase()) ||
                                items_detail.rec_content.toLowerCase().contains(s.toLowerCase())) {
                            search_list.add(items_detail);
                        }
                    }
                    adapter.setItems(search_list);
                }

                // 로그 추가
                //Log.d("SearchFragment", "Search result: " + search_list.toString());
                return true;
            }
        });

        return view;
    }
    // 데이터를 가져오는 메서드
    private void loadData() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        JSONArray locationArray = jsonObject.getJSONArray("locations");

                        for (int i = 0; i < locationArray.length(); i++) {
                            JSONObject locationObject = locationArray.getJSONObject(i);

                            String title = locationObject.getString("rec_title");
                            String category = locationObject.getString("rec_category");
                            String content = locationObject.getString("rec_content");

                            list.add(new Items_Detail(title, category, content));
                        }

                        // 아이템이 변경되었음을 어댑터에 알림
                        adapter.setItems(list);
                    } else {
                        Toast.makeText(getActivity(), "데이터를 불러오는데 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        EcoDetailRequest ecoDetailRequest = new EcoDetailRequest("rec_category", "rec_title", "rec_content", responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(ecoDetailRequest);
    }
}
