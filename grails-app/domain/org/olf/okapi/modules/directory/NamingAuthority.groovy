package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant
import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.custprops.types.CustomPropertyContainer
import com.k_int.web.toolkit.tags.Tag
import grails.gorm.MultiTenant
import com.k_int.web.toolkit.refdata.RefdataValue;
import com.k_int.web.toolkit.refdata.Defaults;
import com.k_int.web.toolkit.databinding.BindUsingWhenRef

/**
 * Some special sauce to allow us to transparently state the authority as a string instead of an object
 */
@BindUsingWhenRef({ obj, propName, source ->

  //@BindUsingWhenRef(org.olf.okapi.modules.directory.Symbol : (unsaved), authority, grails.databinding.SimpleMapDataBindingSource@5d0e003a
  //Result null

  NamingAuthority val = null;

  def data = source.getAt(propName)

  // If the data is asking for null binding then ensure we return here.
  if (data == null) {
    return null
  }

  if ( data instanceof Map ) {
    if ( data.id ) {
      val = NamingAuthority.read(data.id);
    }
    else if ( data.symbol ) {
      val = NamingAuthority.findBySymbol(data.symbol) ?: new NamingAuthority(symbol:data.symbol).save(flush:true, failOnError:true)
      // If the ID isn't present - go fetch it
      if ( val.id == null )
        val = NamingAuthority.findBySymbol(data.symbol)
    }
  }
  else if ( data instanceof String ) {
    val = NamingAuthority.findBySymbol(data) ?: new NamingAuthority(symbol:data).save(flush:true, failOnError:true)
    // If the ID isn't present - go fetch it
    if ( val.id == null )
      val = NamingAuthority.findBySymbol(data.symbol)
  }

  val
})
class NamingAuthority implements MultiTenant<NamingAuthority>  {

  String id
  String symbol

  static hasMany = [
  ]

  static mappedBy = [
  ]

  static mapping = {
                 id column:'na_id', generator: 'uuid2', length:36
             symbol column:'na_symbol'
  }

  static constraints = {
           symbol(nullable:false, blank:false)
  }
}
