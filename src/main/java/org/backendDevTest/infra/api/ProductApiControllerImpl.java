package org.backendDevTest.infra.api;

import org.backendDevTest.infra.model.ProductDetail;
import org.backendDevTest.infra.model.ProductDetailBasic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductApiControllerImpl implements ProductApi {

    @Override
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailBasic> getProductId(@PathVariable("productId") String productId) {
        return ResponseEntity.ok(new ProductDetailBasic());
    }

    @Override
    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDetail>> getSimilarProductDetails(@PathVariable("productId") String productId) {
        return ProductApi.super.getSimilarProductDetails(productId);
    }

    @Override
    @GetMapping("/{productId}/similarids")
    public ResponseEntity<List<String>> getSimilarProductsIds(@PathVariable("productId") String productId) {
        return ProductApi.super.getSimilarProductsIds(productId);
    }
}