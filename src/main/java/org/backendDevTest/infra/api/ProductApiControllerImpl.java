package org.backendDevTest.infra.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.backendDevTest.application.service.ProductService;
import org.backendDevTest.infra.model.ProductDetail;
import org.backendDevTest.infra.model.ProductDetailBasic;
import org.backendDevTest.infra.repository.MockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductApiControllerImpl implements ProductApi {

    @Autowired
    private ProductService productService;

    @Override
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailBasic> getProductId(@PathVariable("productId") String productId) {
        try {
            return ResponseEntity.ok(productService.getProductId(productId));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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