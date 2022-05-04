package com.thoughtworks.people.useCasePeople

import com.thoughtworks.people.businessPeople.Person

interface PersistPerson {

    fun persist(person: Person)
}