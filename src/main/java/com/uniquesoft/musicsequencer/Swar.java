/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquesoft.musicsequencer;

/**
 *
 * @author Uniquetrij
 */
public class Swar {

    public static final Swar NONE = null;
    public static final Swar SA = new Swar(0);
    public static final Swar RE_K = new Swar(1);
    public static final Swar RE = new Swar(2);
    public static final Swar GA_K = new Swar(3);
    public static final Swar GA = new Swar(4);
    public static final Swar MA = new Swar(5);
    public static final Swar MA_T = new Swar(6);
    public static final Swar PA = new Swar(7);
    public static final Swar DHA_K = new Swar(8);
    public static final Swar DHA = new Swar(9);
    public static final Swar NI_K = new Swar(10);
    public static final Swar NI = new Swar(11);

    private final int index;
    private Swar taar;
    private Swar mandra;

    private Swar(int note) {
        this.index = note;
    }

    /**
     *
     * @return the Swar in Taar-Saptak for this Swar when this is in
     * Madhya-Saptak.
     */
    public Swar taar() {
        if (taar == null) {
            taar = new Swar(index + 12);
        }
        return taar;
    }

    /**
     *
     * @return the Swar in Mandra-Saptak for this Swar when this is in
     * Madhya-Saptak.
     */
    public Swar mandra() {
        if (mandra == null) {
            mandra = new Swar(index - 12);
        }
        return mandra;
    }

    public Swar next() {
        return new Swar(index + 1);
    }

    public Swar previous() {
        return new Swar(index - 1);
    }

    public int toMIDInoteNumber(Saptak saptak) {
        return saptak.scale + index;
    }
}
