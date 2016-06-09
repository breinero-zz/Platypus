package com.bryanreinero.platypus.parser;

import com.bryanreinero.platypus.schema.DocumentDescriptor;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by brein on 6/8/2016.
 */
public class JSONParser {
    ObjectMapper mapper = new ObjectMapper();

    public DocumentDescriptor parse( final String json ) throws IOException {
       return  mapper.readValue( json, DocumentDescriptor.class );
    }
}
