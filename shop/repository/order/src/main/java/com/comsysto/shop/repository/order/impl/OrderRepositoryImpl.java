package com.comsysto.shop.repository.order.impl;

import com.comsysto.common.repository.AbstractRepositoryImpl;
import com.comsysto.shop.repository.order.api.OrderRepository;
import com.comsysto.shop.repository.order.model.Order;
import com.comsysto.shop.repository.user.api.UserRepository;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author zutherb
 */
@Repository("orderRepository")
public class OrderRepositoryImpl extends AbstractRepositoryImpl<Order> implements OrderRepository {

    private UserRepository userRepository;

    @Autowired
    public OrderRepositoryImpl(@Qualifier("mongoTemplate") MongoOperations mongoOperations,
                               @Qualifier("userRepository") UserRepository userRepository) {
        super(mongoOperations, Order.class);
        this.userRepository = userRepository;
    }

    @Override
    public List<Order> findAll(int limit, int offset, Sort sort) {
        // TODO: implement query for all orders
        return Collections.emptyList();
    }

    @Override
    public List<Order> findInRange(Date fromDate, Date toDate, int limit, int offset, Sort sort) {
        // TODO: implement query for all orders in given date range
        return Collections.emptyList();
    }

    @Override
    public Order findFirstOrder() {
        Query query = Query.query(Criteria.where("orderDate").exists(true));
        query.with(new Sort(Sort.Direction.ASC, "orderDate"));
        return mongoOperations.findOne(query, Order.class);
    }

    @Override
    public Order findLastOrder() {
        Query query = Query.query(Criteria.where("orderDate").exists(true));
        query.with(new Sort(Sort.Direction.DESC, "orderDate"));
        return mongoOperations.findOne(query, Order.class);
    }

    @Override
    public long countInRange(Date fromDate, Date toDate) {
        Criteria criteria = Criteria.where("orderDate").gte(fromDate).lte(toDate);
        Query query = Query.query(criteria);
        return mongoOperations.count(query, Order.class);
    }

    @Override
    public Order findByOrderId(long orderId) {
        Criteria criteria = Criteria.where("orderId").is(orderId);
        Query query = Query.query(criteria);
        return mongoOperations.findOne(query, Order.class);
    }

    @Override
    public void save(Order entity) {
        Validate.notNull(userRepository.findById(entity.getUserId()));
        super.save(entity);
    }
}