package com.bryanreinero.platypus.schema;

public interface Visitable {
	public void accept( Visitor v );
}
