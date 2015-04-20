package com.bryanreinero.platypus.schema;

public class DoubleInterval implements Interval<Double> {

	private final Double min, max;
	private final Boolean inclusiveMin, inclusiveMax;
	
	public DoubleInterval ( Double min, Boolean inclusiveMin, Double max, Boolean inclusiveMax ) {
		this.min = min;
		this.max = max;
		this.inclusiveMin = inclusiveMin;
		this.inclusiveMax = inclusiveMax;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public Double getMin() { return min; }
	public Double getMax() { return max; }

	@Override
	public boolean inRange(Double t) {
		return ( t >= min && t <= max );
	}
}
