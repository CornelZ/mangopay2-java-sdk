package com.mangopay.core;

import com.mangopay.core.enumerations.*;
import com.mangopay.entities.CardRegistration;
import com.mangopay.entities.TemporaryPaymentCard;
import com.mangopay.entities.UserNatural;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ApiCardRegistrations test methods.
 */
public class ApiCardRegistrationsTest extends BaseTest {
    
    @Test
    public void createCardRegistration() throws Exception {
        CardRegistration cardRegistration_visa = this.getJohnsCardRegistration(CardType.CB_VISA_MASTERCARD);
        UserNatural user = this.getJohn();

        assertNotNull(cardRegistration_visa.Id);
        assertTrue(cardRegistration_visa.Id.length() > 0);

        assertNotNull(cardRegistration_visa.AccessKey);
        assertNotNull(cardRegistration_visa.PreregistrationData);
        assertNotNull(cardRegistration_visa.CardRegistrationURL);
        assertEquals(user.Id, cardRegistration_visa.UserId);
        assertTrue(cardRegistration_visa.Currency == CurrencyIso.EUR);
        assertEquals("CREATED", cardRegistration_visa.Status);
        assertEquals(CardType.CB_VISA_MASTERCARD, cardRegistration_visa.CardType);
        
        
        CardRegistration cardRegistration_maestro = this.getNewJohnsCardRegistration(CardType.MAESTRO);

        assertNotNull(cardRegistration_maestro.Id);
        assertTrue(cardRegistration_maestro.Id.length() > 0);

        assertNotNull(cardRegistration_maestro.AccessKey);
        assertNotNull(cardRegistration_maestro.PreregistrationData);
        assertNotNull(cardRegistration_maestro.CardRegistrationURL);
        assertEquals(user.Id, cardRegistration_maestro.UserId);
        assertTrue(cardRegistration_maestro.Currency == CurrencyIso.EUR);
        assertEquals("CREATED", cardRegistration_maestro.Status);
        assertEquals(CardType.MAESTRO, cardRegistration_maestro.CardType);
    }
    
    @Test
    public void getCardRegistration() throws Exception {
        CardRegistration cardRegistration = this.getJohnsCardRegistration();

        CardRegistration getCardRegistration = this.api.CardRegistrations.get(cardRegistration.Id);
        
        assertTrue(getCardRegistration.Id.length() > 0);
        assertEquals(cardRegistration.Id, getCardRegistration.Id);
    }
    
    @Test
    public void updateCardRegistration() throws Exception {
        CardRegistration cardRegistration = this.getJohnsCardRegistration();
        String registrationData = this.getPaylineCorrectRegistartionData(cardRegistration);
        cardRegistration.RegistrationData = registrationData;
        
        CardRegistration getCardRegistration = this.api.CardRegistrations.update(cardRegistration);
        
        assertEquals(registrationData, getCardRegistration.RegistrationData);
        assertNotNull(getCardRegistration.CardId);
        assertEquals("VALIDATED", getCardRegistration.Status);
        assertEquals("000000", getCardRegistration.ResultCode);
    }
    
    
    /* The two tests below are added to cover temporary use cases, which will be
     * removed in future. */
    
    @Test
    public void temporaryPaymentCardCreate() throws Exception {
        UserNatural user = this.getJohn();
        TemporaryPaymentCard paymentCard = new TemporaryPaymentCard();
        paymentCard.UserId = user.Id;
        paymentCard.Tag = "Test tag";
        paymentCard.Culture = "FR";
        paymentCard.ReturnURL = "http://test.com/test";
        paymentCard.TemplateURL = "https://test.com/test";
                       
        TemporaryPaymentCard paymentCardCreated = this.api.Cards.createTemporaryPaymentCard(paymentCard);
        
        assertTrue(paymentCardCreated.Id.length() > 0);
        assertEquals(paymentCardCreated.UserId, user.Id);
    }
    
    @Test
    public void temporaryPaymentCardGet() throws Exception {
        UserNatural user = this.getJohn();
        TemporaryPaymentCard paymentCard = new TemporaryPaymentCard();
        paymentCard.UserId = user.Id;
        paymentCard.Tag = "Test tag";
        paymentCard.Culture = "FR";
        paymentCard.ReturnURL = "http://test.com/test";
        paymentCard.TemplateURL = "https://test.com/test";
        TemporaryPaymentCard paymentCardCreated = this.api.Cards.createTemporaryPaymentCard(paymentCard);
        
        TemporaryPaymentCard paymentCardGet = this.api.Cards.getTemporaryPaymentCard(paymentCardCreated.Id);
        
        assertTrue(paymentCardGet.Id.length() > 0);
        assertEquals(paymentCardGet.Id, paymentCardCreated.Id);
    }
}
