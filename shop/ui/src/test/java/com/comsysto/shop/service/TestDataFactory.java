package com.comsysto.shop.service;

import com.comsysto.common.util.SeoUtils;
import com.comsysto.shop.service.user.model.AddressInfo;
import com.comsysto.shop.service.user.model.RoleInfo;
import com.comsysto.shop.service.user.model.UserInfo;
import com.comsysto.shop.repository.product.model.ProductType;
import com.comsysto.shop.service.order.model.DeliveryAddressInfo;
import com.comsysto.shop.service.order.model.OrderInfo;
import com.comsysto.shop.service.order.model.OrderItemInfo;
import com.comsysto.shop.service.product.model.ProductInfo;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author zutherb
 */
public class TestDataFactory {
    public static OrderInfo createOrderInfo() {
        return new OrderInfo(createUserInfo(), createDeliveryAdressInfo(), createOrderItems(), "");
    }

    public static List<OrderItemInfo> createOrderItems() {
        return Arrays.asList(createOrderItem());
    }

    public static OrderItemInfo createOrderItem() {
        return new OrderItemInfo(createProductInfo(), UUID.randomUUID().toString());
    }

    public static ProductInfo createProductInfo() {
        return new ProductInfo(new ObjectId().toString(), "P1", "Salami", SeoUtils.urlFriendly("Salami"), "", ProductType.PIZZA, 5.90, "");
    }

    public static DeliveryAddressInfo createDeliveryAdressInfo() {
        return new DeliveryAddressInfo(createUserInfo());
    }

    public static UserInfo createUserInfo() {
        return new UserInfo("zutherb", "comSystoRockz2012", Collections.singleton(new RoleInfo("test")), createAddressInfo());
    }

    public static AddressInfo createAddressInfo() {
        return new AddressInfo("Lindwurmstraße 97", "80337", "München", null, null, null, null, null, 0, 0);
    }
}
