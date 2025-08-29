package org.backendDevTest.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.backendDevTest.infra.model.ProductDetailBasic;
import org.backendDevTest.infra.repository.MockRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final MockRepository mockRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public ProductService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }

    public ProductDetailBasic getProductId(String productId) throws JsonProcessingException {
        return Optional.ofNullable(mapper.readValue(mockRepository.getProduct(productId), ProductDetailBasic.class)).orElse(new ProductDetailBasic());
    }
}
