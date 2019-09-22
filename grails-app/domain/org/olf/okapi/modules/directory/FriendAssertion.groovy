package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant



class FriendAssertion  implements MultiTenant<DirectoryEntry>  {

  String id
  DirectoryEntry owner
  DirectoryEntry friend_org

  static hasMany = [
  ]

  static mappedBy = [
  ]

  static belongsTo = [ owner: DirectoryEntry, friend_org: DirectoryEntry ]

  static mapping = {
                 id column:'fa_id', generator: 'uuid2', length:36
              owner column:'fa_owner'
         friend_org column:'fa_friend_org'
  }

  static constraints = {
           owner(nullable:false, blank:false)
      friend_org(nullable:false, blank:false)
  }
}
