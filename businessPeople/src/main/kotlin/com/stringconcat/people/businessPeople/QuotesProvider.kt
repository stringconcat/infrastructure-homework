package com.stringconcat.people.businessPeople

interface QuotesProvider {
    fun randomQuote(): Quote
}

typealias Quote = String