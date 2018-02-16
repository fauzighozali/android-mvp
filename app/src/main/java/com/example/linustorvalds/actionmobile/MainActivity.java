package com.example.linustorvalds.actionmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] data = {"Camera","Contact","Phone","SMS","Internet","Maps"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, data);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),String.valueOf(spinner.getSelectedItemPosition()),
                        Toast.LENGTH_LONG).show();

                int position = spinner.getSelectedItemPosition();

                Intent intent = null;

                switch (position){
                    case 0 :
                        //intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                        break;

                    case 1 :
                        intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("content://contacts//people//"));
                        break;

                    case 2 :
                        intent = new Intent(Intent.ACTION_DIAL, //ACTION.CALL  implisit menelfon
                                Uri.parse("tel:082234102649"));
                        break;

                    case 3 :
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setType("vnd.android-dir/mms-sms");
                        intent.putExtra("address","082234102649");
                        intent.putExtra("sms_body","sent from android");
                        break;

                    case 4 :
                        intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://google.com"));
                        break;

                    case 5 :
                        intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:15,30?z=17"));

                }
                startActivity(intent);
            }
        });

        spinner.setAdapter(adapter);
    }
}
