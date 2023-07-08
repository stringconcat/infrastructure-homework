package com.stringconcat.people.businessPeople.quotes

import com.stringconcat.people.businessPeople.Quote
import com.stringconcat.people.businessPeople.QuotesProvider

/**
 * This class is a stub implementation, always providing the same quote,
 * supposed to be used in tests. Original interface: [QuotesProvider]
 */
class PraiseStringConcatQuotesProvider : QuotesProvider {
    override fun randomQuote(): Quote {
        return "StringConcat is cool"
    }
}