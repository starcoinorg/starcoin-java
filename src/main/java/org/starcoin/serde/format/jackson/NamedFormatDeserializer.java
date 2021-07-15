package org.starcoin.serde.format.jackson;


import org.starcoin.serde.format.Format;
import org.starcoin.serde.format.NamedFormat;

public class NamedFormatDeserializer extends NamedDeserializer<NamedFormat, Format> {

    @Override
    protected Class<Format> valueType() {
        return Format.class;
    }

    @Override
    protected NamedFormat newNamedInstance() {
        return new NamedFormat();
    }
}