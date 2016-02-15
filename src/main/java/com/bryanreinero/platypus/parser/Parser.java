package com.bryanreinero.platypus.parser;

import java.util.ArrayList;
import java.util.Map;

import com.bryanreinero.platypus.schema.*;
import com.bryanreinero.platypus.generator.RandIntervalGenerators.Type;

import com.mongodb.util.*;

public class Parser {
	
	public static DocumentDescriptor parse( String json ) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>)JSON.parse(json);
		return buildDescriptor( map );
	}


    public static DocumentDescriptor buildDescriptor( Map<String, Object> map  ) {
        DocumentDescriptor d = new DocumentDescriptor( (String)map.get( "name" ) );

        ArrayList<Object> fields = (ArrayList<Object>)map.get( "fields" );
        for( Object field : fields )
            d.setField( parseField( (Map<String, Object>)field ) );
        return d;
    }
	
	public static FieldDescriptor parseField( Map<String, Object> o ) {
		
		FieldDescriptor fd = null;
		String name = (String)o.get("name");
		
		fd = new FieldDescriptor( name );
		
		ArrayList<Object> values = (ArrayList<Object>)o.get( "values" );
		for( Object value : values )
			fd.setValue( buildValue( (Map<String, Object>)value ) );
		
		return fd;
	}
	
	public static ValueDescriptor buildValue(Map<String, Object> map ) {
        ValueDescriptor descriptor = new ValueDescriptor( Type.getType( (String)map.get( "type" ) ) );
        descriptor.setMax( (Integer) map.get("max") );
        descriptor.setMin( (Integer)map.get("min") );
        descriptor.setProbability( ((Double)map.get( "probability" )).floatValue()  );
		return descriptor;
	}
}
