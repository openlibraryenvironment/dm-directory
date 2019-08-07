package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant
import com.k_int.web.toolkit.tags.Tag
import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.custprops.types.CustomPropertyContainer
import com.k_int.web.toolkit.refdata.RefdataValue;
import com.k_int.web.toolkit.refdata.Defaults;


class Address  implements MultiTenant<Address>  {

  String id
  String addressLabel

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
