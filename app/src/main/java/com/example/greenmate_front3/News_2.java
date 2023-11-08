package com.example.greenmate_front3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class News_2 extends Fragment {
    private String URLnews = "https://www.hkbs.co.kr/news/articleList.html?page=1&total=56110&sc_section_code=S1N1&sc_sub_section_code=&sc_serial_code=&sc_area=&sc_level=&sc_article_type=&sc_view_level=&sc_sdate=&sc_edate=&sc_serial_number=&sc_word=&box_idxno=&sc_multi_code=&sc_is_image=&sc_is_movie=&sc_user_name=&sc_order_by=E&view_type=tm";
    String url2;
    private RelativeLayout news2Layout;
    private ImageView imgNews2;
    private TextView textNews2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.news_2, container, false);

        textNews2 = rootView.findViewById(R.id.textNews2);
        imgNews2 = rootView.findViewById(R.id.imgNews2);
        imgNews2.setClipToOutline(true);

        news2Layout = rootView.findViewById(R.id.news2Layout);
        news2Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url2));
                startActivity(urlintent2);
            }
        });

        final Bundle bundle = new Bundle();
        //크롤링
        new Thread(){
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(URLnews).get();
                    Elements contents = doc.select(".type3 li");          //class 혹은 id찾기 : .은 class #은 id
                    //nums =contents.text();

                    // 이전에 선언한 itemList 리스트 초기화
                    ArrayList<NewsItem> itemList = new ArrayList<>();

                    for (Element elem : contents) {
                        String title = elem.select(".titles a").text();
                        String img = elem.select("a.thumb img").attr("src");
                        String url_go = elem.select("a.thumb").attr("href");

                        // MovieItem 아이템 생성 후 추가
                        NewsItem item = new NewsItem(title, img, url_go);
                        itemList.add(item);

                        // 4개만 가져오기
                        if (itemList.size() >= 4) {
                            break;
                        }
                    }

                    // itemList를 메시지로 전달
                    bundle.putSerializable("items", itemList);
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        return rootView;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();

            // itemList를 가져오고 사용자 인터페이스에 설정
            ArrayList<NewsItem> itemList = (ArrayList<NewsItem>) bundle.getSerializable("items");

            if (itemList.size() >= 4) {
                textNews2.setText(itemList.get(1).getTitle());

                // 이미지 로딩 라이브러리 (Glide)를 사용하여 이미지 설정
                Glide.with(News_2.this).load(itemList.get(1).getImgUrl()).into(imgNews2);
                // 각 항목의 URL을 저장하거나 사용
                url2 = itemList.get(1).getUrlGo();
            }
        }
    };

}