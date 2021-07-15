package org.starcoin.serde.format;

import java.util.List;

/**
 * Serde-based serialization format for anonymous "value" types.
 */
public abstract class Format {

    /**
     * The formats of primitive types.
     */
    public static class Primitive extends Format {
        public static final Primitive UNIT = new Primitive("UNIT");
        public static final Primitive BOOL = new Primitive("BOOL");
        public static final Primitive I8 = new Primitive("I8");
        public static final Primitive I16 = new Primitive("I16");
        public static final Primitive I32 = new Primitive("I32");
        public static final Primitive I64 = new Primitive("I64");
        public static final Primitive I128 = new Primitive("I128");
        public static final Primitive U8 = new Primitive("U8");
        public static final Primitive U16 = new Primitive("U16");
        public static final Primitive U32 = new Primitive("U32");
        public static final Primitive U64 = new Primitive("U64");
        public static final Primitive U128 = new Primitive("U128");
        public static final Primitive F32 = new Primitive("F32");
        public static final Primitive F64 = new Primitive("F64");
        public static final Primitive CHAR = new Primitive("CHAR");
        public static final Primitive STR = new Primitive("STR");
        public static final Primitive BYTES = new Primitive("BYTES");

        private String type;

        public Primitive(String type) {
            this.type = type;
        }

        public static Primitive valueOf(String s) {
            if ("UNIT".equals(s)) {
                return UNIT;
            } else if ("BOOL".equals(s)) {
                return BOOL;
            } else if ("U8".equals(s)) {
                return U8;
            } else if ("U16".equals(s)) {
                return U16;
            } else if ("U32".equals(s)) {
                return U32;
            } else if ("U64".equals(s)) {
                return U64;
            } else if ("U128".equals(s)) {
                return U128;
            } else if ("I8".equals(s)) {
                return I8;
            } else if ("I16".equals(s)) {
                return I16;
            } else if ("I32".equals(s)) {
                return I32;
            } else if ("I64".equals(s)) {
                return I64;
            } else if ("I128".equals(s)) {
                return I128;
            } else if ("F32".equals(s)) {
                return F32;
            } else if ("F64".equals(s)) {
                return F64;
            } else if ("CHAR".equals(s)) {
                return CHAR;
            } else if ("STR".equals(s)) {
                return STR;
            } else if ("BYTES".equals(s)) {
                return BYTES;
            }
            throw new IllegalArgumentException("Unknown type name: " + s);
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "Primitive{" +
                    "type='" + type + '\'' +
                    '}';
        }
    }

    /**
     * The name of a container.
     */
    public static class TypeName extends Format {
        private String name;

        public String getName() {
            return name;
        }

        public TypeName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "TypeName{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * The format of `Option<T>`.
     */
    public static class Option extends Format {
        private Format format;

        public Option(Format format) {
            this.format = format;
        }

        public Format getFormat() {
            return format;
        }

        @Override
        public String toString() {
            return "Option{" +
                    "format=" + format +
                    '}';
        }
    }

    /**
     * A sequence, e.g. the format of `Vec<Foo>`.
     */
    public static class Seq extends Format {
        private Format format;

        public Format getFormat() {
            return format;
        }

        public Seq(Format format) {
            this.format = format;
        }

        @Override
        public String toString() {
            return "Seq{" +
                    "format=" + format +
                    '}';
        }
    }

    /**
     * A map, e.g. the format of `BTreeMap<K, V>`.
     */
    public static class Map extends Format {
        private Format key;
        private Format value;

        public Map(Format key, Format value) {
            this.key = key;
            this.value = value;
        }

        public Format getKey() {
            return key;
        }

        public Format getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Map{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * A tuple, e.g. the format of `(Foo, Bar)`.
     */
    public static class Tuple extends Format {
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
     * Alias for `(Foo, ... Foo)`.
     * E.g. the format of `[Foo; N]`.
     */
    public static class TupleArray extends Format {
        private Format content;
        private int size;

        public TupleArray(Format content, int size) {
            this.content = content;
            this.size = size;
        }

        public Format getContent() {
            return content;
        }

        public int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "TupleArray{" +
                    "content=" + content +
                    ", size=" + size +
                    '}';
        }
    }
}
