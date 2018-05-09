package mixit.util.validator

import org.springframework.stereotype.Component

@Component
class MaxLengthValidator {

    fun isValid(value: String, max: Int): Boolean {
        val length = value.length
        return length <= max
    }
}
