package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant
import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.custprops.types.CustomPropertyContainer
import com.k_int.web.toolkit.tags.Tag
import grails.gorm.MultiTenant
import com.k_int.web.toolkit.refdata.RefdataValue;
import com.k_int.web.toolkit.refdata.Defaults;
import com.k_int.web.toolkit.databinding.BindUsingWhenRef
import grails.web.databinding.DataBindingUtils
import java.util.Collections;

import org.springframework.beans.propertyeditors.CustomDateEditor

/**
 * The relationship between a service and a directory entry
 */

@BindUsingWhenRef({ obj, propName, source, isCollection ->
  CustomBinders.bindServiceAccount(obj, propName, source, isCollection)
})
class ServiceAccount  implements CustomProperties,MultiTenant<ServiceAccount>  {

  String id
  String slug
  String accountDetails
  Service service
  DirectoryEntry accountHolder

  static graphql = true

  static belongsTo = [
    accountHolder: DirectoryEntry
  ]


  static mapping = {
                 id column:'sa_id', generator: 'uuid2', length:36
            service column:'sa_service'
               slug column:'sa_slug'
      accountHolder column:'sa_account_holder'
     accountDetails column:'sa_account_details'
  }

  static constraints = {
              slug(nullable:true, blank:false)
           service(nullable:false, blank:false)
     accountHolder(nullable:false, blank:false)
     accountDetails(nullable:true, blank:false)
  }
}
