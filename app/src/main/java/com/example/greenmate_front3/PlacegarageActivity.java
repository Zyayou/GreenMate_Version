package com.example.greenmate_front3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//지도
import net.daum.mf.map.api.MapView;
//마커
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapView.POIItemEventListener;
//주소 -> 위도 경도
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlacegarageActivity extends AppCompatActivity implements MapView.POIItemEventListener{
    private MapView mapView; //mf로 import할것
    private ViewGroup mapViewContainer;
    ImageButton back;
    TextView addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placegarage);

        // <값 가져오기
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras(); //Extra들을 가져옴
        String area_state = bundle.getString("arer_state");   //가져온 Extars 중에서 꺼내기
        String area_city = bundle.getString("area_city");

        //테스트 (텍스트 띄우기)
        addressText = findViewById(R.id.addressText);
        addressText.setText(area_state+" "+area_city);
        // 값 가져오기>

        // <뒤로가기 버튼------------------------------------
        back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OutputAreaActivity.class);
                startActivity(intent);
            }
        });
        // ---------------------------------------뒤로가기 버튼>

        /* 키 해시 얻기, 얻은 후엔 필요 없음*/
/*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) { ////Signature  추가할 때 android~~~로 할것
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("키해시는 :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
*/

        // <지도 띄우기-----------------------------------------------------
        mapView = new MapView(this);
        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        mapView.setPOIItemEventListener(this); // 이벤트 사용 하려면 추가해야 함

        // 중심점 바꾸기(임시좌표)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(36.5785066, 128.573529), true);
        // 지도 표시
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

        // --------------------------------------------------지도 띄우기>

        // <핀-----------------------------------------------------------
        // 복수, DB에서 좌표 값 불러와 지도에 띄우기
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

                            String address = locationObject.getString("cle_address");
                            double latitude = locationObject.getDouble("cle_lat");
                            double longitude = locationObject.getDouble("cle_lng");

                            // 마커 생성
                            MapPOIItem marker = new MapPOIItem();
                            marker.setItemName(address);
                            marker.setTag(i);
                            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
                            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
                            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

                            // 지도에 마커 추가
                            mapView.addPOIItem(marker);
                        }

                        Toast.makeText(getApplicationContext(), "데이터를 성공적으로 불러왔습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        CleanHouseRequest cleanHouseRequest = new CleanHouseRequest(area_state, area_city, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PlacegarageActivity.this);
        queue.add(cleanHouseRequest);


    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem poiItem){
        addressText.setText(poiItem.getItemName());
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}

