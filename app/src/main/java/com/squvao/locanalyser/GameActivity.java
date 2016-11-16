package com.squvao.locanalyser;

import android.*;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GameActivity extends FragmentActivity implements OnMapReadyCallback {
    private LocationManager manager;
    private GoogleMap mMap;
    private LocationListener listener = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
            /*mMap.addMarker(new MarkerOptions().position(point).title("You're here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point));*/
            Toast.makeText(GameActivity.this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //этот метод вызывается при  изменении статуса провайдера
        }

        @Override
        public void onProviderEnabled(String provider) {
            if (ActivityCompat.checkSelfPermission(GameActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(GameActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(GameActivity.this, "Access denied", Toast.LENGTH_SHORT).show();
                return;
            }
            Location location = manager.getLastKnownLocation(provider);
            Toast.makeText(GameActivity.this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
            /*mMap.addMarker(new MarkerOptions().position(point).title("You're here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point));*/
        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void location() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                /*
                Данный код применяется для Андроид 6.0 и выше. В коде происходит запрос андроиду
                на разрешения доступа к локации. Вывод сообщения если доступ к локации запрещен.
                 */
            Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
            return;
        }
        if (mMap != null) {
            /*
            Запрос всем провайдерам на обновление локации с интервалом 0 мили сек.
             */
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
            manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, listener);

        /*
        PASSIVE_PROVIDER это сигнал сотового оператора
        Build.VERSION.SDK_INT проверка на версию Андроид ECLAIR_MR1 (Андроид 2.1)
         */
            Location loc = null;
            String[] providers = new String[]{LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER, LocationManager.PASSIVE_PROVIDER};

            for (String provider : providers) {
                loc = manager.getLastKnownLocation(provider); //получение локации

                /*
                Локация может быть не найдена если провайдеры не отвечают
                Соответственно проверяем ее на пустоту.
                 */
                if (loc != null) {
                    Toast.makeText(this, loc.getLatitude() + " " + loc.getLongitude(), Toast.LENGTH_SHORT).show();
                    LatLng point = new LatLng(loc.getLatitude(), loc.getLongitude());
                    /*mMap.addMarker(new MarkerOptions().position(point).title(provider));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(point));*/
                } else
                    Toast.makeText(this, provider + " не сработал", Toast.LENGTH_SHORT).show();
            }
            Location location = loc;
            if(location != null)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16.0f));

            /*Окно для запроса изменения масштаба карты*//*
            final Location location = loc;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Scale change");
            builder.setMessage("Would you like change scale?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(GameActivity.this, "ZBS", Toast.LENGTH_SHORT).show();
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16.0f));
                    //CameraUpdateFactory.zoomIn();
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();*/
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
        location();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.removeUpdates(listener);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "OnMapReady", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);//установка отображения точки.

        LatLng point = new LatLng(51.2794021,1.086669);
        //mMap.addMarker(new MarkerOptions().position(point).title("You're here").icon(BitmapDescriptorFactory.fromResource(R.drawable.castle_icon)).);
        //добавление точки , которая меняет размер при увеличении масштаба
        mMap.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.castle_icon)).position(point, 35f, 35f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        location();
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        /*mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                Toast.makeText(GameActivity.this, "Change", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
