package com.acme.rewear.platform.store.domain.model.queries;

import com.acme.rewear.platform.store.domain.model.valueobjects.ProductName;

public record GetProductByNameQuery(ProductName productName) {
}
