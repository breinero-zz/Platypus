package com.bryanreinero.platypus.generator;

import com.bryanreinero.markov.Node;
import com.bryanreinero.markov.Edge;
import com.bryanreinero.platypus.schema.FieldDescriptor;
import com.bryanreinero.platypus.schema.ValueDescriptor;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brein on 2/14/2016.
 */
public class FieldGenerator {

    private final Node<RandomInterval> values = new Node<RandomInterval>();
    private final String key;

    public FieldGenerator(FieldDescriptor descriptor ) {

        key = descriptor.getName();

        Set<Edge<RandomInterval>> events = new HashSet<Edge<RandomInterval>>();
        for( ValueDescriptor d : descriptor.getValues() )
            events.add(
                    new Edge<RandomInterval>(
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
