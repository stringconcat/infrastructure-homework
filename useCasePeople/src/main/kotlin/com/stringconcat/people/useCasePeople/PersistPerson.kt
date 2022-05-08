package com.stringconcat.people.useCasePeople

import com.stringconcat.people.businessPeople.Person

interface PersistPerson {

    fun persist(person: Person)
}