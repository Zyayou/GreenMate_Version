package com.example.greenmate_front3.eco;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.greenmate_front3.CleanHouseRequest;
import com.example.greenmate_front3.PlacegarageActivity;
import com.example.greenmate_front3.R;
import com.example.greenmate_front3.activity.MainActivity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EcoPlaceMapFragment extends Fragment {
    public MapView mapView; //mf로 import할것
    public ViewGroup mapViewContainer;
    TextView addressText;
    public EcoPlaceMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_ecoplace_map, container, false);

        String area_state = "경상북도";   //가져온 Extars 중에서 꺼내기
        String area_city = "안동시";


        //테스트 (텍스트 띄우기)
        addressText = view.findViewById(R.id.addressText);
        //addressText.setText(area_state+" "+area_city);
        // 이전 페이지에서 값 가져오기>

        // <지도 띄우기-----------------------------------------------------
        mapView = new MapView(getActivity());
        mapViewContainer = (ViewGroup) view.findViewById(R.id.mapView);
        mapViewContainer.addView(mapView);

/*
        //mapView.setPOIItemEventListener(this); // 이벤트 사용 하려면 추가해야 함

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

                        Toast.makeText(getActivity(), "데이터를 성공적으로 불러왔습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "데이터를 불러오는데 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        CleanHouseRequest cleanHouseRequest = new CleanHouseRequest(area_state, area_city, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(cleanHouseRequest);
*/
        return view;
    }


/*
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

 */
}
