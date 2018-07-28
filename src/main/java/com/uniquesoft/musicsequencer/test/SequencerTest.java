/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquesoft.musicsequencer.test;

import com.uniquesoft.musicsequencer.Matra;
import com.uniquesoft.musicsequencer.Saptak;
import static com.uniquesoft.musicsequencer.Swar.DHA_K;
import static com.uniquesoft.musicsequencer.Swar.MA;
import static com.uniquesoft.musicsequencer.Swar.NONE;
import static com.uniquesoft.musicsequencer.Swar.PA;
import static com.uniquesoft.musicsequencer.Swar.RE_K;
import static com.uniquesoft.musicsequencer.Swar.SA;
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
public class SequencerTest
{

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, InterruptedException
    {

        play(250,
                new Matra(SA), new Matra(NONE), new Matra(RE_K), new Matra(MA),
                new Matra(PA), new Matra(NONE), new Matra(DHA_K), new Matra(PA),
                new Matra(MA), new Matra(NONE), new Matra(RE_K), new Matra(NONE),
                new Matra(SA), new Matra(NONE), new Matra(NONE), new Matra(NONE),
                //
                new Matra(DHA_K.mandra()), new Matra(NONE), new Matra(DHA_K.mandra()), new Matra(NONE),
                new Matra(SA), new Matra(NONE), new Matra(NONE), new Matra(NONE),
                new Matra(MA), new Matra(PA), new Matra(DHA_K), new Matra(SA.taar()),
                new Matra(DHA_K), new Matra(NONE), new Matra(PA), new Matra(NONE),
                //
                new Matra(MA, PA), new Matra(DHA_K, PA), new Matra(MA, PA), new Matra(DHA_K, SA.taar()),
                new Matra(MA, PA), new Matra(DHA_K, SA.taar()), new Matra(RE_K.taar(), MA.taar()), new Matra(RE_K.taar(), SA.taar()),
                new Matra(SA.taar(), SA.taar()), new Matra(DHA_K, DHA_K), new Matra(PA, PA), new Matra(MA, MA),
                new Matra(RE_K, RE_K), new Matra(SA, SA), new Matra(RE_K, RE_K), new Matra(SA),
                //
                //antara
                //
                new Matra(DHA_K), new Matra(NONE), new Matra(PA), new Matra(DHA_K),
                new Matra(SA.taar()), new Matra(NONE), new Matra(DHA_K), new Matra(SA.taar()),
                new Matra(RE_K.taar()), new Matra(NONE), new Matra(SA.taar()), new Matra(NONE),
                new Matra(DHA_K), new Matra(NONE), new Matra(SA.taar()), new Matra(NONE),
                //
                new Matra(RE_K.taar()), new Matra(NONE), new Matra(MA.taar()), new Matra(MA.taar()),
                new Matra(RE_K.taar()), new Matra(NONE), new Matra(SA.taar()), new Matra(NONE),
                new Matra(DHA_K), new Matra(NONE), new Matra(MA), new Matra(PA),
                new Matra(MA), new Matra(RE_K), new Matra(NONE), new Matra(SA),
                //
                new Matra(SA, RE_K), new Matra(MA, RE_K), new Matra(MA, PA), new Matra(DHA_K, SA.taar()),
                new Matra(SA.taar(), SA.taar()), new Matra(DHA_K, PA), new Matra(MA, PA), new Matra(DHA_K, SA.taar()),
                new Matra(SA.taar(), SA.taar()), new Matra(DHA_K, PA), new Matra(SA.taar(), SA.taar()), new Matra(DHA_K, PA),
                new Matra(SA.taar(), SA.taar()), new Matra(DHA_K, PA), new Matra(RE_K, RE_K), new Matra(SA));

        Thread.sleep(5000);
    }

    public static void play(int kaal, Matra... seq) throws MidiUnavailableException, InterruptedException
    {
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel chan = synth.getChannels()[8];
        Instrument instrument = synth.getAvailableInstruments()[1];
        chan.programChange(instrument.getPatch().getBank(), instrument.getPatch().getProgram());
        chan.noteOn(0, 0);
        Integer prevnote = null;
        Saptak saptak = new Saptak(Saptak.SCALE_C_NATURAL);
        for (Matra matra : seq)
        {
            int length = matra.length();
            int duration = kaal / length;
            int elapsed = 0;
            for (int i = 0; i < length - 1; i++)
            {
                if (matra.get(i) != null)
                {
                    if (prevnote != null)
                        chan.noteOff(prevnote);
                    chan.noteOn(prevnote = matra.get(i).toMIDInoteNumber(saptak), 127);
                }
                Thread.sleep(duration);
                elapsed += duration;
            }
            if (matra.get(length - 1) != null)
            {
                if (prevnote != null)
                    chan.noteOff(prevnote);
                chan.noteOn(prevnote = matra.get(length - 1).toMIDInoteNumber(saptak), 127);
            }
            Thread.sleep(kaal - elapsed);
        }
    }
}
