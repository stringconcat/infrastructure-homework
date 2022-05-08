package com.stringconcat.people.useCasePeople

import com.stringconcat.people.businessPeople.Person
import java.util.*
import javax.inject.Named

@Named
class GetPersonUseCase(
        private val getPerson: GetPerson
) {

    operator fun invoke(id: UUID): Person? = getPerson.get(id)
}