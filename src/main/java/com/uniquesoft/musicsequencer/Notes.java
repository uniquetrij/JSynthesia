/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquesoft.musicsequencer;

import java.util.ArrayList;

/**
 *
 * @author uniquetrij
 */
public class Notes {

    public static Matra[] toMatraArray(String notes) {
        String[] splits = notes.replace("\n", " ").split(" ");
        ArrayList<Matra> list = new ArrayList<>();
        for (String split : splits) {
            list.add(getMatra(split));
        }

        return list.toArray(new Matra[0]);

    }

    public static Matra getMatra(String notes) {
        ArrayList<Swar> sequence = new ArrayList<>();
        notes += "  ";
        char note1;
        char note2;
        char note3;
        for (int i = 0; i < notes.length() - 2; i++) {
            note1 = notes.charAt(i);
            note2 = notes.charAt(i + 1);
            note3 = notes.charAt(i + 2);
            Character note = null;
            Boolean sharp = Boolean.FALSE;
            Integer octave = 0;
            switch (note1) {
                case '+':
                    octave = 1;
                    note = note2;
                    i++;
                    break;
                case '-':
                    octave = 1;
                    note = note2;
                    i++;
                    break;
                default:
                    note = note1;
            }
            if (note2 == '#') {
                sharp = Boolean.TRUE;
                i++;
            }
            if (note3 == '#') {
                sharp = Boolean.TRUE;
                i++;
            }

            sequence.add(getSwar(note, sharp, octave));
        }
        return new Matra(sequence.toArray(new Swar[0]));
    }

    public static Swar getSwar(Character note, Boolean sharp, Integer octave) {
        Swar swar = null;
        switch (note) {
            case 'C':
                swar = Swar.SA;
                break;
            case 'D':
                swar = Swar.RE;
                break;
            case 'E':
                swar = Swar.GA;
                break;
            case 'F':
                swar = Swar.MA;
                break;
            case 'G':
                swar = Swar.PA;
                break;
            case 'A':
                swar = Swar.DHA;
                break;
            case 'B':
                swar = Swar.NI;
                break;
            default:
                return Swar.NONE;
        }
        if (sharp) {
            swar = swar.next();
        }

        while (octave-- > 0) {
            swar = swar.taar();
        }

        while (octave++ < 0) {
            swar = swar.mandra();
        }

        return swar;

    }
}
