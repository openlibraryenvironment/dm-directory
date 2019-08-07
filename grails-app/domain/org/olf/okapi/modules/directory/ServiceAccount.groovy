package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant
import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.custprops.types.CustomPropertyContainer
import com.k_int.web.toolkit.tags.Tag
import grails.gorm.MultiTenant
import com.k_int.web.toolkit.refdata.RefdataValue;
import com.k_int.web.toolkit.refdata.Defaults;

/**
 * The relationship between a service and a directory entry
 */
class ServiceAccount  implements CustomProperties,MultiTenant<ServiceAccount>  {

  String id
  Service service
  DirectoryEntry accountHolder

  String accountDetails

  static graphql = true



  static mapping = {
                 id column:'sa_id', generator: 'uuid2', length:36
            service column:'sa_service'
      accountHolder column:'sa_account_holder'
     accountDetails column:'sa_account_details'
  }

  static constraints = {
           service(nullable:false, blank:false)
     accountHolder(nullable:false, blank:false)
     accountDetails(nullable:true, blank:false)
  }
}
