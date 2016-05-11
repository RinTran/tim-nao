package com.thienphuoc.tpkaraoke;

import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import data.Constants;
import data.DataHolder;
import data.DatabaseHandler;
import model.MySong;

public class BackupFavoritesActivity extends AppCompatActivity {

    protected EditText editTextPhone, editTextPassword;
    protected TextInputLayout textInputLayout, textInputLayout2;
    protected Button btnBackup;
    protected TextView tvShowMessage;
    protected DatabaseHandler databaseHandler;
    protected Firebase myFirebase;
    protected ArrayList<MySong> arirangFavorites, musicoreFavorites, vietKTVFavorites, californialFavorites;
    protected String textPhone, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_favorites);

        Firebase.setAndroidContext(this);//init

        refView();

        setFontForAllView();

        btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkUsernamePassword()) {
                    getListFavorites();

                    showMessage("Đang kiểm tra...!");

                    myFirebase = new Firebase("https://kikaraoke.firebaseio.com/").child(textPhone);
                    myFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                showMessage("Tài khoản đã tồn tại!");
                            } else {
                                Firebase myFirebase2 = new Firebase("https://kikaraoke.firebaseio.com/");
                                myFirebase2.child(editTextPhone.getText().toString()).child(editTextPassword.getText().toString()).child(Constants.DB_TABLE_ARIRANG).setValue(arirangFavorites);
                                myFirebase2.child(editTextPhone.getText().toString()).child(editTextPassword.getText().toString()).child(Constants.DB_TABLE_MUSICCORE).setValue(musicoreFavorites);
                                myFirebase2.child(editTextPhone.getText().toString()).child(editTextPassword.getText().toString()).child(Constants.DB_TABLE_CALIFORNIA).setValue(californialFavorites);
                                myFirebase2.child(editTextPhone.getText().toString()).child(editTextPassword.getText().toString()).child(Constants.DB_TABLE_VIETKTV).setValue(vietKTVFavorites);
                                showMessage("Sao lưu thành công!");
                            }
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
            }
        });
    }

    boolean checkUsernamePassword() {
        if (!editTextPhone.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()) {
            textPhone = editTextPhone.getText().toString();
            String head3Number = textPhone.substring(0, 3);
            String head4Number = textPhone.substring(0, 4);
            if (textPhone.length() >= 10 && textPhone.length() <= 11 && (
                            head3Number.equals("090") || head3Number.equals("093") ||
                            head3Number.equals("096") || head3Number.equals("097") || head3Number.equals("098") ||
                            head3Number.equals("091") || head3Number.equals("094") ||
                            head3Number.equals("092") ||
                            head3Number.equals("095") ||
                            head3Number.equals("083") || head3Number.equals("088") || head3Number.equals("082") || head3Number.equals("086") || head3Number.equals("084") || head3Number.equals("087") || head3Number.equals("085") ||
                            head4Number.equals("0120") || head4Number.equals("0121") || head4Number.equals("0122") || head4Number.equals("0126") || head4Number.equals("0128") ||
                            head4Number.equals("0163") || head4Number.equals("0164") || head4Number.equals("0165") || head4Number.equals("0166") || head4Number.equals("0167") || head4Number.equals("0168") || head4Number.equals("0169") ||
                            head4Number.equals("0123") || head4Number.equals("0124") || head4Number.equals("0125") || head4Number.equals("0127") || head4Number.equals("0129") ||
                            head4Number.equals("0188") || head4Number.equals("0993") || head4Number.equals("0994") || head4Number.equals("0995") || head4Number.equals("0996") || head4Number.equals("0199")
            )) {
                textPassword = editTextPassword.getText().toString();
                if (textPassword.length() < 5) {
                    showMessage("Password quá ngắn!\n" + "Vui lòng nhập lại!");
                    return false;
                } else return true;
            } else {
                showMessage("Số điện thoại bạn nhập không hợp lệ!\n" + "Vui lòng nhập lại!");
                return false;
            }
        } else if (!editTextPhone.getText().toString().isEmpty() && editTextPassword.getText().toString().isEmpty()) {
            showMessage("Vui lòng nhập mật khẩu!");
        } else {
            showMessage("Vui lòng nhập số điện thoại!");
        }
        return false;
    }

    void getListFavorites() {
        databaseHandler = DataHolder.getInstance().getDatabaseHandler();
        arirangFavorites = databaseHandler.searchInALLNumber(Constants.DB_TABLE_ARIRANG);
        vietKTVFavorites = databaseHandler.searchInALLNumber(Constants.DB_TABLE_VIETKTV);
        musicoreFavorites = databaseHandler.searchInALLNumber(Constants.DB_TABLE_MUSICCORE);
        californialFavorites = databaseHandler.searchInALLNumber(Constants.DB_TABLE_CALIFORNIA);
    }

    void refView() {
        arirangFavorites = new ArrayList<>();
        musicoreFavorites = new ArrayList<>();
        vietKTVFavorites = new ArrayList<>();
        californialFavorites = new ArrayList<>();


        textInputLayout = (TextInputLayout) findViewById(R.id.txtInpLay1);
        textInputLayout2 = (TextInputLayout) findViewById(R.id.txtInpLay2);
        btnBackup = (Button) findViewById(R.id.btn_backup);
        editTextPhone = (EditText) findViewById(R.id.ed_phone);
        editTextPassword = (EditText) findViewById(R.id.ed_password);
        tvShowMessage = (TextView) findViewById(R.id.tv_show_message);
    }

    void setFontForAllView() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/MTO Dom.ttf");
        editTextPhone.setTypeface(font);
        editTextPassword.setTypeface(font);
        textInputLayout.setTypeface(font);
        textInputLayout2.setTypeface(font);
        btnBackup.setTypeface(font);
        btnBackup.setText("Sao lưu ngày".toLowerCase());
    }

    void showMessage(String message){
        tvShowMessage.setVisibility(View.VISIBLE);
        tvShowMessage.setText(message);
    }
}
