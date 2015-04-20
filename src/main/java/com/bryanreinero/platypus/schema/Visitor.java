package com.bryanreinero.platypus.schema;


public interface Visitor {
	public void visit( StringInterval v );
	public void visit( FieldDescriptor v );
	public void visit( DoubleInterval v );
	public void visit( ObjectInterval v );
}
