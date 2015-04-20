package com.bryanreinero.platypus.parser;

import java.util.ArrayList;
import java.util.Map;

import org.bson.types.BasicBSONList;

import com.bryanreinero.firehose.Transformer;
import com.bryanreinero.platypus.schema.ArrayInterval;
import com.bryanreinero.platypus.schema.DoubleInterval;
import com.bryanreinero.platypus.schema.FieldDescriptor;
import com.bryanreinero.platypus.schema.Interval;
import com.bryanreinero.platypus.schema.SchemaDescriptor;
import com.bryanreinero.platypus.schema.StringInterval;
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
			// the minDesc (minimum description) and maxDesc (maximum description)
			// are substructures containing the min /max values and inclusive flags
			Map<String, Object>minDesc = (Map<String, Object>)map.get("min");
			Map<String, Object>maxDesc = (Map<String, Object>)map.get("max");
			
			i = new DoubleInterval( (Double)minDesc.get("value"), 
					(Boolean)minDesc.get("inclusive"),
					(Double)map.get("max"),
					(Boolean)minDesc.get("inclusive")
					);
			break;
		case ObjectType:
			i = new SchemaDescriptor( map );
			break;
		case ArrayType:
			i = new ArrayInterval();
			break;
		}
		
		return i;
	}
	
	public static void main( String[] args ) {
		String json = "{ fields: [ {a: 1}, { b:2}  ] }";
		//Map<String, Object> map = Parser.toMap(json);
	}
}
