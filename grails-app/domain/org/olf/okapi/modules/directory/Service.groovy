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
 * A service represents an internet callable endpont. A service can support many
 * accounts or tenants. For example, an iso 10161 service endpoint might support 
 * symbols from multiple different institutions. This class then models the service
 * itself.
 */
@BindUsingWhenRef({ obj, propName, source ->

  //@BindUsingWhenRef(org.olf.okapi.modules.directory.Symbol : (unsaved), authority, grails.databinding.SimpleMapDataBindingSource@5d0e003a
  //Result null

  Service val = null;

  def data = source.getAt(propName)

  // If the data is asking for null binding then ensure we return here.
  if (data == null) {
    return null
  }

  if ( data instanceof Map ) {
    if ( data.id ) {
      val = Service.read(data.id);
    }
    else if ( data.address ) {
      val = Service.findByAddress(data.address) ?: new Service(data).save(flush:true, failOnError:true)
    }
  }

  val
})
class Service  implements CustomProperties,MultiTenant<Service>  {

  String id
  String name
  String address

  /**
   * The actual protocol in use
   */
  @Defaults(['Z3950',
             'ISO10161.TCP',
             'ISO10161.SMTP',
             'ISO18626',
             'GSM.SMTP', 
             'OAI-PMH', 
             'SRU', 
             'SRW'])
  RefdataValue type

  /**
   * The business function served - if I want to list all services providing ILL, query this for ILL
   */
  @Defaults(['ILL','CIRC','RTAC'])
  RefdataValue businessFunction

  static hasMany = [
    tags:Tag
  ]

  static mappedBy = [
  ]

  static mapping = {
                  id column:'se_id', generator: 'uuid2', length:36
                name column:'se_name'
             address column:'se_address'
                type column:'se_type_fk'
    businessFunction column:'se_business_function_fk'
  }

  static constraints = {
                name(nullable:true, blank:false)
                type(nullable:false, blank:false)
             address(nullable:false, blank:false)
    businessFunction(nullable:true, blank:false)
  }
}
