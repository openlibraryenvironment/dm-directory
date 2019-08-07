package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant
import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.custprops.types.CustomPropertyContainer
import com.k_int.web.toolkit.tags.Tag
import grails.gorm.MultiTenant
import com.k_int.web.toolkit.refdata.RefdataValue;
import com.k_int.web.toolkit.refdata.Defaults;

class Symbol  implements MultiTenant<Symbol>  {

  String id
  String symbol
  String priority

  static hasMany = [
  ]

  static mappedBy = [
  ]

  static belongsTo = [
    owner: DirectoryEntry,
    authority: NamingAuthority
  ]

  static mapping = {
                 id column:'sym_id', generator: 'uuid2', length:36
              owner column:'sym_owner_fk'
          authority column:'sym_authority_fk'
             symbol column:'sym_symbol'
           priority column:'sym_priority'
  }

  static constraints = {
              owner(nullable:false, blank:false)
          authority(nullable:false, blank:false)
             symbol(nullable:false, blank:false)
           priority(nullable:true, blank:false)

  }
}
