package co.istad.backend.springbootmvc.repository;

import co.istad.backend.springbootmvc.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository
extends JpaRepository<Order, UUID> {
}
