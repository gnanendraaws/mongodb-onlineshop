package com.comsysto.shop.ui.event.basket;


import com.comsysto.shop.service.basket.api.Basket;
import com.comsysto.shop.service.basket.model.BasketItem;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class RemoveFromBasketEvent implements BasketChangeEvent {

    private final AjaxRequestTarget target;

    @SpringBean
    private Basket basket;

    public RemoveFromBasketEvent(BasketItem basketItem, AjaxRequestTarget target) {
        this.target = target;
        Injector.get().inject(this);
        basket.removeItem(basketItem);
    }

    public AjaxRequestTarget getTarget() {
        return target;
    }
}
