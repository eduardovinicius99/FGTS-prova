package com.example.fgts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btnCheck);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoa ps = getData();
                if(ps.getNasc() != null && ps.getCpf() != 0) {
                    ps = calc(ps);
                    toResultActivity(ps);
                }
            }
        });
    }

    private Pessoa getData (){
        EditText cpf = (EditText) findViewById(R.id.txtCpf);
        EditText nasc = (EditText) findViewById(R.id.txtNasc);
        Pessoa ps = new Pessoa(0, null, null);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            ps.setCpf(Long.parseLong(cpf.getText().toString()));
            ps.setNasc(df.parse(nasc.getText().toString()));
        }catch (NumberFormatException e){
            Toast toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos corretamente", Toast.LENGTH_LONG);
            toast.setMargin(0,0);
            toast.show();
        }catch (ParseException pe){
            Toast toast = Toast.makeText(getApplicationContext(), "Data incorreta", Toast.LENGTH_LONG);
            toast.setMargin(0,0);
            toast.show();
        }catch (NullPointerException npe){
            Toast toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos corretamente", Toast.LENGTH_LONG);
            toast.setMargin(0,0);
            toast.show();
        }

        return ps;
    }

    private void toResultActivity(Pessoa ps){
        System.out.println("teste");
        Intent intent = new Intent(MainActivity.this, result.class);
        Bundle b = new Bundle();
        String[] datas = new String[3];
        for(int i = 0; i < ps.getDates().size(); i++){
            Calendar elem = ps.getDates().get(i);
            datas[i] = elem.get(Calendar.YEAR)+"-"+elem.get(Calendar.MONTH)+"-"+elem.get(Calendar.DAY_OF_MONTH);
        }
        b.putStringArray("data", datas);

        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
    private Pessoa calc(Pessoa ps){
        Calendar cal = Calendar.getInstance();
        cal.setTime(ps.getNasc());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int pag;
        boolean nextMonth = false;

        if(day <= 10){
            pag = 5;
        }else if(day > 20){
            pag = 15;
        }else{
            pag = 10;
        }

        Calendar now =  Calendar.getInstance();
        ArrayList<Calendar> dates = ps.getDates();
        for (int i = 0; i < 3; i++){
            int month = cal.get(Calendar.MONTH) + (i+2);
            int year = now.get(Calendar.YEAR);
            if(month < now.get(Calendar.MONTH)){
                year++;
            }
            Calendar dt = Calendar.getInstance();
            dt.set(year,month, pag);
            dates.add(dt);
        }
        ps.setDates(dates);
        return ps;

    }
}
