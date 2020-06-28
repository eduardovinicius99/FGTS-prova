package com.example.fgts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setData();
    }

    private void setData(){
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Bundle b = getIntent().getExtras();
            TextView txtdt1 = (TextView) findViewById(R.id.txtDate1);
            TextView txtdt2 = (TextView) findViewById(R.id.txtDate2);
            TextView txtdt3 = (TextView) findViewById(R.id.txtDate3);
            TextView[] texts = new TextView[3];
            texts[0] = txtdt1;
            texts[1] = txtdt2;
            texts[2] = txtdt3;


            String[] dates = b.getStringArray("data");
            for(int i = 0; i < dates.length; i++){
                cal.setTime(df.parse(dates[i]));
                String date = cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
                texts[i].setText(date);
            }

        }catch (ParseException pe){
            Toast toast = Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG);
            toast.setMargin(0,0);
            toast.show();
        }

    }
}
