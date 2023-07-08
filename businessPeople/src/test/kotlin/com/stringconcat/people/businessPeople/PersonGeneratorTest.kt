package com.stringconcat.people.businessPeople

import com.stringconcat.people.businessPeople.avatars.DefaultBotAvatarProvider
import com.stringconcat.people.businessPeople.quotes.PraiseStringConcatQuotesProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.time.LocalDate

class PersonGeneratorTest {
    companion object {
        private lateinit var personGenerator: PersonGenerator

        @BeforeAll
        @JvmStatic
        fun setup() {
            personGenerator = PersonGenerator(
                quoteProvider = PraiseStringConcatQuotesProvider(),
                avatarProvider = DefaultBotAvatarProvider()
            )
        }
    }

    @Test
    fun `should generate valid person when valid values provided`() {
        // given
        val firstName = "Evgeniy"
        val lastName = "Lukyanov"
        val birthDate = LocalDate.of(1987,12,1)
        val personSex = Person.Sex.MAN

        // when
        val person = personGenerator.generate(
            firstName = firstName,
            secondName = lastName,
            birthDate = birthDate,
            sex = personSex
        )

        assertAll(
            Executable { assertEquals(firstName, person.firstName) },
            Executable { assertEquals(lastName, person.secondName) },
            Executable { assertEquals(birthDate, person.birthDate) },
            Executable { assertEquals(personSex, person.sex) }
        )
    }
}