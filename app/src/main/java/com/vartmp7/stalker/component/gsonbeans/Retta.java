package com.vartmp7.stalker.component.gsonbeans;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class
/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
Retta {

    private double m;
    private double q;
    private Coordinata a, b;

    public Retta(Coordinata c1, Coordinata c2) {
        this.m = (c2.getLatitude() - c1.getLatitude()) / (c2.getLongitude() - c1.getLongitude());
        this.q = (((c2.getLatitude() - c1.getLatitude())*(-1)*c1.getLongitude()) / (c2.getLongitude() - c1.getLongitude())) + c1.getLatitude();
        a= c1;
        b = c2;
    }

    public Retta(float coeffangolare, float q) {
        this.m = coeffangolare;
        this.q = q;
    }

    /**
     * calcola il segno del determinante
     * @param c la coordinata del punto
     * @return return un numero >0 se la coordinata c è a destra della retta (a,b)
     *              un numero = 0 se la coordinata c appartiene alla retta (a,b)
     *              un numero < 0 se la coordinata c è a sinistra della retta (a,b)
     */

    public double distanceToCoordinate(Coordinata c){
        return (b.getLongitude()-c.getLongitude())-(a.getLatitude()-c.getLatitude())/(a.getLatitude()-c.getLatitude());
    }
    public double calcoloLatitude(double longitude) {
        return this.m * longitude + this.q;
    }

    Coordinata intersezione(Retta r) {
        //System.out.println("x/:"+(-this.q + r.q));
        //System.out.println("/x:"+(this.m - r.m));
        double x = (-this.q + r.q) / (this.m - r.m);

        return new Coordinata(calcoloLatitude(x),x);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Retta) {
            Retta c = (Retta) obj;
            return c.m == this.m && c.q == this.q;
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        if (q<=0){
            return  "y="+m+"x"+q;
        }
        return "y="+m+"x+"+q;
    }
    public static boolean linesIntersect(final double X1, final double Y1, final double X2, final double Y2,
                                         final double X3, final double Y3, final double X4, final double Y4) {
        return ((relativeCCW(X1, Y1, X2, Y2, X3, Y3)
                * relativeCCW(X1, Y1, X2, Y2, X4, Y4) <= 0) && (relativeCCW(X3,
                Y3, X4, Y4, X1, Y1)
                * relativeCCW(X3, Y3, X4, Y4, X2, Y2) <= 0));
    }
    private static int relativeCCW(final double X1, final double Y1, double X2, double Y2, double PX,
                                   double PY) {
        X2 -= X1;
        Y2 -= Y1;
        PX -= X1;
        PY -= Y1;
        double ccw = PX * Y2 - PY * X2;
        if (ccw == 0) {
            // The point is colinear, classify based on which side of
            // the segment the point falls on. We can calculate a
            // relative value using the projection of PX,PY onto the
            // segment - a negative value indicates the point projects
            // outside of the segment in the direction of the particular
            // endpoint used as the origin for the projection.
            ccw = PX * X2 + PY * Y2;
            if (ccw > 0) {
                // Reverse the projection to be relative to the original X2,Y2
                // X2 and Y2 are simply negated.
                // PX and PY need to have (X2 - X1) or (Y2 - Y1) subtracted
                // from them (based on the original values)
                // Since we really want to get a positive answer when the
                // point is "beyond (X2,Y2)", then we want to calculate
                // the inverse anyway - thus we leave X2 & Y2 negated.
                PX -= X2;
                PY -= Y2;
                ccw = PX * X2 + PY * Y2;
                if (ccw < 0) {
                    ccw = 0;
                }
            }
        }
        return (ccw < 0) ? -1 : ((ccw > 0) ? 1 : 0);
    }
}