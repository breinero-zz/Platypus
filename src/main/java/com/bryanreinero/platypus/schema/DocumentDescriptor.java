package com.bryanreinero.platypus.schema;

import com.bryanreinero.platypus.schema.FieldDescriptor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brein on 2/13/2016.
 */
public class DocumentDescriptor {

    private final Set<FieldDescriptor> fields = new HashSet<FieldDescriptor>();
    private final String name;

    public DocumentDescriptor( String name ) {
        this.name = name;
    }

    public void setField( FieldDescriptor descriptor ) {
        fields.add( descriptor );
    }

    public Set<FieldDescriptor> getFields() {
        return fields;
    }
}

