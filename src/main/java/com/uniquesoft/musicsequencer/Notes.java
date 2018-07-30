/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquesoft.musicsequencer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author uniquetrij
 */
public class Notes {

    public static Matra[] toMatraArray(String notes) {
        String[] splits = notes.split("\\|");
//        System.out.println(Arrays.toString(splits));
        ArrayList<Matra> list = new ArrayList<>();
        for (String split : splits) {
            Matra m = getMatra(split);
            list.add(m);
        }

//        for (Matra m1 : list) {
//            for (int i = 0; i < m1.length(); i++) {
//                try {
//                    System.out.println("----------------" + m1.get(i).toMIDInoteNumber(new Saptak()));
//                    System.out.println("----------------" + m1.get(i).getIntensity());
//                } catch (Exception e) {
//
//                }
//            }
//        }

        return list.toArray(new Matra[0]);
    }

    public static Matra getMatra(String notes) {
        ArrayList<Swar> sequence = new ArrayList<>();
        String[] splits = notes.split(" ");
//        System.out.println(Arrays.toString(splits));
        for (String split : splits) {
            if (split.isEmpty()) {
                continue;
            }
            Character _1 = split.charAt(0);
            Character _2 = split.length() > 1 ? split.charAt(1) : '\0';
            Character _3 = split.length() > 2 ? split.charAt(2) : '\0';
            Character _4 = split.length() > 3 ? split.charAt(3) : '\0';
            Character note = null;
            Boolean sharp = Boolean.FALSE;
            Integer octave = 0;
            Double intensity = 1.0;
            switch (_1) {
                case '+':
                    octave = 1;
                    note = _2;
                    break;
                case '-':
                    octave = -1;
                    note = _2;
                    break;
                default:
                    note = _1;
            }
            if (_2 == '#') {
                sharp = Boolean.TRUE;
            }
            try {
                intensity = intensity * Integer.parseInt(_2 + "") / 10.0 + 0.1;
//                System.out.println("ERR2");
            } catch (Exception ex) {

            }
            if (_3 == '#') {
                sharp = Boolean.TRUE;
            }
            try {
                intensity = intensity * Integer.parseInt(_3 + "") / 10.0 + 0.1;
//                System.out.println("ERR3");
            } catch (Exception ex) {

            }
            try {
                intensity = intensity * Integer.parseInt(_4 + "") / 10.0 + 0.1;
//                System.out.println("ERR4");
            } catch (Exception ex) {

            }
//            System.out.println(intensity);
            Swar swar = getSwar(note, sharp, octave, intensity);
//            if (swar != null) {
//                System.out.println(swar.getIntensity());
//            }
            sequence.add(swar);
        }
        return new Matra(sequence.toArray(new Swar[0]));
    }

    public static Swar getSwar(Character note, Boolean sharp, Integer octave, Double intensity) {
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
        swar = swar.newIntensity(intensity);
        return swar;

    }
}
