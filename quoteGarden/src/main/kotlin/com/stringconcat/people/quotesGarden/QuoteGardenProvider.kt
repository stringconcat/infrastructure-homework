package com.stringconcat.people.quotesGarden

import com.stringconcat.people.businessPeople.Quote
import com.stringconcat.people.businessPeople.QuotesProvider
import org.springframework.web.client.RestTemplate
import javax.inject.Named

const val defaultQuote = "whoops, something went wrong"

@Named
class QuoteGardenProvider : QuotesProvider {

    private val getRandomUrl = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json"

    override fun randomQuote(): Quote =
            RestTemplate()
                    .getForEntity(getRandomUrl, QuoteResponse::class.java)
                    .body?.quoteText ?: defaultQuote

    internal data class QuoteResponse(
            val quoteText: String,
            val quoteAuthor: String,
            val senderName: String,
            val senderLink: String,
            val quoteLink: String
    )
}