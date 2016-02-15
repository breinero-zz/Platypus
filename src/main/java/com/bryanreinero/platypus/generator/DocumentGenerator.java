package com.bryanreinero.platypus.generator;

import com.bryanreinero.platypus.schema.*;

import org.bson.Document;

import java.util.*;

/**
 * Created by brein on 2/13/2016.
 */
public class DocumentGenerator {
    private Set<FieldGenerator> fields = new HashSet<FieldGenerator>();

    public DocumentGenerator( DocumentDescriptor descriptor ) {
        for( FieldDescriptor d : descriptor.getFields() )
            fields.add( new FieldGenerator( d ) );
    }

    public Document getDocument() {
        Document document = new Document();

        for( FieldGenerator generator : fields  )
            generator.setField(document);

        return document;
    }
}
