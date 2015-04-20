package com.bryanreinero.platypus.schema;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * A representation of a JSON Schema which can be used to validate the 
 * structure of individual JSON documents 
 * 
 * @author breinero
 *
 */
public class SchemaDescriptor implements Interval<Map<String, Object>> {
	
	private String namespace;
	private String name;
	private final Map <String, FieldDescriptor> descriptors = new HashMap<String, FieldDescriptor>();
	
	/**
	 * 
	 * @author breinero
	 *
	 */
	public class Validation {
		
		public final boolean valid;
		public final String comment;
		
		private Validation ( boolean passFail, String comment ) {
			this.valid = passFail;
			this.comment = comment;
		}
	};
	
	/**
	 * 
	 * @param name The name of the schema to be described
	 * @param namespace The namespace of the schema to be described
	 */
	public SchemaDescriptor( String name, String namespace ) {
		this.name = name;
		this.namespace = namespace;
	}
	
	/**
	 * 
	 * @return The name of the Schema
	 */
	public String getName() {
		return name;
	}
	
	public String getNamespace() {
		return namespace;
	}
	
	/**
	 * Add a <code>FieldDescriptor</code> to this descriptor
	 * 
	 * @param descriptor
	 */
	public void SetDescriptor( FieldDescriptor descriptor ) {
		String key = descriptor.getName();
		descriptors.put(key, descriptor);
	}
	

	/**
	 * 
	 * @return The set of <code>FieldDescriptors</code> in this Schema
	 */
	public Set<FieldDescriptor> getFieldDescriptors() {
		Set<FieldDescriptor> ds = new HashSet<FieldDescriptor>();
		for( FieldDescriptor d : descriptors.values() )
			ds.add(d);
		
		return ds;
	}
	
	/**
	 * Return a Validation object which indicates whether the Map
	 * adheres to the schema description this Object was built to support
	 * 
	 * @param o a Map object which represents the JSON object to be validated.
	 * 
	 */
	public Validation validate( Map<String, Object> o ){
		Map <String, Boolean> required = new HashMap<String, Boolean>();
		
		// set up checks for required fields
		for( Entry<String, FieldDescriptor> entry : descriptors.entrySet() )
			if( entry.getValue().isRequired() )
				required.put(entry.getKey(), Boolean.FALSE );
		
		for( Entry<String, Object> entry : o.entrySet() ) {
			
			String key = entry.getKey();
			if ( descriptors.containsKey(key) )
				if( !descriptors.get(key).validate( entry.getValue() ) )
					return new Validation(false, "field \""+key+"\" "+entry.getValue()+" not in range");			
			
			// mark required field as satisfied
			if( required.containsKey( key ) )
				required.put(key, Boolean.TRUE );
				
		}
		
		// check for required fields
		for( Entry<String, Boolean> entry : required.entrySet() )
			if( ! entry.getValue() )
				return new Validation(false, "field"+entry.getKey()+" required");
		
		return new Validation(true, "Valid "+namespace+" document");
	};
	
	@Override 
	public String toString() {
		StringBuffer buf = new StringBuffer();
		return buf.toString();
	}
	
	public SchemaDescriptor ( String json ) {
		Map<String, Object> map = (Map<String, Object>)JSON.parse(json);
		this.name = (String)map.get("name");
		this.namespace = (String)map.get("namespace");
	
	}
	
	public SchemaDescriptor( Map<String, Object> m ) {
		
	}
	
	public static void main ( String[] args ) {
		SchemaDescriptor sd = new SchemaDescriptor( "test", "com.bryanreinero.schema" );
		FieldDescriptor descriptor = new FieldDescriptor( "a", true );
		StringInterval interval = new StringInterval( "^test" );
		descriptor.setInterval( interval );
		sd.SetDescriptor(descriptor);
		
		DBObject object = new BasicDBObject();
		object.put("a", "test111");
		
		Map<String, Object> map = (Map<String, Object>) JSON.parse("{ a: \"test\" }");
		
		Validation v = sd.validate( map );
		
		if ( !v.valid )
			System.out.print( "INVALID: " );
		
		System.out.println( v.comment );
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean inRange( Map<String, Object> t ) {
		Validation v = validate( t );
		return v.valid ;
	}

}
