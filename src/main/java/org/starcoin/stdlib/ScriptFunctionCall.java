package org.starcoin.stdlib;


public abstract class ScriptFunctionCall {

    /**
     *
     */
    public static final class AcceptToken extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_type;

        public AcceptToken(org.starcoin.types.TypeTag token_type) {
            java.util.Objects.requireNonNull(token_type, "token_type must not be null");
            this.token_type = token_type;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            AcceptToken other = (AcceptToken) obj;
            if (!java.util.Objects.equals(this.token_type, other.token_type)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_type != null ? this.token_type.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_type;

            public AcceptToken build() {
                return new AcceptToken(
                        token_type
                );
            }
        }
    }

    /**
     *
     */
    public static final class CancelUpgradePlan extends ScriptFunctionCall {
        public CancelUpgradePlan() {
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CancelUpgradePlan other = (CancelUpgradePlan) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public CancelUpgradePlan build() {
                return new CancelUpgradePlan(
                );
            }
        }
    }

    /**
     *
     */
    public static final class CastVote extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token;
        public final org.starcoin.types.TypeTag action_t;
        public final org.starcoin.types.AccountAddress proposer_address;
        public final @com.novi.serde.Unsigned Long proposal_id;
        public final Boolean agree;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger votes;

        public CastVote(org.starcoin.types.TypeTag token, org.starcoin.types.TypeTag action_t, org.starcoin.types.AccountAddress proposer_address, @com.novi.serde.Unsigned Long proposal_id, Boolean agree, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger votes) {
            java.util.Objects.requireNonNull(token, "token must not be null");
            java.util.Objects.requireNonNull(action_t, "action_t must not be null");
            java.util.Objects.requireNonNull(proposer_address, "proposer_address must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            java.util.Objects.requireNonNull(agree, "agree must not be null");
            java.util.Objects.requireNonNull(votes, "votes must not be null");
            this.token = token;
            this.action_t = action_t;
            this.proposer_address = proposer_address;
            this.proposal_id = proposal_id;
            this.agree = agree;
            this.votes = votes;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CastVote other = (CastVote) obj;
            if (!java.util.Objects.equals(this.token, other.token)) {
                return false;
            }
            if (!java.util.Objects.equals(this.action_t, other.action_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposer_address, other.proposer_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            if (!java.util.Objects.equals(this.agree, other.agree)) {
                return false;
            }
            if (!java.util.Objects.equals(this.votes, other.votes)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token != null ? this.token.hashCode() : 0);
            value = 31 * value + (this.action_t != null ? this.action_t.hashCode() : 0);
            value = 31 * value + (this.proposer_address != null ? this.proposer_address.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            value = 31 * value + (this.agree != null ? this.agree.hashCode() : 0);
            value = 31 * value + (this.votes != null ? this.votes.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token;
            public org.starcoin.types.TypeTag action_t;
            public org.starcoin.types.AccountAddress proposer_address;
            public @com.novi.serde.Unsigned Long proposal_id;
            public Boolean agree;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger votes;

            public CastVote build() {
                return new CastVote(
                        token,
                        action_t,
                        proposer_address,
                        proposal_id,
                        agree,
                        votes
                );
            }
        }
    }

    /**
     *
     */
    public static final class ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2 extends ScriptFunctionCall {
        public final org.starcoin.types.AccountAddress package_address;

        public ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2(org.starcoin.types.AccountAddress package_address) {
            java.util.Objects.requireNonNull(package_address, "package_address must not be null");
            this.package_address = package_address;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2 other = (ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2) obj;
            if (!java.util.Objects.equals(this.package_address, other.package_address)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.package_address != null ? this.package_address.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.AccountAddress package_address;

            public ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2 build() {
                return new ConvertTwoPhaseUpgradeToTwoPhaseUpgradeV2(
                        package_address
                );
            }
        }
    }

    /**
     *
     */
    public static final class CreateAccountWithInitialAmount extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_type;
        public final org.starcoin.types.AccountAddress fresh_address;
        public final com.novi.serde.Bytes _auth_key;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger initial_amount;

        public CreateAccountWithInitialAmount(org.starcoin.types.TypeTag token_type, org.starcoin.types.AccountAddress fresh_address, com.novi.serde.Bytes _auth_key, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger initial_amount) {
            java.util.Objects.requireNonNull(token_type, "token_type must not be null");
            java.util.Objects.requireNonNull(fresh_address, "fresh_address must not be null");
            java.util.Objects.requireNonNull(_auth_key, "_auth_key must not be null");
            java.util.Objects.requireNonNull(initial_amount, "initial_amount must not be null");
            this.token_type = token_type;
            this.fresh_address = fresh_address;
            this._auth_key = _auth_key;
            this.initial_amount = initial_amount;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CreateAccountWithInitialAmount other = (CreateAccountWithInitialAmount) obj;
            if (!java.util.Objects.equals(this.token_type, other.token_type)) {
                return false;
            }
            if (!java.util.Objects.equals(this.fresh_address, other.fresh_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this._auth_key, other._auth_key)) {
                return false;
            }
            if (!java.util.Objects.equals(this.initial_amount, other.initial_amount)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_type != null ? this.token_type.hashCode() : 0);
            value = 31 * value + (this.fresh_address != null ? this.fresh_address.hashCode() : 0);
            value = 31 * value + (this._auth_key != null ? this._auth_key.hashCode() : 0);
            value = 31 * value + (this.initial_amount != null ? this.initial_amount.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_type;
            public org.starcoin.types.AccountAddress fresh_address;
            public com.novi.serde.Bytes _auth_key;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger initial_amount;

            public CreateAccountWithInitialAmount build() {
                return new CreateAccountWithInitialAmount(
                        token_type,
                        fresh_address,
                        _auth_key,
                        initial_amount
                );
            }
        }
    }

    /**
     *
     */
    public static final class CreateAccountWithInitialAmountV2 extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_type;
        public final org.starcoin.types.AccountAddress fresh_address;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger initial_amount;

        public CreateAccountWithInitialAmountV2(org.starcoin.types.TypeTag token_type, org.starcoin.types.AccountAddress fresh_address, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger initial_amount) {
            java.util.Objects.requireNonNull(token_type, "token_type must not be null");
            java.util.Objects.requireNonNull(fresh_address, "fresh_address must not be null");
            java.util.Objects.requireNonNull(initial_amount, "initial_amount must not be null");
            this.token_type = token_type;
            this.fresh_address = fresh_address;
            this.initial_amount = initial_amount;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            CreateAccountWithInitialAmountV2 other = (CreateAccountWithInitialAmountV2) obj;
            if (!java.util.Objects.equals(this.token_type, other.token_type)) {
                return false;
            }
            if (!java.util.Objects.equals(this.fresh_address, other.fresh_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.initial_amount, other.initial_amount)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_type != null ? this.token_type.hashCode() : 0);
            value = 31 * value + (this.fresh_address != null ? this.fresh_address.hashCode() : 0);
            value = 31 * value + (this.initial_amount != null ? this.initial_amount.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_type;
            public org.starcoin.types.AccountAddress fresh_address;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger initial_amount;

            public CreateAccountWithInitialAmountV2 build() {
                return new CreateAccountWithInitialAmountV2(
                        token_type,
                        fresh_address,
                        initial_amount
                );
            }
        }
    }

    /**
     * remove terminated proposal from proposer
     */
    public static final class DestroyTerminatedProposal extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_t;
        public final org.starcoin.types.TypeTag action_t;
        public final org.starcoin.types.AccountAddress proposer_address;
        public final @com.novi.serde.Unsigned Long proposal_id;

        public DestroyTerminatedProposal(org.starcoin.types.TypeTag token_t, org.starcoin.types.TypeTag action_t, org.starcoin.types.AccountAddress proposer_address, @com.novi.serde.Unsigned Long proposal_id) {
            java.util.Objects.requireNonNull(token_t, "token_t must not be null");
            java.util.Objects.requireNonNull(action_t, "action_t must not be null");
            java.util.Objects.requireNonNull(proposer_address, "proposer_address must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            this.token_t = token_t;
            this.action_t = action_t;
            this.proposer_address = proposer_address;
            this.proposal_id = proposal_id;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            DestroyTerminatedProposal other = (DestroyTerminatedProposal) obj;
            if (!java.util.Objects.equals(this.token_t, other.token_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.action_t, other.action_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposer_address, other.proposer_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_t != null ? this.token_t.hashCode() : 0);
            value = 31 * value + (this.action_t != null ? this.action_t.hashCode() : 0);
            value = 31 * value + (this.proposer_address != null ? this.proposer_address.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_t;
            public org.starcoin.types.TypeTag action_t;
            public org.starcoin.types.AccountAddress proposer_address;
            public @com.novi.serde.Unsigned Long proposal_id;

            public DestroyTerminatedProposal build() {
                return new DestroyTerminatedProposal(
                        token_t,
                        action_t,
                        proposer_address,
                        proposal_id
                );
            }
        }
    }

    /**
     *
     */
    public static final class EmptyScript extends ScriptFunctionCall {
        public EmptyScript() {
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            EmptyScript other = (EmptyScript) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public EmptyScript build() {
                return new EmptyScript(
                );
            }
        }
    }

    /**
     * Once the proposal is agreed, anyone can call the method to make the proposal happen.
     */
    public static final class Execute extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_t;
        public final org.starcoin.types.AccountAddress proposer_address;
        public final @com.novi.serde.Unsigned Long proposal_id;

        public Execute(org.starcoin.types.TypeTag token_t, org.starcoin.types.AccountAddress proposer_address, @com.novi.serde.Unsigned Long proposal_id) {
            java.util.Objects.requireNonNull(token_t, "token_t must not be null");
            java.util.Objects.requireNonNull(proposer_address, "proposer_address must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            this.token_t = token_t;
            this.proposer_address = proposer_address;
            this.proposal_id = proposal_id;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Execute other = (Execute) obj;
            if (!java.util.Objects.equals(this.token_t, other.token_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposer_address, other.proposer_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_t != null ? this.token_t.hashCode() : 0);
            value = 31 * value + (this.proposer_address != null ? this.proposer_address.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_t;
            public org.starcoin.types.AccountAddress proposer_address;
            public @com.novi.serde.Unsigned Long proposal_id;

            public Execute build() {
                return new Execute(
                        token_t,
                        proposer_address,
                        proposal_id
                );
            }
        }
    }

    /**
     *
     */
    public static final class ExecuteOnChainConfigProposal extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag config_t;
        public final @com.novi.serde.Unsigned Long proposal_id;

        public ExecuteOnChainConfigProposal(org.starcoin.types.TypeTag config_t, @com.novi.serde.Unsigned Long proposal_id) {
            java.util.Objects.requireNonNull(config_t, "config_t must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            this.config_t = config_t;
            this.proposal_id = proposal_id;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ExecuteOnChainConfigProposal other = (ExecuteOnChainConfigProposal) obj;
            if (!java.util.Objects.equals(this.config_t, other.config_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.config_t != null ? this.config_t.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag config_t;
            public @com.novi.serde.Unsigned Long proposal_id;

            public ExecuteOnChainConfigProposal build() {
                return new ExecuteOnChainConfigProposal(
                        config_t,
                        proposal_id
                );
            }
        }
    }

    /**
     *
     */
    public static final class ExecuteWithdrawProposal extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_t;
        public final org.starcoin.types.AccountAddress proposer_address;
        public final @com.novi.serde.Unsigned Long proposal_id;

        public ExecuteWithdrawProposal(org.starcoin.types.TypeTag token_t, org.starcoin.types.AccountAddress proposer_address, @com.novi.serde.Unsigned Long proposal_id) {
            java.util.Objects.requireNonNull(token_t, "token_t must not be null");
            java.util.Objects.requireNonNull(proposer_address, "proposer_address must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            this.token_t = token_t;
            this.proposer_address = proposer_address;
            this.proposal_id = proposal_id;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ExecuteWithdrawProposal other = (ExecuteWithdrawProposal) obj;
            if (!java.util.Objects.equals(this.token_t, other.token_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposer_address, other.proposer_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_t != null ? this.token_t.hashCode() : 0);
            value = 31 * value + (this.proposer_address != null ? this.proposer_address.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_t;
            public org.starcoin.types.AccountAddress proposer_address;
            public @com.novi.serde.Unsigned Long proposal_id;

            public ExecuteWithdrawProposal build() {
                return new ExecuteWithdrawProposal(
                        token_t,
                        proposer_address,
                        proposal_id
                );
            }
        }
    }

    /**
     *
     */
    public static final class Initialize extends ScriptFunctionCall {
        public final @com.novi.serde.Unsigned Long stdlib_version;
        public final @com.novi.serde.Unsigned Long reward_delay;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger pre_mine_stc_amount;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger time_mint_stc_amount;
        public final @com.novi.serde.Unsigned Long time_mint_stc_period;
        public final com.novi.serde.Bytes parent_hash;
        public final com.novi.serde.Bytes association_auth_key;
        public final com.novi.serde.Bytes genesis_auth_key;
        public final @com.novi.serde.Unsigned Byte chain_id;
        public final @com.novi.serde.Unsigned Long genesis_timestamp;
        public final @com.novi.serde.Unsigned Long uncle_rate_target;
        public final @com.novi.serde.Unsigned Long epoch_block_count;
        public final @com.novi.serde.Unsigned Long base_block_time_target;
        public final @com.novi.serde.Unsigned Long base_block_difficulty_window;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block;
        public final @com.novi.serde.Unsigned Long base_reward_per_uncle_percent;
        public final @com.novi.serde.Unsigned Long min_block_time_target;
        public final @com.novi.serde.Unsigned Long max_block_time_target;
        public final @com.novi.serde.Unsigned Long base_max_uncles_per_block;
        public final @com.novi.serde.Unsigned Long base_block_gas_limit;
        public final @com.novi.serde.Unsigned Byte strategy;
        public final Boolean script_allowed;
        public final Boolean module_publishing_allowed;
        public final com.novi.serde.Bytes instruction_schedule;
        public final com.novi.serde.Bytes native_schedule;
        public final @com.novi.serde.Unsigned Long global_memory_per_byte_cost;
        public final @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost;
        public final @com.novi.serde.Unsigned Long min_transaction_gas_units;
        public final @com.novi.serde.Unsigned Long large_transaction_cutoff;
        public final @com.novi.serde.Unsigned Long instrinsic_gas_per_byte;
        public final @com.novi.serde.Unsigned Long maximum_number_of_gas_units;
        public final @com.novi.serde.Unsigned Long min_price_per_gas_unit;
        public final @com.novi.serde.Unsigned Long max_price_per_gas_unit;
        public final @com.novi.serde.Unsigned Long max_transaction_size_in_bytes;
        public final @com.novi.serde.Unsigned Long gas_unit_scaling_factor;
        public final @com.novi.serde.Unsigned Long default_account_size;
        public final @com.novi.serde.Unsigned Long voting_delay;
        public final @com.novi.serde.Unsigned Long voting_period;
        public final @com.novi.serde.Unsigned Byte voting_quorum_rate;
        public final @com.novi.serde.Unsigned Long min_action_delay;
        public final @com.novi.serde.Unsigned Long transaction_timeout;

        public Initialize(@com.novi.serde.Unsigned Long stdlib_version, @com.novi.serde.Unsigned Long reward_delay, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger pre_mine_stc_amount, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger time_mint_stc_amount, @com.novi.serde.Unsigned Long time_mint_stc_period, com.novi.serde.Bytes parent_hash, com.novi.serde.Bytes association_auth_key, com.novi.serde.Bytes genesis_auth_key, @com.novi.serde.Unsigned Byte chain_id, @com.novi.serde.Unsigned Long genesis_timestamp, @com.novi.serde.Unsigned Long uncle_rate_target, @com.novi.serde.Unsigned Long epoch_block_count, @com.novi.serde.Unsigned Long base_block_time_target, @com.novi.serde.Unsigned Long base_block_difficulty_window, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block, @com.novi.serde.Unsigned Long base_reward_per_uncle_percent, @com.novi.serde.Unsigned Long min_block_time_target, @com.novi.serde.Unsigned Long max_block_time_target, @com.novi.serde.Unsigned Long base_max_uncles_per_block, @com.novi.serde.Unsigned Long base_block_gas_limit, @com.novi.serde.Unsigned Byte strategy, Boolean script_allowed, Boolean module_publishing_allowed, com.novi.serde.Bytes instruction_schedule, com.novi.serde.Bytes native_schedule, @com.novi.serde.Unsigned Long global_memory_per_byte_cost, @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost, @com.novi.serde.Unsigned Long min_transaction_gas_units, @com.novi.serde.Unsigned Long large_transaction_cutoff, @com.novi.serde.Unsigned Long instrinsic_gas_per_byte, @com.novi.serde.Unsigned Long maximum_number_of_gas_units, @com.novi.serde.Unsigned Long min_price_per_gas_unit, @com.novi.serde.Unsigned Long max_price_per_gas_unit, @com.novi.serde.Unsigned Long max_transaction_size_in_bytes, @com.novi.serde.Unsigned Long gas_unit_scaling_factor, @com.novi.serde.Unsigned Long default_account_size, @com.novi.serde.Unsigned Long voting_delay, @com.novi.serde.Unsigned Long voting_period, @com.novi.serde.Unsigned Byte voting_quorum_rate, @com.novi.serde.Unsigned Long min_action_delay, @com.novi.serde.Unsigned Long transaction_timeout) {
            java.util.Objects.requireNonNull(stdlib_version, "stdlib_version must not be null");
            java.util.Objects.requireNonNull(reward_delay, "reward_delay must not be null");
            java.util.Objects.requireNonNull(pre_mine_stc_amount, "pre_mine_stc_amount must not be null");
            java.util.Objects.requireNonNull(time_mint_stc_amount, "time_mint_stc_amount must not be null");
            java.util.Objects.requireNonNull(time_mint_stc_period, "time_mint_stc_period must not be null");
            java.util.Objects.requireNonNull(parent_hash, "parent_hash must not be null");
            java.util.Objects.requireNonNull(association_auth_key, "association_auth_key must not be null");
            java.util.Objects.requireNonNull(genesis_auth_key, "genesis_auth_key must not be null");
            java.util.Objects.requireNonNull(chain_id, "chain_id must not be null");
            java.util.Objects.requireNonNull(genesis_timestamp, "genesis_timestamp must not be null");
            java.util.Objects.requireNonNull(uncle_rate_target, "uncle_rate_target must not be null");
            java.util.Objects.requireNonNull(epoch_block_count, "epoch_block_count must not be null");
            java.util.Objects.requireNonNull(base_block_time_target, "base_block_time_target must not be null");
            java.util.Objects.requireNonNull(base_block_difficulty_window, "base_block_difficulty_window must not be null");
            java.util.Objects.requireNonNull(base_reward_per_block, "base_reward_per_block must not be null");
            java.util.Objects.requireNonNull(base_reward_per_uncle_percent, "base_reward_per_uncle_percent must not be null");
            java.util.Objects.requireNonNull(min_block_time_target, "min_block_time_target must not be null");
            java.util.Objects.requireNonNull(max_block_time_target, "max_block_time_target must not be null");
            java.util.Objects.requireNonNull(base_max_uncles_per_block, "base_max_uncles_per_block must not be null");
            java.util.Objects.requireNonNull(base_block_gas_limit, "base_block_gas_limit must not be null");
            java.util.Objects.requireNonNull(strategy, "strategy must not be null");
            java.util.Objects.requireNonNull(script_allowed, "script_allowed must not be null");
            java.util.Objects.requireNonNull(module_publishing_allowed, "module_publishing_allowed must not be null");
            java.util.Objects.requireNonNull(instruction_schedule, "instruction_schedule must not be null");
            java.util.Objects.requireNonNull(native_schedule, "native_schedule must not be null");
            java.util.Objects.requireNonNull(global_memory_per_byte_cost, "global_memory_per_byte_cost must not be null");
            java.util.Objects.requireNonNull(global_memory_per_byte_write_cost, "global_memory_per_byte_write_cost must not be null");
            java.util.Objects.requireNonNull(min_transaction_gas_units, "min_transaction_gas_units must not be null");
            java.util.Objects.requireNonNull(large_transaction_cutoff, "large_transaction_cutoff must not be null");
            java.util.Objects.requireNonNull(instrinsic_gas_per_byte, "instrinsic_gas_per_byte must not be null");
            java.util.Objects.requireNonNull(maximum_number_of_gas_units, "maximum_number_of_gas_units must not be null");
            java.util.Objects.requireNonNull(min_price_per_gas_unit, "min_price_per_gas_unit must not be null");
            java.util.Objects.requireNonNull(max_price_per_gas_unit, "max_price_per_gas_unit must not be null");
            java.util.Objects.requireNonNull(max_transaction_size_in_bytes, "max_transaction_size_in_bytes must not be null");
            java.util.Objects.requireNonNull(gas_unit_scaling_factor, "gas_unit_scaling_factor must not be null");
            java.util.Objects.requireNonNull(default_account_size, "default_account_size must not be null");
            java.util.Objects.requireNonNull(voting_delay, "voting_delay must not be null");
            java.util.Objects.requireNonNull(voting_period, "voting_period must not be null");
            java.util.Objects.requireNonNull(voting_quorum_rate, "voting_quorum_rate must not be null");
            java.util.Objects.requireNonNull(min_action_delay, "min_action_delay must not be null");
            java.util.Objects.requireNonNull(transaction_timeout, "transaction_timeout must not be null");
            this.stdlib_version = stdlib_version;
            this.reward_delay = reward_delay;
            this.pre_mine_stc_amount = pre_mine_stc_amount;
            this.time_mint_stc_amount = time_mint_stc_amount;
            this.time_mint_stc_period = time_mint_stc_period;
            this.parent_hash = parent_hash;
            this.association_auth_key = association_auth_key;
            this.genesis_auth_key = genesis_auth_key;
            this.chain_id = chain_id;
            this.genesis_timestamp = genesis_timestamp;
            this.uncle_rate_target = uncle_rate_target;
            this.epoch_block_count = epoch_block_count;
            this.base_block_time_target = base_block_time_target;
            this.base_block_difficulty_window = base_block_difficulty_window;
            this.base_reward_per_block = base_reward_per_block;
            this.base_reward_per_uncle_percent = base_reward_per_uncle_percent;
            this.min_block_time_target = min_block_time_target;
            this.max_block_time_target = max_block_time_target;
            this.base_max_uncles_per_block = base_max_uncles_per_block;
            this.base_block_gas_limit = base_block_gas_limit;
            this.strategy = strategy;
            this.script_allowed = script_allowed;
            this.module_publishing_allowed = module_publishing_allowed;
            this.instruction_schedule = instruction_schedule;
            this.native_schedule = native_schedule;
            this.global_memory_per_byte_cost = global_memory_per_byte_cost;
            this.global_memory_per_byte_write_cost = global_memory_per_byte_write_cost;
            this.min_transaction_gas_units = min_transaction_gas_units;
            this.large_transaction_cutoff = large_transaction_cutoff;
            this.instrinsic_gas_per_byte = instrinsic_gas_per_byte;
            this.maximum_number_of_gas_units = maximum_number_of_gas_units;
            this.min_price_per_gas_unit = min_price_per_gas_unit;
            this.max_price_per_gas_unit = max_price_per_gas_unit;
            this.max_transaction_size_in_bytes = max_transaction_size_in_bytes;
            this.gas_unit_scaling_factor = gas_unit_scaling_factor;
            this.default_account_size = default_account_size;
            this.voting_delay = voting_delay;
            this.voting_period = voting_period;
            this.voting_quorum_rate = voting_quorum_rate;
            this.min_action_delay = min_action_delay;
            this.transaction_timeout = transaction_timeout;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Initialize other = (Initialize) obj;
            if (!java.util.Objects.equals(this.stdlib_version, other.stdlib_version)) {
                return false;
            }
            if (!java.util.Objects.equals(this.reward_delay, other.reward_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.pre_mine_stc_amount, other.pre_mine_stc_amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.time_mint_stc_amount, other.time_mint_stc_amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.time_mint_stc_period, other.time_mint_stc_period)) {
                return false;
            }
            if (!java.util.Objects.equals(this.parent_hash, other.parent_hash)) {
                return false;
            }
            if (!java.util.Objects.equals(this.association_auth_key, other.association_auth_key)) {
                return false;
            }
            if (!java.util.Objects.equals(this.genesis_auth_key, other.genesis_auth_key)) {
                return false;
            }
            if (!java.util.Objects.equals(this.chain_id, other.chain_id)) {
                return false;
            }
            if (!java.util.Objects.equals(this.genesis_timestamp, other.genesis_timestamp)) {
                return false;
            }
            if (!java.util.Objects.equals(this.uncle_rate_target, other.uncle_rate_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.epoch_block_count, other.epoch_block_count)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_time_target, other.base_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_difficulty_window, other.base_block_difficulty_window)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_reward_per_block, other.base_reward_per_block)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_reward_per_uncle_percent, other.base_reward_per_uncle_percent)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_block_time_target, other.min_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_block_time_target, other.max_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_max_uncles_per_block, other.base_max_uncles_per_block)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_gas_limit, other.base_block_gas_limit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.strategy, other.strategy)) {
                return false;
            }
            if (!java.util.Objects.equals(this.script_allowed, other.script_allowed)) {
                return false;
            }
            if (!java.util.Objects.equals(this.module_publishing_allowed, other.module_publishing_allowed)) {
                return false;
            }
            if (!java.util.Objects.equals(this.instruction_schedule, other.instruction_schedule)) {
                return false;
            }
            if (!java.util.Objects.equals(this.native_schedule, other.native_schedule)) {
                return false;
            }
            if (!java.util.Objects.equals(this.global_memory_per_byte_cost, other.global_memory_per_byte_cost)) {
                return false;
            }
            if (!java.util.Objects.equals(this.global_memory_per_byte_write_cost, other.global_memory_per_byte_write_cost)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_transaction_gas_units, other.min_transaction_gas_units)) {
                return false;
            }
            if (!java.util.Objects.equals(this.large_transaction_cutoff, other.large_transaction_cutoff)) {
                return false;
            }
            if (!java.util.Objects.equals(this.instrinsic_gas_per_byte, other.instrinsic_gas_per_byte)) {
                return false;
            }
            if (!java.util.Objects.equals(this.maximum_number_of_gas_units, other.maximum_number_of_gas_units)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_price_per_gas_unit, other.min_price_per_gas_unit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_price_per_gas_unit, other.max_price_per_gas_unit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_transaction_size_in_bytes, other.max_transaction_size_in_bytes)) {
                return false;
            }
            if (!java.util.Objects.equals(this.gas_unit_scaling_factor, other.gas_unit_scaling_factor)) {
                return false;
            }
            if (!java.util.Objects.equals(this.default_account_size, other.default_account_size)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_delay, other.voting_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_period, other.voting_period)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_quorum_rate, other.voting_quorum_rate)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_action_delay, other.min_action_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.transaction_timeout, other.transaction_timeout)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.stdlib_version != null ? this.stdlib_version.hashCode() : 0);
            value = 31 * value + (this.reward_delay != null ? this.reward_delay.hashCode() : 0);
            value = 31 * value + (this.pre_mine_stc_amount != null ? this.pre_mine_stc_amount.hashCode() : 0);
            value = 31 * value + (this.time_mint_stc_amount != null ? this.time_mint_stc_amount.hashCode() : 0);
            value = 31 * value + (this.time_mint_stc_period != null ? this.time_mint_stc_period.hashCode() : 0);
            value = 31 * value + (this.parent_hash != null ? this.parent_hash.hashCode() : 0);
            value = 31 * value + (this.association_auth_key != null ? this.association_auth_key.hashCode() : 0);
            value = 31 * value + (this.genesis_auth_key != null ? this.genesis_auth_key.hashCode() : 0);
            value = 31 * value + (this.chain_id != null ? this.chain_id.hashCode() : 0);
            value = 31 * value + (this.genesis_timestamp != null ? this.genesis_timestamp.hashCode() : 0);
            value = 31 * value + (this.uncle_rate_target != null ? this.uncle_rate_target.hashCode() : 0);
            value = 31 * value + (this.epoch_block_count != null ? this.epoch_block_count.hashCode() : 0);
            value = 31 * value + (this.base_block_time_target != null ? this.base_block_time_target.hashCode() : 0);
            value = 31 * value + (this.base_block_difficulty_window != null ? this.base_block_difficulty_window.hashCode() : 0);
            value = 31 * value + (this.base_reward_per_block != null ? this.base_reward_per_block.hashCode() : 0);
            value = 31 * value + (this.base_reward_per_uncle_percent != null ? this.base_reward_per_uncle_percent.hashCode() : 0);
            value = 31 * value + (this.min_block_time_target != null ? this.min_block_time_target.hashCode() : 0);
            value = 31 * value + (this.max_block_time_target != null ? this.max_block_time_target.hashCode() : 0);
            value = 31 * value + (this.base_max_uncles_per_block != null ? this.base_max_uncles_per_block.hashCode() : 0);
            value = 31 * value + (this.base_block_gas_limit != null ? this.base_block_gas_limit.hashCode() : 0);
            value = 31 * value + (this.strategy != null ? this.strategy.hashCode() : 0);
            value = 31 * value + (this.script_allowed != null ? this.script_allowed.hashCode() : 0);
            value = 31 * value + (this.module_publishing_allowed != null ? this.module_publishing_allowed.hashCode() : 0);
            value = 31 * value + (this.instruction_schedule != null ? this.instruction_schedule.hashCode() : 0);
            value = 31 * value + (this.native_schedule != null ? this.native_schedule.hashCode() : 0);
            value = 31 * value + (this.global_memory_per_byte_cost != null ? this.global_memory_per_byte_cost.hashCode() : 0);
            value = 31 * value + (this.global_memory_per_byte_write_cost != null ? this.global_memory_per_byte_write_cost.hashCode() : 0);
            value = 31 * value + (this.min_transaction_gas_units != null ? this.min_transaction_gas_units.hashCode() : 0);
            value = 31 * value + (this.large_transaction_cutoff != null ? this.large_transaction_cutoff.hashCode() : 0);
            value = 31 * value + (this.instrinsic_gas_per_byte != null ? this.instrinsic_gas_per_byte.hashCode() : 0);
            value = 31 * value + (this.maximum_number_of_gas_units != null ? this.maximum_number_of_gas_units.hashCode() : 0);
            value = 31 * value + (this.min_price_per_gas_unit != null ? this.min_price_per_gas_unit.hashCode() : 0);
            value = 31 * value + (this.max_price_per_gas_unit != null ? this.max_price_per_gas_unit.hashCode() : 0);
            value = 31 * value + (this.max_transaction_size_in_bytes != null ? this.max_transaction_size_in_bytes.hashCode() : 0);
            value = 31 * value + (this.gas_unit_scaling_factor != null ? this.gas_unit_scaling_factor.hashCode() : 0);
            value = 31 * value + (this.default_account_size != null ? this.default_account_size.hashCode() : 0);
            value = 31 * value + (this.voting_delay != null ? this.voting_delay.hashCode() : 0);
            value = 31 * value + (this.voting_period != null ? this.voting_period.hashCode() : 0);
            value = 31 * value + (this.voting_quorum_rate != null ? this.voting_quorum_rate.hashCode() : 0);
            value = 31 * value + (this.min_action_delay != null ? this.min_action_delay.hashCode() : 0);
            value = 31 * value + (this.transaction_timeout != null ? this.transaction_timeout.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long stdlib_version;
            public @com.novi.serde.Unsigned Long reward_delay;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger pre_mine_stc_amount;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger time_mint_stc_amount;
            public @com.novi.serde.Unsigned Long time_mint_stc_period;
            public com.novi.serde.Bytes parent_hash;
            public com.novi.serde.Bytes association_auth_key;
            public com.novi.serde.Bytes genesis_auth_key;
            public @com.novi.serde.Unsigned Byte chain_id;
            public @com.novi.serde.Unsigned Long genesis_timestamp;
            public @com.novi.serde.Unsigned Long uncle_rate_target;
            public @com.novi.serde.Unsigned Long epoch_block_count;
            public @com.novi.serde.Unsigned Long base_block_time_target;
            public @com.novi.serde.Unsigned Long base_block_difficulty_window;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block;
            public @com.novi.serde.Unsigned Long base_reward_per_uncle_percent;
            public @com.novi.serde.Unsigned Long min_block_time_target;
            public @com.novi.serde.Unsigned Long max_block_time_target;
            public @com.novi.serde.Unsigned Long base_max_uncles_per_block;
            public @com.novi.serde.Unsigned Long base_block_gas_limit;
            public @com.novi.serde.Unsigned Byte strategy;
            public Boolean script_allowed;
            public Boolean module_publishing_allowed;
            public com.novi.serde.Bytes instruction_schedule;
            public com.novi.serde.Bytes native_schedule;
            public @com.novi.serde.Unsigned Long global_memory_per_byte_cost;
            public @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost;
            public @com.novi.serde.Unsigned Long min_transaction_gas_units;
            public @com.novi.serde.Unsigned Long large_transaction_cutoff;
            public @com.novi.serde.Unsigned Long instrinsic_gas_per_byte;
            public @com.novi.serde.Unsigned Long maximum_number_of_gas_units;
            public @com.novi.serde.Unsigned Long min_price_per_gas_unit;
            public @com.novi.serde.Unsigned Long max_price_per_gas_unit;
            public @com.novi.serde.Unsigned Long max_transaction_size_in_bytes;
            public @com.novi.serde.Unsigned Long gas_unit_scaling_factor;
            public @com.novi.serde.Unsigned Long default_account_size;
            public @com.novi.serde.Unsigned Long voting_delay;
            public @com.novi.serde.Unsigned Long voting_period;
            public @com.novi.serde.Unsigned Byte voting_quorum_rate;
            public @com.novi.serde.Unsigned Long min_action_delay;
            public @com.novi.serde.Unsigned Long transaction_timeout;

            public Initialize build() {
                return new Initialize(
                        stdlib_version,
                        reward_delay,
                        pre_mine_stc_amount,
                        time_mint_stc_amount,
                        time_mint_stc_period,
                        parent_hash,
                        association_auth_key,
                        genesis_auth_key,
                        chain_id,
                        genesis_timestamp,
                        uncle_rate_target,
                        epoch_block_count,
                        base_block_time_target,
                        base_block_difficulty_window,
                        base_reward_per_block,
                        base_reward_per_uncle_percent,
                        min_block_time_target,
                        max_block_time_target,
                        base_max_uncles_per_block,
                        base_block_gas_limit,
                        strategy,
                        script_allowed,
                        module_publishing_allowed,
                        instruction_schedule,
                        native_schedule,
                        global_memory_per_byte_cost,
                        global_memory_per_byte_write_cost,
                        min_transaction_gas_units,
                        large_transaction_cutoff,
                        instrinsic_gas_per_byte,
                        maximum_number_of_gas_units,
                        min_price_per_gas_unit,
                        max_price_per_gas_unit,
                        max_transaction_size_in_bytes,
                        gas_unit_scaling_factor,
                        default_account_size,
                        voting_delay,
                        voting_period,
                        voting_quorum_rate,
                        min_action_delay,
                        transaction_timeout
                );
            }
        }
    }

    /**
     *
     */
    public static final class InitializeV2 extends ScriptFunctionCall {
        public final @com.novi.serde.Unsigned Long stdlib_version;
        public final @com.novi.serde.Unsigned Long reward_delay;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger total_stc_amount;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger pre_mine_stc_amount;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger time_mint_stc_amount;
        public final @com.novi.serde.Unsigned Long time_mint_stc_period;
        public final com.novi.serde.Bytes parent_hash;
        public final com.novi.serde.Bytes association_auth_key;
        public final com.novi.serde.Bytes genesis_auth_key;
        public final @com.novi.serde.Unsigned Byte chain_id;
        public final @com.novi.serde.Unsigned Long genesis_timestamp;
        public final @com.novi.serde.Unsigned Long uncle_rate_target;
        public final @com.novi.serde.Unsigned Long epoch_block_count;
        public final @com.novi.serde.Unsigned Long base_block_time_target;
        public final @com.novi.serde.Unsigned Long base_block_difficulty_window;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block;
        public final @com.novi.serde.Unsigned Long base_reward_per_uncle_percent;
        public final @com.novi.serde.Unsigned Long min_block_time_target;
        public final @com.novi.serde.Unsigned Long max_block_time_target;
        public final @com.novi.serde.Unsigned Long base_max_uncles_per_block;
        public final @com.novi.serde.Unsigned Long base_block_gas_limit;
        public final @com.novi.serde.Unsigned Byte strategy;
        public final Boolean script_allowed;
        public final Boolean module_publishing_allowed;
        public final com.novi.serde.Bytes instruction_schedule;
        public final com.novi.serde.Bytes native_schedule;
        public final @com.novi.serde.Unsigned Long global_memory_per_byte_cost;
        public final @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost;
        public final @com.novi.serde.Unsigned Long min_transaction_gas_units;
        public final @com.novi.serde.Unsigned Long large_transaction_cutoff;
        public final @com.novi.serde.Unsigned Long instrinsic_gas_per_byte;
        public final @com.novi.serde.Unsigned Long maximum_number_of_gas_units;
        public final @com.novi.serde.Unsigned Long min_price_per_gas_unit;
        public final @com.novi.serde.Unsigned Long max_price_per_gas_unit;
        public final @com.novi.serde.Unsigned Long max_transaction_size_in_bytes;
        public final @com.novi.serde.Unsigned Long gas_unit_scaling_factor;
        public final @com.novi.serde.Unsigned Long default_account_size;
        public final @com.novi.serde.Unsigned Long voting_delay;
        public final @com.novi.serde.Unsigned Long voting_period;
        public final @com.novi.serde.Unsigned Byte voting_quorum_rate;
        public final @com.novi.serde.Unsigned Long min_action_delay;
        public final @com.novi.serde.Unsigned Long transaction_timeout;

        public InitializeV2(@com.novi.serde.Unsigned Long stdlib_version, @com.novi.serde.Unsigned Long reward_delay, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger total_stc_amount, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger pre_mine_stc_amount, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger time_mint_stc_amount, @com.novi.serde.Unsigned Long time_mint_stc_period, com.novi.serde.Bytes parent_hash, com.novi.serde.Bytes association_auth_key, com.novi.serde.Bytes genesis_auth_key, @com.novi.serde.Unsigned Byte chain_id, @com.novi.serde.Unsigned Long genesis_timestamp, @com.novi.serde.Unsigned Long uncle_rate_target, @com.novi.serde.Unsigned Long epoch_block_count, @com.novi.serde.Unsigned Long base_block_time_target, @com.novi.serde.Unsigned Long base_block_difficulty_window, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block, @com.novi.serde.Unsigned Long base_reward_per_uncle_percent, @com.novi.serde.Unsigned Long min_block_time_target, @com.novi.serde.Unsigned Long max_block_time_target, @com.novi.serde.Unsigned Long base_max_uncles_per_block, @com.novi.serde.Unsigned Long base_block_gas_limit, @com.novi.serde.Unsigned Byte strategy, Boolean script_allowed, Boolean module_publishing_allowed, com.novi.serde.Bytes instruction_schedule, com.novi.serde.Bytes native_schedule, @com.novi.serde.Unsigned Long global_memory_per_byte_cost, @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost, @com.novi.serde.Unsigned Long min_transaction_gas_units, @com.novi.serde.Unsigned Long large_transaction_cutoff, @com.novi.serde.Unsigned Long instrinsic_gas_per_byte, @com.novi.serde.Unsigned Long maximum_number_of_gas_units, @com.novi.serde.Unsigned Long min_price_per_gas_unit, @com.novi.serde.Unsigned Long max_price_per_gas_unit, @com.novi.serde.Unsigned Long max_transaction_size_in_bytes, @com.novi.serde.Unsigned Long gas_unit_scaling_factor, @com.novi.serde.Unsigned Long default_account_size, @com.novi.serde.Unsigned Long voting_delay, @com.novi.serde.Unsigned Long voting_period, @com.novi.serde.Unsigned Byte voting_quorum_rate, @com.novi.serde.Unsigned Long min_action_delay, @com.novi.serde.Unsigned Long transaction_timeout) {
            java.util.Objects.requireNonNull(stdlib_version, "stdlib_version must not be null");
            java.util.Objects.requireNonNull(reward_delay, "reward_delay must not be null");
            java.util.Objects.requireNonNull(total_stc_amount, "total_stc_amount must not be null");
            java.util.Objects.requireNonNull(pre_mine_stc_amount, "pre_mine_stc_amount must not be null");
            java.util.Objects.requireNonNull(time_mint_stc_amount, "time_mint_stc_amount must not be null");
            java.util.Objects.requireNonNull(time_mint_stc_period, "time_mint_stc_period must not be null");
            java.util.Objects.requireNonNull(parent_hash, "parent_hash must not be null");
            java.util.Objects.requireNonNull(association_auth_key, "association_auth_key must not be null");
            java.util.Objects.requireNonNull(genesis_auth_key, "genesis_auth_key must not be null");
            java.util.Objects.requireNonNull(chain_id, "chain_id must not be null");
            java.util.Objects.requireNonNull(genesis_timestamp, "genesis_timestamp must not be null");
            java.util.Objects.requireNonNull(uncle_rate_target, "uncle_rate_target must not be null");
            java.util.Objects.requireNonNull(epoch_block_count, "epoch_block_count must not be null");
            java.util.Objects.requireNonNull(base_block_time_target, "base_block_time_target must not be null");
            java.util.Objects.requireNonNull(base_block_difficulty_window, "base_block_difficulty_window must not be null");
            java.util.Objects.requireNonNull(base_reward_per_block, "base_reward_per_block must not be null");
            java.util.Objects.requireNonNull(base_reward_per_uncle_percent, "base_reward_per_uncle_percent must not be null");
            java.util.Objects.requireNonNull(min_block_time_target, "min_block_time_target must not be null");
            java.util.Objects.requireNonNull(max_block_time_target, "max_block_time_target must not be null");
            java.util.Objects.requireNonNull(base_max_uncles_per_block, "base_max_uncles_per_block must not be null");
            java.util.Objects.requireNonNull(base_block_gas_limit, "base_block_gas_limit must not be null");
            java.util.Objects.requireNonNull(strategy, "strategy must not be null");
            java.util.Objects.requireNonNull(script_allowed, "script_allowed must not be null");
            java.util.Objects.requireNonNull(module_publishing_allowed, "module_publishing_allowed must not be null");
            java.util.Objects.requireNonNull(instruction_schedule, "instruction_schedule must not be null");
            java.util.Objects.requireNonNull(native_schedule, "native_schedule must not be null");
            java.util.Objects.requireNonNull(global_memory_per_byte_cost, "global_memory_per_byte_cost must not be null");
            java.util.Objects.requireNonNull(global_memory_per_byte_write_cost, "global_memory_per_byte_write_cost must not be null");
            java.util.Objects.requireNonNull(min_transaction_gas_units, "min_transaction_gas_units must not be null");
            java.util.Objects.requireNonNull(large_transaction_cutoff, "large_transaction_cutoff must not be null");
            java.util.Objects.requireNonNull(instrinsic_gas_per_byte, "instrinsic_gas_per_byte must not be null");
            java.util.Objects.requireNonNull(maximum_number_of_gas_units, "maximum_number_of_gas_units must not be null");
            java.util.Objects.requireNonNull(min_price_per_gas_unit, "min_price_per_gas_unit must not be null");
            java.util.Objects.requireNonNull(max_price_per_gas_unit, "max_price_per_gas_unit must not be null");
            java.util.Objects.requireNonNull(max_transaction_size_in_bytes, "max_transaction_size_in_bytes must not be null");
            java.util.Objects.requireNonNull(gas_unit_scaling_factor, "gas_unit_scaling_factor must not be null");
            java.util.Objects.requireNonNull(default_account_size, "default_account_size must not be null");
            java.util.Objects.requireNonNull(voting_delay, "voting_delay must not be null");
            java.util.Objects.requireNonNull(voting_period, "voting_period must not be null");
            java.util.Objects.requireNonNull(voting_quorum_rate, "voting_quorum_rate must not be null");
            java.util.Objects.requireNonNull(min_action_delay, "min_action_delay must not be null");
            java.util.Objects.requireNonNull(transaction_timeout, "transaction_timeout must not be null");
            this.stdlib_version = stdlib_version;
            this.reward_delay = reward_delay;
            this.total_stc_amount = total_stc_amount;
            this.pre_mine_stc_amount = pre_mine_stc_amount;
            this.time_mint_stc_amount = time_mint_stc_amount;
            this.time_mint_stc_period = time_mint_stc_period;
            this.parent_hash = parent_hash;
            this.association_auth_key = association_auth_key;
            this.genesis_auth_key = genesis_auth_key;
            this.chain_id = chain_id;
            this.genesis_timestamp = genesis_timestamp;
            this.uncle_rate_target = uncle_rate_target;
            this.epoch_block_count = epoch_block_count;
            this.base_block_time_target = base_block_time_target;
            this.base_block_difficulty_window = base_block_difficulty_window;
            this.base_reward_per_block = base_reward_per_block;
            this.base_reward_per_uncle_percent = base_reward_per_uncle_percent;
            this.min_block_time_target = min_block_time_target;
            this.max_block_time_target = max_block_time_target;
            this.base_max_uncles_per_block = base_max_uncles_per_block;
            this.base_block_gas_limit = base_block_gas_limit;
            this.strategy = strategy;
            this.script_allowed = script_allowed;
            this.module_publishing_allowed = module_publishing_allowed;
            this.instruction_schedule = instruction_schedule;
            this.native_schedule = native_schedule;
            this.global_memory_per_byte_cost = global_memory_per_byte_cost;
            this.global_memory_per_byte_write_cost = global_memory_per_byte_write_cost;
            this.min_transaction_gas_units = min_transaction_gas_units;
            this.large_transaction_cutoff = large_transaction_cutoff;
            this.instrinsic_gas_per_byte = instrinsic_gas_per_byte;
            this.maximum_number_of_gas_units = maximum_number_of_gas_units;
            this.min_price_per_gas_unit = min_price_per_gas_unit;
            this.max_price_per_gas_unit = max_price_per_gas_unit;
            this.max_transaction_size_in_bytes = max_transaction_size_in_bytes;
            this.gas_unit_scaling_factor = gas_unit_scaling_factor;
            this.default_account_size = default_account_size;
            this.voting_delay = voting_delay;
            this.voting_period = voting_period;
            this.voting_quorum_rate = voting_quorum_rate;
            this.min_action_delay = min_action_delay;
            this.transaction_timeout = transaction_timeout;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            InitializeV2 other = (InitializeV2) obj;
            if (!java.util.Objects.equals(this.stdlib_version, other.stdlib_version)) {
                return false;
            }
            if (!java.util.Objects.equals(this.reward_delay, other.reward_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.total_stc_amount, other.total_stc_amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.pre_mine_stc_amount, other.pre_mine_stc_amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.time_mint_stc_amount, other.time_mint_stc_amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.time_mint_stc_period, other.time_mint_stc_period)) {
                return false;
            }
            if (!java.util.Objects.equals(this.parent_hash, other.parent_hash)) {
                return false;
            }
            if (!java.util.Objects.equals(this.association_auth_key, other.association_auth_key)) {
                return false;
            }
            if (!java.util.Objects.equals(this.genesis_auth_key, other.genesis_auth_key)) {
                return false;
            }
            if (!java.util.Objects.equals(this.chain_id, other.chain_id)) {
                return false;
            }
            if (!java.util.Objects.equals(this.genesis_timestamp, other.genesis_timestamp)) {
                return false;
            }
            if (!java.util.Objects.equals(this.uncle_rate_target, other.uncle_rate_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.epoch_block_count, other.epoch_block_count)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_time_target, other.base_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_difficulty_window, other.base_block_difficulty_window)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_reward_per_block, other.base_reward_per_block)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_reward_per_uncle_percent, other.base_reward_per_uncle_percent)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_block_time_target, other.min_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_block_time_target, other.max_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_max_uncles_per_block, other.base_max_uncles_per_block)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_gas_limit, other.base_block_gas_limit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.strategy, other.strategy)) {
                return false;
            }
            if (!java.util.Objects.equals(this.script_allowed, other.script_allowed)) {
                return false;
            }
            if (!java.util.Objects.equals(this.module_publishing_allowed, other.module_publishing_allowed)) {
                return false;
            }
            if (!java.util.Objects.equals(this.instruction_schedule, other.instruction_schedule)) {
                return false;
            }
            if (!java.util.Objects.equals(this.native_schedule, other.native_schedule)) {
                return false;
            }
            if (!java.util.Objects.equals(this.global_memory_per_byte_cost, other.global_memory_per_byte_cost)) {
                return false;
            }
            if (!java.util.Objects.equals(this.global_memory_per_byte_write_cost, other.global_memory_per_byte_write_cost)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_transaction_gas_units, other.min_transaction_gas_units)) {
                return false;
            }
            if (!java.util.Objects.equals(this.large_transaction_cutoff, other.large_transaction_cutoff)) {
                return false;
            }
            if (!java.util.Objects.equals(this.instrinsic_gas_per_byte, other.instrinsic_gas_per_byte)) {
                return false;
            }
            if (!java.util.Objects.equals(this.maximum_number_of_gas_units, other.maximum_number_of_gas_units)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_price_per_gas_unit, other.min_price_per_gas_unit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_price_per_gas_unit, other.max_price_per_gas_unit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_transaction_size_in_bytes, other.max_transaction_size_in_bytes)) {
                return false;
            }
            if (!java.util.Objects.equals(this.gas_unit_scaling_factor, other.gas_unit_scaling_factor)) {
                return false;
            }
            if (!java.util.Objects.equals(this.default_account_size, other.default_account_size)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_delay, other.voting_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_period, other.voting_period)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_quorum_rate, other.voting_quorum_rate)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_action_delay, other.min_action_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.transaction_timeout, other.transaction_timeout)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.stdlib_version != null ? this.stdlib_version.hashCode() : 0);
            value = 31 * value + (this.reward_delay != null ? this.reward_delay.hashCode() : 0);
            value = 31 * value + (this.total_stc_amount != null ? this.total_stc_amount.hashCode() : 0);
            value = 31 * value + (this.pre_mine_stc_amount != null ? this.pre_mine_stc_amount.hashCode() : 0);
            value = 31 * value + (this.time_mint_stc_amount != null ? this.time_mint_stc_amount.hashCode() : 0);
            value = 31 * value + (this.time_mint_stc_period != null ? this.time_mint_stc_period.hashCode() : 0);
            value = 31 * value + (this.parent_hash != null ? this.parent_hash.hashCode() : 0);
            value = 31 * value + (this.association_auth_key != null ? this.association_auth_key.hashCode() : 0);
            value = 31 * value + (this.genesis_auth_key != null ? this.genesis_auth_key.hashCode() : 0);
            value = 31 * value + (this.chain_id != null ? this.chain_id.hashCode() : 0);
            value = 31 * value + (this.genesis_timestamp != null ? this.genesis_timestamp.hashCode() : 0);
            value = 31 * value + (this.uncle_rate_target != null ? this.uncle_rate_target.hashCode() : 0);
            value = 31 * value + (this.epoch_block_count != null ? this.epoch_block_count.hashCode() : 0);
            value = 31 * value + (this.base_block_time_target != null ? this.base_block_time_target.hashCode() : 0);
            value = 31 * value + (this.base_block_difficulty_window != null ? this.base_block_difficulty_window.hashCode() : 0);
            value = 31 * value + (this.base_reward_per_block != null ? this.base_reward_per_block.hashCode() : 0);
            value = 31 * value + (this.base_reward_per_uncle_percent != null ? this.base_reward_per_uncle_percent.hashCode() : 0);
            value = 31 * value + (this.min_block_time_target != null ? this.min_block_time_target.hashCode() : 0);
            value = 31 * value + (this.max_block_time_target != null ? this.max_block_time_target.hashCode() : 0);
            value = 31 * value + (this.base_max_uncles_per_block != null ? this.base_max_uncles_per_block.hashCode() : 0);
            value = 31 * value + (this.base_block_gas_limit != null ? this.base_block_gas_limit.hashCode() : 0);
            value = 31 * value + (this.strategy != null ? this.strategy.hashCode() : 0);
            value = 31 * value + (this.script_allowed != null ? this.script_allowed.hashCode() : 0);
            value = 31 * value + (this.module_publishing_allowed != null ? this.module_publishing_allowed.hashCode() : 0);
            value = 31 * value + (this.instruction_schedule != null ? this.instruction_schedule.hashCode() : 0);
            value = 31 * value + (this.native_schedule != null ? this.native_schedule.hashCode() : 0);
            value = 31 * value + (this.global_memory_per_byte_cost != null ? this.global_memory_per_byte_cost.hashCode() : 0);
            value = 31 * value + (this.global_memory_per_byte_write_cost != null ? this.global_memory_per_byte_write_cost.hashCode() : 0);
            value = 31 * value + (this.min_transaction_gas_units != null ? this.min_transaction_gas_units.hashCode() : 0);
            value = 31 * value + (this.large_transaction_cutoff != null ? this.large_transaction_cutoff.hashCode() : 0);
            value = 31 * value + (this.instrinsic_gas_per_byte != null ? this.instrinsic_gas_per_byte.hashCode() : 0);
            value = 31 * value + (this.maximum_number_of_gas_units != null ? this.maximum_number_of_gas_units.hashCode() : 0);
            value = 31 * value + (this.min_price_per_gas_unit != null ? this.min_price_per_gas_unit.hashCode() : 0);
            value = 31 * value + (this.max_price_per_gas_unit != null ? this.max_price_per_gas_unit.hashCode() : 0);
            value = 31 * value + (this.max_transaction_size_in_bytes != null ? this.max_transaction_size_in_bytes.hashCode() : 0);
            value = 31 * value + (this.gas_unit_scaling_factor != null ? this.gas_unit_scaling_factor.hashCode() : 0);
            value = 31 * value + (this.default_account_size != null ? this.default_account_size.hashCode() : 0);
            value = 31 * value + (this.voting_delay != null ? this.voting_delay.hashCode() : 0);
            value = 31 * value + (this.voting_period != null ? this.voting_period.hashCode() : 0);
            value = 31 * value + (this.voting_quorum_rate != null ? this.voting_quorum_rate.hashCode() : 0);
            value = 31 * value + (this.min_action_delay != null ? this.min_action_delay.hashCode() : 0);
            value = 31 * value + (this.transaction_timeout != null ? this.transaction_timeout.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long stdlib_version;
            public @com.novi.serde.Unsigned Long reward_delay;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger total_stc_amount;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger pre_mine_stc_amount;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger time_mint_stc_amount;
            public @com.novi.serde.Unsigned Long time_mint_stc_period;
            public com.novi.serde.Bytes parent_hash;
            public com.novi.serde.Bytes association_auth_key;
            public com.novi.serde.Bytes genesis_auth_key;
            public @com.novi.serde.Unsigned Byte chain_id;
            public @com.novi.serde.Unsigned Long genesis_timestamp;
            public @com.novi.serde.Unsigned Long uncle_rate_target;
            public @com.novi.serde.Unsigned Long epoch_block_count;
            public @com.novi.serde.Unsigned Long base_block_time_target;
            public @com.novi.serde.Unsigned Long base_block_difficulty_window;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block;
            public @com.novi.serde.Unsigned Long base_reward_per_uncle_percent;
            public @com.novi.serde.Unsigned Long min_block_time_target;
            public @com.novi.serde.Unsigned Long max_block_time_target;
            public @com.novi.serde.Unsigned Long base_max_uncles_per_block;
            public @com.novi.serde.Unsigned Long base_block_gas_limit;
            public @com.novi.serde.Unsigned Byte strategy;
            public Boolean script_allowed;
            public Boolean module_publishing_allowed;
            public com.novi.serde.Bytes instruction_schedule;
            public com.novi.serde.Bytes native_schedule;
            public @com.novi.serde.Unsigned Long global_memory_per_byte_cost;
            public @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost;
            public @com.novi.serde.Unsigned Long min_transaction_gas_units;
            public @com.novi.serde.Unsigned Long large_transaction_cutoff;
            public @com.novi.serde.Unsigned Long instrinsic_gas_per_byte;
            public @com.novi.serde.Unsigned Long maximum_number_of_gas_units;
            public @com.novi.serde.Unsigned Long min_price_per_gas_unit;
            public @com.novi.serde.Unsigned Long max_price_per_gas_unit;
            public @com.novi.serde.Unsigned Long max_transaction_size_in_bytes;
            public @com.novi.serde.Unsigned Long gas_unit_scaling_factor;
            public @com.novi.serde.Unsigned Long default_account_size;
            public @com.novi.serde.Unsigned Long voting_delay;
            public @com.novi.serde.Unsigned Long voting_period;
            public @com.novi.serde.Unsigned Byte voting_quorum_rate;
            public @com.novi.serde.Unsigned Long min_action_delay;
            public @com.novi.serde.Unsigned Long transaction_timeout;

            public InitializeV2 build() {
                return new InitializeV2(
                        stdlib_version,
                        reward_delay,
                        total_stc_amount,
                        pre_mine_stc_amount,
                        time_mint_stc_amount,
                        time_mint_stc_period,
                        parent_hash,
                        association_auth_key,
                        genesis_auth_key,
                        chain_id,
                        genesis_timestamp,
                        uncle_rate_target,
                        epoch_block_count,
                        base_block_time_target,
                        base_block_difficulty_window,
                        base_reward_per_block,
                        base_reward_per_uncle_percent,
                        min_block_time_target,
                        max_block_time_target,
                        base_max_uncles_per_block,
                        base_block_gas_limit,
                        strategy,
                        script_allowed,
                        module_publishing_allowed,
                        instruction_schedule,
                        native_schedule,
                        global_memory_per_byte_cost,
                        global_memory_per_byte_write_cost,
                        min_transaction_gas_units,
                        large_transaction_cutoff,
                        instrinsic_gas_per_byte,
                        maximum_number_of_gas_units,
                        min_price_per_gas_unit,
                        max_price_per_gas_unit,
                        max_transaction_size_in_bytes,
                        gas_unit_scaling_factor,
                        default_account_size,
                        voting_delay,
                        voting_period,
                        voting_quorum_rate,
                        min_action_delay,
                        transaction_timeout
                );
            }
        }
    }

    /**
     *
     */
    public static final class PeerToPeer extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_type;
        public final org.starcoin.types.AccountAddress payee;
        public final com.novi.serde.Bytes _payee_auth_key;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;

        public PeerToPeer(org.starcoin.types.TypeTag token_type, org.starcoin.types.AccountAddress payee, com.novi.serde.Bytes _payee_auth_key, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount) {
            java.util.Objects.requireNonNull(token_type, "token_type must not be null");
            java.util.Objects.requireNonNull(payee, "payee must not be null");
            java.util.Objects.requireNonNull(_payee_auth_key, "_payee_auth_key must not be null");
            java.util.Objects.requireNonNull(amount, "amount must not be null");
            this.token_type = token_type;
            this.payee = payee;
            this._payee_auth_key = _payee_auth_key;
            this.amount = amount;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PeerToPeer other = (PeerToPeer) obj;
            if (!java.util.Objects.equals(this.token_type, other.token_type)) {
                return false;
            }
            if (!java.util.Objects.equals(this.payee, other.payee)) {
                return false;
            }
            if (!java.util.Objects.equals(this._payee_auth_key, other._payee_auth_key)) {
                return false;
            }
            if (!java.util.Objects.equals(this.amount, other.amount)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_type != null ? this.token_type.hashCode() : 0);
            value = 31 * value + (this.payee != null ? this.payee.hashCode() : 0);
            value = 31 * value + (this._payee_auth_key != null ? this._payee_auth_key.hashCode() : 0);
            value = 31 * value + (this.amount != null ? this.amount.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_type;
            public org.starcoin.types.AccountAddress payee;
            public com.novi.serde.Bytes _payee_auth_key;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;

            public PeerToPeer build() {
                return new PeerToPeer(
                        token_type,
                        payee,
                        _payee_auth_key,
                        amount
                );
            }
        }
    }

    /**
     *
     */
    public static final class PeerToPeerBatch extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_type;
        public final com.novi.serde.Bytes _payeees;
        public final com.novi.serde.Bytes _payee_auth_keys;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger _amount;

        public PeerToPeerBatch(org.starcoin.types.TypeTag token_type, com.novi.serde.Bytes _payeees, com.novi.serde.Bytes _payee_auth_keys, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger _amount) {
            java.util.Objects.requireNonNull(token_type, "token_type must not be null");
            java.util.Objects.requireNonNull(_payeees, "_payeees must not be null");
            java.util.Objects.requireNonNull(_payee_auth_keys, "_payee_auth_keys must not be null");
            java.util.Objects.requireNonNull(_amount, "_amount must not be null");
            this.token_type = token_type;
            this._payeees = _payeees;
            this._payee_auth_keys = _payee_auth_keys;
            this._amount = _amount;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PeerToPeerBatch other = (PeerToPeerBatch) obj;
            if (!java.util.Objects.equals(this.token_type, other.token_type)) {
                return false;
            }
            if (!java.util.Objects.equals(this._payeees, other._payeees)) {
                return false;
            }
            if (!java.util.Objects.equals(this._payee_auth_keys, other._payee_auth_keys)) {
                return false;
            }
            if (!java.util.Objects.equals(this._amount, other._amount)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_type != null ? this.token_type.hashCode() : 0);
            value = 31 * value + (this._payeees != null ? this._payeees.hashCode() : 0);
            value = 31 * value + (this._payee_auth_keys != null ? this._payee_auth_keys.hashCode() : 0);
            value = 31 * value + (this._amount != null ? this._amount.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_type;
            public com.novi.serde.Bytes _payeees;
            public com.novi.serde.Bytes _payee_auth_keys;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger _amount;

            public PeerToPeerBatch build() {
                return new PeerToPeerBatch(
                        token_type,
                        _payeees,
                        _payee_auth_keys,
                        _amount
                );
            }
        }
    }

    /**
     *
     */
    public static final class PeerToPeerV2 extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_type;
        public final org.starcoin.types.AccountAddress payee;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;

        public PeerToPeerV2(org.starcoin.types.TypeTag token_type, org.starcoin.types.AccountAddress payee, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount) {
            java.util.Objects.requireNonNull(token_type, "token_type must not be null");
            java.util.Objects.requireNonNull(payee, "payee must not be null");
            java.util.Objects.requireNonNull(amount, "amount must not be null");
            this.token_type = token_type;
            this.payee = payee;
            this.amount = amount;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PeerToPeerV2 other = (PeerToPeerV2) obj;
            if (!java.util.Objects.equals(this.token_type, other.token_type)) {
                return false;
            }
            if (!java.util.Objects.equals(this.payee, other.payee)) {
                return false;
            }
            if (!java.util.Objects.equals(this.amount, other.amount)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_type != null ? this.token_type.hashCode() : 0);
            value = 31 * value + (this.payee != null ? this.payee.hashCode() : 0);
            value = 31 * value + (this.amount != null ? this.amount.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_type;
            public org.starcoin.types.AccountAddress payee;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;

            public PeerToPeerV2 build() {
                return new PeerToPeerV2(
                        token_type,
                        payee,
                        amount
                );
            }
        }
    }

    /**
     *
     */
    public static final class PeerToPeerWithMetadata extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_type;
        public final org.starcoin.types.AccountAddress payee;
        public final com.novi.serde.Bytes _payee_auth_key;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
        public final com.novi.serde.Bytes metadata;

        public PeerToPeerWithMetadata(org.starcoin.types.TypeTag token_type, org.starcoin.types.AccountAddress payee, com.novi.serde.Bytes _payee_auth_key, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount, com.novi.serde.Bytes metadata) {
            java.util.Objects.requireNonNull(token_type, "token_type must not be null");
            java.util.Objects.requireNonNull(payee, "payee must not be null");
            java.util.Objects.requireNonNull(_payee_auth_key, "_payee_auth_key must not be null");
            java.util.Objects.requireNonNull(amount, "amount must not be null");
            java.util.Objects.requireNonNull(metadata, "metadata must not be null");
            this.token_type = token_type;
            this.payee = payee;
            this._payee_auth_key = _payee_auth_key;
            this.amount = amount;
            this.metadata = metadata;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PeerToPeerWithMetadata other = (PeerToPeerWithMetadata) obj;
            if (!java.util.Objects.equals(this.token_type, other.token_type)) {
                return false;
            }
            if (!java.util.Objects.equals(this.payee, other.payee)) {
                return false;
            }
            if (!java.util.Objects.equals(this._payee_auth_key, other._payee_auth_key)) {
                return false;
            }
            if (!java.util.Objects.equals(this.amount, other.amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.metadata, other.metadata)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_type != null ? this.token_type.hashCode() : 0);
            value = 31 * value + (this.payee != null ? this.payee.hashCode() : 0);
            value = 31 * value + (this._payee_auth_key != null ? this._payee_auth_key.hashCode() : 0);
            value = 31 * value + (this.amount != null ? this.amount.hashCode() : 0);
            value = 31 * value + (this.metadata != null ? this.metadata.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_type;
            public org.starcoin.types.AccountAddress payee;
            public com.novi.serde.Bytes _payee_auth_key;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
            public com.novi.serde.Bytes metadata;

            public PeerToPeerWithMetadata build() {
                return new PeerToPeerWithMetadata(
                        token_type,
                        payee,
                        _payee_auth_key,
                        amount,
                        metadata
                );
            }
        }
    }

    /**
     *
     */
    public static final class PeerToPeerWithMetadataV2 extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_type;
        public final org.starcoin.types.AccountAddress payee;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
        public final com.novi.serde.Bytes metadata;

        public PeerToPeerWithMetadataV2(org.starcoin.types.TypeTag token_type, org.starcoin.types.AccountAddress payee, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount, com.novi.serde.Bytes metadata) {
            java.util.Objects.requireNonNull(token_type, "token_type must not be null");
            java.util.Objects.requireNonNull(payee, "payee must not be null");
            java.util.Objects.requireNonNull(amount, "amount must not be null");
            java.util.Objects.requireNonNull(metadata, "metadata must not be null");
            this.token_type = token_type;
            this.payee = payee;
            this.amount = amount;
            this.metadata = metadata;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            PeerToPeerWithMetadataV2 other = (PeerToPeerWithMetadataV2) obj;
            if (!java.util.Objects.equals(this.token_type, other.token_type)) {
                return false;
            }
            if (!java.util.Objects.equals(this.payee, other.payee)) {
                return false;
            }
            if (!java.util.Objects.equals(this.amount, other.amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.metadata, other.metadata)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_type != null ? this.token_type.hashCode() : 0);
            value = 31 * value + (this.payee != null ? this.payee.hashCode() : 0);
            value = 31 * value + (this.amount != null ? this.amount.hashCode() : 0);
            value = 31 * value + (this.metadata != null ? this.metadata.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_type;
            public org.starcoin.types.AccountAddress payee;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
            public com.novi.serde.Bytes metadata;

            public PeerToPeerWithMetadataV2 build() {
                return new PeerToPeerWithMetadataV2(
                        token_type,
                        payee,
                        amount,
                        metadata
                );
            }
        }
    }

    /**
     * Entrypoint for the proposal.
     */
    public static final class Propose extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_t;
        public final @com.novi.serde.Unsigned Long voting_delay;
        public final @com.novi.serde.Unsigned Long voting_period;
        public final @com.novi.serde.Unsigned Byte voting_quorum_rate;
        public final @com.novi.serde.Unsigned Long min_action_delay;
        public final @com.novi.serde.Unsigned Long exec_delay;

        public Propose(org.starcoin.types.TypeTag token_t, @com.novi.serde.Unsigned Long voting_delay, @com.novi.serde.Unsigned Long voting_period, @com.novi.serde.Unsigned Byte voting_quorum_rate, @com.novi.serde.Unsigned Long min_action_delay, @com.novi.serde.Unsigned Long exec_delay) {
            java.util.Objects.requireNonNull(token_t, "token_t must not be null");
            java.util.Objects.requireNonNull(voting_delay, "voting_delay must not be null");
            java.util.Objects.requireNonNull(voting_period, "voting_period must not be null");
            java.util.Objects.requireNonNull(voting_quorum_rate, "voting_quorum_rate must not be null");
            java.util.Objects.requireNonNull(min_action_delay, "min_action_delay must not be null");
            java.util.Objects.requireNonNull(exec_delay, "exec_delay must not be null");
            this.token_t = token_t;
            this.voting_delay = voting_delay;
            this.voting_period = voting_period;
            this.voting_quorum_rate = voting_quorum_rate;
            this.min_action_delay = min_action_delay;
            this.exec_delay = exec_delay;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Propose other = (Propose) obj;
            if (!java.util.Objects.equals(this.token_t, other.token_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_delay, other.voting_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_period, other.voting_period)) {
                return false;
            }
            if (!java.util.Objects.equals(this.voting_quorum_rate, other.voting_quorum_rate)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_action_delay, other.min_action_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.exec_delay, other.exec_delay)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_t != null ? this.token_t.hashCode() : 0);
            value = 31 * value + (this.voting_delay != null ? this.voting_delay.hashCode() : 0);
            value = 31 * value + (this.voting_period != null ? this.voting_period.hashCode() : 0);
            value = 31 * value + (this.voting_quorum_rate != null ? this.voting_quorum_rate.hashCode() : 0);
            value = 31 * value + (this.min_action_delay != null ? this.min_action_delay.hashCode() : 0);
            value = 31 * value + (this.exec_delay != null ? this.exec_delay.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_t;
            public @com.novi.serde.Unsigned Long voting_delay;
            public @com.novi.serde.Unsigned Long voting_period;
            public @com.novi.serde.Unsigned Byte voting_quorum_rate;
            public @com.novi.serde.Unsigned Long min_action_delay;
            public @com.novi.serde.Unsigned Long exec_delay;

            public Propose build() {
                return new Propose(
                        token_t,
                        voting_delay,
                        voting_period,
                        voting_quorum_rate,
                        min_action_delay,
                        exec_delay
                );
            }
        }
    }

    /**
     *
     */
    public static final class ProposeModuleUpgradeV2 extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token;
        public final org.starcoin.types.AccountAddress module_address;
        public final com.novi.serde.Bytes package_hash;
        public final @com.novi.serde.Unsigned Long version;
        public final @com.novi.serde.Unsigned Long exec_delay;
        public final Boolean enforced;

        public ProposeModuleUpgradeV2(org.starcoin.types.TypeTag token, org.starcoin.types.AccountAddress module_address, com.novi.serde.Bytes package_hash, @com.novi.serde.Unsigned Long version, @com.novi.serde.Unsigned Long exec_delay, Boolean enforced) {
            java.util.Objects.requireNonNull(token, "token must not be null");
            java.util.Objects.requireNonNull(module_address, "module_address must not be null");
            java.util.Objects.requireNonNull(package_hash, "package_hash must not be null");
            java.util.Objects.requireNonNull(version, "version must not be null");
            java.util.Objects.requireNonNull(exec_delay, "exec_delay must not be null");
            java.util.Objects.requireNonNull(enforced, "enforced must not be null");
            this.token = token;
            this.module_address = module_address;
            this.package_hash = package_hash;
            this.version = version;
            this.exec_delay = exec_delay;
            this.enforced = enforced;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ProposeModuleUpgradeV2 other = (ProposeModuleUpgradeV2) obj;
            if (!java.util.Objects.equals(this.token, other.token)) {
                return false;
            }
            if (!java.util.Objects.equals(this.module_address, other.module_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.package_hash, other.package_hash)) {
                return false;
            }
            if (!java.util.Objects.equals(this.version, other.version)) {
                return false;
            }
            if (!java.util.Objects.equals(this.exec_delay, other.exec_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.enforced, other.enforced)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token != null ? this.token.hashCode() : 0);
            value = 31 * value + (this.module_address != null ? this.module_address.hashCode() : 0);
            value = 31 * value + (this.package_hash != null ? this.package_hash.hashCode() : 0);
            value = 31 * value + (this.version != null ? this.version.hashCode() : 0);
            value = 31 * value + (this.exec_delay != null ? this.exec_delay.hashCode() : 0);
            value = 31 * value + (this.enforced != null ? this.enforced.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token;
            public org.starcoin.types.AccountAddress module_address;
            public com.novi.serde.Bytes package_hash;
            public @com.novi.serde.Unsigned Long version;
            public @com.novi.serde.Unsigned Long exec_delay;
            public Boolean enforced;

            public ProposeModuleUpgradeV2 build() {
                return new ProposeModuleUpgradeV2(
                        token,
                        module_address,
                        package_hash,
                        version,
                        exec_delay,
                        enforced
                );
            }
        }
    }

    /**
     *
     */
    public static final class ProposeUpdateConsensusConfig extends ScriptFunctionCall {
        public final @com.novi.serde.Unsigned Long uncle_rate_target;
        public final @com.novi.serde.Unsigned Long base_block_time_target;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block;
        public final @com.novi.serde.Unsigned Long base_reward_per_uncle_percent;
        public final @com.novi.serde.Unsigned Long epoch_block_count;
        public final @com.novi.serde.Unsigned Long base_block_difficulty_window;
        public final @com.novi.serde.Unsigned Long min_block_time_target;
        public final @com.novi.serde.Unsigned Long max_block_time_target;
        public final @com.novi.serde.Unsigned Long base_max_uncles_per_block;
        public final @com.novi.serde.Unsigned Long base_block_gas_limit;
        public final @com.novi.serde.Unsigned Byte strategy;
        public final @com.novi.serde.Unsigned Long exec_delay;

        public ProposeUpdateConsensusConfig(@com.novi.serde.Unsigned Long uncle_rate_target, @com.novi.serde.Unsigned Long base_block_time_target, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block, @com.novi.serde.Unsigned Long base_reward_per_uncle_percent, @com.novi.serde.Unsigned Long epoch_block_count, @com.novi.serde.Unsigned Long base_block_difficulty_window, @com.novi.serde.Unsigned Long min_block_time_target, @com.novi.serde.Unsigned Long max_block_time_target, @com.novi.serde.Unsigned Long base_max_uncles_per_block, @com.novi.serde.Unsigned Long base_block_gas_limit, @com.novi.serde.Unsigned Byte strategy, @com.novi.serde.Unsigned Long exec_delay) {
            java.util.Objects.requireNonNull(uncle_rate_target, "uncle_rate_target must not be null");
            java.util.Objects.requireNonNull(base_block_time_target, "base_block_time_target must not be null");
            java.util.Objects.requireNonNull(base_reward_per_block, "base_reward_per_block must not be null");
            java.util.Objects.requireNonNull(base_reward_per_uncle_percent, "base_reward_per_uncle_percent must not be null");
            java.util.Objects.requireNonNull(epoch_block_count, "epoch_block_count must not be null");
            java.util.Objects.requireNonNull(base_block_difficulty_window, "base_block_difficulty_window must not be null");
            java.util.Objects.requireNonNull(min_block_time_target, "min_block_time_target must not be null");
            java.util.Objects.requireNonNull(max_block_time_target, "max_block_time_target must not be null");
            java.util.Objects.requireNonNull(base_max_uncles_per_block, "base_max_uncles_per_block must not be null");
            java.util.Objects.requireNonNull(base_block_gas_limit, "base_block_gas_limit must not be null");
            java.util.Objects.requireNonNull(strategy, "strategy must not be null");
            java.util.Objects.requireNonNull(exec_delay, "exec_delay must not be null");
            this.uncle_rate_target = uncle_rate_target;
            this.base_block_time_target = base_block_time_target;
            this.base_reward_per_block = base_reward_per_block;
            this.base_reward_per_uncle_percent = base_reward_per_uncle_percent;
            this.epoch_block_count = epoch_block_count;
            this.base_block_difficulty_window = base_block_difficulty_window;
            this.min_block_time_target = min_block_time_target;
            this.max_block_time_target = max_block_time_target;
            this.base_max_uncles_per_block = base_max_uncles_per_block;
            this.base_block_gas_limit = base_block_gas_limit;
            this.strategy = strategy;
            this.exec_delay = exec_delay;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ProposeUpdateConsensusConfig other = (ProposeUpdateConsensusConfig) obj;
            if (!java.util.Objects.equals(this.uncle_rate_target, other.uncle_rate_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_time_target, other.base_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_reward_per_block, other.base_reward_per_block)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_reward_per_uncle_percent, other.base_reward_per_uncle_percent)) {
                return false;
            }
            if (!java.util.Objects.equals(this.epoch_block_count, other.epoch_block_count)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_difficulty_window, other.base_block_difficulty_window)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_block_time_target, other.min_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_block_time_target, other.max_block_time_target)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_max_uncles_per_block, other.base_max_uncles_per_block)) {
                return false;
            }
            if (!java.util.Objects.equals(this.base_block_gas_limit, other.base_block_gas_limit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.strategy, other.strategy)) {
                return false;
            }
            if (!java.util.Objects.equals(this.exec_delay, other.exec_delay)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.uncle_rate_target != null ? this.uncle_rate_target.hashCode() : 0);
            value = 31 * value + (this.base_block_time_target != null ? this.base_block_time_target.hashCode() : 0);
            value = 31 * value + (this.base_reward_per_block != null ? this.base_reward_per_block.hashCode() : 0);
            value = 31 * value + (this.base_reward_per_uncle_percent != null ? this.base_reward_per_uncle_percent.hashCode() : 0);
            value = 31 * value + (this.epoch_block_count != null ? this.epoch_block_count.hashCode() : 0);
            value = 31 * value + (this.base_block_difficulty_window != null ? this.base_block_difficulty_window.hashCode() : 0);
            value = 31 * value + (this.min_block_time_target != null ? this.min_block_time_target.hashCode() : 0);
            value = 31 * value + (this.max_block_time_target != null ? this.max_block_time_target.hashCode() : 0);
            value = 31 * value + (this.base_max_uncles_per_block != null ? this.base_max_uncles_per_block.hashCode() : 0);
            value = 31 * value + (this.base_block_gas_limit != null ? this.base_block_gas_limit.hashCode() : 0);
            value = 31 * value + (this.strategy != null ? this.strategy.hashCode() : 0);
            value = 31 * value + (this.exec_delay != null ? this.exec_delay.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long uncle_rate_target;
            public @com.novi.serde.Unsigned Long base_block_time_target;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger base_reward_per_block;
            public @com.novi.serde.Unsigned Long base_reward_per_uncle_percent;
            public @com.novi.serde.Unsigned Long epoch_block_count;
            public @com.novi.serde.Unsigned Long base_block_difficulty_window;
            public @com.novi.serde.Unsigned Long min_block_time_target;
            public @com.novi.serde.Unsigned Long max_block_time_target;
            public @com.novi.serde.Unsigned Long base_max_uncles_per_block;
            public @com.novi.serde.Unsigned Long base_block_gas_limit;
            public @com.novi.serde.Unsigned Byte strategy;
            public @com.novi.serde.Unsigned Long exec_delay;

            public ProposeUpdateConsensusConfig build() {
                return new ProposeUpdateConsensusConfig(
                        uncle_rate_target,
                        base_block_time_target,
                        base_reward_per_block,
                        base_reward_per_uncle_percent,
                        epoch_block_count,
                        base_block_difficulty_window,
                        min_block_time_target,
                        max_block_time_target,
                        base_max_uncles_per_block,
                        base_block_gas_limit,
                        strategy,
                        exec_delay
                );
            }
        }
    }

    /**
     *
     */
    public static final class ProposeUpdateRewardConfig extends ScriptFunctionCall {
        public final @com.novi.serde.Unsigned Long reward_delay;
        public final @com.novi.serde.Unsigned Long exec_delay;

        public ProposeUpdateRewardConfig(@com.novi.serde.Unsigned Long reward_delay, @com.novi.serde.Unsigned Long exec_delay) {
            java.util.Objects.requireNonNull(reward_delay, "reward_delay must not be null");
            java.util.Objects.requireNonNull(exec_delay, "exec_delay must not be null");
            this.reward_delay = reward_delay;
            this.exec_delay = exec_delay;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ProposeUpdateRewardConfig other = (ProposeUpdateRewardConfig) obj;
            if (!java.util.Objects.equals(this.reward_delay, other.reward_delay)) {
                return false;
            }
            if (!java.util.Objects.equals(this.exec_delay, other.exec_delay)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.reward_delay != null ? this.reward_delay.hashCode() : 0);
            value = 31 * value + (this.exec_delay != null ? this.exec_delay.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long reward_delay;
            public @com.novi.serde.Unsigned Long exec_delay;

            public ProposeUpdateRewardConfig build() {
                return new ProposeUpdateRewardConfig(
                        reward_delay,
                        exec_delay
                );
            }
        }
    }

    /**
     *
     */
    public static final class ProposeUpdateTxnPublishOption extends ScriptFunctionCall {
        public final Boolean script_allowed;
        public final Boolean module_publishing_allowed;
        public final @com.novi.serde.Unsigned Long exec_delay;

        public ProposeUpdateTxnPublishOption(Boolean script_allowed, Boolean module_publishing_allowed, @com.novi.serde.Unsigned Long exec_delay) {
            java.util.Objects.requireNonNull(script_allowed, "script_allowed must not be null");
            java.util.Objects.requireNonNull(module_publishing_allowed, "module_publishing_allowed must not be null");
            java.util.Objects.requireNonNull(exec_delay, "exec_delay must not be null");
            this.script_allowed = script_allowed;
            this.module_publishing_allowed = module_publishing_allowed;
            this.exec_delay = exec_delay;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ProposeUpdateTxnPublishOption other = (ProposeUpdateTxnPublishOption) obj;
            if (!java.util.Objects.equals(this.script_allowed, other.script_allowed)) {
                return false;
            }
            if (!java.util.Objects.equals(this.module_publishing_allowed, other.module_publishing_allowed)) {
                return false;
            }
            if (!java.util.Objects.equals(this.exec_delay, other.exec_delay)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.script_allowed != null ? this.script_allowed.hashCode() : 0);
            value = 31 * value + (this.module_publishing_allowed != null ? this.module_publishing_allowed.hashCode() : 0);
            value = 31 * value + (this.exec_delay != null ? this.exec_delay.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public Boolean script_allowed;
            public Boolean module_publishing_allowed;
            public @com.novi.serde.Unsigned Long exec_delay;

            public ProposeUpdateTxnPublishOption build() {
                return new ProposeUpdateTxnPublishOption(
                        script_allowed,
                        module_publishing_allowed,
                        exec_delay
                );
            }
        }
    }

    /**
     *
     */
    public static final class ProposeUpdateTxnTimeoutConfig extends ScriptFunctionCall {
        public final @com.novi.serde.Unsigned Long duration_seconds;
        public final @com.novi.serde.Unsigned Long exec_delay;

        public ProposeUpdateTxnTimeoutConfig(@com.novi.serde.Unsigned Long duration_seconds, @com.novi.serde.Unsigned Long exec_delay) {
            java.util.Objects.requireNonNull(duration_seconds, "duration_seconds must not be null");
            java.util.Objects.requireNonNull(exec_delay, "exec_delay must not be null");
            this.duration_seconds = duration_seconds;
            this.exec_delay = exec_delay;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ProposeUpdateTxnTimeoutConfig other = (ProposeUpdateTxnTimeoutConfig) obj;
            if (!java.util.Objects.equals(this.duration_seconds, other.duration_seconds)) {
                return false;
            }
            if (!java.util.Objects.equals(this.exec_delay, other.exec_delay)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.duration_seconds != null ? this.duration_seconds.hashCode() : 0);
            value = 31 * value + (this.exec_delay != null ? this.exec_delay.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Long duration_seconds;
            public @com.novi.serde.Unsigned Long exec_delay;

            public ProposeUpdateTxnTimeoutConfig build() {
                return new ProposeUpdateTxnTimeoutConfig(
                        duration_seconds,
                        exec_delay
                );
            }
        }
    }

    /**
     *
     */
    public static final class ProposeUpdateVmConfig extends ScriptFunctionCall {
        public final com.novi.serde.Bytes instruction_schedule;
        public final com.novi.serde.Bytes native_schedule;
        public final @com.novi.serde.Unsigned Long global_memory_per_byte_cost;
        public final @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost;
        public final @com.novi.serde.Unsigned Long min_transaction_gas_units;
        public final @com.novi.serde.Unsigned Long large_transaction_cutoff;
        public final @com.novi.serde.Unsigned Long instrinsic_gas_per_byte;
        public final @com.novi.serde.Unsigned Long maximum_number_of_gas_units;
        public final @com.novi.serde.Unsigned Long min_price_per_gas_unit;
        public final @com.novi.serde.Unsigned Long max_price_per_gas_unit;
        public final @com.novi.serde.Unsigned Long max_transaction_size_in_bytes;
        public final @com.novi.serde.Unsigned Long gas_unit_scaling_factor;
        public final @com.novi.serde.Unsigned Long default_account_size;
        public final @com.novi.serde.Unsigned Long exec_delay;

        public ProposeUpdateVmConfig(com.novi.serde.Bytes instruction_schedule, com.novi.serde.Bytes native_schedule, @com.novi.serde.Unsigned Long global_memory_per_byte_cost, @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost, @com.novi.serde.Unsigned Long min_transaction_gas_units, @com.novi.serde.Unsigned Long large_transaction_cutoff, @com.novi.serde.Unsigned Long instrinsic_gas_per_byte, @com.novi.serde.Unsigned Long maximum_number_of_gas_units, @com.novi.serde.Unsigned Long min_price_per_gas_unit, @com.novi.serde.Unsigned Long max_price_per_gas_unit, @com.novi.serde.Unsigned Long max_transaction_size_in_bytes, @com.novi.serde.Unsigned Long gas_unit_scaling_factor, @com.novi.serde.Unsigned Long default_account_size, @com.novi.serde.Unsigned Long exec_delay) {
            java.util.Objects.requireNonNull(instruction_schedule, "instruction_schedule must not be null");
            java.util.Objects.requireNonNull(native_schedule, "native_schedule must not be null");
            java.util.Objects.requireNonNull(global_memory_per_byte_cost, "global_memory_per_byte_cost must not be null");
            java.util.Objects.requireNonNull(global_memory_per_byte_write_cost, "global_memory_per_byte_write_cost must not be null");
            java.util.Objects.requireNonNull(min_transaction_gas_units, "min_transaction_gas_units must not be null");
            java.util.Objects.requireNonNull(large_transaction_cutoff, "large_transaction_cutoff must not be null");
            java.util.Objects.requireNonNull(instrinsic_gas_per_byte, "instrinsic_gas_per_byte must not be null");
            java.util.Objects.requireNonNull(maximum_number_of_gas_units, "maximum_number_of_gas_units must not be null");
            java.util.Objects.requireNonNull(min_price_per_gas_unit, "min_price_per_gas_unit must not be null");
            java.util.Objects.requireNonNull(max_price_per_gas_unit, "max_price_per_gas_unit must not be null");
            java.util.Objects.requireNonNull(max_transaction_size_in_bytes, "max_transaction_size_in_bytes must not be null");
            java.util.Objects.requireNonNull(gas_unit_scaling_factor, "gas_unit_scaling_factor must not be null");
            java.util.Objects.requireNonNull(default_account_size, "default_account_size must not be null");
            java.util.Objects.requireNonNull(exec_delay, "exec_delay must not be null");
            this.instruction_schedule = instruction_schedule;
            this.native_schedule = native_schedule;
            this.global_memory_per_byte_cost = global_memory_per_byte_cost;
            this.global_memory_per_byte_write_cost = global_memory_per_byte_write_cost;
            this.min_transaction_gas_units = min_transaction_gas_units;
            this.large_transaction_cutoff = large_transaction_cutoff;
            this.instrinsic_gas_per_byte = instrinsic_gas_per_byte;
            this.maximum_number_of_gas_units = maximum_number_of_gas_units;
            this.min_price_per_gas_unit = min_price_per_gas_unit;
            this.max_price_per_gas_unit = max_price_per_gas_unit;
            this.max_transaction_size_in_bytes = max_transaction_size_in_bytes;
            this.gas_unit_scaling_factor = gas_unit_scaling_factor;
            this.default_account_size = default_account_size;
            this.exec_delay = exec_delay;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ProposeUpdateVmConfig other = (ProposeUpdateVmConfig) obj;
            if (!java.util.Objects.equals(this.instruction_schedule, other.instruction_schedule)) {
                return false;
            }
            if (!java.util.Objects.equals(this.native_schedule, other.native_schedule)) {
                return false;
            }
            if (!java.util.Objects.equals(this.global_memory_per_byte_cost, other.global_memory_per_byte_cost)) {
                return false;
            }
            if (!java.util.Objects.equals(this.global_memory_per_byte_write_cost, other.global_memory_per_byte_write_cost)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_transaction_gas_units, other.min_transaction_gas_units)) {
                return false;
            }
            if (!java.util.Objects.equals(this.large_transaction_cutoff, other.large_transaction_cutoff)) {
                return false;
            }
            if (!java.util.Objects.equals(this.instrinsic_gas_per_byte, other.instrinsic_gas_per_byte)) {
                return false;
            }
            if (!java.util.Objects.equals(this.maximum_number_of_gas_units, other.maximum_number_of_gas_units)) {
                return false;
            }
            if (!java.util.Objects.equals(this.min_price_per_gas_unit, other.min_price_per_gas_unit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_price_per_gas_unit, other.max_price_per_gas_unit)) {
                return false;
            }
            if (!java.util.Objects.equals(this.max_transaction_size_in_bytes, other.max_transaction_size_in_bytes)) {
                return false;
            }
            if (!java.util.Objects.equals(this.gas_unit_scaling_factor, other.gas_unit_scaling_factor)) {
                return false;
            }
            if (!java.util.Objects.equals(this.default_account_size, other.default_account_size)) {
                return false;
            }
            if (!java.util.Objects.equals(this.exec_delay, other.exec_delay)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.instruction_schedule != null ? this.instruction_schedule.hashCode() : 0);
            value = 31 * value + (this.native_schedule != null ? this.native_schedule.hashCode() : 0);
            value = 31 * value + (this.global_memory_per_byte_cost != null ? this.global_memory_per_byte_cost.hashCode() : 0);
            value = 31 * value + (this.global_memory_per_byte_write_cost != null ? this.global_memory_per_byte_write_cost.hashCode() : 0);
            value = 31 * value + (this.min_transaction_gas_units != null ? this.min_transaction_gas_units.hashCode() : 0);
            value = 31 * value + (this.large_transaction_cutoff != null ? this.large_transaction_cutoff.hashCode() : 0);
            value = 31 * value + (this.instrinsic_gas_per_byte != null ? this.instrinsic_gas_per_byte.hashCode() : 0);
            value = 31 * value + (this.maximum_number_of_gas_units != null ? this.maximum_number_of_gas_units.hashCode() : 0);
            value = 31 * value + (this.min_price_per_gas_unit != null ? this.min_price_per_gas_unit.hashCode() : 0);
            value = 31 * value + (this.max_price_per_gas_unit != null ? this.max_price_per_gas_unit.hashCode() : 0);
            value = 31 * value + (this.max_transaction_size_in_bytes != null ? this.max_transaction_size_in_bytes.hashCode() : 0);
            value = 31 * value + (this.gas_unit_scaling_factor != null ? this.gas_unit_scaling_factor.hashCode() : 0);
            value = 31 * value + (this.default_account_size != null ? this.default_account_size.hashCode() : 0);
            value = 31 * value + (this.exec_delay != null ? this.exec_delay.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public com.novi.serde.Bytes instruction_schedule;
            public com.novi.serde.Bytes native_schedule;
            public @com.novi.serde.Unsigned Long global_memory_per_byte_cost;
            public @com.novi.serde.Unsigned Long global_memory_per_byte_write_cost;
            public @com.novi.serde.Unsigned Long min_transaction_gas_units;
            public @com.novi.serde.Unsigned Long large_transaction_cutoff;
            public @com.novi.serde.Unsigned Long instrinsic_gas_per_byte;
            public @com.novi.serde.Unsigned Long maximum_number_of_gas_units;
            public @com.novi.serde.Unsigned Long min_price_per_gas_unit;
            public @com.novi.serde.Unsigned Long max_price_per_gas_unit;
            public @com.novi.serde.Unsigned Long max_transaction_size_in_bytes;
            public @com.novi.serde.Unsigned Long gas_unit_scaling_factor;
            public @com.novi.serde.Unsigned Long default_account_size;
            public @com.novi.serde.Unsigned Long exec_delay;

            public ProposeUpdateVmConfig build() {
                return new ProposeUpdateVmConfig(
                        instruction_schedule,
                        native_schedule,
                        global_memory_per_byte_cost,
                        global_memory_per_byte_write_cost,
                        min_transaction_gas_units,
                        large_transaction_cutoff,
                        instrinsic_gas_per_byte,
                        maximum_number_of_gas_units,
                        min_price_per_gas_unit,
                        max_price_per_gas_unit,
                        max_transaction_size_in_bytes,
                        gas_unit_scaling_factor,
                        default_account_size,
                        exec_delay
                );
            }
        }
    }

    /**
     *
     */
    public static final class ProposeWithdraw extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_t;
        public final org.starcoin.types.AccountAddress receiver;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
        public final @com.novi.serde.Unsigned Long period;
        public final @com.novi.serde.Unsigned Long exec_delay;

        public ProposeWithdraw(org.starcoin.types.TypeTag token_t, org.starcoin.types.AccountAddress receiver, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount, @com.novi.serde.Unsigned Long period, @com.novi.serde.Unsigned Long exec_delay) {
            java.util.Objects.requireNonNull(token_t, "token_t must not be null");
            java.util.Objects.requireNonNull(receiver, "receiver must not be null");
            java.util.Objects.requireNonNull(amount, "amount must not be null");
            java.util.Objects.requireNonNull(period, "period must not be null");
            java.util.Objects.requireNonNull(exec_delay, "exec_delay must not be null");
            this.token_t = token_t;
            this.receiver = receiver;
            this.amount = amount;
            this.period = period;
            this.exec_delay = exec_delay;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            ProposeWithdraw other = (ProposeWithdraw) obj;
            if (!java.util.Objects.equals(this.token_t, other.token_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.receiver, other.receiver)) {
                return false;
            }
            if (!java.util.Objects.equals(this.amount, other.amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.period, other.period)) {
                return false;
            }
            if (!java.util.Objects.equals(this.exec_delay, other.exec_delay)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_t != null ? this.token_t.hashCode() : 0);
            value = 31 * value + (this.receiver != null ? this.receiver.hashCode() : 0);
            value = 31 * value + (this.amount != null ? this.amount.hashCode() : 0);
            value = 31 * value + (this.period != null ? this.period.hashCode() : 0);
            value = 31 * value + (this.exec_delay != null ? this.exec_delay.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_t;
            public org.starcoin.types.AccountAddress receiver;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
            public @com.novi.serde.Unsigned Long period;
            public @com.novi.serde.Unsigned Long exec_delay;

            public ProposeWithdraw build() {
                return new ProposeWithdraw(
                        token_t,
                        receiver,
                        amount,
                        period,
                        exec_delay
                );
            }
        }
    }

    /**
     * queue agreed proposal to execute.
     */
    public static final class QueueProposalAction extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_t;
        public final org.starcoin.types.TypeTag action_t;
        public final org.starcoin.types.AccountAddress proposer_address;
        public final @com.novi.serde.Unsigned Long proposal_id;

        public QueueProposalAction(org.starcoin.types.TypeTag token_t, org.starcoin.types.TypeTag action_t, org.starcoin.types.AccountAddress proposer_address, @com.novi.serde.Unsigned Long proposal_id) {
            java.util.Objects.requireNonNull(token_t, "token_t must not be null");
            java.util.Objects.requireNonNull(action_t, "action_t must not be null");
            java.util.Objects.requireNonNull(proposer_address, "proposer_address must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            this.token_t = token_t;
            this.action_t = action_t;
            this.proposer_address = proposer_address;
            this.proposal_id = proposal_id;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            QueueProposalAction other = (QueueProposalAction) obj;
            if (!java.util.Objects.equals(this.token_t, other.token_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.action_t, other.action_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposer_address, other.proposer_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_t != null ? this.token_t.hashCode() : 0);
            value = 31 * value + (this.action_t != null ? this.action_t.hashCode() : 0);
            value = 31 * value + (this.proposer_address != null ? this.proposer_address.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_t;
            public org.starcoin.types.TypeTag action_t;
            public org.starcoin.types.AccountAddress proposer_address;
            public @com.novi.serde.Unsigned Long proposal_id;

            public QueueProposalAction build() {
                return new QueueProposalAction(
                        token_t,
                        action_t,
                        proposer_address,
                        proposal_id
                );
            }
        }
    }

    /**
     *
     */
    public static final class RevokeVote extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token;
        public final org.starcoin.types.TypeTag action;
        public final org.starcoin.types.AccountAddress proposer_address;
        public final @com.novi.serde.Unsigned Long proposal_id;

        public RevokeVote(org.starcoin.types.TypeTag token, org.starcoin.types.TypeTag action, org.starcoin.types.AccountAddress proposer_address, @com.novi.serde.Unsigned Long proposal_id) {
            java.util.Objects.requireNonNull(token, "token must not be null");
            java.util.Objects.requireNonNull(action, "action must not be null");
            java.util.Objects.requireNonNull(proposer_address, "proposer_address must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            this.token = token;
            this.action = action;
            this.proposer_address = proposer_address;
            this.proposal_id = proposal_id;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            RevokeVote other = (RevokeVote) obj;
            if (!java.util.Objects.equals(this.token, other.token)) {
                return false;
            }
            if (!java.util.Objects.equals(this.action, other.action)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposer_address, other.proposer_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token != null ? this.token.hashCode() : 0);
            value = 31 * value + (this.action != null ? this.action.hashCode() : 0);
            value = 31 * value + (this.proposer_address != null ? this.proposer_address.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token;
            public org.starcoin.types.TypeTag action;
            public org.starcoin.types.AccountAddress proposer_address;
            public @com.novi.serde.Unsigned Long proposal_id;

            public RevokeVote build() {
                return new RevokeVote(
                        token,
                        action,
                        proposer_address,
                        proposal_id
                );
            }
        }
    }

    /**
     *
     */
    public static final class RotateAuthenticationKey extends ScriptFunctionCall {
        public final com.novi.serde.Bytes new_key;

        public RotateAuthenticationKey(com.novi.serde.Bytes new_key) {
            java.util.Objects.requireNonNull(new_key, "new_key must not be null");
            this.new_key = new_key;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            RotateAuthenticationKey other = (RotateAuthenticationKey) obj;
            if (!java.util.Objects.equals(this.new_key, other.new_key)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.new_key != null ? this.new_key.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public com.novi.serde.Bytes new_key;

            public RotateAuthenticationKey build() {
                return new RotateAuthenticationKey(
                        new_key
                );
            }
        }
    }

    /**
     *
     */
    public static final class SubmitModuleUpgradePlan extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token;
        public final org.starcoin.types.AccountAddress proposer_address;
        public final @com.novi.serde.Unsigned Long proposal_id;

        public SubmitModuleUpgradePlan(org.starcoin.types.TypeTag token, org.starcoin.types.AccountAddress proposer_address, @com.novi.serde.Unsigned Long proposal_id) {
            java.util.Objects.requireNonNull(token, "token must not be null");
            java.util.Objects.requireNonNull(proposer_address, "proposer_address must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            this.token = token;
            this.proposer_address = proposer_address;
            this.proposal_id = proposal_id;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            SubmitModuleUpgradePlan other = (SubmitModuleUpgradePlan) obj;
            if (!java.util.Objects.equals(this.token, other.token)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposer_address, other.proposer_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token != null ? this.token.hashCode() : 0);
            value = 31 * value + (this.proposer_address != null ? this.proposer_address.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token;
            public org.starcoin.types.AccountAddress proposer_address;
            public @com.novi.serde.Unsigned Long proposal_id;

            public SubmitModuleUpgradePlan build() {
                return new SubmitModuleUpgradePlan(
                        token,
                        proposer_address,
                        proposal_id
                );
            }
        }
    }

    /**
     * association account should call this script after upgrade from v2 to v3.
     */
    public static final class TakeLinearWithdrawCapability extends ScriptFunctionCall {
        public TakeLinearWithdrawCapability() {
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            TakeLinearWithdrawCapability other = (TakeLinearWithdrawCapability) obj;
            return true;
        }

        public int hashCode() {
            int value = 7;
            return value;
        }

        public static final class Builder {
            public TakeLinearWithdrawCapability build() {
                return new TakeLinearWithdrawCapability(
                );
            }
        }
    }

    /**
     * Take Offer and put to signer's Collection{@code <}Offered{@code >}.
     */
    public static final class TakeOffer extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag offered;
        public final org.starcoin.types.AccountAddress offer_address;

        public TakeOffer(org.starcoin.types.TypeTag offered, org.starcoin.types.AccountAddress offer_address) {
            java.util.Objects.requireNonNull(offered, "offered must not be null");
            java.util.Objects.requireNonNull(offer_address, "offer_address must not be null");
            this.offered = offered;
            this.offer_address = offer_address;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            TakeOffer other = (TakeOffer) obj;
            if (!java.util.Objects.equals(this.offered, other.offered)) {
                return false;
            }
            if (!java.util.Objects.equals(this.offer_address, other.offer_address)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.offered != null ? this.offered.hashCode() : 0);
            value = 31 * value + (this.offer_address != null ? this.offer_address.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag offered;
            public org.starcoin.types.AccountAddress offer_address;

            public TakeOffer build() {
                return new TakeOffer(
                        offered,
                        offer_address
                );
            }
        }
    }

    /**
     *
     */
    public static final class UnstakeVote extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token;
        public final org.starcoin.types.TypeTag action;
        public final org.starcoin.types.AccountAddress proposer_address;
        public final @com.novi.serde.Unsigned Long proposal_id;

        public UnstakeVote(org.starcoin.types.TypeTag token, org.starcoin.types.TypeTag action, org.starcoin.types.AccountAddress proposer_address, @com.novi.serde.Unsigned Long proposal_id) {
            java.util.Objects.requireNonNull(token, "token must not be null");
            java.util.Objects.requireNonNull(action, "action must not be null");
            java.util.Objects.requireNonNull(proposer_address, "proposer_address must not be null");
            java.util.Objects.requireNonNull(proposal_id, "proposal_id must not be null");
            this.token = token;
            this.action = action;
            this.proposer_address = proposer_address;
            this.proposal_id = proposal_id;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            UnstakeVote other = (UnstakeVote) obj;
            if (!java.util.Objects.equals(this.token, other.token)) {
                return false;
            }
            if (!java.util.Objects.equals(this.action, other.action)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposer_address, other.proposer_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.proposal_id, other.proposal_id)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token != null ? this.token.hashCode() : 0);
            value = 31 * value + (this.action != null ? this.action.hashCode() : 0);
            value = 31 * value + (this.proposer_address != null ? this.proposer_address.hashCode() : 0);
            value = 31 * value + (this.proposal_id != null ? this.proposal_id.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token;
            public org.starcoin.types.TypeTag action;
            public org.starcoin.types.AccountAddress proposer_address;
            public @com.novi.serde.Unsigned Long proposal_id;

            public UnstakeVote build() {
                return new UnstakeVote(
                        token,
                        action,
                        proposer_address,
                        proposal_id
                );
            }
        }
    }

    /**
     *
     */
    public static final class UpdateModuleUpgradeStrategy extends ScriptFunctionCall {
        public final @com.novi.serde.Unsigned Byte strategy;

        public UpdateModuleUpgradeStrategy(@com.novi.serde.Unsigned Byte strategy) {
            java.util.Objects.requireNonNull(strategy, "strategy must not be null");
            this.strategy = strategy;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            UpdateModuleUpgradeStrategy other = (UpdateModuleUpgradeStrategy) obj;
            if (!java.util.Objects.equals(this.strategy, other.strategy)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.strategy != null ? this.strategy.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public @com.novi.serde.Unsigned Byte strategy;

            public UpdateModuleUpgradeStrategy build() {
                return new UpdateModuleUpgradeStrategy(
                        strategy
                );
            }
        }
    }

    /**
     * Stdlib upgrade script from v2 to v3
     */
    public static final class UpgradeFromV2ToV3 extends ScriptFunctionCall {
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger total_stc_amount;

        public UpgradeFromV2ToV3(java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger total_stc_amount) {
            java.util.Objects.requireNonNull(total_stc_amount, "total_stc_amount must not be null");
            this.total_stc_amount = total_stc_amount;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            UpgradeFromV2ToV3 other = (UpgradeFromV2ToV3) obj;
            if (!java.util.Objects.equals(this.total_stc_amount, other.total_stc_amount)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.total_stc_amount != null ? this.total_stc_amount.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger total_stc_amount;

            public UpgradeFromV2ToV3 build() {
                return new UpgradeFromV2ToV3(
                        total_stc_amount
                );
            }
        }
    }

    /**
     *
     */
    public static final class WithdrawAndSplitLtWithdrawCap extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_t;
        public final org.starcoin.types.AccountAddress for_address;
        public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
        public final @com.novi.serde.Unsigned Long lock_period;

        public WithdrawAndSplitLtWithdrawCap(org.starcoin.types.TypeTag token_t, org.starcoin.types.AccountAddress for_address, java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount, @com.novi.serde.Unsigned Long lock_period) {
            java.util.Objects.requireNonNull(token_t, "token_t must not be null");
            java.util.Objects.requireNonNull(for_address, "for_address must not be null");
            java.util.Objects.requireNonNull(amount, "amount must not be null");
            java.util.Objects.requireNonNull(lock_period, "lock_period must not be null");
            this.token_t = token_t;
            this.for_address = for_address;
            this.amount = amount;
            this.lock_period = lock_period;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            WithdrawAndSplitLtWithdrawCap other = (WithdrawAndSplitLtWithdrawCap) obj;
            if (!java.util.Objects.equals(this.token_t, other.token_t)) {
                return false;
            }
            if (!java.util.Objects.equals(this.for_address, other.for_address)) {
                return false;
            }
            if (!java.util.Objects.equals(this.amount, other.amount)) {
                return false;
            }
            if (!java.util.Objects.equals(this.lock_period, other.lock_period)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_t != null ? this.token_t.hashCode() : 0);
            value = 31 * value + (this.for_address != null ? this.for_address.hashCode() : 0);
            value = 31 * value + (this.amount != null ? this.amount.hashCode() : 0);
            value = 31 * value + (this.lock_period != null ? this.lock_period.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_t;
            public org.starcoin.types.AccountAddress for_address;
            public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
            public @com.novi.serde.Unsigned Long lock_period;

            public WithdrawAndSplitLtWithdrawCap build() {
                return new WithdrawAndSplitLtWithdrawCap(
                        token_t,
                        for_address,
                        amount,
                        lock_period
                );
            }
        }
    }

    /**
     *
     */
    public static final class WithdrawTokenWithLinearWithdrawCapability extends ScriptFunctionCall {
        public final org.starcoin.types.TypeTag token_t;

        public WithdrawTokenWithLinearWithdrawCapability(org.starcoin.types.TypeTag token_t) {
            java.util.Objects.requireNonNull(token_t, "token_t must not be null");
            this.token_t = token_t;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            WithdrawTokenWithLinearWithdrawCapability other = (WithdrawTokenWithLinearWithdrawCapability) obj;
            if (!java.util.Objects.equals(this.token_t, other.token_t)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int value = 7;
            value = 31 * value + (this.token_t != null ? this.token_t.hashCode() : 0);
            return value;
        }

        public static final class Builder {
            public org.starcoin.types.TypeTag token_t;

            public WithdrawTokenWithLinearWithdrawCapability build() {
                return new WithdrawTokenWithLinearWithdrawCapability(
                        token_t
                );
            }
        }
    }
}

