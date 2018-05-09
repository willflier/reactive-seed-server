package mixit.util.validator

import org.springframework.stereotype.Component
import java.net.MalformedURLException
import java.net.URL

@Component
class UrlValidator {

    fun isValid(value: String?): Boolean {
        if (value.isNullOrEmpty()) return true

        val url: URL
        try {
            url = URL(value)
        } catch (e: MalformedURLException) {
            return false
        }

        if ("http" != url.protocol && "https" != url.protocol) {
            return false
        }

        return url.port == 80 || url.port == -1
    }
}
