package com.manheiminfoscanner.util;

import com.squareup.otto.Bus;

/**
 * Created by zfolkz on 11/29/16 AD.
 */
public class BusManheim extends  Bus{
    private static BusManheim ourInstance = new BusManheim();

    public static BusManheim getInstance() {
        return ourInstance;
    }

    private BusManheim() {

    }

}
