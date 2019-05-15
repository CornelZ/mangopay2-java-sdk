package com.mangopay.core.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mangopay.entities.PayIn;
import com.mangopay.entities.subentities.*;

import java.lang.reflect.Type;

public class PayInSerializer implements JsonSerializer<PayIn> {


    @Override
    public JsonElement serialize(PayIn src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = SerializedTransaction.getTransactionObject(src, context);
        object.add("CreditedWalletId", context.serialize(src.getCreditedWalletId()));
        object.add("PaymentType", context.serialize(src.getPaymentType()));
        object.add("ExecutionType", context.serialize(src.getExecutionType()));
        switch (src.getPaymentDetails().getClass().getSimpleName()) {
            case "PayInPaymentDetailsBankWire":
                object.add("DeclaredDebitedFunds", context.serialize(((PayInPaymentDetailsBankWire) src.getPaymentDetails()).getDeclaredDebitedFunds()));
                object.add("DeclaredFees", context.serialize(((PayInPaymentDetailsBankWire) src.getPaymentDetails()).getDeclaredFees()));
                object.add("BankAccount", context.serialize(((PayInPaymentDetailsBankWire) src.getPaymentDetails()).getBankAccount()));
                object.add("WireReference", context.serialize(((PayInPaymentDetailsBankWire) src.getPaymentDetails()).getWireReference()));
                break;
            case "PayInPaymentDetailsCard":
                object.add("CardType", context.serialize(((PayInPaymentDetailsCard) src.getPaymentDetails()).getCardType()));
                object.add("CardId", context.serialize(((PayInPaymentDetailsCard) src.getPaymentDetails()).getCardId()));
                object.add("StatementDescriptor", context.serialize(((PayInPaymentDetailsCard) src.getPaymentDetails()).getStatementDescriptor()));
                break;
            default:
                return null;
        }
        switch (src.getExecutionDetails().getClass().getSimpleName()) {
            case "PayInExecutionDetailsBankingAlias":
                object.add("BankingAliasId", context.serialize(((PayInExecutionDetailsBankingAlias) src.getExecutionDetails()).getBankingAliasId()));
                object.add("WireReference", context.serialize(((PayInExecutionDetailsBankingAlias) src.getExecutionDetails()).getWireReference()));
                object.add("DebitedBankAccount", context.serialize(((PayInExecutionDetailsBankingAlias) src.getExecutionDetails()).getDebitedBankAccount()));
                break;
            case "PayInExecutionDetailsDirect":
                object.add("CardId", context.serialize(((PayInExecutionDetailsDirect) src.getExecutionDetails()).getCardId()));
                object.add("SecureMode", context.serialize(((PayInExecutionDetailsDirect) src.getExecutionDetails()).getSecureMode()));
                object.add("SecureModeReturnURL", context.serialize(((PayInExecutionDetailsDirect) src.getExecutionDetails()).getSecureModeReturnUrl()));
                object.add("SecureModeRedirectURL", context.serialize(((PayInExecutionDetailsDirect) src.getExecutionDetails()).getSecureModeRedirectUrl()));
                object.add("SecureModeNeeded", context.serialize(((PayInExecutionDetailsDirect) src.getExecutionDetails()).getSecureModeNeeded()));
                object.add("Billing", context.serialize(((PayInExecutionDetailsDirect) src.getExecutionDetails()).getBilling()));
                object.add("SecurityInfo", context.serialize(((PayInExecutionDetailsDirect) src.getExecutionDetails()).getSecurityInfo()));
                object.add("Culture", context.serialize(((PayInExecutionDetailsDirect) src.getExecutionDetails()).getCulture()));
                break;
            case "PayInExecutionDetailsWeb":
                object.add("TemplateURL", context.serialize(((PayInExecutionDetailsWeb) src.getExecutionDetails()).getTemplateUrl()));
                object.add("Culture", context.serialize(((PayInExecutionDetailsWeb) src.getExecutionDetails()).getCulture()));
                object.add("SecureMode", context.serialize(((PayInExecutionDetailsWeb) src.getExecutionDetails()).getSecureMode()));
                object.add("RedirectURL", context.serialize(((PayInExecutionDetailsWeb) src.getExecutionDetails()).getRedirectUrl()));
                object.add("ReturnURL", context.serialize(((PayInExecutionDetailsWeb) src.getExecutionDetails()).getReturnUrl()));
                break;
            default:
                return null;
        }
        return object;
    }

}
