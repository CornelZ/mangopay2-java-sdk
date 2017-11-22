package com.mangopay.core.APIs.implementation;

import com.mangopay.MangoPayApi;
import com.mangopay.core.APIs.ApiBase;
import com.mangopay.core.APIs.CardApi;
import com.mangopay.core.enumerations.Validity;
import com.mangopay.entities.Card;
import com.mangopay.entities.CardPreAuthorization;

import java.util.List;

/**
 * API for cards.
 */
public class CardApiImpl extends ApiBase implements CardApi {

    /**
     * Instantiates new CardApiImpl object.
     * @param root Root/parent instance that holds the OAuthToken and Configuration instance
     */
    public CardApiImpl(MangoPayApi root) { super(root); }

    @Override
    public Card get(String cardId) throws Exception {
        return this.getObject(Card.class, "card_get", cardId);
    }

    @Override
    public List<Card> getByFingerprint(String fingerprint) throws Exception {
        return this.getList(Card[].class, Card.class, "cards_get_by_fingerprint", null, fingerprint);
    }

    @Override
    public Card update(Card card) throws Exception {
        return this.updateObject(Card.class, "card_save", card);
    }

    @Override
    public Card disable(Card card) throws Exception {
        card.setValidity(Validity.INVALID);
        return update(card);
    }

    @Override
    public List<CardPreAuthorization> getCardPreAuthorizations(String cardId) throws Exception {
        return this.getList(CardPreAuthorization[].class, CardPreAuthorization.class, "card_get_preauthorization",null,cardId);
    }

}
