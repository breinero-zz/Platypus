package com.bryanreinero.platypus.schema;

import java.util.HashSet;
import java.util.Set;

public class FieldDescriptor implements Visitable {
	
	private final String name;

	private Set <ValueDescriptor> values = new HashSet <ValueDescriptor> ();
	
	public FieldDescriptor ( String name ) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

    public void setValue( ValueDescriptor value ) { values.add( value ); }

    public Set< ValueDescriptor> getValues() {
        return values;
    }

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
