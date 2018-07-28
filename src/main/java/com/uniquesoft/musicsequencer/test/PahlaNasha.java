/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquesoft.musicsequencer.test;


import com.uniquesoft.musicsequencer.Matra;
import com.uniquesoft.musicsequencer.Notes;
import com.uniquesoft.musicsequencer.Saptak;
import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Uniquetrij
 */
public class PahlaNasha {

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
        String notes = ".... "
                + "F..F F... "
                + ".EF+C .F.E F..F F... "
                + ".EF+C .F.E D..D D... "
                + ".DEF AFFG .GG. .... "
                + ".CFG AGFE "
                + "F..F F... "
                + ".EF+C .F.E F..F F... "
                + ".EF+C .F.E D..D D... "
                + ".DEF AFFG .GG. .... "
                + ".F E.F. ... ";

        play(800, Notes.toMatraArray(notes));

        Thread.sleep(5000);
    }

    public static void play(int kaal, Matra... seq) throws MidiUnavailableException, InterruptedException {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel channel1 = synth.getChannels()[8];
        MidiChannel channel2 = synth.getChannels()[8];
        Instrument instrument1 = synth.getAvailableInstruments()[6];
        Instrument instrument2 = synth.getAvailableInstruments()[6];
        channel1.programChange(instrument1.getPatch().getBank(), instrument1.getPatch().getProgram());
        channel2.programChange(instrument2.getPatch().getBank(), instrument2.getPatch().getProgram());
        channel1.noteOn(0, 0);
        channel2.noteOn(0, 0);
        Integer prevnote = null;
        Saptak saptak = new Saptak(Saptak.SCALE_C_NATURAL).upOctave(2);
        for (Matra matra : seq) {
            int length = matra.length();
            int duration = kaal / length;
            int elapsed = 0;
            for (int i = 0; i < length - 1; i++) {
                if (matra.get(i) != null) {
                    if (prevnote != null) {
                        channel1.noteOff(prevnote);
                        channel2.noteOff(prevnote);
                    }
//                    channel1.noteOn(prevnote = matra.get(i).toMIDInoteNumber(saptak), 127);
                    channel2.noteOn(prevnote = matra.get(i).toMIDInoteNumber(saptak.upOctave(1)), 127);

                }
                Thread.sleep(duration);
                elapsed += duration;
            }
            if (matra.get(length - 1) != null) {
                if (prevnote != null) {
                    channel1.noteOff(prevnote);
                    channel2.noteOff(prevnote);
                }
//                channel1.noteOn(prevnote = matra.get(length - 1).toMIDInoteNumber(saptak), 127);
                channel2.noteOn(prevnote = matra.get(length - 1).toMIDInoteNumber(saptak.upOctave(1)), 127);

            }
            Thread.sleep(kaal - elapsed);
        }
    }
}
