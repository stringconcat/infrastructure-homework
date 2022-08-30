package com.stringconcat.people.useCasePeople

import com.stringconcat.people.businessPeople.Person
import com.stringconcat.people.businessPeople.PersonGenerator
import java.time.LocalDate
import java.util.*
import javax.inject.Named

@Named
class CreateNewPersonUseCase(
        private val persistPerson: PersistPerson,
        private val personGenerator: PersonGenerator
) {
    operator fun invoke(
            personInput: PersonCreationSummary
    ): Person {
        val inputSex = when(personInput.gender.lowercase(Locale.getDefault())) {
            "male" -> Person.Sex.MAN
            "female" -> Person.Sex.WOMAN
            else -> Person.Sex.MAN
        }

        val generatedPerson = personGenerator.generate(
                firstName = personInput.firstName,
                secondName = personInput.secondName,
                birthDate = LocalDate.parse(personInput.birthDate),
                sex = inputSex
        )

        persistPerson.persist(generatedPerson)

        // TODO Send email to user
        // TODO Add the event to event queue

        return generatedPerson
    }
}

data class PersonCreationSummary(
        val firstName: String,
        val secondName: String,
        val birthDate: String,
        val gender: String
)
