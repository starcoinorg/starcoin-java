package org.starcoin.serde.format;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Serde-based serialization format for named "container" types.
 * In Rust, those are enums and structs.
 */
public abstract class ContainerFormat implements IReferenceContainerType {

    /**
     * An empty struct, e.g. `struct A`.
     */
    public static class UnitStruct extends ContainerFormat {

        @Override
        public String toString() {
            return "UnitStruct{}";
        }

        @Override
        public List<String> referencedContainerTypeNames() {
            return Collections.emptyList();
        }

    }

    /**
     * A struct with a single unnamed parameter, e.g. `struct A(u16)`
     */
    public static class NewTypeStruct extends ContainerFormat {
        private final Format format;

        public NewTypeStruct(Format format) {
            this.format = format;
        }

        public Format getFormat() {
            return format;
        }

        @Override
        public String toString() {
            return "NewTypeStruct{" +
                    "format=" + format +
                    '}';
        }

        @Override
        public List<String> referencedContainerTypeNames() {
            return format.referencedContainerTypeNames();
        }
    }

    /**
     * A struct with several unnamed parameters, e.g. `struct A(u16, u32)`
     */
    public static class TupleStruct extends ContainerFormat {
        private final List<Format> formats;

        public TupleStruct(List<Format> formats) {
            this.formats = formats;
        }

        public List<Format> getFormats() {
            return formats;
        }

        @Override
        public String toString() {
            return "TupleStruct{" +
                    "formats=" + formats +
                    '}';
        }

        @Override
        public List<String> referencedContainerTypeNames() {
            return formats.stream().flatMap(f -> f.referencedContainerTypeNames().stream())
                    .collect(Collectors.toList());
        }
    }

    /**
     * A struct with named parameters, e.g. `struct A { a: Foo }`.
     */
    public static class Struct extends ContainerFormat {
        private final List<NamedFormat> namedFormats;

        public Struct(List<NamedFormat> namedFormats) {
            this.namedFormats = namedFormats;
        }

        public List<NamedFormat> getNamedFormats() {
            return namedFormats;
        }

        @Override
        public String toString() {
            return "Struct{" +
                    "namedFormats=" + namedFormats +
                    '}';
        }

        @Override
        public List<String> referencedContainerTypeNames() {
            return namedFormats.stream().flatMap(f -> f.referencedContainerTypeNames().stream())
                    .collect(Collectors.toList());
        }
    }

    /**
     * An enum, that is, an enumeration of variants.
     * Each variant has a unique name and index within the enum.
     */
    public static class Enum extends ContainerFormat {
        private final Map<Integer, NamedVariantFormat> indexedNamedVariantFormats;

        public Enum(Map<Integer, NamedVariantFormat> indexedNamedVariantFormats) {
            this.indexedNamedVariantFormats = indexedNamedVariantFormats;
        }

        public Map<Integer, NamedVariantFormat> getIndexedNamedVariantFormats() {
            return indexedNamedVariantFormats;
        }

        @Override
        public String toString() {
            return "Enum{" +
                    "indexedNamedVariantFormats=" + indexedNamedVariantFormats +
                    '}';
        }

        @Override
        public List<String> referencedContainerTypeNames() {
            return indexedNamedVariantFormats.values().stream().flatMap(f -> f.referencedContainerTypeNames().stream())
                    .collect(Collectors.toList());
        }
    }
}
