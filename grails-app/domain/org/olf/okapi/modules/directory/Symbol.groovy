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

@BindUsingWhenRef({ obj, propName, source, isCollection ->

  Symbol val = null;

  println("Symbol::@BindUsingWhenRef ${obj} ${propName} ${source}");

  def data = isCollection ? source : source[propName]

  // If the data is asking for null binding then ensure we return here.
  if (data == null) {
    return null
  }



  if ( data instanceof Map ) {
    if ( data.id ) {
      val = Symbol.read(data.id);
    }
    else if ( ( data.symbol != null ) && ( data.authority != null ) ) {

      def qr = Symbol.executeQuery('select s from Symbol as s where s.symbol=:s and s.authority.symbol=:a',[s:data.symbol, a:data.authority])

      if ( qr.size() == 1 )
        val = qr.get(0);

      if ( val == null ) {
        println("Create new directory entry, ${data} - prop=${propName}, source=${source}, source.id=${source?.id}");
        val = new Symbol()
        if ( propName == 'symbols' ) {
          println("Add new directory entry to parent units");
          obj.addToSymbols(val);
          // val.owner = obj
        }
      }
    }
  }

  val
})
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
              owner(nullable:false)
          authority(nullable:false)
             symbol(nullable:false, blank:false)
           priority(nullable:true, blank:false)

  }
}
