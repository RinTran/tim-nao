package com.lgp.lgp.ipaddress;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtvDuong,txtXa,txtQuan,txtTinh,txtNuoc;
    EditText edtA,edtB;
    Button btnOk;
    private static String url;

    private static final String TAG_CONTACTS = "results";
    private static final String TAG_ADDRESS = "address_components";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String eqDuong = "[\"premise\"]";
    private static final String eqXa = "[\"sublocality_level_1\",\"sublocality\",\"political\"]";
    private static final String eqQuan = "[\"administrative_area_level_2\",\"political\"]";
    private static final String eqTinh = "[\"administrative_area_level_1\",\"political\"]";
    private static final String eqNuoc = "[\"country\",\"political\"]";
    ListView lvdata;
    Adapter adapter;

    ArrayList list = new ArrayList();
    // contacts JSONArray
    JSONArray contacts = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtvDuong = (TextView) findViewById(R.id.textViewShow);
        txtXa = (TextView) findViewById(R.id.textViewShowXa);
        txtQuan = (TextView) findViewById(R.id.textViewShowQuan);
        txtTinh = (TextView) findViewById(R.id.textViewShowTinh);
        txtNuoc = (TextView) findViewById(R.id.textViewShowdatnuoc);
        edtA = (EditText) findViewById(R.id.editTexta);
        edtB = (EditText) findViewById(R.id.editTextb);
        btnOk = (Button) findViewById(R.id.buttonok);


        edtA.setText("10");edtB.setText("106");
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtvDuong.setText("Đường: ");
                txtXa.setText("Xã/Phường: ");
                txtQuan.setText("Quận/Huyện: ");
                txtTinh.setText("Tình/Tp: ");
                txtNuoc.setText("Đất Nước: ");
                url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + edtA.getText().toString() + "," + edtB.getText().toString() + "&sensor=true";
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new LoadStringURL().execute(url);
                    }
                });
            }
        });

    }

    class LoadStringURL extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            String noidung = getXmlFromUrl(params[0]);
            return noidung;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray result = jsonObject.getJSONArray(TAG_CONTACTS);
                for(int i = 0; i < 1;i++){
                    JSONObject c = result.getJSONObject(i);
                    JSONArray address = c.getJSONArray(TAG_ADDRESS);
                    for(int j = 0;j<address.length();j++){
                        JSONObject jb = address.getJSONObject(j);
                        String type = jb.getString("types");
                        if(type.equals(eqDuong)){
                            String duong = jb.getString("long_name");
                            txtvDuong.setText("Đường: "+duong);
                        }
                        else if(type.equals(eqXa)){
                            String xa = jb.getString("long_name");
                            txtXa.setText("Xã/Phường: "+xa);

                        }
                        else if(type.equals(eqQuan)){
                            String quan = jb.getString("long_name");
                            txtQuan.setText("Quận/Huyện: "+quan);
                        }
                        else if(type.equals(eqTinh)){
                            String tinh = jb.getString("long_name");
                            txtTinh.setText("Tình/Tp: "+tinh);
                        }
                        else if(type.equals(eqNuoc)){
                            String nuoc = jb.getString("long_name");
                            txtNuoc.setText("Đất Nước: "+nuoc);
                        }

                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private String getXmlFromUrl(String urlString) {
        String xml = null;
        try {
            // defaultHttpClient lấy toàn bộ dữ liệu trong http đổ vào 1 chuỗi String
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlString);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity, HTTP.UTF_8);
            // set UTF-8 cho ra chữ unikey
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
        return xml;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
