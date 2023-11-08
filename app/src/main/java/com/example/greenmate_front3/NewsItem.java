package com.example.greenmate_front3;

public class NewsItem {
    String title;
    String img;
    String url_go;

    NewsItem(String title, String img, String url_go){
        this.title = title;
        this.img = img;
        this.url_go = url_go;
    }

    String getTitle(){
        return title;
    }
    String getImgUrl(){
        return img;
    }
    String getUrlGo(){
        return url_go;
    }


}
