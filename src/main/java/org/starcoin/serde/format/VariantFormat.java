package org.starcoin.serde.format;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description of a variant in an enum.
 */
public abstract class VariantFormat implements IReferenceContainerType {
    /**
     * A variant without parameters, e.g. `A` in `enum X { A }`
     */
    public static class Unit extends VariantFormat {

        @Override
        public String toString() {
            return "Unit{}";
        }

        @Override
        public List referencedContainerTypeNames() {
            return Collections.EMPTY_LIST;
        }

    }

    /**
     * A variant with a single unnamed parameter, e.g. `A` in `enum X { A(u16) }`
     */
    public static class NewType extends VariantFormat {
        private Format format;

        public NewType(Format format) {
            this.format = format;
        }

        public Format getFormat() {
            return format;
        }

        @Override
        public String toString() {
            return "NewType{" +
                    "format=" + format +
                    '}';
        }

        @Override
        public List<String> referencedContainerTypeNames() {
            return format.referencedContainerTypeNames();
        }
    }

    /**
     * A struct with several unnamed parameters, e.g. `A` in `enum X { A(u16, u32) }`
     */
    public static class Tuple extends VariantFormat {
        private List<Format> formats;

        public Tuple(List<Format> formats) {
            this.formats = formats;
        }

        public List<Format> getFormats() {
            return formats;
        }

        @Override
        public String toString() {
            return "Tuple{" +
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
     * A struct with named parameters, e.g. `A` in `enum X { A { a: Foo } }`
     */
    public static class Struct extends VariantFormat {
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

        @Override
        public List<String> referencedContainerTypeNames() {
            return namedFormats.stream().flatMap(f -> f.referencedContainerTypeNames().stream())
                    .collect(Collectors.toList());
        }
    }
}
