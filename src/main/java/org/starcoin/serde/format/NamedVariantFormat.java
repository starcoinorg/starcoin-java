package org.starcoin.serde.format;


import java.util.List;

public class NamedVariantFormat extends Named<VariantFormat> implements IReferenceContainerType {

    @Override
    public List<String> referencedContainerTypeNames() {
        return getValue().referencedContainerTypeNames();
    }

}
