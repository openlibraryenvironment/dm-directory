package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant

class SigningKey  implements MultiTenant<SigningKey>  {

  String id
  Date validFrom
  Date validTo
  String name
  String publicKey
  String privateKey

  static hasMany = [
  ]

  static mappedBy = [
  ]

  static belongsTo = [
    owner: DirectoryEntry
  ]

  static mapping = {
                 id column:'sk_id', generator: 'uuid2', length:36
              owner column:'sk_owner_fk'
          validFrom column:'sk_valid_from'
            validTo column:'sk_valid_to'
               name column:'sk_name'
          publicKey column:'sk_public_key', type:'text'
         privateKey column:'sk_private_key', type:'text'
  }

  static constraints = {
             owner(nullable:false)
         validFrom(nullable:false, blank:false)
           validTo(nullable:true, blank:false)
              name(nullable:true, blank:false)
         publicKey(nullable:false, blank:false)
        privateKey(nullable:true, blank:false)

  }
}
