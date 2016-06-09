package com.bryanreinero.platypus.generator;

import com.bryanreinero.platypus.parser.Parser;
import com.bryanreinero.platypus.schema.DocumentDescriptor;
import org.bson.Document;

/**
 * Created by brein on 2/15/2016.
 */
public class Test {
    private static String json = "{ fields: [ { name: \"a\", values: [ { type: \"int\", min: 0, max: 10, probability: 1.0 } ] } ] }";


    public static void main( String[] args ) {
        DocumentDescriptor descriptor = Parser.parse(json);
        DocumentGenerator generator = new DocumentGenerator( descriptor );

        Document document = generator.getDocument();

        assert ( document != null );
        assert ( document.get( "fields" ) != null );
    }
}