package org.starcoin.serde.format;

import java.util.List;

public class NamedFormat extends Named<Format> implements IReferenceContainerType {

    @Override
    public List<String> referencedContainerTypeNames() {
        return getValue().referencedContainerTypeNames();
    }

}
