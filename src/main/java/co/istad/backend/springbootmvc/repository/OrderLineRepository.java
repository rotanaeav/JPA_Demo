package co.istad.backend.springbootmvc.repository;

import co.istad.backend.springbootmvc.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderLineRepository
        extends JpaRepository<OrderLine, UUID> {

}
