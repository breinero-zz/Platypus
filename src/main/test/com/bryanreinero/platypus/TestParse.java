package com.bryanreinero.platypus;

import com.bryanreinero.platypus.parser.JSONParser;
import com.bryanreinero.platypus.schema.DocumentDescriptor;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by brein on 6/8/2016.
 */
public class TestParse {

    @Test
    public void TestParseJSON() {
        final String json = "{ fields: [ { name: \"a\", values: [ { type: \"int\", min: 0, max: 10, probability: 1.0 } ] } ] }";

        JSONParser parser = new JSONParser();
        try {
            DocumentDescriptor descriptor = parser.parse(json);
            assert( descriptor.getFields().size() == 1 );
            descriptor.getFields().forEach(
                    d -> {
                        assert( d.getName().equals( "a" ) );
                        assert( d.getValues().size() == 1 );
                    }

            );
        } catch( IOException e ) {
            assert( false );
        }
    }
}
