package com.vartmp7.stalker.GsonBeans;

import android.util.Log;

import androidx.annotation.Nullable;

public class Retta {

    float m;
    float q;

    public Retta(Coordinata c1, Coordinata c2) {
        this.m = (c1.getLatitude() - c2.getLatitude()) / (c1.getLongitude() - c1.getLongitude());
        this.q = (c1.getLatitude() - c2.getLatitude()) / (c1.getLongitude() - c1.getLongitude()) + c2.getLatitude();

    }

    public Retta(float coeffangolare, float q) {
        this.m = coeffangolare;
        this.q = q;
    }

    public float calcoloY(float x) {
        return this.m * x + this.q;
    }

    Coordinata intersezione(Retta r) {
        //System.out.println("x/:"+(-this.q + r.q));
        //System.out.println("/x:"+(this.m - r.m));
        float x = (-this.q + r.q) / (this.m - r.m);

        return new Coordinata(x, calcoloY(x));
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Retta) {
            Retta c = (Retta) obj;
            return c.m == this.m && c.q == this.q;
        }
        return false;
    }

}