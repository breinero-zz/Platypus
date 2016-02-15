package com.bryanreinero.platypus.generator;

import com.bryanreinero.firehose.util.markov.Chain;
import com.bryanreinero.firehose.util.markov.Event;

import com.bryanreinero.platypus.schema.FieldDescriptor;
import com.bryanreinero.platypus.schema.ValueDescriptor;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brein on 2/14/2016.
 */
public class FieldGenerator {

    private final Chain<ValueGenerator> values = new Chain<ValueGenerator>();
    private final String key;

    public FieldGenerator(FieldDescriptor descriptor ) {

        key = descriptor.getName();

        Set<Event<ValueGenerator>> events = new HashSet<Event<ValueGenerator>>();
        for( ValueDescriptor d : descriptor.getValues() )
            events.add( new Event<ValueGenerator>( d.getProbability(), new ValueGenerator( d, this ) ) );

        values.setProbabilities( events );
    }

    public void setField( Document doc ) {
        ValueGenerator generator = values.run();

        if( generator != null )
            doc.put(  key, generator.getValue() );
    }
}
