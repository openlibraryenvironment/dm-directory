package org.olf.okapi.modules.directory

import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.databinding.BindUsingWhenRef
import com.k_int.web.toolkit.refdata.Defaults;
import com.k_int.web.toolkit.refdata.RefdataValue;
import com.k_int.web.toolkit.tags.Tag

import grails.gorm.MultiTenant
import grails.web.databinding.DataBindingUtils

/**
 * A service represents an internet callable endpont. A service can support many
 * accounts or tenants. For example, an iso 10161 service endpoint might support 
 * symbols from multiple different institutions. This class then models the service
 * itself.
 */
@BindUsingWhenRef({ obj, propName, source, isCollection = false ->

  println("Service::@BindUsingWhenRef ${obj} ${propName} ${source} ${isCollection}");

  Service val = null;

  def data = isCollection ? source : source[propName]

  // If the data is asking for null binding then ensure we return here.
  if (data == null) {
    return null
  }

  if ( data instanceof Map ) {
    if ( data.id ) {
      val = Service.read(data.id);
    }
    else if ( data.address ) {
      val = Service.findByAddress(data.address) ?: new Service(address:data.address)
    }
  }

  // Now allow binding of the properties set for that directory entry
  if (val) {
    if (data instanceof Map ) {
      DataBindingUtils.bindObjectToInstance(val, data)
    }
  }

  println("Service::@BindUsingWhenRef returning ${val}");

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
             'NCIP', 
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
                tags cascade:'save-update'
  }

  static constraints = {
                name(nullable:true, blank:false)
                type(nullable:false)
             address(nullable:false, blank:false)
    businessFunction(nullable:true)
  }
}
