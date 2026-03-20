package co.istad.backend.springbootmvc.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "order_lines")

public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderLineId;
    @ManyToOne
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private BigDecimal price;
}
