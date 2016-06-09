package com.bryanreinero.platypus.schema;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brein on 2/13/2016.
 */
public class DocumentDescriptor {

    @JsonProperty
    private final String name;
    private final Set<FieldDescriptor> fields = new HashSet<FieldDescriptor>();

    @JsonCreator
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

