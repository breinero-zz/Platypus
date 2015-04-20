package com.bryanreinero.platypus.schema;

import java.util.Map;

public class ObjectInterval implements Interval<Map<String, Object>> {

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public boolean inRange(Map<String, Object> t) {
		// TODO Auto-generated method stub
		return false;
	}

}
