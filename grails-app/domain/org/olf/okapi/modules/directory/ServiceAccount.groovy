package org.olf.okapi.modules.directory

import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.databinding.BindUsingWhenRef

import grails.gorm.MultiTenant

/**
 * The relationship between a service and a directory entry
 */

@BindUsingWhenRef({ obj, propName, source, isCollection = false ->
  CustomBinders.bindServiceAccount(obj, propName, source, isCollection)
})
class ServiceAccount  implements CustomProperties,MultiTenant<ServiceAccount>  {

  String id
  String slug
  String accountDetails
  Service service

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
            service cascade:'save-update'
  }

  static constraints = {
               slug(nullable:false, blank:false, unique:true)
            service(nullable:false)
      accountHolder(nullable:false)
     accountDetails(nullable:true, blank:false)
  }
}
