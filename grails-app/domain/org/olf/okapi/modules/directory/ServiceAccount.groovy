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

/**
 * The relationship between a service and a directory entry
 */

@BindUsingWhenRef({ obj, propName, source, isCollection ->

  ServiceAccount val = null;
  println("ServiceAccount::@BindUsingWhenRef ${obj} ${propName} ${source} ${isCollection}");


  def data = isCollection ? source : source[propName]

  // If the data is asking for null binding then ensure we return here.
  if (data == null) {
    return null
  }

  if ( data instanceof Map ) {
    if ( data.id ) {
      val = ServiceAccount.read(data.id);
    }
    else if ( data.slug ) {
      println("Lookup existing service account by slug ${data.slug}");
      val = ServiceAccount.findBySlug(data.slug)
      if ( val == null ) {
        println("Create new SA ${data} obj:${obj} id:${source?.id}...");
        // Create the account, but use cascade saving so that we get the ID of the parent
        // val = new ServiceAccount(data)

        // Try recursively calling the bind here to do the right thing
        val = new ServiceAccount(slug:data.slug)

        if ( propName == 'services' ) {
          println("Add new SA to services list");
          obj.addToServices(val);
          // source.accountHolder = obj;
        }
      }
    }
  }

  if ( val != null ) {
    println("Bind ServiceAccount properties using data ${val} ${data}");

    // We're going to try ONLY binding the service to see if thats what is causing the problem - this still gives us a NULL service
    // def dbr = DataBindingUtils.bindObjectToInstance(val, data, ['service'],Collections.emptyList(), null)

    def dbr = DataBindingUtils.bindObjectToInstance(val, data)
    
    println("Check value of service property After bind: ${val?.service} - should not be null if ${data.service} is present - data binding result is ${dbr}");
  }
  else {
    println("-- val is null, can't merge ${data}");
  }

  val
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
