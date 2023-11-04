package com.acme.rewear.platform.store.interfaces.acl;

import com.acme.rewear.platform.store.domain.model.commands.CreateProductCommand;
import com.acme.rewear.platform.store.domain.model.queries.GetProductByNameQuery;
import com.acme.rewear.platform.store.domain.model.valueobjects.ProductName;
import com.acme.rewear.platform.store.domain.services.ProductCommandService;
import com.acme.rewear.platform.store.domain.services.ProductQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProductContextFacade {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductContextFacade(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    public Long createProduct(String name, String description, String size, String state, String price, String color, String urlToImg){
        var createProductCommand = new CreateProductCommand(name, description, size, state, price, color, urlToImg);
        return productCommandService.handle(createProductCommand);
    }

    public Long getProductByName(String name){
        var getProductByNameQuery = new GetProductByNameQuery(new ProductName(name));
        var  product = productQueryService.handle(getProductByNameQuery);
        if (product.isEmpty()){
            return 0L;
        }
        return product.get().getId();
    }

}
