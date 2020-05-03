/*
 * MIT License
 *
 * Copyright (c) 2020 VarTmp7
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vartmp7.stalker.datamodel.placecomponent;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class Edge {
    public static final String TAG = "com.vartmp7.stalker.gsonbeans.placecomponent.lato";
    @Getter
    @Setter
    @Accessors(chain = true)
    private double startX;
    @Getter
    @Setter
    @Accessors(chain = true)
    private double startY;
    @Getter
    @Setter
    @Accessors(chain = true)
    private double endX;
    @Getter
    @Setter
    @Accessors(chain = true)
    private double endY;

    public Edge(Coordinate a, Coordinate b) {
        this.startX = a.getLongitude();
        this.startY = a.getLatitude();
        this.endX = b.getLongitude();
        this.endY = b.getLatitude();
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.getStartX(), getStartX()) == 0 &&
                Double.compare(edge.getStartY(), getStartY()) == 0 &&
                Double.compare(edge.getEndX(), getEndX()) == 0 &&
                Double.compare(edge.getEndY(), getEndY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartX(), getStartY(), getEndX(), getEndY());
    }

    //restituisce true sse il lato interseca con il segmento che unisce  'coordinata' con (Double.Max_VALUE,Double.MAX_VALUE)
    public boolean linesIntersect(@NotNull Coordinate coordinate) {
        final double X1 = startX;
        final double Y1 = startY;
        final double X2 = endX;
        final double Y2 = endY;
        final double X3 = coordinate.getLongitude();
        final double Y3 = coordinate.getLatitude();
        final double X4 = Double.MAX_VALUE;
        final double Y4 = Double.MAX_VALUE;


        return ((relativeCCW(X1, Y1, X2, Y2, X3, Y3)
                * relativeCCW(X1, Y1, X2, Y2, X4, Y4) <= 0) && (relativeCCW(X3,
                Y3, X4, Y4, X1, Y1)
                * relativeCCW(X3, Y3, X4, Y4, X2, Y2) <= 0));
    }

    @Contract(pure = true)
    private int relativeCCW(final double X1, final double Y1, double X2, double Y2, double PX,
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
