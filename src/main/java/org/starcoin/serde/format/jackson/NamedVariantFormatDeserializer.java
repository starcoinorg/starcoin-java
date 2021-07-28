package org.starcoin.serde.format.jackson;


import org.starcoin.serde.format.NamedVariantFormat;
import org.starcoin.serde.format.VariantFormat;

public class NamedVariantFormatDeserializer extends NamedDeserializer<NamedVariantFormat, VariantFormat> {

    @Override
    protected Class<VariantFormat> valueType() {
        return VariantFormat.class;
    }

    @Override
    protected NamedVariantFormat newNamedInstance() {
        return new NamedVariantFormat();
    }
}