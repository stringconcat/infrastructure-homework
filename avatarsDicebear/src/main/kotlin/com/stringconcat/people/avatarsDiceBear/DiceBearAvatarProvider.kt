package com.stringconcat.people.avatarsDiceBear

import com.stringconcat.people.businessPeople.AvatarProvider
import com.stringconcat.people.businessPeople.Person
import javax.inject.Named

@Named
class DiceBearAvatarProvider : AvatarProvider {
    override fun createForPerson(person: Person): String {
        val uniqueValue = person.firstName + person.secondName
        return "https://avatars.dicebear.com/v2/" +
                "${if (person.sex == Person.Sex.MAN) "male" else "female"}/$uniqueValue.svg"
    }

}
