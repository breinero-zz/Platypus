package com.bryanreinero.platypus.schema;

import java.util.regex.Pattern;

public class StringInterval implements Interval<String> {

	private final Pattern pattern ;

	/**
	 * 
	 * @param regex
	 */
	public StringInterval( String regex ) {
		pattern = Pattern.compile(regex );
	};

	@Override
	public boolean inRange(String t) {
		return pattern.matcher( t ).matches();
	}

	public String getRegex() { 
		return pattern.pattern();
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
