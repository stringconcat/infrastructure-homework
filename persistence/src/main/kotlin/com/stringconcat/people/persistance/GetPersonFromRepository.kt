package com.stringconcat.people.persistance

import com.stringconcat.people.businessPeople.Person
import com.stringconcat.people.persistance.model.PersonEntity
import com.stringconcat.people.persistance.repository.PersonRepository
import com.stringconcat.people.useCasePeople.GetPerson
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetPersonFromRepository(
        private val repository: PersonRepository
) : GetPerson {
    override fun get(id: UUID): Person? =
            repository
                    .findById(id)
                    .toNullable()
                    ?.let { PersonEntity.toBusiness(it) }

}