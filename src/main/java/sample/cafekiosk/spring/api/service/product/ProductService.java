package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.spring.domain.product.*;

import java.util.List;

/*
* readOnly = true : 읽기 전용
* CRUD에서 CUD 동작 X / only Read
* JPA : CUD 스냅샷 저장, 변경감지 X (성능 향상)
*
* CQRS - Command(CUD) / Read Query 분리
* Read 빈도가 훨씬 많음
* Read에 의해 CUD가 영향 받아도 안되고 CUD에 의해 Read Query가 영향 받아서도 안됨
* */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        // productNumber
        // ex 001 002 003
        // DM에 저장된 마지막 Product 상품의 번호 + 1
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    public List<ProductResponse> getSellingProducts(){
        List<Product> product = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return product.stream()
                .map(ProductResponse::of)
                .toList();
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProduct();
        if (latestProductNumber ==  null){
            return "001";
        }
        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
