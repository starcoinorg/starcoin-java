// Copyright (c) Facebook, Inc. and its affiliates
// SPDX-License-Identifier: MIT OR Apache-2.0

package com.novi.serde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Immutable wrapper class around byte[].
 * <p>
 * Enforces value-semantice for `equals` and `hashCode`.
 */
public final class Bytes {
    private static final Bytes EMPTY = new Bytes(new byte[0]);
    private final byte[] content;

    /// Low-level constructor (use with care).
    public Bytes(byte[] content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    public static Bytes empty() {
        return EMPTY;
    }

    public static Bytes valueOf(byte[] content) {
        Objects.requireNonNull(content, "content must not be null");
        if (content.length == 0) {
            return EMPTY;
        } else {
            return new Bytes(content.clone());
        }
    }

    public static Bytes fromList(List<@com.novi.serde.Unsigned Byte> listBytes) {
        Objects.requireNonNull(listBytes, "list bytes must not be null");
        byte[] content = new byte[listBytes.size()];
        int i = 0;
        for (Byte item : listBytes) {
            content[i] = item.byteValue();
            i++;
        }
        return valueOf(content);
    }

    public List<@com.novi.serde.Unsigned Byte> toList() {
        List<@com.novi.serde.Unsigned Byte> result = new ArrayList<>();
        for (byte item : this.content.clone()) {
            result.add(item);
        }
        return result;
    }

    public byte[] content() {
        return this.content.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Bytes other = (Bytes) obj;
        return Arrays.equals(this.content, other.content);
    }

    public int hashCode() {
        return Arrays.hashCode(content);
    }

}
