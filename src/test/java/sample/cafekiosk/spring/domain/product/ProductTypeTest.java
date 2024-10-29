package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTypeTest {

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지 체크한다")
    public void containsStockType() {
        // given
        ProductType productType = ProductType.BAKERY;

        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isTrue();
//        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @CsvSource({
        "HANDMADE, false",
        "BOTTLE, true" ,
        "BAKERY, true" ,
    })
    @DisplayName("재고타입인지 테스트")
    public void test2(ProductType productType, boolean expected) {
        // given


        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> ofProductTypes(){
        return Stream.of(
                Arguments.arguments(ProductType.HANDMADE, false),
                Arguments.arguments(ProductType.BOTTLE, true),
                Arguments.arguments(ProductType.BAKERY, true)
        );
    }
    @ParameterizedTest
    @MethodSource("ofProductTypes")
    @DisplayName("재고 관련 상품타입 확인")
    void initializeGame(ProductType productType, boolean expected) {
        // given


        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }

}