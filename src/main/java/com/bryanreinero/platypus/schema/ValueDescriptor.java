package com.bryanreinero.platypus.schema;

import com.bryanreinero.platypus.generator.RandIntervalGenerators.Type;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by brein on 2/14/2016.
 */
public class ValueDescriptor <T> {

    @JsonProperty
    private final Type type;
    private T max, min;
    private float probability = 0.0f;

    @JsonCreator
    public ValueDescriptor( Type type ) {
        this.type = type;
    }

    public T getMax() {
        return max;
    }

    public void setMax( T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin( T min) {
        this.min = min;
    }


    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

    public String getRegex() {
        return null;
    }

    public Type getType() {
        return type;
    }
}
