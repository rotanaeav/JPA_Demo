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
    public ProductResponse getProduct(@PathVariable Integer code){
        log.info("getProduct: {}",code);
        return null;
    }
@PostMapping
    public ProductResponse createProduct(@RequestBody CreateProductRequest request){
        log.info("Creating product: {}",request);
        return productService.createNew(request);
    }
    @PutMapping("/{code}")
    public ProductResponse updateProduct(
            @PathVariable Integer code,
            @RequestBody UpdateProductRequest updateProductRequest){
        log.info("Updating product: {},code: {}",updateProductRequest,code);
        return null;
    }
    @DeleteMapping("/{code}")
    public void deleteProduct(@PathVariable Integer code){
        log.info("Deleting product: {}",code);
    }
    @PatchMapping("{code}")
    public ProductResponse patchProduct(@PathVariable Integer code,
                                        @RequestBody UpdateProductRequest updateProductRequest){
        log.info("Patching product: {}, {}",updateProductRequest,code);
        return null;
    }




}
