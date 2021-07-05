package org.starcoin.stdlib;


import com.novi.bcs.BcsDeserializer;
import com.novi.bcs.BcsSerializer;
import com.novi.serde.*;
import org.starcoin.types.*;

import java.math.BigInteger;


public final class Helpers {

    private static final java.util.Map<Class<?>, ScriptEncodingHelper> TRANSACTION_SCRIPT_ENCODER_MAP = initTransactionScriptEncoderMap();
    private static final java.util.Map<Class<?>, ScriptFunctionEncodingHelper> SCRIPT_FUNCTION_ENCODER_MAP = initScriptFunctionEncoderMap();
    private static final java.util.Map<Bytes, TransactionScriptDecodingHelper> TRANSACTION_SCRIPT_DECODER_MAP = initTransactionScriptDecoderMap();
    private static final java.util.Map<String, ScriptFunctionDecodingHelper> SCRIPT_FUNCTION_DECODER_MAP = initDecoderMap();

    /**
     * Build a Diem {@link org.starcoin.types.Script} from a structured value {@link ScriptCall}.
     *
     * @param call {@link ScriptCall} value to encode.
     * @return Encoded script.
     */
    public static Script encode_script(ScriptCall call) {
        ScriptEncodingHelper helper = TRANSACTION_SCRIPT_ENCODER_MAP.get(call.getClass());
        return helper.encode(call);
    }

    /**
     * Build a Diem {@link org.starcoin.types.TransactionPayload} from a structured value {@link ScriptFunctionCall}.
     *
     * @param call {@link ScriptFunctionCall} value to encode.
     * @return Encoded TransactionPayload.
     */
    public static TransactionPayload encode_script_function(ScriptFunctionCall call) {
        ScriptFunctionEncodingHelper helper = SCRIPT_FUNCTION_ENCODER_MAP.get(call.getClass());
        return helper.encode(call);
    }

    /**
     * Try to recognize a Diem {@link org.starcoin.types.Script} and convert it into a structured value {@code ScriptCall}.
     *
     * @param script {@link org.starcoin.types.Script} values to decode.
     * @return Decoded {@link ScriptCall} value.
     */
    public static ScriptCall decode_script(Script script) throws IllegalArgumentException, IndexOutOfBoundsException {
        TransactionScriptDecodingHelper helper = TRANSACTION_SCRIPT_DECODER_MAP.get(script.code);
        if (helper == null) {
            throw new IllegalArgumentException("Unknown script bytecode");
        }
        return helper.decode(script);
    }

    /**
     * Try to recognize a Diem {@link org.starcoin.types.TransactionPayload} and convert it into a structured value {@code ScriptFunctionCall}.
     *
     * @param payload {@link org.starcoin.types.TransactionPayload} values to decode.
     * @return Decoded {@link ScriptFunctionCall} value.
     */
    public static ScriptFunctionCall decode_script_function_payload(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (payload instanceof TransactionPayload.ScriptFunction) {
            ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
            ScriptFunctionDecodingHelper helper = SCRIPT_FUNCTION_DECODER_MAP.get(script.module.name.value + script.function.value);
            if (helper == null) {
                throw new IllegalArgumentException("Unknown script function");
            }
            return helper.decode(payload);
        } else {
            throw new IllegalArgumentException("Unknown transaction payload");
        }
    }

    /**
     * @param token_type {@code TypeTag} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_accept_token_script_function(TypeTag token_type) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_type);
        script_function_builder.args = java.util.Arrays.asList();
        script_function_builder.function = new Identifier("accept_token");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Account"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_cancel_upgrade_plan_script_function() {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList();
        script_function_builder.function = new Identifier("cancel_upgrade_plan");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("ModuleUpgradeScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token            {@code TypeTag} value
     * @param action_t         {@code TypeTag} value
     * @param proposer_address {@code AccountAddress} value
     * @param proposal_id      {@code @Unsigned Long} value
     * @param agree            {@code Boolean} value
     * @param votes            {@code @Unsigned @Int128 BigInteger} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_cast_vote_script_function(TypeTag token, TypeTag action_t, AccountAddress proposer_address, @Unsigned Long proposal_id, Boolean agree, @Unsigned @Int128 BigInteger votes) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token, action_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(proposer_address), Helpers.encode_u64_argument(proposal_id), Helpers.encode_bool_argument(agree), Helpers.encode_u128_argument(votes));
        script_function_builder.function = new Identifier("cast_vote");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("DaoVoteScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param package_address {@code AccountAddress} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_convert_TwoPhaseUpgrade_to_TwoPhaseUpgradeV2_script_function(AccountAddress package_address) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(package_address));
        script_function_builder.function = new Identifier("convert_TwoPhaseUpgrade_to_TwoPhaseUpgradeV2");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("PackageTxnManager"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_type     {@code TypeTag} value
     * @param fresh_address  {@code AccountAddress} value
     * @param _auth_key      {@code Bytes} value
     * @param initial_amount {@code @Unsigned @Int128 BigInteger} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_create_account_with_initial_amount_script_function(TypeTag token_type, AccountAddress fresh_address, Bytes _auth_key, @Unsigned @Int128 BigInteger initial_amount) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_type);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(fresh_address), Helpers.encode_u8vector_argument(_auth_key), Helpers.encode_u128_argument(initial_amount));
        script_function_builder.function = new Identifier("create_account_with_initial_amount");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Account"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_type     {@code TypeTag} value
     * @param fresh_address  {@code AccountAddress} value
     * @param initial_amount {@code @Unsigned @Int128 BigInteger} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_create_account_with_initial_amount_v2_script_function(TypeTag token_type, AccountAddress fresh_address, @Unsigned @Int128 BigInteger initial_amount) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_type);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(fresh_address), Helpers.encode_u128_argument(initial_amount));
        script_function_builder.function = new Identifier("create_account_with_initial_amount_v2");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Account"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * remove terminated proposal from proposer
     *
     * @param token_t          {@code TypeTag} value
     * @param action_t         {@code TypeTag} value
     * @param proposer_address {@code AccountAddress} value
     * @param proposal_id      {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_destroy_terminated_proposal_script_function(TypeTag token_t, TypeTag action_t, AccountAddress proposer_address, @Unsigned Long proposal_id) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_t, action_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(proposer_address), Helpers.encode_u64_argument(proposal_id));
        script_function_builder.function = new Identifier("destroy_terminated_proposal");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Dao"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_empty_script_script_function() {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList();
        script_function_builder.function = new Identifier("empty_script");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("EmptyScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * Once the proposal is agreed, anyone can call the method to make the proposal happen.
     *
     * @param token_t          {@code TypeTag} value
     * @param proposer_address {@code AccountAddress} value
     * @param proposal_id      {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_execute_script_function(TypeTag token_t, AccountAddress proposer_address, @Unsigned Long proposal_id) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(proposer_address), Helpers.encode_u64_argument(proposal_id));
        script_function_builder.function = new Identifier("execute");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("ModifyDaoConfigProposal"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param config_t    {@code TypeTag} value
     * @param proposal_id {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_execute_on_chain_config_proposal_script_function(TypeTag config_t, @Unsigned Long proposal_id) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(config_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u64_argument(proposal_id));
        script_function_builder.function = new Identifier("execute_on_chain_config_proposal");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("OnChainConfigScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_t          {@code TypeTag} value
     * @param proposer_address {@code AccountAddress} value
     * @param proposal_id      {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_execute_withdraw_proposal_script_function(TypeTag token_t, AccountAddress proposer_address, @Unsigned Long proposal_id) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(proposer_address), Helpers.encode_u64_argument(proposal_id));
        script_function_builder.function = new Identifier("execute_withdraw_proposal");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TreasuryScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param stdlib_version                    {@code @Unsigned Long} value
     * @param reward_delay                      {@code @Unsigned Long} value
     * @param pre_mine_stc_amount               {@code @Unsigned @Int128 BigInteger} value
     * @param time_mint_stc_amount              {@code @Unsigned @Int128 BigInteger} value
     * @param time_mint_stc_period              {@code @Unsigned Long} value
     * @param parent_hash                       {@code Bytes} value
     * @param association_auth_key              {@code Bytes} value
     * @param genesis_auth_key                  {@code Bytes} value
     * @param chain_id                          {@code @Unsigned Byte} value
     * @param genesis_timestamp                 {@code @Unsigned Long} value
     * @param uncle_rate_target                 {@code @Unsigned Long} value
     * @param epoch_block_count                 {@code @Unsigned Long} value
     * @param base_block_time_target            {@code @Unsigned Long} value
     * @param base_block_difficulty_window      {@code @Unsigned Long} value
     * @param base_reward_per_block             {@code @Unsigned @Int128 BigInteger} value
     * @param base_reward_per_uncle_percent     {@code @Unsigned Long} value
     * @param min_block_time_target             {@code @Unsigned Long} value
     * @param max_block_time_target             {@code @Unsigned Long} value
     * @param base_max_uncles_per_block         {@code @Unsigned Long} value
     * @param base_block_gas_limit              {@code @Unsigned Long} value
     * @param strategy                          {@code @Unsigned Byte} value
     * @param script_allowed                    {@code Boolean} value
     * @param module_publishing_allowed         {@code Boolean} value
     * @param instruction_schedule              {@code Bytes} value
     * @param native_schedule                   {@code Bytes} value
     * @param global_memory_per_byte_cost       {@code @Unsigned Long} value
     * @param global_memory_per_byte_write_cost {@code @Unsigned Long} value
     * @param min_transaction_gas_units         {@code @Unsigned Long} value
     * @param large_transaction_cutoff          {@code @Unsigned Long} value
     * @param instrinsic_gas_per_byte           {@code @Unsigned Long} value
     * @param maximum_number_of_gas_units       {@code @Unsigned Long} value
     * @param min_price_per_gas_unit            {@code @Unsigned Long} value
     * @param max_price_per_gas_unit            {@code @Unsigned Long} value
     * @param max_transaction_size_in_bytes     {@code @Unsigned Long} value
     * @param gas_unit_scaling_factor           {@code @Unsigned Long} value
     * @param default_account_size              {@code @Unsigned Long} value
     * @param voting_delay                      {@code @Unsigned Long} value
     * @param voting_period                     {@code @Unsigned Long} value
     * @param voting_quorum_rate                {@code @Unsigned Byte} value
     * @param min_action_delay                  {@code @Unsigned Long} value
     * @param transaction_timeout               {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_initialize_script_function(@Unsigned Long stdlib_version, @Unsigned Long reward_delay, @Unsigned @Int128 BigInteger pre_mine_stc_amount, @Unsigned @Int128 BigInteger time_mint_stc_amount, @Unsigned Long time_mint_stc_period, Bytes parent_hash, Bytes association_auth_key, Bytes genesis_auth_key, @Unsigned Byte chain_id, @Unsigned Long genesis_timestamp, @Unsigned Long uncle_rate_target, @Unsigned Long epoch_block_count, @Unsigned Long base_block_time_target, @Unsigned Long base_block_difficulty_window, @Unsigned @Int128 BigInteger base_reward_per_block, @Unsigned Long base_reward_per_uncle_percent, @Unsigned Long min_block_time_target, @Unsigned Long max_block_time_target, @Unsigned Long base_max_uncles_per_block, @Unsigned Long base_block_gas_limit, @Unsigned Byte strategy, Boolean script_allowed, Boolean module_publishing_allowed, Bytes instruction_schedule, Bytes native_schedule, @Unsigned Long global_memory_per_byte_cost, @Unsigned Long global_memory_per_byte_write_cost, @Unsigned Long min_transaction_gas_units, @Unsigned Long large_transaction_cutoff, @Unsigned Long instrinsic_gas_per_byte, @Unsigned Long maximum_number_of_gas_units, @Unsigned Long min_price_per_gas_unit, @Unsigned Long max_price_per_gas_unit, @Unsigned Long max_transaction_size_in_bytes, @Unsigned Long gas_unit_scaling_factor, @Unsigned Long default_account_size, @Unsigned Long voting_delay, @Unsigned Long voting_period, @Unsigned Byte voting_quorum_rate, @Unsigned Long min_action_delay, @Unsigned Long transaction_timeout) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u64_argument(stdlib_version), Helpers.encode_u64_argument(reward_delay), Helpers.encode_u128_argument(pre_mine_stc_amount), Helpers.encode_u128_argument(time_mint_stc_amount), Helpers.encode_u64_argument(time_mint_stc_period), Helpers.encode_u8vector_argument(parent_hash), Helpers.encode_u8vector_argument(association_auth_key), Helpers.encode_u8vector_argument(genesis_auth_key), Helpers.encode_u8_argument(chain_id), Helpers.encode_u64_argument(genesis_timestamp), Helpers.encode_u64_argument(uncle_rate_target), Helpers.encode_u64_argument(epoch_block_count), Helpers.encode_u64_argument(base_block_time_target), Helpers.encode_u64_argument(base_block_difficulty_window), Helpers.encode_u128_argument(base_reward_per_block), Helpers.encode_u64_argument(base_reward_per_uncle_percent), Helpers.encode_u64_argument(min_block_time_target), Helpers.encode_u64_argument(max_block_time_target), Helpers.encode_u64_argument(base_max_uncles_per_block), Helpers.encode_u64_argument(base_block_gas_limit), Helpers.encode_u8_argument(strategy), Helpers.encode_bool_argument(script_allowed), Helpers.encode_bool_argument(module_publishing_allowed), Helpers.encode_u8vector_argument(instruction_schedule), Helpers.encode_u8vector_argument(native_schedule), Helpers.encode_u64_argument(global_memory_per_byte_cost), Helpers.encode_u64_argument(global_memory_per_byte_write_cost), Helpers.encode_u64_argument(min_transaction_gas_units), Helpers.encode_u64_argument(large_transaction_cutoff), Helpers.encode_u64_argument(instrinsic_gas_per_byte), Helpers.encode_u64_argument(maximum_number_of_gas_units), Helpers.encode_u64_argument(min_price_per_gas_unit), Helpers.encode_u64_argument(max_price_per_gas_unit), Helpers.encode_u64_argument(max_transaction_size_in_bytes), Helpers.encode_u64_argument(gas_unit_scaling_factor), Helpers.encode_u64_argument(default_account_size), Helpers.encode_u64_argument(voting_delay), Helpers.encode_u64_argument(voting_period), Helpers.encode_u8_argument(voting_quorum_rate), Helpers.encode_u64_argument(min_action_delay), Helpers.encode_u64_argument(transaction_timeout));
        script_function_builder.function = new Identifier("initialize");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Genesis"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param stdlib_version                    {@code @Unsigned Long} value
     * @param reward_delay                      {@code @Unsigned Long} value
     * @param total_stc_amount                  {@code @Unsigned @Int128 BigInteger} value
     * @param pre_mine_stc_amount               {@code @Unsigned @Int128 BigInteger} value
     * @param time_mint_stc_amount              {@code @Unsigned @Int128 BigInteger} value
     * @param time_mint_stc_period              {@code @Unsigned Long} value
     * @param parent_hash                       {@code Bytes} value
     * @param association_auth_key              {@code Bytes} value
     * @param genesis_auth_key                  {@code Bytes} value
     * @param chain_id                          {@code @Unsigned Byte} value
     * @param genesis_timestamp                 {@code @Unsigned Long} value
     * @param uncle_rate_target                 {@code @Unsigned Long} value
     * @param epoch_block_count                 {@code @Unsigned Long} value
     * @param base_block_time_target            {@code @Unsigned Long} value
     * @param base_block_difficulty_window      {@code @Unsigned Long} value
     * @param base_reward_per_block             {@code @Unsigned @Int128 BigInteger} value
     * @param base_reward_per_uncle_percent     {@code @Unsigned Long} value
     * @param min_block_time_target             {@code @Unsigned Long} value
     * @param max_block_time_target             {@code @Unsigned Long} value
     * @param base_max_uncles_per_block         {@code @Unsigned Long} value
     * @param base_block_gas_limit              {@code @Unsigned Long} value
     * @param strategy                          {@code @Unsigned Byte} value
     * @param script_allowed                    {@code Boolean} value
     * @param module_publishing_allowed         {@code Boolean} value
     * @param instruction_schedule              {@code Bytes} value
     * @param native_schedule                   {@code Bytes} value
     * @param global_memory_per_byte_cost       {@code @Unsigned Long} value
     * @param global_memory_per_byte_write_cost {@code @Unsigned Long} value
     * @param min_transaction_gas_units         {@code @Unsigned Long} value
     * @param large_transaction_cutoff          {@code @Unsigned Long} value
     * @param instrinsic_gas_per_byte           {@code @Unsigned Long} value
     * @param maximum_number_of_gas_units       {@code @Unsigned Long} value
     * @param min_price_per_gas_unit            {@code @Unsigned Long} value
     * @param max_price_per_gas_unit            {@code @Unsigned Long} value
     * @param max_transaction_size_in_bytes     {@code @Unsigned Long} value
     * @param gas_unit_scaling_factor           {@code @Unsigned Long} value
     * @param default_account_size              {@code @Unsigned Long} value
     * @param voting_delay                      {@code @Unsigned Long} value
     * @param voting_period                     {@code @Unsigned Long} value
     * @param voting_quorum_rate                {@code @Unsigned Byte} value
     * @param min_action_delay                  {@code @Unsigned Long} value
     * @param transaction_timeout               {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_initialize_v2_script_function(@Unsigned Long stdlib_version, @Unsigned Long reward_delay, @Unsigned @Int128 BigInteger total_stc_amount, @Unsigned @Int128 BigInteger pre_mine_stc_amount, @Unsigned @Int128 BigInteger time_mint_stc_amount, @Unsigned Long time_mint_stc_period, Bytes parent_hash, Bytes association_auth_key, Bytes genesis_auth_key, @Unsigned Byte chain_id, @Unsigned Long genesis_timestamp, @Unsigned Long uncle_rate_target, @Unsigned Long epoch_block_count, @Unsigned Long base_block_time_target, @Unsigned Long base_block_difficulty_window, @Unsigned @Int128 BigInteger base_reward_per_block, @Unsigned Long base_reward_per_uncle_percent, @Unsigned Long min_block_time_target, @Unsigned Long max_block_time_target, @Unsigned Long base_max_uncles_per_block, @Unsigned Long base_block_gas_limit, @Unsigned Byte strategy, Boolean script_allowed, Boolean module_publishing_allowed, Bytes instruction_schedule, Bytes native_schedule, @Unsigned Long global_memory_per_byte_cost, @Unsigned Long global_memory_per_byte_write_cost, @Unsigned Long min_transaction_gas_units, @Unsigned Long large_transaction_cutoff, @Unsigned Long instrinsic_gas_per_byte, @Unsigned Long maximum_number_of_gas_units, @Unsigned Long min_price_per_gas_unit, @Unsigned Long max_price_per_gas_unit, @Unsigned Long max_transaction_size_in_bytes, @Unsigned Long gas_unit_scaling_factor, @Unsigned Long default_account_size, @Unsigned Long voting_delay, @Unsigned Long voting_period, @Unsigned Byte voting_quorum_rate, @Unsigned Long min_action_delay, @Unsigned Long transaction_timeout) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u64_argument(stdlib_version), Helpers.encode_u64_argument(reward_delay), Helpers.encode_u128_argument(total_stc_amount), Helpers.encode_u128_argument(pre_mine_stc_amount), Helpers.encode_u128_argument(time_mint_stc_amount), Helpers.encode_u64_argument(time_mint_stc_period), Helpers.encode_u8vector_argument(parent_hash), Helpers.encode_u8vector_argument(association_auth_key), Helpers.encode_u8vector_argument(genesis_auth_key), Helpers.encode_u8_argument(chain_id), Helpers.encode_u64_argument(genesis_timestamp), Helpers.encode_u64_argument(uncle_rate_target), Helpers.encode_u64_argument(epoch_block_count), Helpers.encode_u64_argument(base_block_time_target), Helpers.encode_u64_argument(base_block_difficulty_window), Helpers.encode_u128_argument(base_reward_per_block), Helpers.encode_u64_argument(base_reward_per_uncle_percent), Helpers.encode_u64_argument(min_block_time_target), Helpers.encode_u64_argument(max_block_time_target), Helpers.encode_u64_argument(base_max_uncles_per_block), Helpers.encode_u64_argument(base_block_gas_limit), Helpers.encode_u8_argument(strategy), Helpers.encode_bool_argument(script_allowed), Helpers.encode_bool_argument(module_publishing_allowed), Helpers.encode_u8vector_argument(instruction_schedule), Helpers.encode_u8vector_argument(native_schedule), Helpers.encode_u64_argument(global_memory_per_byte_cost), Helpers.encode_u64_argument(global_memory_per_byte_write_cost), Helpers.encode_u64_argument(min_transaction_gas_units), Helpers.encode_u64_argument(large_transaction_cutoff), Helpers.encode_u64_argument(instrinsic_gas_per_byte), Helpers.encode_u64_argument(maximum_number_of_gas_units), Helpers.encode_u64_argument(min_price_per_gas_unit), Helpers.encode_u64_argument(max_price_per_gas_unit), Helpers.encode_u64_argument(max_transaction_size_in_bytes), Helpers.encode_u64_argument(gas_unit_scaling_factor), Helpers.encode_u64_argument(default_account_size), Helpers.encode_u64_argument(voting_delay), Helpers.encode_u64_argument(voting_period), Helpers.encode_u8_argument(voting_quorum_rate), Helpers.encode_u64_argument(min_action_delay), Helpers.encode_u64_argument(transaction_timeout));
        script_function_builder.function = new Identifier("initialize_v2");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Genesis"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_type      {@code TypeTag} value
     * @param payee           {@code AccountAddress} value
     * @param _payee_auth_key {@code Bytes} value
     * @param amount          {@code @Unsigned @Int128 BigInteger} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_peer_to_peer_script_function(TypeTag token_type, AccountAddress payee, Bytes _payee_auth_key, @Unsigned @Int128 BigInteger amount) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_type);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(payee), Helpers.encode_u8vector_argument(_payee_auth_key), Helpers.encode_u128_argument(amount));
        script_function_builder.function = new Identifier("peer_to_peer");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TransferScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_type       {@code TypeTag} value
     * @param _payeees         {@code Bytes} value
     * @param _payee_auth_keys {@code Bytes} value
     * @param _amount          {@code @Unsigned @Int128 BigInteger} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_peer_to_peer_batch_script_function(TypeTag token_type, Bytes _payeees, Bytes _payee_auth_keys, @Unsigned @Int128 BigInteger _amount) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_type);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u8vector_argument(_payeees), Helpers.encode_u8vector_argument(_payee_auth_keys), Helpers.encode_u128_argument(_amount));
        script_function_builder.function = new Identifier("peer_to_peer_batch");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TransferScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_type {@code TypeTag} value
     * @param payee      {@code AccountAddress} value
     * @param amount     {@code @Unsigned @Int128 BigInteger} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_peer_to_peer_v2_script_function(TypeTag token_type, AccountAddress payee, @Unsigned @Int128 BigInteger amount) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_type);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(payee), Helpers.encode_u128_argument(amount));
        script_function_builder.function = new Identifier("peer_to_peer_v2");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TransferScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_type      {@code TypeTag} value
     * @param payee           {@code AccountAddress} value
     * @param _payee_auth_key {@code Bytes} value
     * @param amount          {@code @Unsigned @Int128 BigInteger} value
     * @param metadata        {@code Bytes} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_peer_to_peer_with_metadata_script_function(TypeTag token_type, AccountAddress payee, Bytes _payee_auth_key, @Unsigned @Int128 BigInteger amount, Bytes metadata) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_type);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(payee), Helpers.encode_u8vector_argument(_payee_auth_key), Helpers.encode_u128_argument(amount), Helpers.encode_u8vector_argument(metadata));
        script_function_builder.function = new Identifier("peer_to_peer_with_metadata");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TransferScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_type {@code TypeTag} value
     * @param payee      {@code AccountAddress} value
     * @param amount     {@code @Unsigned @Int128 BigInteger} value
     * @param metadata   {@code Bytes} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_peer_to_peer_with_metadata_v2_script_function(TypeTag token_type, AccountAddress payee, @Unsigned @Int128 BigInteger amount, Bytes metadata) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_type);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(payee), Helpers.encode_u128_argument(amount), Helpers.encode_u8vector_argument(metadata));
        script_function_builder.function = new Identifier("peer_to_peer_with_metadata_v2");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TransferScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * Entrypoint for the proposal.
     *
     * @param token_t            {@code TypeTag} value
     * @param voting_delay       {@code @Unsigned Long} value
     * @param voting_period      {@code @Unsigned Long} value
     * @param voting_quorum_rate {@code @Unsigned Byte} value
     * @param min_action_delay   {@code @Unsigned Long} value
     * @param exec_delay         {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_propose_script_function(TypeTag token_t, @Unsigned Long voting_delay, @Unsigned Long voting_period, @Unsigned Byte voting_quorum_rate, @Unsigned Long min_action_delay, @Unsigned Long exec_delay) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u64_argument(voting_delay), Helpers.encode_u64_argument(voting_period), Helpers.encode_u8_argument(voting_quorum_rate), Helpers.encode_u64_argument(min_action_delay), Helpers.encode_u64_argument(exec_delay));
        script_function_builder.function = new Identifier("propose");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("ModifyDaoConfigProposal"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token          {@code TypeTag} value
     * @param module_address {@code AccountAddress} value
     * @param package_hash   {@code Bytes} value
     * @param version        {@code @Unsigned Long} value
     * @param exec_delay     {@code @Unsigned Long} value
     * @param enforced       {@code Boolean} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_propose_module_upgrade_v2_script_function(TypeTag token, AccountAddress module_address, Bytes package_hash, @Unsigned Long version, @Unsigned Long exec_delay, Boolean enforced) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(module_address), Helpers.encode_u8vector_argument(package_hash), Helpers.encode_u64_argument(version), Helpers.encode_u64_argument(exec_delay), Helpers.encode_bool_argument(enforced));
        script_function_builder.function = new Identifier("propose_module_upgrade_v2");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("ModuleUpgradeScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param uncle_rate_target             {@code @Unsigned Long} value
     * @param base_block_time_target        {@code @Unsigned Long} value
     * @param base_reward_per_block         {@code @Unsigned @Int128 BigInteger} value
     * @param base_reward_per_uncle_percent {@code @Unsigned Long} value
     * @param epoch_block_count             {@code @Unsigned Long} value
     * @param base_block_difficulty_window  {@code @Unsigned Long} value
     * @param min_block_time_target         {@code @Unsigned Long} value
     * @param max_block_time_target         {@code @Unsigned Long} value
     * @param base_max_uncles_per_block     {@code @Unsigned Long} value
     * @param base_block_gas_limit          {@code @Unsigned Long} value
     * @param strategy                      {@code @Unsigned Byte} value
     * @param exec_delay                    {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_propose_update_consensus_config_script_function(@Unsigned Long uncle_rate_target, @Unsigned Long base_block_time_target, @Unsigned @Int128 BigInteger base_reward_per_block, @Unsigned Long base_reward_per_uncle_percent, @Unsigned Long epoch_block_count, @Unsigned Long base_block_difficulty_window, @Unsigned Long min_block_time_target, @Unsigned Long max_block_time_target, @Unsigned Long base_max_uncles_per_block, @Unsigned Long base_block_gas_limit, @Unsigned Byte strategy, @Unsigned Long exec_delay) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u64_argument(uncle_rate_target), Helpers.encode_u64_argument(base_block_time_target), Helpers.encode_u128_argument(base_reward_per_block), Helpers.encode_u64_argument(base_reward_per_uncle_percent), Helpers.encode_u64_argument(epoch_block_count), Helpers.encode_u64_argument(base_block_difficulty_window), Helpers.encode_u64_argument(min_block_time_target), Helpers.encode_u64_argument(max_block_time_target), Helpers.encode_u64_argument(base_max_uncles_per_block), Helpers.encode_u64_argument(base_block_gas_limit), Helpers.encode_u8_argument(strategy), Helpers.encode_u64_argument(exec_delay));
        script_function_builder.function = new Identifier("propose_update_consensus_config");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("OnChainConfigScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param reward_delay {@code @Unsigned Long} value
     * @param exec_delay   {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_propose_update_reward_config_script_function(@Unsigned Long reward_delay, @Unsigned Long exec_delay) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u64_argument(reward_delay), Helpers.encode_u64_argument(exec_delay));
        script_function_builder.function = new Identifier("propose_update_reward_config");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("OnChainConfigScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param script_allowed            {@code Boolean} value
     * @param module_publishing_allowed {@code Boolean} value
     * @param exec_delay                {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_propose_update_txn_publish_option_script_function(Boolean script_allowed, Boolean module_publishing_allowed, @Unsigned Long exec_delay) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_bool_argument(script_allowed), Helpers.encode_bool_argument(module_publishing_allowed), Helpers.encode_u64_argument(exec_delay));
        script_function_builder.function = new Identifier("propose_update_txn_publish_option");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("OnChainConfigScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param duration_seconds {@code @Unsigned Long} value
     * @param exec_delay       {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_propose_update_txn_timeout_config_script_function(@Unsigned Long duration_seconds, @Unsigned Long exec_delay) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u64_argument(duration_seconds), Helpers.encode_u64_argument(exec_delay));
        script_function_builder.function = new Identifier("propose_update_txn_timeout_config");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("OnChainConfigScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param instruction_schedule              {@code Bytes} value
     * @param native_schedule                   {@code Bytes} value
     * @param global_memory_per_byte_cost       {@code @Unsigned Long} value
     * @param global_memory_per_byte_write_cost {@code @Unsigned Long} value
     * @param min_transaction_gas_units         {@code @Unsigned Long} value
     * @param large_transaction_cutoff          {@code @Unsigned Long} value
     * @param instrinsic_gas_per_byte           {@code @Unsigned Long} value
     * @param maximum_number_of_gas_units       {@code @Unsigned Long} value
     * @param min_price_per_gas_unit            {@code @Unsigned Long} value
     * @param max_price_per_gas_unit            {@code @Unsigned Long} value
     * @param max_transaction_size_in_bytes     {@code @Unsigned Long} value
     * @param gas_unit_scaling_factor           {@code @Unsigned Long} value
     * @param default_account_size              {@code @Unsigned Long} value
     * @param exec_delay                        {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_propose_update_vm_config_script_function(Bytes instruction_schedule, Bytes native_schedule, @Unsigned Long global_memory_per_byte_cost, @Unsigned Long global_memory_per_byte_write_cost, @Unsigned Long min_transaction_gas_units, @Unsigned Long large_transaction_cutoff, @Unsigned Long instrinsic_gas_per_byte, @Unsigned Long maximum_number_of_gas_units, @Unsigned Long min_price_per_gas_unit, @Unsigned Long max_price_per_gas_unit, @Unsigned Long max_transaction_size_in_bytes, @Unsigned Long gas_unit_scaling_factor, @Unsigned Long default_account_size, @Unsigned Long exec_delay) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u8vector_argument(instruction_schedule), Helpers.encode_u8vector_argument(native_schedule), Helpers.encode_u64_argument(global_memory_per_byte_cost), Helpers.encode_u64_argument(global_memory_per_byte_write_cost), Helpers.encode_u64_argument(min_transaction_gas_units), Helpers.encode_u64_argument(large_transaction_cutoff), Helpers.encode_u64_argument(instrinsic_gas_per_byte), Helpers.encode_u64_argument(maximum_number_of_gas_units), Helpers.encode_u64_argument(min_price_per_gas_unit), Helpers.encode_u64_argument(max_price_per_gas_unit), Helpers.encode_u64_argument(max_transaction_size_in_bytes), Helpers.encode_u64_argument(gas_unit_scaling_factor), Helpers.encode_u64_argument(default_account_size), Helpers.encode_u64_argument(exec_delay));
        script_function_builder.function = new Identifier("propose_update_vm_config");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("OnChainConfigScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_t    {@code TypeTag} value
     * @param receiver   {@code AccountAddress} value
     * @param amount     {@code @Unsigned @Int128 BigInteger} value
     * @param period     {@code @Unsigned Long} value
     * @param exec_delay {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_propose_withdraw_script_function(TypeTag token_t, AccountAddress receiver, @Unsigned @Int128 BigInteger amount, @Unsigned Long period, @Unsigned Long exec_delay) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(receiver), Helpers.encode_u128_argument(amount), Helpers.encode_u64_argument(period), Helpers.encode_u64_argument(exec_delay));
        script_function_builder.function = new Identifier("propose_withdraw");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TreasuryScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * queue agreed proposal to execute.
     *
     * @param token_t          {@code TypeTag} value
     * @param action_t         {@code TypeTag} value
     * @param proposer_address {@code AccountAddress} value
     * @param proposal_id      {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_queue_proposal_action_script_function(TypeTag token_t, TypeTag action_t, AccountAddress proposer_address, @Unsigned Long proposal_id) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_t, action_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(proposer_address), Helpers.encode_u64_argument(proposal_id));
        script_function_builder.function = new Identifier("queue_proposal_action");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Dao"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token            {@code TypeTag} value
     * @param action           {@code TypeTag} value
     * @param proposer_address {@code AccountAddress} value
     * @param proposal_id      {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_revoke_vote_script_function(TypeTag token, TypeTag action, AccountAddress proposer_address, @Unsigned Long proposal_id) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token, action);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(proposer_address), Helpers.encode_u64_argument(proposal_id));
        script_function_builder.function = new Identifier("revoke_vote");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("DaoVoteScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param new_key {@code Bytes} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_rotate_authentication_key_script_function(Bytes new_key) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u8vector_argument(new_key));
        script_function_builder.function = new Identifier("rotate_authentication_key");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Account"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token            {@code TypeTag} value
     * @param proposer_address {@code AccountAddress} value
     * @param proposal_id      {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_submit_module_upgrade_plan_script_function(TypeTag token, AccountAddress proposer_address, @Unsigned Long proposal_id) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(proposer_address), Helpers.encode_u64_argument(proposal_id));
        script_function_builder.function = new Identifier("submit_module_upgrade_plan");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("ModuleUpgradeScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * association account should call this script after upgrade from v2 to v3.
     *
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_take_linear_withdraw_capability_script_function() {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList();
        script_function_builder.function = new Identifier("take_linear_withdraw_capability");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("StdlibUpgradeScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * Take Offer and put to signer's Collection{@code <}Offered{@code >}.
     *
     * @param offered       {@code TypeTag} value
     * @param offer_address {@code AccountAddress} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_take_offer_script_function(TypeTag offered, AccountAddress offer_address) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(offered);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(offer_address));
        script_function_builder.function = new Identifier("take_offer");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("Offer"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token            {@code TypeTag} value
     * @param action           {@code TypeTag} value
     * @param proposer_address {@code AccountAddress} value
     * @param proposal_id      {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_unstake_vote_script_function(TypeTag token, TypeTag action, AccountAddress proposer_address, @Unsigned Long proposal_id) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token, action);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(proposer_address), Helpers.encode_u64_argument(proposal_id));
        script_function_builder.function = new Identifier("unstake_vote");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("DaoVoteScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param strategy {@code @Unsigned Byte} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_update_module_upgrade_strategy_script_function(@Unsigned Byte strategy) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u8_argument(strategy));
        script_function_builder.function = new Identifier("update_module_upgrade_strategy");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("ModuleUpgradeScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * Stdlib upgrade script from v2 to v3
     *
     * @param total_stc_amount {@code @Unsigned @Int128 BigInteger} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_upgrade_from_v2_to_v3_script_function(@Unsigned @Int128 BigInteger total_stc_amount) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList();
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_u128_argument(total_stc_amount));
        script_function_builder.function = new Identifier("upgrade_from_v2_to_v3");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("StdlibUpgradeScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_t     {@code TypeTag} value
     * @param for_address {@code AccountAddress} value
     * @param amount      {@code @Unsigned @Int128 BigInteger} value
     * @param lock_period {@code @Unsigned Long} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_withdraw_and_split_lt_withdraw_cap_script_function(TypeTag token_t, AccountAddress for_address, @Unsigned @Int128 BigInteger amount, @Unsigned Long lock_period) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_t);
        script_function_builder.args = java.util.Arrays.asList(Helpers.encode_address_argument(for_address), Helpers.encode_u128_argument(amount), Helpers.encode_u64_argument(lock_period));
        script_function_builder.function = new Identifier("withdraw_and_split_lt_withdraw_cap");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TreasuryScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    /**
     * @param token_t {@code TypeTag} value
     * @return Encoded {@link org.starcoin.types.TransactionPayload} value.
     */
    public static TransactionPayload encode_withdraw_token_with_linear_withdraw_capability_script_function(TypeTag token_t) {
        ScriptFunction.Builder script_function_builder = new ScriptFunction.Builder();
        script_function_builder.ty_args = java.util.Arrays.asList(token_t);
        script_function_builder.args = java.util.Arrays.asList();
        script_function_builder.function = new Identifier("withdraw_token_with_linear_withdraw_capability");
        script_function_builder.module = new ModuleId(AccountAddress.valueOf(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), new Identifier("TreasuryScripts"));

        TransactionPayload.ScriptFunction.Builder builder = new TransactionPayload.ScriptFunction.Builder();
        builder.value = script_function_builder.build();
        return builder.build();
    }

    private static ScriptFunctionCall decode_accept_token_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.AcceptToken.Builder builder = new ScriptFunctionCall.AcceptToken.Builder();
        builder.token_type = script.ty_args.get(0);
        return builder.build();
    }

    private static ScriptFunctionCall decode_cancel_upgrade_plan_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction _script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.CancelUpgradePlan.Builder builder = new ScriptFunctionCall.CancelUpgradePlan.Builder();
        return builder.build();
    }

    private static ScriptFunctionCall decode_cast_vote_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.CastVote.Builder builder = new ScriptFunctionCall.CastVote.Builder();
        builder.token = script.ty_args.get(0);
        builder.action_t = script.ty_args.get(1);
        builder.proposer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.proposal_id = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        builder.agree = new BcsDeserializer(script.args.get(2).content()).deserialize_bool();
        builder.votes = new BcsDeserializer(script.args.get(3).content()).deserialize_u128();
        return builder.build();
    }

    private static ScriptFunctionCall decode_convert_TwoPhaseUpgrade_to_TwoPhaseUpgradeV2_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2.Builder builder = new ScriptFunctionCall.ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2.Builder();
        builder.package_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        return builder.build();
    }

    private static ScriptFunctionCall decode_create_account_with_initial_amount_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.CreateAccountWithInitialAmount.Builder builder = new ScriptFunctionCall.CreateAccountWithInitialAmount.Builder();
        builder.token_type = script.ty_args.get(0);
        builder.fresh_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder._auth_key = new BcsDeserializer(script.args.get(1).content()).deserialize_bytes();
        builder.initial_amount = new BcsDeserializer(script.args.get(2).content()).deserialize_u128();
        return builder.build();
    }

    private static ScriptFunctionCall decode_create_account_with_initial_amount_v2_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.CreateAccountWithInitialAmountV2.Builder builder = new ScriptFunctionCall.CreateAccountWithInitialAmountV2.Builder();
        builder.token_type = script.ty_args.get(0);
        builder.fresh_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.initial_amount = new BcsDeserializer(script.args.get(1).content()).deserialize_u128();
        return builder.build();
    }

    private static ScriptFunctionCall decode_destroy_terminated_proposal_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.DestroyTerminatedProposal.Builder builder = new ScriptFunctionCall.DestroyTerminatedProposal.Builder();
        builder.token_t = script.ty_args.get(0);
        builder.action_t = script.ty_args.get(1);
        builder.proposer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.proposal_id = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_empty_script_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction _script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.EmptyScript.Builder builder = new ScriptFunctionCall.EmptyScript.Builder();
        return builder.build();
    }

    private static ScriptFunctionCall decode_execute_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.Execute.Builder builder = new ScriptFunctionCall.Execute.Builder();
        builder.token_t = script.ty_args.get(0);
        builder.proposer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.proposal_id = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_execute_on_chain_config_proposal_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ExecuteOnChainConfigProposal.Builder builder = new ScriptFunctionCall.ExecuteOnChainConfigProposal.Builder();
        builder.config_t = script.ty_args.get(0);
        builder.proposal_id = new BcsDeserializer(script.args.get(0).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_execute_withdraw_proposal_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ExecuteWithdrawProposal.Builder builder = new ScriptFunctionCall.ExecuteWithdrawProposal.Builder();
        builder.token_t = script.ty_args.get(0);
        builder.proposer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.proposal_id = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_initialize_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.Initialize.Builder builder = new ScriptFunctionCall.Initialize.Builder();
        builder.stdlib_version = new BcsDeserializer(script.args.get(0).content()).deserialize_u64();
        builder.reward_delay = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        builder.pre_mine_stc_amount = new BcsDeserializer(script.args.get(2).content()).deserialize_u128();
        builder.time_mint_stc_amount = new BcsDeserializer(script.args.get(3).content()).deserialize_u128();
        builder.time_mint_stc_period = new BcsDeserializer(script.args.get(4).content()).deserialize_u64();
        builder.parent_hash = new BcsDeserializer(script.args.get(5).content()).deserialize_bytes();
        builder.association_auth_key = new BcsDeserializer(script.args.get(6).content()).deserialize_bytes();
        builder.genesis_auth_key = new BcsDeserializer(script.args.get(7).content()).deserialize_bytes();
        builder.chain_id = new BcsDeserializer(script.args.get(8).content()).deserialize_u8();
        builder.genesis_timestamp = new BcsDeserializer(script.args.get(9).content()).deserialize_u64();
        builder.uncle_rate_target = new BcsDeserializer(script.args.get(10).content()).deserialize_u64();
        builder.epoch_block_count = new BcsDeserializer(script.args.get(11).content()).deserialize_u64();
        builder.base_block_time_target = new BcsDeserializer(script.args.get(12).content()).deserialize_u64();
        builder.base_block_difficulty_window = new BcsDeserializer(script.args.get(13).content()).deserialize_u64();
        builder.base_reward_per_block = new BcsDeserializer(script.args.get(14).content()).deserialize_u128();
        builder.base_reward_per_uncle_percent = new BcsDeserializer(script.args.get(15).content()).deserialize_u64();
        builder.min_block_time_target = new BcsDeserializer(script.args.get(16).content()).deserialize_u64();
        builder.max_block_time_target = new BcsDeserializer(script.args.get(17).content()).deserialize_u64();
        builder.base_max_uncles_per_block = new BcsDeserializer(script.args.get(18).content()).deserialize_u64();
        builder.base_block_gas_limit = new BcsDeserializer(script.args.get(19).content()).deserialize_u64();
        builder.strategy = new BcsDeserializer(script.args.get(20).content()).deserialize_u8();
        builder.script_allowed = new BcsDeserializer(script.args.get(21).content()).deserialize_bool();
        builder.module_publishing_allowed = new BcsDeserializer(script.args.get(22).content()).deserialize_bool();
        builder.instruction_schedule = new BcsDeserializer(script.args.get(23).content()).deserialize_bytes();
        builder.native_schedule = new BcsDeserializer(script.args.get(24).content()).deserialize_bytes();
        builder.global_memory_per_byte_cost = new BcsDeserializer(script.args.get(25).content()).deserialize_u64();
        builder.global_memory_per_byte_write_cost = new BcsDeserializer(script.args.get(26).content()).deserialize_u64();
        builder.min_transaction_gas_units = new BcsDeserializer(script.args.get(27).content()).deserialize_u64();
        builder.large_transaction_cutoff = new BcsDeserializer(script.args.get(28).content()).deserialize_u64();
        builder.instrinsic_gas_per_byte = new BcsDeserializer(script.args.get(29).content()).deserialize_u64();
        builder.maximum_number_of_gas_units = new BcsDeserializer(script.args.get(30).content()).deserialize_u64();
        builder.min_price_per_gas_unit = new BcsDeserializer(script.args.get(31).content()).deserialize_u64();
        builder.max_price_per_gas_unit = new BcsDeserializer(script.args.get(32).content()).deserialize_u64();
        builder.max_transaction_size_in_bytes = new BcsDeserializer(script.args.get(33).content()).deserialize_u64();
        builder.gas_unit_scaling_factor = new BcsDeserializer(script.args.get(34).content()).deserialize_u64();
        builder.default_account_size = new BcsDeserializer(script.args.get(35).content()).deserialize_u64();
        builder.voting_delay = new BcsDeserializer(script.args.get(36).content()).deserialize_u64();
        builder.voting_period = new BcsDeserializer(script.args.get(37).content()).deserialize_u64();
        builder.voting_quorum_rate = new BcsDeserializer(script.args.get(38).content()).deserialize_u8();
        builder.min_action_delay = new BcsDeserializer(script.args.get(39).content()).deserialize_u64();
        builder.transaction_timeout = new BcsDeserializer(script.args.get(40).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_initialize_v2_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.InitializeV2.Builder builder = new ScriptFunctionCall.InitializeV2.Builder();
        builder.stdlib_version = new BcsDeserializer(script.args.get(0).content()).deserialize_u64();
        builder.reward_delay = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        builder.total_stc_amount = new BcsDeserializer(script.args.get(2).content()).deserialize_u128();
        builder.pre_mine_stc_amount = new BcsDeserializer(script.args.get(3).content()).deserialize_u128();
        builder.time_mint_stc_amount = new BcsDeserializer(script.args.get(4).content()).deserialize_u128();
        builder.time_mint_stc_period = new BcsDeserializer(script.args.get(5).content()).deserialize_u64();
        builder.parent_hash = new BcsDeserializer(script.args.get(6).content()).deserialize_bytes();
        builder.association_auth_key = new BcsDeserializer(script.args.get(7).content()).deserialize_bytes();
        builder.genesis_auth_key = new BcsDeserializer(script.args.get(8).content()).deserialize_bytes();
        builder.chain_id = new BcsDeserializer(script.args.get(9).content()).deserialize_u8();
        builder.genesis_timestamp = new BcsDeserializer(script.args.get(10).content()).deserialize_u64();
        builder.uncle_rate_target = new BcsDeserializer(script.args.get(11).content()).deserialize_u64();
        builder.epoch_block_count = new BcsDeserializer(script.args.get(12).content()).deserialize_u64();
        builder.base_block_time_target = new BcsDeserializer(script.args.get(13).content()).deserialize_u64();
        builder.base_block_difficulty_window = new BcsDeserializer(script.args.get(14).content()).deserialize_u64();
        builder.base_reward_per_block = new BcsDeserializer(script.args.get(15).content()).deserialize_u128();
        builder.base_reward_per_uncle_percent = new BcsDeserializer(script.args.get(16).content()).deserialize_u64();
        builder.min_block_time_target = new BcsDeserializer(script.args.get(17).content()).deserialize_u64();
        builder.max_block_time_target = new BcsDeserializer(script.args.get(18).content()).deserialize_u64();
        builder.base_max_uncles_per_block = new BcsDeserializer(script.args.get(19).content()).deserialize_u64();
        builder.base_block_gas_limit = new BcsDeserializer(script.args.get(20).content()).deserialize_u64();
        builder.strategy = new BcsDeserializer(script.args.get(21).content()).deserialize_u8();
        builder.script_allowed = new BcsDeserializer(script.args.get(22).content()).deserialize_bool();
        builder.module_publishing_allowed = new BcsDeserializer(script.args.get(23).content()).deserialize_bool();
        builder.instruction_schedule = new BcsDeserializer(script.args.get(24).content()).deserialize_bytes();
        builder.native_schedule = new BcsDeserializer(script.args.get(25).content()).deserialize_bytes();
        builder.global_memory_per_byte_cost = new BcsDeserializer(script.args.get(26).content()).deserialize_u64();
        builder.global_memory_per_byte_write_cost = new BcsDeserializer(script.args.get(27).content()).deserialize_u64();
        builder.min_transaction_gas_units = new BcsDeserializer(script.args.get(28).content()).deserialize_u64();
        builder.large_transaction_cutoff = new BcsDeserializer(script.args.get(29).content()).deserialize_u64();
        builder.instrinsic_gas_per_byte = new BcsDeserializer(script.args.get(30).content()).deserialize_u64();
        builder.maximum_number_of_gas_units = new BcsDeserializer(script.args.get(31).content()).deserialize_u64();
        builder.min_price_per_gas_unit = new BcsDeserializer(script.args.get(32).content()).deserialize_u64();
        builder.max_price_per_gas_unit = new BcsDeserializer(script.args.get(33).content()).deserialize_u64();
        builder.max_transaction_size_in_bytes = new BcsDeserializer(script.args.get(34).content()).deserialize_u64();
        builder.gas_unit_scaling_factor = new BcsDeserializer(script.args.get(35).content()).deserialize_u64();
        builder.default_account_size = new BcsDeserializer(script.args.get(36).content()).deserialize_u64();
        builder.voting_delay = new BcsDeserializer(script.args.get(37).content()).deserialize_u64();
        builder.voting_period = new BcsDeserializer(script.args.get(38).content()).deserialize_u64();
        builder.voting_quorum_rate = new BcsDeserializer(script.args.get(39).content()).deserialize_u8();
        builder.min_action_delay = new BcsDeserializer(script.args.get(40).content()).deserialize_u64();
        builder.transaction_timeout = new BcsDeserializer(script.args.get(41).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_peer_to_peer_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.PeerToPeer.Builder builder = new ScriptFunctionCall.PeerToPeer.Builder();
        builder.token_type = script.ty_args.get(0);
        builder.payee = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder._payee_auth_key = new BcsDeserializer(script.args.get(1).content()).deserialize_bytes();
        builder.amount = new BcsDeserializer(script.args.get(2).content()).deserialize_u128();
        return builder.build();
    }

    private static ScriptFunctionCall decode_peer_to_peer_batch_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.PeerToPeerBatch.Builder builder = new ScriptFunctionCall.PeerToPeerBatch.Builder();
        builder.token_type = script.ty_args.get(0);
        builder._payeees = new BcsDeserializer(script.args.get(0).content()).deserialize_bytes();
        builder._payee_auth_keys = new BcsDeserializer(script.args.get(1).content()).deserialize_bytes();
        builder._amount = new BcsDeserializer(script.args.get(2).content()).deserialize_u128();
        return builder.build();
    }

    private static ScriptFunctionCall decode_peer_to_peer_v2_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.PeerToPeerV2.Builder builder = new ScriptFunctionCall.PeerToPeerV2.Builder();
        builder.token_type = script.ty_args.get(0);
        builder.payee = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.amount = new BcsDeserializer(script.args.get(1).content()).deserialize_u128();
        return builder.build();
    }

    private static ScriptFunctionCall decode_peer_to_peer_with_metadata_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.PeerToPeerWithMetadata.Builder builder = new ScriptFunctionCall.PeerToPeerWithMetadata.Builder();
        builder.token_type = script.ty_args.get(0);
        builder.payee = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder._payee_auth_key = new BcsDeserializer(script.args.get(1).content()).deserialize_bytes();
        builder.amount = new BcsDeserializer(script.args.get(2).content()).deserialize_u128();
        builder.metadata = new BcsDeserializer(script.args.get(3).content()).deserialize_bytes();
        return builder.build();
    }

    private static ScriptFunctionCall decode_peer_to_peer_with_metadata_v2_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.PeerToPeerWithMetadataV2.Builder builder = new ScriptFunctionCall.PeerToPeerWithMetadataV2.Builder();
        builder.token_type = script.ty_args.get(0);
        builder.payee = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.amount = new BcsDeserializer(script.args.get(1).content()).deserialize_u128();
        builder.metadata = new BcsDeserializer(script.args.get(2).content()).deserialize_bytes();
        return builder.build();
    }

    private static ScriptFunctionCall decode_propose_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.Propose.Builder builder = new ScriptFunctionCall.Propose.Builder();
        builder.token_t = script.ty_args.get(0);
        builder.voting_delay = new BcsDeserializer(script.args.get(0).content()).deserialize_u64();
        builder.voting_period = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        builder.voting_quorum_rate = new BcsDeserializer(script.args.get(2).content()).deserialize_u8();
        builder.min_action_delay = new BcsDeserializer(script.args.get(3).content()).deserialize_u64();
        builder.exec_delay = new BcsDeserializer(script.args.get(4).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_propose_module_upgrade_v2_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ProposeModuleUpgradeV2.Builder builder = new ScriptFunctionCall.ProposeModuleUpgradeV2.Builder();
        builder.token = script.ty_args.get(0);
        builder.module_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.package_hash = new BcsDeserializer(script.args.get(1).content()).deserialize_bytes();
        builder.version = new BcsDeserializer(script.args.get(2).content()).deserialize_u64();
        builder.exec_delay = new BcsDeserializer(script.args.get(3).content()).deserialize_u64();
        builder.enforced = new BcsDeserializer(script.args.get(4).content()).deserialize_bool();
        return builder.build();
    }

    private static ScriptFunctionCall decode_propose_update_consensus_config_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ProposeUpdateConsensusConfig.Builder builder = new ScriptFunctionCall.ProposeUpdateConsensusConfig.Builder();
        builder.uncle_rate_target = new BcsDeserializer(script.args.get(0).content()).deserialize_u64();
        builder.base_block_time_target = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        builder.base_reward_per_block = new BcsDeserializer(script.args.get(2).content()).deserialize_u128();
        builder.base_reward_per_uncle_percent = new BcsDeserializer(script.args.get(3).content()).deserialize_u64();
        builder.epoch_block_count = new BcsDeserializer(script.args.get(4).content()).deserialize_u64();
        builder.base_block_difficulty_window = new BcsDeserializer(script.args.get(5).content()).deserialize_u64();
        builder.min_block_time_target = new BcsDeserializer(script.args.get(6).content()).deserialize_u64();
        builder.max_block_time_target = new BcsDeserializer(script.args.get(7).content()).deserialize_u64();
        builder.base_max_uncles_per_block = new BcsDeserializer(script.args.get(8).content()).deserialize_u64();
        builder.base_block_gas_limit = new BcsDeserializer(script.args.get(9).content()).deserialize_u64();
        builder.strategy = new BcsDeserializer(script.args.get(10).content()).deserialize_u8();
        builder.exec_delay = new BcsDeserializer(script.args.get(11).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_propose_update_reward_config_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ProposeUpdateRewardConfig.Builder builder = new ScriptFunctionCall.ProposeUpdateRewardConfig.Builder();
        builder.reward_delay = new BcsDeserializer(script.args.get(0).content()).deserialize_u64();
        builder.exec_delay = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_propose_update_txn_publish_option_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ProposeUpdateTxnPublishOption.Builder builder = new ScriptFunctionCall.ProposeUpdateTxnPublishOption.Builder();
        builder.script_allowed = new BcsDeserializer(script.args.get(0).content()).deserialize_bool();
        builder.module_publishing_allowed = new BcsDeserializer(script.args.get(1).content()).deserialize_bool();
        builder.exec_delay = new BcsDeserializer(script.args.get(2).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_propose_update_txn_timeout_config_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ProposeUpdateTxnTimeoutConfig.Builder builder = new ScriptFunctionCall.ProposeUpdateTxnTimeoutConfig.Builder();
        builder.duration_seconds = new BcsDeserializer(script.args.get(0).content()).deserialize_u64();
        builder.exec_delay = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_propose_update_vm_config_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ProposeUpdateVmConfig.Builder builder = new ScriptFunctionCall.ProposeUpdateVmConfig.Builder();
        builder.instruction_schedule = new BcsDeserializer(script.args.get(0).content()).deserialize_bytes();
        builder.native_schedule = new BcsDeserializer(script.args.get(1).content()).deserialize_bytes();
        builder.global_memory_per_byte_cost = new BcsDeserializer(script.args.get(2).content()).deserialize_u64();
        builder.global_memory_per_byte_write_cost = new BcsDeserializer(script.args.get(3).content()).deserialize_u64();
        builder.min_transaction_gas_units = new BcsDeserializer(script.args.get(4).content()).deserialize_u64();
        builder.large_transaction_cutoff = new BcsDeserializer(script.args.get(5).content()).deserialize_u64();
        builder.instrinsic_gas_per_byte = new BcsDeserializer(script.args.get(6).content()).deserialize_u64();
        builder.maximum_number_of_gas_units = new BcsDeserializer(script.args.get(7).content()).deserialize_u64();
        builder.min_price_per_gas_unit = new BcsDeserializer(script.args.get(8).content()).deserialize_u64();
        builder.max_price_per_gas_unit = new BcsDeserializer(script.args.get(9).content()).deserialize_u64();
        builder.max_transaction_size_in_bytes = new BcsDeserializer(script.args.get(10).content()).deserialize_u64();
        builder.gas_unit_scaling_factor = new BcsDeserializer(script.args.get(11).content()).deserialize_u64();
        builder.default_account_size = new BcsDeserializer(script.args.get(12).content()).deserialize_u64();
        builder.exec_delay = new BcsDeserializer(script.args.get(13).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_propose_withdraw_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.ProposeWithdraw.Builder builder = new ScriptFunctionCall.ProposeWithdraw.Builder();
        builder.token_t = script.ty_args.get(0);
        builder.receiver = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.amount = new BcsDeserializer(script.args.get(1).content()).deserialize_u128();
        builder.period = new BcsDeserializer(script.args.get(2).content()).deserialize_u64();
        builder.exec_delay = new BcsDeserializer(script.args.get(3).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_queue_proposal_action_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.QueueProposalAction.Builder builder = new ScriptFunctionCall.QueueProposalAction.Builder();
        builder.token_t = script.ty_args.get(0);
        builder.action_t = script.ty_args.get(1);
        builder.proposer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.proposal_id = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_revoke_vote_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.RevokeVote.Builder builder = new ScriptFunctionCall.RevokeVote.Builder();
        builder.token = script.ty_args.get(0);
        builder.action = script.ty_args.get(1);
        builder.proposer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.proposal_id = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_rotate_authentication_key_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.RotateAuthenticationKey.Builder builder = new ScriptFunctionCall.RotateAuthenticationKey.Builder();
        builder.new_key = new BcsDeserializer(script.args.get(0).content()).deserialize_bytes();
        return builder.build();
    }

    private static ScriptFunctionCall decode_submit_module_upgrade_plan_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.SubmitModuleUpgradePlan.Builder builder = new ScriptFunctionCall.SubmitModuleUpgradePlan.Builder();
        builder.token = script.ty_args.get(0);
        builder.proposer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.proposal_id = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_take_linear_withdraw_capability_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction _script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.TakeLinearWithdrawCapability.Builder builder = new ScriptFunctionCall.TakeLinearWithdrawCapability.Builder();
        return builder.build();
    }

    private static ScriptFunctionCall decode_take_offer_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.TakeOffer.Builder builder = new ScriptFunctionCall.TakeOffer.Builder();
        builder.offered = script.ty_args.get(0);
        builder.offer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        return builder.build();
    }

    private static ScriptFunctionCall decode_unstake_vote_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.UnstakeVote.Builder builder = new ScriptFunctionCall.UnstakeVote.Builder();
        builder.token = script.ty_args.get(0);
        builder.action = script.ty_args.get(1);
        builder.proposer_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.proposal_id = new BcsDeserializer(script.args.get(1).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_update_module_upgrade_strategy_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.UpdateModuleUpgradeStrategy.Builder builder = new ScriptFunctionCall.UpdateModuleUpgradeStrategy.Builder();
        builder.strategy = new BcsDeserializer(script.args.get(0).content()).deserialize_u8();
        return builder.build();
    }

    private static ScriptFunctionCall decode_upgrade_from_v2_to_v3_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.UpgradeFromV2ToV3.Builder builder = new ScriptFunctionCall.UpgradeFromV2ToV3.Builder();
        builder.total_stc_amount = new BcsDeserializer(script.args.get(0).content()).deserialize_u128();
        return builder.build();
    }

    private static ScriptFunctionCall decode_withdraw_and_split_lt_withdraw_cap_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.WithdrawAndSplitLtWithdrawCap.Builder builder = new ScriptFunctionCall.WithdrawAndSplitLtWithdrawCap.Builder();
        builder.token_t = script.ty_args.get(0);
        builder.for_address = AccountAddress.bcsDeserialize(script.args.get(0).content());
        builder.amount = new BcsDeserializer(script.args.get(1).content()).deserialize_u128();
        builder.lock_period = new BcsDeserializer(script.args.get(2).content()).deserialize_u64();
        return builder.build();
    }

    private static ScriptFunctionCall decode_withdraw_token_with_linear_withdraw_capability_script_function(TransactionPayload payload) throws DeserializationError, IllegalArgumentException, IndexOutOfBoundsException {
        if (!(payload instanceof TransactionPayload.ScriptFunction)) {
            throw new IllegalArgumentException("Transaction payload not a Script Function");
        }
        ScriptFunction script = ((TransactionPayload.ScriptFunction) payload).value;
        ScriptFunctionCall.WithdrawTokenWithLinearWithdrawCapability.Builder builder = new ScriptFunctionCall.WithdrawTokenWithLinearWithdrawCapability.Builder();
        builder.token_t = script.ty_args.get(0);
        return builder.build();
    }

    private static java.util.Map<Class<?>, ScriptEncodingHelper> initTransactionScriptEncoderMap() {
        java.util.HashMap<Class<?>, ScriptEncodingHelper> map = new java.util.HashMap<>();
        return map;
    }

    private static java.util.Map<Class<?>, ScriptFunctionEncodingHelper> initScriptFunctionEncoderMap() {
        java.util.HashMap<Class<?>, ScriptFunctionEncodingHelper> map = new java.util.HashMap<>();
        map.put(ScriptFunctionCall.AcceptToken.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.AcceptToken obj = (ScriptFunctionCall.AcceptToken) call;
            return Helpers.encode_accept_token_script_function(obj.token_type);
        }));
        map.put(ScriptFunctionCall.CancelUpgradePlan.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.CancelUpgradePlan obj = (ScriptFunctionCall.CancelUpgradePlan) call;
            return Helpers.encode_cancel_upgrade_plan_script_function();
        }));
        map.put(ScriptFunctionCall.CastVote.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.CastVote obj = (ScriptFunctionCall.CastVote) call;
            return Helpers.encode_cast_vote_script_function(obj.token, obj.action_t, obj.proposer_address, obj.proposal_id, obj.agree, obj.votes);
        }));
        map.put(ScriptFunctionCall.ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2 obj = (ScriptFunctionCall.ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2) call;
            return Helpers.encode_convert_TwoPhaseUpgrade_to_TwoPhaseUpgradeV2_script_function(obj.package_address);
        }));
        map.put(ScriptFunctionCall.CreateAccountWithInitialAmount.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.CreateAccountWithInitialAmount obj = (ScriptFunctionCall.CreateAccountWithInitialAmount) call;
            return Helpers.encode_create_account_with_initial_amount_script_function(obj.token_type, obj.fresh_address, obj._auth_key, obj.initial_amount);
        }));
        map.put(ScriptFunctionCall.CreateAccountWithInitialAmountV2.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.CreateAccountWithInitialAmountV2 obj = (ScriptFunctionCall.CreateAccountWithInitialAmountV2) call;
            return Helpers.encode_create_account_with_initial_amount_v2_script_function(obj.token_type, obj.fresh_address, obj.initial_amount);
        }));
        map.put(ScriptFunctionCall.DestroyTerminatedProposal.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.DestroyTerminatedProposal obj = (ScriptFunctionCall.DestroyTerminatedProposal) call;
            return Helpers.encode_destroy_terminated_proposal_script_function(obj.token_t, obj.action_t, obj.proposer_address, obj.proposal_id);
        }));
        map.put(ScriptFunctionCall.EmptyScript.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.EmptyScript obj = (ScriptFunctionCall.EmptyScript) call;
            return Helpers.encode_empty_script_script_function();
        }));
        map.put(ScriptFunctionCall.Execute.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.Execute obj = (ScriptFunctionCall.Execute) call;
            return Helpers.encode_execute_script_function(obj.token_t, obj.proposer_address, obj.proposal_id);
        }));
        map.put(ScriptFunctionCall.ExecuteOnChainConfigProposal.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ExecuteOnChainConfigProposal obj = (ScriptFunctionCall.ExecuteOnChainConfigProposal) call;
            return Helpers.encode_execute_on_chain_config_proposal_script_function(obj.config_t, obj.proposal_id);
        }));
        map.put(ScriptFunctionCall.ExecuteWithdrawProposal.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ExecuteWithdrawProposal obj = (ScriptFunctionCall.ExecuteWithdrawProposal) call;
            return Helpers.encode_execute_withdraw_proposal_script_function(obj.token_t, obj.proposer_address, obj.proposal_id);
        }));
        map.put(ScriptFunctionCall.Initialize.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.Initialize obj = (ScriptFunctionCall.Initialize) call;
            return Helpers.encode_initialize_script_function(obj.stdlib_version, obj.reward_delay, obj.pre_mine_stc_amount, obj.time_mint_stc_amount, obj.time_mint_stc_period, obj.parent_hash, obj.association_auth_key, obj.genesis_auth_key, obj.chain_id, obj.genesis_timestamp, obj.uncle_rate_target, obj.epoch_block_count, obj.base_block_time_target, obj.base_block_difficulty_window, obj.base_reward_per_block, obj.base_reward_per_uncle_percent, obj.min_block_time_target, obj.max_block_time_target, obj.base_max_uncles_per_block, obj.base_block_gas_limit, obj.strategy, obj.script_allowed, obj.module_publishing_allowed, obj.instruction_schedule, obj.native_schedule, obj.global_memory_per_byte_cost, obj.global_memory_per_byte_write_cost, obj.min_transaction_gas_units, obj.large_transaction_cutoff, obj.instrinsic_gas_per_byte, obj.maximum_number_of_gas_units, obj.min_price_per_gas_unit, obj.max_price_per_gas_unit, obj.max_transaction_size_in_bytes, obj.gas_unit_scaling_factor, obj.default_account_size, obj.voting_delay, obj.voting_period, obj.voting_quorum_rate, obj.min_action_delay, obj.transaction_timeout);
        }));
        map.put(ScriptFunctionCall.InitializeV2.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.InitializeV2 obj = (ScriptFunctionCall.InitializeV2) call;
            return Helpers.encode_initialize_v2_script_function(obj.stdlib_version, obj.reward_delay, obj.total_stc_amount, obj.pre_mine_stc_amount, obj.time_mint_stc_amount, obj.time_mint_stc_period, obj.parent_hash, obj.association_auth_key, obj.genesis_auth_key, obj.chain_id, obj.genesis_timestamp, obj.uncle_rate_target, obj.epoch_block_count, obj.base_block_time_target, obj.base_block_difficulty_window, obj.base_reward_per_block, obj.base_reward_per_uncle_percent, obj.min_block_time_target, obj.max_block_time_target, obj.base_max_uncles_per_block, obj.base_block_gas_limit, obj.strategy, obj.script_allowed, obj.module_publishing_allowed, obj.instruction_schedule, obj.native_schedule, obj.global_memory_per_byte_cost, obj.global_memory_per_byte_write_cost, obj.min_transaction_gas_units, obj.large_transaction_cutoff, obj.instrinsic_gas_per_byte, obj.maximum_number_of_gas_units, obj.min_price_per_gas_unit, obj.max_price_per_gas_unit, obj.max_transaction_size_in_bytes, obj.gas_unit_scaling_factor, obj.default_account_size, obj.voting_delay, obj.voting_period, obj.voting_quorum_rate, obj.min_action_delay, obj.transaction_timeout);
        }));
        map.put(ScriptFunctionCall.PeerToPeer.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.PeerToPeer obj = (ScriptFunctionCall.PeerToPeer) call;
            return Helpers.encode_peer_to_peer_script_function(obj.token_type, obj.payee, obj._payee_auth_key, obj.amount);
        }));
        map.put(ScriptFunctionCall.PeerToPeerBatch.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.PeerToPeerBatch obj = (ScriptFunctionCall.PeerToPeerBatch) call;
            return Helpers.encode_peer_to_peer_batch_script_function(obj.token_type, obj._payeees, obj._payee_auth_keys, obj._amount);
        }));
        map.put(ScriptFunctionCall.PeerToPeerV2.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.PeerToPeerV2 obj = (ScriptFunctionCall.PeerToPeerV2) call;
            return Helpers.encode_peer_to_peer_v2_script_function(obj.token_type, obj.payee, obj.amount);
        }));
        map.put(ScriptFunctionCall.PeerToPeerWithMetadata.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.PeerToPeerWithMetadata obj = (ScriptFunctionCall.PeerToPeerWithMetadata) call;
            return Helpers.encode_peer_to_peer_with_metadata_script_function(obj.token_type, obj.payee, obj._payee_auth_key, obj.amount, obj.metadata);
        }));
        map.put(ScriptFunctionCall.PeerToPeerWithMetadataV2.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.PeerToPeerWithMetadataV2 obj = (ScriptFunctionCall.PeerToPeerWithMetadataV2) call;
            return Helpers.encode_peer_to_peer_with_metadata_v2_script_function(obj.token_type, obj.payee, obj.amount, obj.metadata);
        }));
        map.put(ScriptFunctionCall.Propose.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.Propose obj = (ScriptFunctionCall.Propose) call;
            return Helpers.encode_propose_script_function(obj.token_t, obj.voting_delay, obj.voting_period, obj.voting_quorum_rate, obj.min_action_delay, obj.exec_delay);
        }));
        map.put(ScriptFunctionCall.ProposeModuleUpgradeV2.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ProposeModuleUpgradeV2 obj = (ScriptFunctionCall.ProposeModuleUpgradeV2) call;
            return Helpers.encode_propose_module_upgrade_v2_script_function(obj.token, obj.module_address, obj.package_hash, obj.version, obj.exec_delay, obj.enforced);
        }));
        map.put(ScriptFunctionCall.ProposeUpdateConsensusConfig.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ProposeUpdateConsensusConfig obj = (ScriptFunctionCall.ProposeUpdateConsensusConfig) call;
            return Helpers.encode_propose_update_consensus_config_script_function(obj.uncle_rate_target, obj.base_block_time_target, obj.base_reward_per_block, obj.base_reward_per_uncle_percent, obj.epoch_block_count, obj.base_block_difficulty_window, obj.min_block_time_target, obj.max_block_time_target, obj.base_max_uncles_per_block, obj.base_block_gas_limit, obj.strategy, obj.exec_delay);
        }));
        map.put(ScriptFunctionCall.ProposeUpdateRewardConfig.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ProposeUpdateRewardConfig obj = (ScriptFunctionCall.ProposeUpdateRewardConfig) call;
            return Helpers.encode_propose_update_reward_config_script_function(obj.reward_delay, obj.exec_delay);
        }));
        map.put(ScriptFunctionCall.ProposeUpdateTxnPublishOption.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ProposeUpdateTxnPublishOption obj = (ScriptFunctionCall.ProposeUpdateTxnPublishOption) call;
            return Helpers.encode_propose_update_txn_publish_option_script_function(obj.script_allowed, obj.module_publishing_allowed, obj.exec_delay);
        }));
        map.put(ScriptFunctionCall.ProposeUpdateTxnTimeoutConfig.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ProposeUpdateTxnTimeoutConfig obj = (ScriptFunctionCall.ProposeUpdateTxnTimeoutConfig) call;
            return Helpers.encode_propose_update_txn_timeout_config_script_function(obj.duration_seconds, obj.exec_delay);
        }));
        map.put(ScriptFunctionCall.ProposeUpdateVmConfig.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ProposeUpdateVmConfig obj = (ScriptFunctionCall.ProposeUpdateVmConfig) call;
            return Helpers.encode_propose_update_vm_config_script_function(obj.instruction_schedule, obj.native_schedule, obj.global_memory_per_byte_cost, obj.global_memory_per_byte_write_cost, obj.min_transaction_gas_units, obj.large_transaction_cutoff, obj.instrinsic_gas_per_byte, obj.maximum_number_of_gas_units, obj.min_price_per_gas_unit, obj.max_price_per_gas_unit, obj.max_transaction_size_in_bytes, obj.gas_unit_scaling_factor, obj.default_account_size, obj.exec_delay);
        }));
        map.put(ScriptFunctionCall.ProposeWithdraw.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.ProposeWithdraw obj = (ScriptFunctionCall.ProposeWithdraw) call;
            return Helpers.encode_propose_withdraw_script_function(obj.token_t, obj.receiver, obj.amount, obj.period, obj.exec_delay);
        }));
        map.put(ScriptFunctionCall.QueueProposalAction.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.QueueProposalAction obj = (ScriptFunctionCall.QueueProposalAction) call;
            return Helpers.encode_queue_proposal_action_script_function(obj.token_t, obj.action_t, obj.proposer_address, obj.proposal_id);
        }));
        map.put(ScriptFunctionCall.RevokeVote.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.RevokeVote obj = (ScriptFunctionCall.RevokeVote) call;
            return Helpers.encode_revoke_vote_script_function(obj.token, obj.action, obj.proposer_address, obj.proposal_id);
        }));
        map.put(ScriptFunctionCall.RotateAuthenticationKey.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.RotateAuthenticationKey obj = (ScriptFunctionCall.RotateAuthenticationKey) call;
            return Helpers.encode_rotate_authentication_key_script_function(obj.new_key);
        }));
        map.put(ScriptFunctionCall.SubmitModuleUpgradePlan.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.SubmitModuleUpgradePlan obj = (ScriptFunctionCall.SubmitModuleUpgradePlan) call;
            return Helpers.encode_submit_module_upgrade_plan_script_function(obj.token, obj.proposer_address, obj.proposal_id);
        }));
        map.put(ScriptFunctionCall.TakeLinearWithdrawCapability.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.TakeLinearWithdrawCapability obj = (ScriptFunctionCall.TakeLinearWithdrawCapability) call;
            return Helpers.encode_take_linear_withdraw_capability_script_function();
        }));
        map.put(ScriptFunctionCall.TakeOffer.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.TakeOffer obj = (ScriptFunctionCall.TakeOffer) call;
            return Helpers.encode_take_offer_script_function(obj.offered, obj.offer_address);
        }));
        map.put(ScriptFunctionCall.UnstakeVote.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.UnstakeVote obj = (ScriptFunctionCall.UnstakeVote) call;
            return Helpers.encode_unstake_vote_script_function(obj.token, obj.action, obj.proposer_address, obj.proposal_id);
        }));
        map.put(ScriptFunctionCall.UpdateModuleUpgradeStrategy.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.UpdateModuleUpgradeStrategy obj = (ScriptFunctionCall.UpdateModuleUpgradeStrategy) call;
            return Helpers.encode_update_module_upgrade_strategy_script_function(obj.strategy);
        }));
        map.put(ScriptFunctionCall.UpgradeFromV2ToV3.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.UpgradeFromV2ToV3 obj = (ScriptFunctionCall.UpgradeFromV2ToV3) call;
            return Helpers.encode_upgrade_from_v2_to_v3_script_function(obj.total_stc_amount);
        }));
        map.put(ScriptFunctionCall.WithdrawAndSplitLtWithdrawCap.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.WithdrawAndSplitLtWithdrawCap obj = (ScriptFunctionCall.WithdrawAndSplitLtWithdrawCap) call;
            return Helpers.encode_withdraw_and_split_lt_withdraw_cap_script_function(obj.token_t, obj.for_address, obj.amount, obj.lock_period);
        }));
        map.put(ScriptFunctionCall.WithdrawTokenWithLinearWithdrawCapability.class, (ScriptFunctionEncodingHelper) ((call) -> {
            ScriptFunctionCall.WithdrawTokenWithLinearWithdrawCapability obj = (ScriptFunctionCall.WithdrawTokenWithLinearWithdrawCapability) call;
            return Helpers.encode_withdraw_token_with_linear_withdraw_capability_script_function(obj.token_t);
        }));
        return map;
    }

    private static java.util.Map<Bytes, TransactionScriptDecodingHelper> initTransactionScriptDecoderMap() {
        java.util.HashMap<Bytes, TransactionScriptDecodingHelper> map = new java.util.HashMap<>();
        return map;
    }

    private static java.util.Map<String, ScriptFunctionDecodingHelper> initDecoderMap() {
        java.util.HashMap<String, ScriptFunctionDecodingHelper> map = new java.util.HashMap<>();
        map.put("Accountaccept_token", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_accept_token_script_function(payload)));
        map.put("ModuleUpgradeScriptscancel_upgrade_plan", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_cancel_upgrade_plan_script_function(payload)));
        map.put("DaoVoteScriptscast_vote", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_cast_vote_script_function(payload)));
        map.put("PackageTxnManagerconvert_TwoPhaseUpgrade_to_TwoPhaseUpgradeV2", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_convert_TwoPhaseUpgrade_to_TwoPhaseUpgradeV2_script_function(payload)));
        map.put("Accountcreate_account_with_initial_amount", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_create_account_with_initial_amount_script_function(payload)));
        map.put("Accountcreate_account_with_initial_amount_v2", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_create_account_with_initial_amount_v2_script_function(payload)));
        map.put("Daodestroy_terminated_proposal", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_destroy_terminated_proposal_script_function(payload)));
        map.put("EmptyScriptsempty_script", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_empty_script_script_function(payload)));
        map.put("ModifyDaoConfigProposalexecute", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_execute_script_function(payload)));
        map.put("OnChainConfigScriptsexecute_on_chain_config_proposal", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_execute_on_chain_config_proposal_script_function(payload)));
        map.put("TreasuryScriptsexecute_withdraw_proposal", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_execute_withdraw_proposal_script_function(payload)));
        map.put("Genesisinitialize", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_initialize_script_function(payload)));
        map.put("Genesisinitialize_v2", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_initialize_v2_script_function(payload)));
        map.put("TransferScriptspeer_to_peer", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_peer_to_peer_script_function(payload)));
        map.put("TransferScriptspeer_to_peer_batch", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_peer_to_peer_batch_script_function(payload)));
        map.put("TransferScriptspeer_to_peer_v2", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_peer_to_peer_v2_script_function(payload)));
        map.put("TransferScriptspeer_to_peer_with_metadata", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_peer_to_peer_with_metadata_script_function(payload)));
        map.put("TransferScriptspeer_to_peer_with_metadata_v2", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_peer_to_peer_with_metadata_v2_script_function(payload)));
        map.put("ModifyDaoConfigProposalpropose", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_propose_script_function(payload)));
        map.put("ModuleUpgradeScriptspropose_module_upgrade_v2", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_propose_module_upgrade_v2_script_function(payload)));
        map.put("OnChainConfigScriptspropose_update_consensus_config", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_propose_update_consensus_config_script_function(payload)));
        map.put("OnChainConfigScriptspropose_update_reward_config", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_propose_update_reward_config_script_function(payload)));
        map.put("OnChainConfigScriptspropose_update_txn_publish_option", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_propose_update_txn_publish_option_script_function(payload)));
        map.put("OnChainConfigScriptspropose_update_txn_timeout_config", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_propose_update_txn_timeout_config_script_function(payload)));
        map.put("OnChainConfigScriptspropose_update_vm_config", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_propose_update_vm_config_script_function(payload)));
        map.put("TreasuryScriptspropose_withdraw", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_propose_withdraw_script_function(payload)));
        map.put("Daoqueue_proposal_action", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_queue_proposal_action_script_function(payload)));
        map.put("DaoVoteScriptsrevoke_vote", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_revoke_vote_script_function(payload)));
        map.put("Accountrotate_authentication_key", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_rotate_authentication_key_script_function(payload)));
        map.put("ModuleUpgradeScriptssubmit_module_upgrade_plan", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_submit_module_upgrade_plan_script_function(payload)));
        map.put("StdlibUpgradeScriptstake_linear_withdraw_capability", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_take_linear_withdraw_capability_script_function(payload)));
        map.put("Offertake_offer", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_take_offer_script_function(payload)));
        map.put("DaoVoteScriptsunstake_vote", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_unstake_vote_script_function(payload)));
        map.put("ModuleUpgradeScriptsupdate_module_upgrade_strategy", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_update_module_upgrade_strategy_script_function(payload)));
        map.put("StdlibUpgradeScriptsupgrade_from_v2_to_v3", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_upgrade_from_v2_to_v3_script_function(payload)));
        map.put("TreasuryScriptswithdraw_and_split_lt_withdraw_cap", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_withdraw_and_split_lt_withdraw_cap_script_function(payload)));
        map.put("TreasuryScriptswithdraw_token_with_linear_withdraw_capability", (ScriptFunctionDecodingHelper) ((payload) -> Helpers.decode_withdraw_token_with_linear_withdraw_capability_script_function(payload)));
        return map;
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

    private static Bytes encode_u8_argument(@Unsigned Byte arg) {
        try {

            BcsSerializer s = new BcsSerializer();
            s.serialize_u8(arg);
            return Bytes.valueOf(s.get_bytes());

        } catch (SerializationError e) {
            throw new IllegalArgumentException("Unable to serialize argument of type u8");
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

    private static Boolean decode_bool_argument(TransactionArgument arg) {
        if (!(arg instanceof TransactionArgument.Bool)) {
            throw new IllegalArgumentException("Was expecting a Bool argument");
        }
        return ((TransactionArgument.Bool) arg).value;
    }

    private static @Unsigned Byte decode_u8_argument(TransactionArgument arg) {
        if (!(arg instanceof TransactionArgument.U8)) {
            throw new IllegalArgumentException("Was expecting a U8 argument");
        }
        return ((TransactionArgument.U8) arg).value;
    }

    private static @Unsigned Long decode_u64_argument(TransactionArgument arg) {
        if (!(arg instanceof TransactionArgument.U64)) {
            throw new IllegalArgumentException("Was expecting a U64 argument");
        }
        return ((TransactionArgument.U64) arg).value;
    }

    private static @Unsigned @Int128 BigInteger decode_u128_argument(TransactionArgument arg) {
        if (!(arg instanceof TransactionArgument.U128)) {
            throw new IllegalArgumentException("Was expecting a U128 argument");
        }
        return ((TransactionArgument.U128) arg).value;
    }

    private static AccountAddress decode_address_argument(TransactionArgument arg) {
        if (!(arg instanceof TransactionArgument.Address)) {
            throw new IllegalArgumentException("Was expecting a Address argument");
        }
        return ((TransactionArgument.Address) arg).value;
    }

    private static Bytes decode_u8vector_argument(TransactionArgument arg) {
        if (!(arg instanceof TransactionArgument.U8Vector)) {
            throw new IllegalArgumentException("Was expecting a U8Vector argument");
        }
        return ((TransactionArgument.U8Vector) arg).value;
    }


    interface ScriptEncodingHelper {
        public Script encode(ScriptCall call);
    }


    interface ScriptFunctionEncodingHelper {
        public TransactionPayload encode(ScriptFunctionCall call);
    }


    interface TransactionScriptDecodingHelper {
        public ScriptCall decode(Script script);
    }


    interface ScriptFunctionDecodingHelper {
        public ScriptFunctionCall decode(TransactionPayload payload) throws DeserializationError;
    }


}

