package co.istad.backend.springbootmvc.service;

import co.istad.backend.springbootmvc.dto.CreateProductRequest;
import co.istad.backend.springbootmvc.dto.ProductResponse;
import co.istad.backend.springbootmvc.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse partialUpdateProduct(String code, UpdateProductRequest updateProductRequest);
    ProductResponse updateProduct(String code, UpdateProductRequest updateProductRequest);
    void deleteProduct(String code);
    ProductResponse getProductById(String code);
    Page<ProductResponse> getProducts(int pageNumber, int pageSize);
    ProductResponse createNew(CreateProductRequest createProductRequest);
}
