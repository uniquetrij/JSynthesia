/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniquesoft.musicsequencer;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Uniquetrij
 */
public class JavaSequencer
{

    private final Synthesizer synthesizer;
    private final MidiChannel[] channels;
    private final Instrument[] instruments;

    public JavaSequencer() throws MidiUnavailableException
    {
        this(MidiSystem.getSynthesizer());
    }

    public JavaSequencer(Synthesizer synthesizer)
    {
        this.synthesizer = synthesizer;
        channels = this.synthesizer.getChannels();
        instruments = synthesizer.getAvailableInstruments();
    }

    public Instrument[] getInstruments()
    {
        return instruments.clone();
    }

    public void play() throws MidiUnavailableException, InterruptedException
    {
        synthesizer.open();
        channels[0].noteOn(60, 127);
        Thread.sleep(2000);
        channels[0].noteOn(62, 50);
        channels[0].noteOff(60);
        channels[0].setPitchBend(0);
        Thread.sleep(200);
        channels[0].setPitchBend(8192);
//        channels[0].setPitchBend(16383);
//        Thread.sleep(200);
//        channels[0].setPitchBend(8192);
        Thread.sleep(5000);
//        channels[0].noteOff(60);
    }

    public static void main(String[] args) throws MidiUnavailableException, InterruptedException
    {
        JavaSequencer javaSequencer = new JavaSequencer();
//        javaSequencer.play();
        for (int i = 0; i < javaSequencer.instruments.length; i++)
            System.out.println(i + "\t" + javaSequencer.instruments[i].toString());
//        
//        Thread.sleep(Long.MAX_VALUE);

    }

}
