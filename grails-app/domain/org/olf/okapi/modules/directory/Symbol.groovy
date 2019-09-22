package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant
import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.custprops.types.CustomPropertyContainer
import com.k_int.web.toolkit.tags.Tag
import grails.gorm.MultiTenant
import com.k_int.web.toolkit.refdata.RefdataValue;
import com.k_int.web.toolkit.refdata.Defaults;
import com.k_int.web.toolkit.databinding.BindUsingWhenRef
import groovy.util.logging.Log4j
import grails.web.databinding.DataBindingUtils

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
