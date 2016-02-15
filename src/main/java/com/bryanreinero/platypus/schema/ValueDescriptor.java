package com.bryanreinero.platypus.schema;

import com.bryanreinero.platypus.generator.RandIntervalGenerators.Type;

/**
 * Created by brein on 2/14/2016.
 */
public class ValueDescriptor {
    private final Type type;
    private Integer max, min;
    private float probability = 0.0f;

    public ValueDescriptor( Type type ) {
        this.type = type;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax( Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin( Integer min) {
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
