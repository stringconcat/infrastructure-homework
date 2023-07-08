package com.stringconcat.people.businessPeople

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.time.LocalDate
import java.util.*

val DATE_OF_PROJECT: LocalDate = LocalDate.of(/* year = */ 2023, /* month = */ 7, /* dayOfMonth = */ 8)

class PersonTest {
    private lateinit var person: Person

    @BeforeEach
    fun setup() {
        // given
        person = defaultPerson()
    }


    @Test
    fun `should create correct person when valid parameters provided`() {
        // given
        val personId = UUID.fromString("29f4d7e3-fd7c-4664-ad07-763326215ec4")
        val firstName = "Evgeniy"
        val lastName = "Lukyanov"
        val birthDate = LocalDate.of(1987,12,1)
        val personSex = Person.Sex.MAN
        val favoriteQuote = "make the easy things easy, and the hard things possible"

        // when
        person = Person(
            id = personId,
            firstName = firstName,
            secondName = lastName,
            birthDate = birthDate,
            sex = personSex,
            favoriteQuote = favoriteQuote
        )

        // then
        assertAll(
            Executable { assertEquals(personId.toString(), person.id.toString()) },
            Executable { assertEquals(firstName, person.firstName) },
            Executable { assertEquals(lastName, person.secondName) },
            Executable { assertEquals(birthDate, person.birthDate) },
            Executable { assertEquals(personSex, person.sex) },
            Executable { assertEquals(favoriteQuote, person.favoriteQuote) },
            Executable { assertEquals(DEFAULT_ROBOT_AVATAR, person.avatartUrl) }
        )
    }

    @Test
    fun `should return mature false when age less than 40`() {
        // then
        assertFalse(person.mature(DATE_OF_PROJECT))
    }

    @Test
    fun `should return mature true when age bigger than 40`() {
        // given
        val personBirthDate =  LocalDate.of(/* year = */ 1950, /* month = */ 12, /* dayOfMonth = */ 1)

        // when
        person = person.copy(birthDate = personBirthDate)

        // then
        assertTrue(person.mature())
    }

    @Test
    fun `should change person avatar when changeAvatar(String) called`() {
        // when
        person.changeAvatar(DEFAULT_ROBOT_AVATAR)

        // then
        assertEquals(DEFAULT_ROBOT_AVATAR, person.avatartUrl)
    }

    @Test
    fun `should calculate age properly when age(LocalDate) called`() {
        // given
        val yearAfterBirth = person.birthDate.plusYears(1)
        val expectedAgeAfterBirth = 1

        // when
        val actualAgeAfterBirth = person.age(yearAfterBirth)

        // then
        assertEquals(expectedAgeAfterBirth, actualAgeAfterBirth)
    }

    private fun defaultPerson(): Person {
        return Person(
            id = UUID.fromString("29f4d7e3-fd7c-4664-ad07-763326215ec4"),
            firstName = "Sergey",
            secondName = "Bukharov",
            birthDate = LocalDate.of(1987,12,1),
            sex = Person.Sex.MAN,
            avatartUrl = "https://avatars.dicebear.com/v2/male/my-somffething.svg",
            favoriteQuote = "make the easy things easy, and the hard things possible"
        )
    }
}