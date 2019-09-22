package org.olf.okapi.modules.directory

import com.k_int.web.toolkit.databinding.BindUsingWhenRef

import grails.gorm.MultiTenant

@BindUsingWhenRef({ obj, propName, source, isCollection = false ->
  CustomBinders.bindSymbol(obj, propName, source, isCollection)
})
class Symbol  implements MultiTenant<Symbol>  {

  String id
  String symbol
  String priority
  DirectoryEntry owner
  NamingAuthority authority

  static hasMany = [
  ]

  static mappedBy = [
  ]

  static belongsTo = [
    owner: DirectoryEntry
  ]

  static mapping = {
                 id column:'sym_id', generator: 'uuid2', length:36
              owner column:'sym_owner_fk'
          authority column:'sym_authority_fk'
             symbol column:'sym_symbol'
           priority column:'sym_priority'
  }

  static constraints = {
              owner(nullable:false)
          authority(nullable:false)
             symbol(nullable:false, blank:false)
           priority(nullable:true, blank:false)

  }
}
