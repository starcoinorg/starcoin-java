package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StateProof {

    @JSONField(name = "account_proof")
    @JsonProperty("account_proof")
    private AccountProof accountProof;

    @JSONField(name = "account_state")
    @JsonProperty("account_state")
    private String accountState;

    @JSONField(name = "account_state_proof")
    @JsonProperty("account_state_proof")
    private AccountProof accountStateProof;

    private String state;

    public AccountProof getAccountProof() {
        return accountProof;
    }

    public void setAccountProof(AccountProof accountProof) {
        this.accountProof = accountProof;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public AccountProof getAccountStateProof() {
        return accountStateProof;
    }

    public void setAccountStateProof(AccountProof accountStateProof) {
        this.accountStateProof = accountStateProof;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "StateProof{" +
                "accountProof=" + accountProof +
                ", accountState='" + accountState + '\'' +
                ", accountStateProof=" + accountStateProof +
                ", state='" + state + '\'' +
                '}';
    }

}
