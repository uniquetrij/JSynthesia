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
public class Matra
{

    private final Swar[] sequence;

    public Matra(Swar... sequence)
    {
        this.sequence = sequence.clone();
    }

    /**
     * 
     * @return the number of Swars in this Matra.
     */
    public int length()
    {
        return sequence.length;
    }

    /**
     * 
     * @param index 
     * @return the Swar at the given index in this Matra.
     */
    public Swar get(int index)
    {
        return sequence[index];
    }
}
