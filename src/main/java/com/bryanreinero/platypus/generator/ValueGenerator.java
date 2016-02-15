package com.bryanreinero.platypus.generator;

import com.bryanreinero.platypus.schema.ValueDescriptor;


/**
 * Created by brein on 2/13/2016.
 */
public class ValueGenerator implements Outcome<ValueGenerator> {

    private final FieldGenerator parent;
    private final RandomInterval generator;

    public ValueGenerator(ValueDescriptor descriptor, FieldGenerator parent ){
        this.parent = parent;
        generator = RandIntervalGenerators.getRandomIntervalGenerator( descriptor );

    }

    public Object getValue() {
        return generator.getNext();
    }

    @Override
    public ValueGenerator execute() {
        return this;
    }

    @Override
    public String getName() {
        return this.toString();
    }
}
