package com.example.greenmate_front3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainpageActivity extends Activity {
    private Button gohome_button, goqna_button, gomy_button, ecosearch_button;
    private TextView News1_title, News2_title, News3_title, News4_title;
    private ImageView News1_img, News2_img, News3_img, News4_img;

    //크롤링용
    private String URLnews = "https://www.hkbs.co.kr/news/articleList.html?page=1&total=56110&sc_section_code=S1N1&sc_sub_section_code=&sc_serial_code=&sc_area=&sc_level=&sc_article_type=&sc_view_level=&sc_sdate=&sc_edate=&sc_serial_number=&sc_word=&box_idxno=&sc_multi_code=&sc_is_image=&sc_is_movie=&sc_user_name=&sc_order_by=E&view_type=tm";
    String url1, url2, url3, url4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        gohome_button = findViewById(R.id.gohome);
        goqna_button = findViewById(R.id.goqna);
        gomy_button = findViewById(R.id.gomy);
        ecosearch_button = findViewById(R.id.ecosearch_button);

        News1_title =  findViewById(R.id.News1_title);
        News2_title =  findViewById(R.id.News2_title);
        News3_title =  findViewById(R.id.News3_title);
        News4_title =  findViewById(R.id.News4_title);
        News1_img = findViewById(R.id.News1_img);
        News2_img = findViewById(R.id.News2_img);
        News3_img = findViewById(R.id.News3_img);
        News4_img = findViewById(R.id.News4_img);


        gohome_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainpageActivity.class );
                startActivity(intent);
            }
        });

        goqna_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class );
                startActivity(intent);
            }
        });

        gomy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class );
                startActivity(intent);
            }
        });

        ecosearch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EcosearchActivity.class );
                startActivity(intent);
            }
        });


        //링크로 가기
        News1_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url1));
                startActivity(urlintent);
            }
        });
        News2_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url2));
                startActivity(urlintent);
            }
        });
        News3_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url3));
                startActivity(urlintent);
            }
        });
        News4_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url4));
                startActivity(urlintent);
            }
        });

        News1_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url1));
                startActivity(urlintent);
            }
        });
        News2_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url2));
                startActivity(urlintent);
            }
        });
        News3_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url3));
                startActivity(urlintent);
            }
        });
        News4_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hkbs.co.kr"+url4));
                startActivity(urlintent);
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

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();

            // itemList를 가져오고 사용자 인터페이스에 설정
            ArrayList<NewsItem> itemList = (ArrayList<NewsItem>) bundle.getSerializable("items");

            if (itemList.size() >= 4) {
                News1_title.setText(itemList.get(0).getTitle());
                News2_title.setText(itemList.get(1).getTitle());
                News3_title.setText(itemList.get(2).getTitle());
                News4_title.setText(itemList.get(3).getTitle());

                // 이미지 로딩 라이브러리 (Glide)를 사용하여 이미지 설정
                Glide.with(MainpageActivity.this).load(itemList.get(0).getImgUrl()).into(News1_img);
                Glide.with(MainpageActivity.this).load(itemList.get(1).getImgUrl()).into(News2_img);
                Glide.with(MainpageActivity.this).load(itemList.get(2).getImgUrl()).into(News3_img);
                Glide.with(MainpageActivity.this).load(itemList.get(3).getImgUrl()).into(News4_img);

                // 각 항목의 URL을 저장하거나 사용
                url1 = itemList.get(0).getUrlGo();
                url2 = itemList.get(1).getUrlGo();
                url3 = itemList.get(2).getUrlGo();
                url4 = itemList.get(3).getUrlGo();
            }
        }
    };

}
