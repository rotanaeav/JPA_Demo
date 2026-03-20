package co.istad.backend.springbootmvc.service;

import co.istad.backend.springbootmvc.dto.CreateProductRequest;
import co.istad.backend.springbootmvc.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    public Page<ProductResponse> getProducts(int pageNumber, int pageSize);
    public ProductResponse createNew(CreateProductRequest productResponse);
}
