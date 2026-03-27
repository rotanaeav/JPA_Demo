package co.istad.backend.springbootmvc.service;

import co.istad.backend.springbootmvc.domain.Category;
import co.istad.backend.springbootmvc.domain.Product;
import co.istad.backend.springbootmvc.dto.CreateProductRequest;
import co.istad.backend.springbootmvc.dto.ProductResponse;
import co.istad.backend.springbootmvc.dto.UpdateProductRequest;
import co.istad.backend.springbootmvc.mapper.ProductMapper;
import co.istad.backend.springbootmvc.repository.CategoryRepository;
import co.istad.backend.springbootmvc.repository.ProductRepository;
import co.istad.backend.springbootmvc.utils.GenerateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
// constructor injection
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    //dependency injection
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private Category findCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category ID not found"
                ));
    }

    private Product findProductByCode(String code) {
        return productRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product ID not found"
                ));
    }


    @Override
    public ProductResponse partialUpdateProduct(String code, UpdateProductRequest updateProductRequest) {
        Product product = findProductByCode(code);
        if (updateProductRequest.name()!= null) {
            product.setName(updateProductRequest.name());
        }
        if (updateProductRequest.price()!=null) {
            product.setPrice(updateProductRequest.price());
        }
        product.setDescription(updateProductRequest.description());
        if (updateProductRequest.categoryId() != null) {
            Category category = findCategoryById(updateProductRequest.categoryId());
            product.setCategory(category);
        }
        productRepository.save(product);
        return productMapper.ptopResponse(product);
    }

    @Override
    public ProductResponse updateProduct(String code, UpdateProductRequest updateProductRequest) {
        Product product = findProductByCode(code);

        product.setName(updateProductRequest.name());
        product.setPrice(updateProductRequest.price());
        product.setDescription(updateProductRequest.description());

        product.setQty(updateProductRequest.qty());
//        if (updateProductRequest.qty() != null) product.setQty(updateProductRequest.qty());
//        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Qty is required");
//

        if (updateProductRequest.categoryId() != null) {
            Category category = findCategoryById(updateProductRequest.categoryId());
            product.setCategory(category);
        }
        productRepository.save(product);
        return productMapper.ptopResponse(product);
    }


    @Override
    public void deleteProduct(String code) {
        Product product = findProductByCode(code);
        product.setIsAvailable(false);
        productRepository.save(product);
//        productRepository.deleteById(code);

    }


    public ProductResponse getProductById(String code) {

        Product product = findProductByCode(code);
        return productMapper.ptopResponse(product);
    }
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
        Category category = findCategoryById(createProductRequest.categoryId());

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

        // 4. Save into a database
        product = productRepository.save(product);

        // 5. Transfer data from Entity to DTO
        return productMapper.ptopResponse(product);
    }

}
