package org.starcoin.stdlib;

import com.novi.bcs.BcsSerializer;
import com.novi.serde.Bytes;
import com.novi.serde.Int128;
import com.novi.serde.SerializationError;
import com.novi.serde.Unsigned;
import org.starcoin.types.*;
import org.starcoin.utils.AccountAddressUtils;

import java.math.BigInteger;
import java.util.Collections;

public class ExtHelpers {

    private ExtHelpers() {
    }

    public static TransactionPayload encode_empty_args_script_function(String moduleId, String functionName) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = Collections.emptyList();
        script_function_builder.function = new Identifier(functionName);
        script_function_builder.module = parseModuleId(moduleId);
        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    public static TransactionPayload encode_u64_and_u8_script_function(String moduleId, String functionName, Long u64, Byte u8) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(
                encode_u64_argument(u64),
                encode_u8_argument(u8)
        );
        script_function_builder.function = new Identifier(functionName);
        script_function_builder.module = parseModuleId(moduleId);
        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    public static TransactionPayload encode_u8vector_and_u64_and_bool_script_function(String moduleId, String functionName, Bytes bytes, Long u64, Boolean b) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(
                encode_u8vector_argument(bytes),
                encode_u64_argument(u64),
                encode_bool_argument(b)
        );
        script_function_builder.function = new Identifier(functionName);
        script_function_builder.module = parseModuleId(moduleId);
        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    public static TransactionPayload encode_u8vector_and_u64_script_function(String moduleId, String functionName, Bytes bytes, Long u64) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(
                encode_u8vector_argument(bytes),
                encode_u64_argument(u64)
        );
        script_function_builder.function = new Identifier(functionName);
        script_function_builder.module = parseModuleId(moduleId);
        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    private static ModuleId parseModuleId(String m) {
        String[] ss = m.split("::");
        if (ss.length < 2) {
            throw new IllegalArgumentException("Illegal module id.: " + m);
        }
        return new ModuleId(AccountAddressUtils.create(ss[0]), new Identifier(ss[1]));
    }


    private static Bytes encode_bool_argument(Boolean arg) {
        try {
            BcsSerializer s = new BcsSerializer();
            s.serialize_bool(arg);
            return Bytes.valueOf(s.get_bytes());

        } catch (SerializationError e) {
            throw new IllegalArgumentException("Unable to serialize argument of type bool");
        }
    }

    private static Bytes encode_u64_argument(@Unsigned Long arg) {
        try {
            BcsSerializer s = new BcsSerializer();
            s.serialize_u64(arg);
            return Bytes.valueOf(s.get_bytes());

        } catch (SerializationError e) {
            throw new IllegalArgumentException("Unable to serialize argument of type u64");
        }
    }

    private static Bytes encode_u8_argument(@Unsigned Byte arg) {
        try {
            BcsSerializer s = new BcsSerializer();
            s.serialize_u8(arg);
            return Bytes.valueOf(s.get_bytes());

        } catch (SerializationError e) {
            throw new IllegalArgumentException("Unable to serialize argument of type u8");
        }
    }

    private static Bytes encode_u128_argument(@Unsigned @Int128 BigInteger arg) {
        try {
            BcsSerializer s = new BcsSerializer();
            s.serialize_u128(arg);
            return Bytes.valueOf(s.get_bytes());
        } catch (SerializationError e) {
            throw new IllegalArgumentException("Unable to serialize argument of type u128");
        }
    }

    private static Bytes encode_address_argument(AccountAddress arg) {
        try {
            return Bytes.valueOf(arg.bcsSerialize());
        } catch (SerializationError e) {
            throw new IllegalArgumentException("Unable to serialize argument of type address");
        }
    }

    private static Bytes encode_u8vector_argument(Bytes arg) {
        try {
            BcsSerializer s = new BcsSerializer();
            s.serialize_bytes(arg);
            return Bytes.valueOf(s.get_bytes());
        } catch (SerializationError e) {
            throw new IllegalArgumentException("Unable to serialize argument of type u8vector");
        }
    }

}
