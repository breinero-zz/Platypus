package com.bryanreinero.firehose.schema;

import java.util.ArrayList;
import java.util.Map;

import org.bson.types.BasicBSONList;

import com.bryanreinero.firehose.Transformer;
import com.mongodb.util.*;

public class Parser {
	
	public static SchemaDescriptor parse( String json ) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>)JSON.parse(json);
		return buildDescriptor( map );
	}
	
	public static SchemaDescriptor buildDescriptor( Map<String, Object> map ) {
		
		SchemaDescriptor sd = new SchemaDescriptor( "bing", "blah" );
		
		ArrayList<Object> fields = (BasicBSONList)map.get( "fields" );
		FieldDescriptor fd;
		for ( Object o : fields )
			sd.SetDescriptor( parseField( (Map<String, Object>)o ) );
		
		return sd;
	}
	
	public static FieldDescriptor parseField( Map<String, Object> o ) {
		
		FieldDescriptor fd = null;
		String name = (String)o.get("name");
		Boolean required = (Boolean)o.get("Required");
		
		fd = new FieldDescriptor( name, required );
		
		ArrayList<Object> ranges = (ArrayList<Object>)o.get( "intervals" );
		for( Object range : ranges )
			fd.setInterval( buildInterval( (Map<String, Object>)range ) );
		
		return fd;
	}
	
	public static Interval buildInterval( Map<String, Object> map ) {
		Interval i = null;
		
		Transformer.Type type = Transformer.Type.getType((String)map.get("type"));
        
		switch( type ) {
		case StringType:
			i = new StringInterval( (String)map.get("regex") );
			break;
		case DoubleType:
			i = new DoubleInterval( (Double)map.get("min"),  (Double)map.get("max") );
			break;
		case Object_Id:
			i = new DoubleInterval( (Double)map.get("min"),  (Double)map.get("max") );
			break;
		case ObjectType:
			i = new SchemaDescriptor( map );
			break;
			
		}
		return i;
	}
	
	
	
	public static void main( String[] args ) {
		String json = "{ fields: [ {a: 1}, { b:2}  ] }";
		//Map<String, Object> map = Parser.toMap(json);
	}
	
	
}
