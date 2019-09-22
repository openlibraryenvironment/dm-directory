package org.olf.okapi.modules.directory

import com.k_int.web.toolkit.tags.Tag

import grails.gorm.MultiTenant


class Address  implements MultiTenant<Address>  {

  String id
  String addressLabel
  DirectoryEntry owner

  static hasMany = [
    lines: AddressLine,
    tags:Tag
  ]

  static mappedBy = [
    lines: 'owner'
  ]

  static belongsTo = [
    owner: DirectoryEntry
  ]

  static mapping = {
                 id column:'addr_id', generator: 'uuid2', length:36
       addressLabel column:'addr_label'
  }

  static constraints = {
    addressLabel(nullable:false, blank:false)
  }
}
