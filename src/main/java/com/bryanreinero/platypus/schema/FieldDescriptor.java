package com.bryanreinero.platypus.schema;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class FieldDescriptor implements Visitable {

	@JsonProperty
	private final String name;
	private Set <ValueDescriptor> values = new HashSet <ValueDescriptor> ();

	@JsonCreator
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
