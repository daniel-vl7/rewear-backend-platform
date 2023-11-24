package com.savewise.rewear.platform.store.interfaces.rest;

import com.savewise.rewear.platform.store.domain.model.commands.DeleteProductCommand;
import com.savewise.rewear.platform.store.domain.model.queries.GetAllProductsQuery;
import com.savewise.rewear.platform.store.domain.model.queries.GetProductByIdQuery;
import com.savewise.rewear.platform.store.domain.services.ProductCommandService;
import com.savewise.rewear.platform.store.domain.services.ProductQueryService;
import com.savewise.rewear.platform.store.interfaces.rest.resources.CreateProductResource;
import com.savewise.rewear.platform.store.interfaces.rest.resources.ProductResource;
import com.savewise.rewear.platform.store.interfaces.rest.resources.UpdateProductResource;
import com.savewise.rewear.platform.store.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.savewise.rewear.platform.store.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.savewise.rewear.platform.store.interfaces.rest.transform.UpdateProductCommandFomResourceAssembles;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Product Management Endpoints")
public class ProductsController {
    private final ProductCommandService productCommandService;

    private final ProductQueryService productQueryService;

    public ProductsController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource resource){
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var productId = productCommandService.handle(createProductCommand);
        if (productId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);

        if (product.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long productId){
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts(){
        var getAllProductsQuery = new GetAllProductsQuery();
        var products = productQueryService.handle(getAllProductsQuery);
        var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResources);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        try {
            var deleteProductCommand = new DeleteProductCommand(productId);
            productCommandService.handle(deleteProductCommand);
            return ResponseEntity.ok("Product with id " + productId + " deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting product with id " + productId + ": " + ex.getMessage());
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductResource resource){
        var updateProductCommand = UpdateProductCommandFomResourceAssembles.toCommandFromResource(productId, resource);
        productCommandService.handle(updateProductCommand);

        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }
}
