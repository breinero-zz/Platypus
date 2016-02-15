package com.bryanreinero.platypus.schema;

/**
 * Describes a range of acceptable values for a given data type
 * @author breinero
 *
 * @param <T>
 */
public interface Interval <T> extends Visitable {
	boolean inRange( T t);
}
