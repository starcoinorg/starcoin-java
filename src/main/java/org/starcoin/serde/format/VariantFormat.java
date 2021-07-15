package org.starcoin.serde.format;

import java.util.List;

/**
 * Description of a variant in an enum.
 */
public abstract class VariantFormat {
    /**
     * A variant without parameters, e.g. `A` in `enum X { A }`
      */
    public static class Unit extends VariantFormat {
        //


        @Override
        public String toString() {
            return "Unit{}";
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
    }
}
