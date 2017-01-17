package com.mangopay.core.APIs;

import com.mangopay.MangoPayApi;
import com.mangopay.entities.PayOut;

/**
 * API for PayOuts.
 */
public class ApiPayOuts extends ApiBase {

    /**
     * Instantiates new ApiPayOuts object.
     * @param root Root/parent instance that holds the OAuthToken and Configuration instance.
     */
    public ApiPayOuts(MangoPayApi root) { super(root); }
    
    /**
     * Creates new PayOut object.
     * @param payOut    The PayOut object to be created.
     * @return          Created PayOut object returned by API.
     * @throws Exception
     */
    public PayOut create(PayOut payOut) throws Exception {
        return this.create(null, payOut);
    }
    
    /**
     * Creates new PayOut object.
     * @param idempotencyKey    Idempotency key for this request.
     * @param payOut            The PayOut object to be created.
     * @return                  Created PayOut object returned by API.
     * @throws Exception
     */
    public PayOut create(String idempotencyKey, PayOut payOut) throws Exception {
        String paymentKey = this.getPaymentKey(payOut);
        return this.createObject(PayOut.class, idempotencyKey, String.format("payouts_%s_create", paymentKey), payOut);
    }
    
    /**
     * Gets PayOut entity by its identifier.
     * @param payOutId  PayOut identifier.
     * @return          PayOut instance returned by API.
     * @throws Exception
     */
    public PayOut get(String payOutId) throws Exception {
        return this.getObject(PayOut.class, "payouts_get", payOutId);
    }
    
    private String getPaymentKey(PayOut payOut) throws Exception {
        
        if (payOut.getMeanOfPaymentDetails() == null)
            throw new Exception("Mean of payment is not defined or it is not object type");
        
        String className = payOut.getMeanOfPaymentDetails().getClass().getSimpleName().replace("PayOutPaymentDetails", "");
        return className.toLowerCase();
        
    }
    
}
