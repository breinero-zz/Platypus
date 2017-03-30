# Platypus
#### Version 1.0.0

A JSON schema descriptor specifcation. This project includes a reference implementation in Java.

## Descriptor Struture
A Platypus schema descriptor is a simple, flat array of field descriptors (defined below).

```
{
	fields: [
		<Field Descriptor>,
		<Field Descriptor>,
		<Field Descriptor>,
		...
	]
}
```
JSON schema description composed of two sub-descriptors: field descriptors and type descriptors 


### Field Descriptor
A JSON object describing the allowed values of a named field.

* name - of type String, the name of the field 
* values: of type Array, A set of Type Descriptors which define the allow values of this field.


#### Example
```
{ 
	name: "user",
    values: [
    	{ type: "int", min: "$minInteger", max: "$maxValue", probability; 1.0 }
    ]   
}
```

### Type Descriptor
Describes a interval of allowed values. A polymorphic object, the interval definition specific to data type being described.

#### Types
* array
* boolean
* null
* number
* object
* string

##### Array Descriptor
```
{
	type: "array", 
	size: { min: 0, max: 10},
	values: [
		<typeDescriptor>
	]
}
```
##### Boolean Descriptor
```
{ type: "boolean" }
```
##### Null Descriptor
```
{ type: "null" }
```
##### Number Descriptor
```
{
	type: "number", 
	min: { value: 0, inclusive: true},
	max: { value: 0, inclusive: true}
}
```

##### Object Descriptor
```
{
	type: "object", 
	uri: "https://github.com/breinero/Platypus/descriptors/test.json"
}
```

##### String Descriptor
```
{
	type: "object", 
	regex: "^sample string"
}
```
### Describing Nested Structures
Each field descriptor is a itself JSON document, but is a 'flat' structure which does not nest in a pattern similar to the document it describes. The Platypus schema descriptor models nested fields with javascript's 'dot' notation. For example consider how the following simple example document can be described with Platypus:

#### Example address document
```
{	
	address: {
		number:
		street:
		city:
	}
}
```
#### Field Descriptor for field 'address.street'
```
{
	name: "adress.street",
	required: true,
	nullable: false,
	values: {
		type: "string", 
		regex: "^\w+"
	}
}
```
Using dot notation is particularly helpful in keeping the descriptors document structure simple and easily human readible. Consider how complex the descriptor nested structure would be when describing a nested array of sub-objects. 


### Sample Schema Descriptor

```
{
    fields: [
        { name: "user",
          values: [
          	{ type: "int", min: "$minInteger", max: "$maxValue" }
          ]   
        },
        {	name: "userId",
            values: [
            	{ type: "int", min: "$minInteger", max: "$maxValue" }
            ]
        },
        {
            name: "clues",
            values: [
            	{ type: "array", min: 0, max: 3 }
            ]
        },
        {
            name: "clues.$",
            values: [
            	{ type: "string", regex: "\.144" }
            ]
        },
        {
        	name: "geometry",
        	values: [
        		{ type: "object", uri: "bryanreinero.com/schema/poi.json" }
        	]
        },
        {
         	name: "description",
         	values: [
         		{ type: "string", regex: "\.144" }
         	]
        }
    ]
}
```
