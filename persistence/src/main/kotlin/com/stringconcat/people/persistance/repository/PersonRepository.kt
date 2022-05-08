package com.stringconcat.people.persistance.repository

import com.stringconcat.people.persistance.model.PersonEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface PersonRepository: CrudRepository<PersonEntity, UUID>