package cz.muni.fi.pv217.barewbeer.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import cz.muni.fi.pv217.barewbeer.entity.Order;
import cz.muni.fi.pv217.barewbeer.repository.OrderRepository;

@ApplicationScoped
@Transactional
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.items.forEach((orderItem) -> orderItem.order = order);
        orderRepository.persist(order);
        return order;
    }

    public Order findById(long id) {
        return orderRepository.findById(id);
    }

    public List<Order> listAll() {
        return orderRepository.listAll();
    }

    public Order updateOrder(long id, Order update) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            throw new NotFoundException(String.format("Order with id %d not found.", id));
        }

        order.merge(update);
        orderRepository.persist(order);
        return order;
    }

    public Order deleteOrder(long id) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            throw new NotFoundException(String.format("Order with id %d not found.", id));
        }

        boolean deleted = orderRepository.deleteById(id);
        return deleted ? order : null;
    }
}
