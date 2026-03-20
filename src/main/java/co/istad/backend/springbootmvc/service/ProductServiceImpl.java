package co.istad.backend.springbootmvc.service;

import co.istad.backend.springbootmvc.domain.Category;
import co.istad.backend.springbootmvc.domain.Product;
import co.istad.backend.springbootmvc.dto.CreateProductRequest;
import co.istad.backend.springbootmvc.dto.ProductResponse;
import co.istad.backend.springbootmvc.mapper.ProductMapper;
import co.istad.backend.springbootmvc.repository.CategoryRepository;
import co.istad.backend.springbootmvc.repository.ProductRepository;
import co.istad.backend.springbootmvc.utils.GenerateUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductResponse> getProducts(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return productRepository
                .findAll(pageable)
                .map(productMapper::ptopResponse);
    }

    @Override
    public ProductResponse createNew(CreateProductRequest createProductRequest) {
        // TODO: write your business logic
        // 1. Validate category ID (exists or not)
        Category category = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category ID not found"
                ));

        // 2. Transfer data from DTO to Entity
        Product product = new Product();
        product.setName(createProductRequest.name());
        product.setPrice(createProductRequest.price());
        product.setQty(createProductRequest.qty());
        product.setDescription(createProductRequest.description());
        product.setCategory(category);

        // 3. System data
        product.setCode(GenerateUtils.randomProductCode());
        product.setIsAvailable(true);

        // 4. Save into database
        product = productRepository.save(product);

        // 5. Transfer data from Entity to DTO
        return productMapper.ptopResponse(product);
    }
}
