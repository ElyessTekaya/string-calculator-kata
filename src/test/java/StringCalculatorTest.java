import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {
    //Step 1
    @Test
    void step_1_when_empty_string_should_return_zero() {
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    @Test
    void step_1_when_one_number_should_return_number() {
        assertThat(StringCalculator.add("3")).isEqualTo(3);
    }

    @Test
    void step_1_when_two_number_split_by_comma_should_return_sum() {
        assertThat(StringCalculator.add("1,2")).isEqualTo(3);
    }

    //Step 2
    @Test
    void step_2_when_many_numbers_should_return_sum() {
        assertThat(StringCalculator.add("1,3,6")).isEqualTo(10);
    }

    //Step 3
    @Test
    void step_3_when_numbers_split_by_commas_or_back_line_should_return_sum() {
        assertThat(StringCalculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    void step_3_when_comma_next_to_back_line_should_throw_exception() {
        assertThatThrownBy(() -> StringCalculator.add("1,\n5")).isInstanceOf(NumberFormatException.class);
    }

    //Step 4
    @Test
    void step_4_when_given_default_delimiter_should_return_sum() {
        assertThat(StringCalculator.add("//;\n1;2")).isEqualTo(3);
        assertThat(StringCalculator.add("//;\n1;2;7")).isEqualTo(10);
        assertThat(StringCalculator.add("//#\n1#2#7")).isEqualTo(10);
    }

    //Step 5
    @Test
    void step_5_when_contains_negative_numbers_should_throw_them_with_exception() {
        assertThatThrownBy(() -> StringCalculator.add("1,-5")).isInstanceOf(RuntimeException.class).hasMessageContaining("negatives not allowed -5");
        assertThatThrownBy(() -> StringCalculator.add("//#\n1#2#-5#7#-3")).isInstanceOf(RuntimeException.class).hasMessageContaining("negatives not allowed -5 -3");
    }
}