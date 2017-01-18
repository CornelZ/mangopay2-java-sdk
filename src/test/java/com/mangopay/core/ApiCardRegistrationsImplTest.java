package com.mangopay.core;

import com.mangopay.core.enumerations.*;
import com.mangopay.entities.CardRegistration;
import com.mangopay.entities.TemporaryPaymentCard;
import com.mangopay.entities.UserNatural;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ApiCardRegistrationsImpl test methods.
 */
public class ApiCardRegistrationsImplTest extends BaseTest {

    @Test
    public void createCardRegistration() throws Exception {
        CardRegistration cardRegistration_visa = this.getJohnsCardRegistration(CardType.CB_VISA_MASTERCARD);
        UserNatural user = this.getJohn();

        assertNotNull(cardRegistration_visa.getId());
        assertTrue(cardRegistration_visa.getId().length() > 0);

        assertNotNull(cardRegistration_visa.getAccessKey());
        assertNotNull(cardRegistration_visa.getPreregistrationData());
        assertNotNull(cardRegistration_visa.getCardRegistrationURL());
        assertEquals(user.Id, cardRegistration_visa.getUserId());
        assertTrue(cardRegistration_visa.getCurrency() == CurrencyIso.EUR);
        assertEquals("CREATED", cardRegistration_visa.getStatus());
        assertEquals(CardType.CB_VISA_MASTERCARD, cardRegistration_visa.getCardType());


        CardRegistration cardRegistration_maestro = this.getNewJohnsCardRegistration(CardType.MAESTRO);

        assertNotNull(cardRegistration_maestro.getId());
        assertTrue(cardRegistration_maestro.getId().length() > 0);

        assertNotNull(cardRegistration_maestro.getAccessKey());
        assertNotNull(cardRegistration_maestro.getPreregistrationData());
        assertNotNull(cardRegistration_maestro.getCardRegistrationURL());
        assertEquals(user.Id, cardRegistration_maestro.getUserId());
        assertTrue(cardRegistration_maestro.getCurrency() == CurrencyIso.EUR);
        assertEquals("CREATED", cardRegistration_maestro.getStatus());
        assertEquals(CardType.MAESTRO, cardRegistration_maestro.getCardType());
    }

    @Test
    public void getCardRegistration() throws Exception {
        CardRegistration cardRegistration = this.getJohnsCardRegistration();

        CardRegistration getCardRegistration = this.api.getCardRegistrations().get(cardRegistration.getId());

        assertTrue(getCardRegistration.getId().length() > 0);
        assertEquals(cardRegistration.getId(), getCardRegistration.getId());
    }

    @Test
    public void updateCardRegistration() throws Exception {
        CardRegistration cardRegistration = this.getJohnsCardRegistration();
        String registrationData = this.getPaylineCorrectRegistartionData(cardRegistration);
        cardRegistration.setRegistrationData(registrationData);

        CardRegistration getCardRegistration = this.api.getCardRegistrations().update(cardRegistration);

        assertEquals(registrationData, getCardRegistration.getRegistrationData());
        assertNotNull(getCardRegistration.getCardId());
        assertEquals("VALIDATED", getCardRegistration.getStatus());
        assertEquals("000000", getCardRegistration.getResultCode());
    }
    
    
    /* The two tests below are added to cover temporary use cases, which will be
     * removed in future. */

    @Test
    public void temporaryPaymentCardCreate() throws Exception {
        UserNatural user = this.getJohn();
        TemporaryPaymentCard paymentCard = new TemporaryPaymentCard();
        paymentCard.setUserId(user.getId());
        paymentCard.setTag("Test tag");
        paymentCard.setCulture("FR");
        paymentCard.setReturnURL("http://test.com/test");
        paymentCard.setTemplateURL("https://test.com/test");

        TemporaryPaymentCard paymentCardCreated = this.api.getCards().createTemporaryPaymentCard(paymentCard);

        assertTrue(paymentCardCreated.getId().length() > 0);
        assertEquals(paymentCardCreated.getUserId(), user.getId());
    }

    @Test
    public void temporaryPaymentCardGet() throws Exception {
        UserNatural user = this.getJohn();
        TemporaryPaymentCard paymentCard = new TemporaryPaymentCard();
        paymentCard.setUserId(user.getId());
        paymentCard.setTag("Test tag");
        paymentCard.setCulture("FR");
        paymentCard.setReturnURL("http://test.com/test");
        paymentCard.setTemplateURL("https://test.com/test");
        TemporaryPaymentCard paymentCardCreated = this.api.getCards().createTemporaryPaymentCard(paymentCard);

        TemporaryPaymentCard paymentCardGet = this.api.getCards().getTemporaryPaymentCard(paymentCardCreated.getId());

        assertTrue(paymentCardGet.getId().length() > 0);
        assertEquals(paymentCardGet.getId(), paymentCardCreated.getId());
    }
}
