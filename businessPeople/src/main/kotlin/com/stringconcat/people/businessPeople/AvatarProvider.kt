package com.stringconcat.people.businessPeople

interface AvatarProvider {
    fun createForPerson(person: Person): String
}
