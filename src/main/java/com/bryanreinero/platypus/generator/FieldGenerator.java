package com.bryanreinero.platypus.generator;

import com.bryanreinero.markov.Chain;
import com.bryanreinero.markov.Event;

import com.bryanreinero.platypus.schema.FieldDescriptor;
import com.bryanreinero.platypus.schema.ValueDescriptor;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brein on 2/14/2016.
 */
public class FieldGenerator {

    private final Chain<RandomInterval> values = new Chain<RandomInterval>();
    private final String key;

    public FieldGenerator(FieldDescriptor descriptor ) {

        key = descriptor.getName();

        Set<Event<RandomInterval>> events = new HashSet<Event<RandomInterval>>();
        for( ValueDescriptor d : descriptor.getValues() )
            events.add(
                    new Event<RandomInterval>(
                            d.getProbability(),
                            RandIntervalGenerators.getRandomIntervalGenerator( d )
                    )
            );

        values.setProbabilities( events );
    }

    public void setField( Document doc ) {
        RandomInterval generator = values.run();

        if( generator != null )
            doc.put(  key, generator.getNext() );
    }
}
