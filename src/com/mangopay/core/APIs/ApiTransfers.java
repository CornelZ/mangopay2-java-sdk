package com.mangopay.core.APIs;

import com.mangopay.MangoPayApi;
import com.mangopay.entities.*;

/**
 * API for transfers.
 */
public class ApiTransfers extends ApiBase {

    /**
     * Instantiates new ApiTransfers object.
     * @param root Root/parent instance that holds the OAuthToken and Configuration instance.
     */
    public ApiTransfers(MangoPayApi root) { super(root); }
    
    /**
     * Creates new transfer.
     * @param transfer  Instance of Transfer class to be created.
     * @return          Transfer object returned from API.
     * @throws Exception
     */
    public Transfer create(Transfer transfer) throws Exception {
        return this.create(null, transfer);
    }
    
    /**
     * Creates new transfer.
     * @param idempotencyKey    Idempotency key for this request.
     * @param transfer          Instance of Transfer class to be created.
     * @return                  Transfer object returned from API.
     * @throws Exception
     */
    public Transfer create(String idempotencyKey, Transfer transfer) throws Exception {
        return this.createObject(Transfer.class, idempotencyKey, "transfers_create", transfer);
    }
    
    /**
     * Gets the transfer.
     * @param transferId    Transfer identifier.
     * @return              Transfer instance returned from API.
     * @throws Exception
     */
    public Transfer get(String transferId) throws Exception {
        return this.getObject(Transfer.class, "transfers_get", transferId);
    }
    
    /**
     * Creates refund for transfer object.
     * @param transferId Transfer identifier.
     * @param refund Refund object to create.
     * @return Refund entity instance returned from API.
     * @throws Exception
     */
    public Refund createRefund(String transferId, Refund refund) throws Exception {
        return this.createRefund(null, transferId, refund);
    }
    
    /**
     * Creates refund for transfer object.
     * @param idempotencyKey    Idempotency key for this request.
     * @param transferId        Transfer identifier.
     * @param refund            Refund object to create.
     * @return Refund entity instance returned from API.
     * @throws Exception
     */
    public Refund createRefund(String idempotencyKey, String transferId, Refund refund) throws Exception {
        return this.createObject(Refund.class, idempotencyKey, "transfers_createrefunds", refund, transferId);
    }
    
    /**
     * Gets refund for transfer object.
     * @param transferId Transfer identifier.
     * @return Refund entity instance returned from API.
     * @throws Exception
     */
    public Refund getRefund(String transferId) throws Exception {
        return this.getObject(Refund.class, "transfers_getrefunds", transferId);
    }
    
}
