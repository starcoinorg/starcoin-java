package org.starcoin.serde.format;

/**
 * A named value.
 * Used for named parameters or variants.
  */
public class Named<T> {
    private String name;
    private T value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Named{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
