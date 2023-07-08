package com.stringconcat.people.businessPeople.avatars

import com.stringconcat.people.businessPeople.AvatarProvider
import com.stringconcat.people.businessPeople.DEFAULT_ROBOT_AVATAR
import com.stringconcat.people.businessPeople.Person

/**
 * This class is a stub implementation, always providing the same avatar URL,
 * supposed to be used in tests. Original interface: [AvatarProvider]
 */
class DefaultBotAvatarProvider : AvatarProvider {
    override fun createForPerson(person: Person): String {
        return DEFAULT_ROBOT_AVATAR
    }
}