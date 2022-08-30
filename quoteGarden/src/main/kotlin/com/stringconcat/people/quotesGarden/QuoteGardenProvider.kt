package com.stringconcat.people.quotesGarden

import com.stringconcat.people.businessPeople.Quote
import com.stringconcat.people.businessPeople.QuotesProvider
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestTemplate
import javax.inject.Named

const val DEFAULT_QUOTE = "whoops, something went wrong"

@Named
class QuoteGardenProvider : QuotesProvider {

    private val getRandomUrl = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json"

    override fun randomQuote(): Quote {

        val restTemplate = RestTemplate()
        restTemplate.errorHandler = EmptyErrorHandler()

        val response = restTemplate.getForEntity(getRandomUrl, QuoteResponse::class.java)

        check(response.statusCode == HttpStatus.OK) {
            return DEFAULT_QUOTE
        }

        return response.body?.quoteText ?: DEFAULT_QUOTE
    }

    internal data class QuoteResponse(
            val quoteText: String,
            val quoteAuthor: String,
            val senderName: String,
            val senderLink: String,
            val quoteLink: String
    )
}

class EmptyErrorHandler : ResponseErrorHandler {
    override fun hasError(response: ClientHttpResponse) = false

    override fun handleError(response: ClientHttpResponse) {
        // nothing to do
    }
}
