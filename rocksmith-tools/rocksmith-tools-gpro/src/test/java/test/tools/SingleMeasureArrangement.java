/*
 * Created on Mar 27, 2005
 */
package test.tools;

import java.util.ArrayList;
import java.util.List;

import dguitar.players.sound.Arrangement;

/**
 * @author Chris
 */
public class SingleMeasureArrangement implements Arrangement
{
    int measure;
    
    public SingleMeasureArrangement(int measure)
    {
        this.measure=measure;
    }

    /* (non-Javadoc)
     * @see players.sound.Arrangement#getMeasureList()
     */
    public List<Integer> getMeasureList()
    {
        ArrayList<Integer> aList=new ArrayList<Integer>(1);
        aList.add(new Integer(measure));
        return aList;
    }

}
