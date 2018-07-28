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
public class Saptak {

    public static final int UP_SCALE_OCTAVE = 12;
    public static final int DOWN_SCALE_OCTAVE = -12;

    public static final int SCALE_C_NATURAL = 60;
    public static final int SCALE_C_SHARP = 61;

    protected final int scale;

    public Saptak() {
        this(SCALE_C_NATURAL);
    }

    public Saptak(int scale) {
        this.scale = scale;
    }


        public Saptak upScale(int notes) {
        return new Saptak(scale + notes);
    }
            public Saptak downScale(int notes) {
        return new Saptak(scale + notes);
    }

    public Saptak upOctave(int octaves) {
        return upScale(octaves * UP_SCALE_OCTAVE);
    }

    public Saptak downOctave(int octaves) {
        return downScale(octaves * DOWN_SCALE_OCTAVE);
    }

}
