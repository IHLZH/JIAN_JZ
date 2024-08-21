package com.example.jian_jz.Entity;

import java.io.Serializable;

public class Header implements Serializable {
    private Double in;
    private Double out;
    private String time;

    public Header() {
    }

    public Header(Double in, Double out, String time) {
        this.in = in;
        this.out = out;
        this.time = time;
    }

    public Double getIn() {
        return in;
    }

    public void setIn(Double in) {
        this.in = in;
    }

    public Double getOut() {
        return out;
    }

    public void setOut(Double out) {
        this.out = out;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
