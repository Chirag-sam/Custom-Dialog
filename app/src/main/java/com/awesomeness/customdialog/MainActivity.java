package com.awesomeness.customdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button openDialog;
    TextView infoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDialog = findViewById(R.id.open_dialog);
        infoTv = findViewById(R.id.info_tv);

        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        final EditText nameEt = dialog.findViewById(R.id.name_et);
        final EditText ageEt = dialog.findViewById(R.id.age_et);
        final CheckBox termsCb = dialog.findViewById(R.id.terms_cb);
        Button submitButton = dialog.findViewById(R.id.submit_button);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getText().toString();
                String age = ageEt.getText().toString();
                Boolean hasAccepted = termsCb.isChecked();
                populateInfoTv(name,age,hasAccepted);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void populateInfoTv(String name, String age, Boolean hasAcceptedTerms) {
        infoTv.setVisibility(View.VISIBLE);
        String acceptedText = "have";
        if(!hasAcceptedTerms) {
            acceptedText = "have not";
        }
        infoTv.setText(String.format(getString(R.string.info), name, age, acceptedText));
    }
}
