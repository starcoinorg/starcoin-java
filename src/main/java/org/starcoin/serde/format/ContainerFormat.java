package org.starcoin.serde.format;

import java.util.List;
import java.util.Map;

/**
 * Serde-based serialization format for named "container" types.
 * In Rust, those are enums and structs.
 */
public abstract class ContainerFormat {

    /**
     * An empty struct, e.g. `struct A`.
     */
    public static class UnitStruct extends ContainerFormat {
        //


        @Override
        public String toString() {
            return "UnitStruct{}";
        }
    }

    /**
     * A struct with a single unnamed parameter, e.g. `struct A(u16)`
     */
    public static class NewTypeStruct extends ContainerFormat {
        private Format format;

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
    }

    /**
     * A struct with several unnamed parameters, e.g. `struct A(u16, u32)`
     */
    public static class TupleStruct extends ContainerFormat {
        private List<Format> formats;

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
    }

    /**
     * A struct with named parameters, e.g. `struct A { a: Foo }`.
     */
    public static class Struct extends ContainerFormat {
        private List<NamedFormat> namedFormats;

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
    }

    /**
     * An enum, that is, an enumeration of variants.
     * Each variant has a unique name and index within the enum.
     */
    public static class Enum extends ContainerFormat {
        private Map<Integer, NamedVariantFormat> indexedNamedVariantFormats;

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
    }
}
