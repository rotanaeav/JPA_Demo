package co.istad.backend.springbootmvc.controller;

import co.istad.backend.springbootmvc.dto.CreateProductRequest;
import co.istad.backend.springbootmvc.dto.ProductResponse;
import co.istad.backend.springbootmvc.dto.UpdateProductRequest;
import co.istad.backend.springbootmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Slf4j //log
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    //getall
    @GetMapping()
    public Page<ProductResponse> getProducts(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return productService.getProducts(pageNumber, pageSize);
    }
    @GetMapping("/{code}")
    public ProductResponse getProduct(@PathVariable String code){
        return productService.getProductById(code);
    }

@ResponseStatus(HttpStatus.CREATED)
@PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody CreateProductRequest request){
        log.info("Creating product: {}",request);
        return productService.createNew(request);
    }

    @PutMapping("/{code}")
    public ProductResponse updateProduct(
            @PathVariable String code,
            @Valid @RequestBody UpdateProductRequest updateProductRequest){
        return productService.updateProduct(code,updateProductRequest);
    }
    @DeleteMapping("/{code}")
    public void deleteProduct(@PathVariable String code){
        productService.deleteProduct(code);
    }
    @PatchMapping("{code}")
    public ProductResponse patchProduct(@PathVariable String code,
                                        @Valid @RequestBody UpdateProductRequest updateProductRequest){
     return productService.partialUpdateProduct(code,updateProductRequest);
    }




}
