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
    fun `Person created valid`() {
        // given
        person = Person(
            id = UUID.fromString("29f4d7e3-fd7c-4664-ad07-763326215ec4"),
            firstName = "Evgeniy",
            secondName = "Lukyanov",
            birthDate = LocalDate.of(1987,12,1),
            sex = Person.Sex.MAN,
            favoriteQuote = "make the easy things easy, and the hard things possible"
        )

        // then
        assertAll(
            Executable { assertEquals("29f4d7e3-fd7c-4664-ad07-763326215ec4", person.id.toString()) },
            Executable { assertEquals("Evgeniy", person.firstName) },
            Executable { assertEquals("Lukyanov", person.secondName) },
            Executable { assertEquals(LocalDate.of(1987,12,1), person.birthDate) },
            Executable { assertEquals(Person.Sex.MAN, person.sex) },
            Executable { assertEquals("make the easy things easy, and the hard things possible",
                person.favoriteQuote) },
            Executable { assertEquals(DEFAULT_ROBOT_AVATAR, person.avatartUrl) }
        )
    }

    @Test
    fun `Person age check - not mature`() {
        // then
        assertFalse(person.mature(DATE_OF_PROJECT))
    }

    @Test
    fun `Person age check - mature`() {
        // given
        person = person.copy(birthDate = LocalDate.of(
            /* year = */ 1950,
            /* month = */ 12,
            /* dayOfMonth = */ 1))

        // then
        assertTrue(person.mature())
    }

    @Test
    fun `Change avatar works`() {
        // when
        person.changeAvatar(DEFAULT_ROBOT_AVATAR)

        // then
        assertEquals(DEFAULT_ROBOT_AVATAR, person.avatartUrl)
    }

    @Test
    fun `Age calculated properly for person`() {
        // then
        assertEquals(1, person.age(person.birthDate.plusYears(1)))
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