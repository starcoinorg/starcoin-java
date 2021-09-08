package org.starcoin.bean;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OracleTokenPair {

    private String pairId;

    private String pairName;

    private BigDecimal deviationPercentage;

    private float heartbeatHours;

    private int decimals;

    private BigInteger latestPrice;

    private String onChainStatus;

    private String onChainTransactionHash;

    private String createdBy;

    private String updatedBy;

    private long createdAt;

    private long updatedAt;

    private int version;

    public String getPairId() {
        return pairId;
    }

    public void setPairId(String pairId) {
        this.pairId = pairId;
    }

    public String getPairName() {
        return pairName;
    }

    public void setPairName(String pairName) {
        this.pairName = pairName;
    }

    public BigDecimal getDeviationPercentage() {
        return deviationPercentage;
    }

    public void setDeviationPercentage(BigDecimal deviationPercentage) {
        this.deviationPercentage = deviationPercentage;
    }

    public float getHeartbeatHours() {
        return heartbeatHours;
    }

    public void setHeartbeatHours(float heartbeatHours) {
        this.heartbeatHours = heartbeatHours;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public BigInteger getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(BigInteger latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getOnChainStatus() {
        return onChainStatus;
    }

    public void setOnChainStatus(String onChainStatus) {
        this.onChainStatus = onChainStatus;
    }

    public String getOnChainTransactionHash() {
        return onChainTransactionHash;
    }

    public void setOnChainTransactionHash(String onChainTransactionHash) {
        this.onChainTransactionHash = onChainTransactionHash;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "OracleTokenPair{" +
                "pairId='" + pairId + '\'' +
                ", pairName='" + pairName + '\'' +
                ", deviationPercentage=" + deviationPercentage +
                ", heartbeatHours=" + heartbeatHours +
                ", decimals=" + decimals +
                ", latestPrice=" + latestPrice +
                ", onChainStatus='" + onChainStatus + '\'' +
                ", onChainTransactionHash='" + onChainTransactionHash + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", version=" + version +
                '}';
    }
}
