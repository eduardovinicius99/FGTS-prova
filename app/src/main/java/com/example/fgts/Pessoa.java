package com.example.fgts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Pessoa {
    private long cpf;
    private Date nasc;
    private ArrayList<Calendar> dates;

    public Pessoa(long cpf, Date nasc, ArrayList<Calendar> dates) {
        this.cpf = cpf;
        this.nasc = nasc;
        this.dates = new ArrayList<>();
    }

    public long getCpf() {
        return cpf;
    }

    public Date getNasc() {
        return nasc;
    }


    public ArrayList<Calendar> getDates() {
        return dates;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void setNasc(Date nasc) {
        this.nasc = nasc;
    }

    public void setDates(ArrayList<Calendar> dates) {
        this.dates = dates;
    }
}
