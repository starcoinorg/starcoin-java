package org.starcoin.serde.format;

import java.util.List;

/**
 * "I Reference Container Type" interface.
 */
public interface IReferenceContainerType {

    List<String> referencedContainerTypeNames();

}
