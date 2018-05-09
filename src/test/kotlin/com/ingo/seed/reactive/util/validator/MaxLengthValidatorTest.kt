package com.ingo.seed.reactive.util.validator

import mixit.util.validator.MaxLengthValidator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Test {@link MaxLengthValidator}
 */
class MaxLengthValidatorTest{

    val validator = MaxLengthValidator()

    @Test
    fun `should valid text`() {
        assertThat(validator.isValid("ingo", 10)).isTrue()
        assertThat(validator.isValid("", 10)).isTrue()
        assertThat(validator.isValid("ingo", 3)).isFalse()
    }
}