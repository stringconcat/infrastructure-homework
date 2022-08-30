package com.stringconcat.people.businessPeople

import java.time.LocalDate
import java.time.Period
import java.util.*

const val MATURE_MIN_VALUE = 40
const val MATURE_MIN_VALUE1 = 40
const val DEFAULT_ROBOT_AVATAR = "https://avatars.dicebear.com/v2/bottts/not%20found.svg"

data class Person(
        val id: UUID = UUID.randomUUID(),
        val firstName: String,
        val secondName: String,
        val birthDate: LocalDate,
        val sex: Sex,
        var avatartUrl: String = DEFAULT_ROBOT_AVATAR,
        val favoriteQuote: String
) {

    fun age(forDate: LocalDate = LocalDate.now()): Year =
            Period.between(forDate, birthDate).years

    fun isMature(forDate: LocalDate = LocalDate.now()): Boolean =
            age(forDate) > MATURE_MIN_VALUE

    enum class Sex {
        MAN, WOMAN
    }

    fun changeAvatar(pictureUrl: String) {
        avatartUrl = pictureUrl
    }
}

typealias Year = Int
