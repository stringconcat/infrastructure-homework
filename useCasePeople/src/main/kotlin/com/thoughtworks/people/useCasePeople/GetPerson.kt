package com.thoughtworks.people.useCasePeople

import com.thoughtworks.people.businessPeople.Person
import java.util.*

interface GetPerson {

    fun get(id: UUID): Person?
}