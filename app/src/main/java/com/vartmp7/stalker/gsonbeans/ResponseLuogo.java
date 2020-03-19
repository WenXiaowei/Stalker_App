package com.vartmp7.stalker.gsonbeans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xiaowei Wen, Lorenzo Taschin
 * per fare il parsing della gerarchia dei luoghi, guardare la guida.
 * https://www.javadoc.io/doc/org.danilopianini/gson-extras/0.2.1/com/google/gson/typeadapters/RuntimeTypeAdapterFactory.html
 */
public class ResponseLuogo {
    private static final String TAG="com.vartmp7.stalker.GsonBeans.ResponseLuogo";
    private List<? extends AbstractLuogo> places;

    public void setLuoghi(List<AbstractLuogo> luoghi) {
        this.places = luoghi;
    }
    public int getPlacesLength(){return places.size();}

}
