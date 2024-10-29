package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {

    @Test
    @DisplayName("이름 가져오기")
    public void getName() {
        // given
        Americano americano = new Americano();

        // when


        // then
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @Test
    @DisplayName("가격 가져오기")
    public void getPrice() {
        // given
        Americano americano = new Americano();

        // when


        // then
        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}