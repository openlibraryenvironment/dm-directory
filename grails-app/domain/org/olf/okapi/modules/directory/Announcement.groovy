package org.olf.okapi.modules.directory

import grails.gorm.MultiTenant
import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.custprops.types.CustomPropertyContainer
import com.k_int.web.toolkit.tags.Tag
import grails.gorm.MultiTenant
import com.k_int.web.toolkit.refdata.RefdataValue;
import com.k_int.web.toolkit.refdata.Defaults;

class Announcement  implements MultiTenant<Announcement>  {

  String id
  Date announceDate
  Date expiryDate
  String code
  String description

  static hasMany = [
  ]

  static mappedBy = [
  ]

  static belongsTo = [
    owner: DirectoryEntry
  ]

  static mapping = {
                 id column:'ann_id', generator: 'uuid2', length:36
              owner column:'ann_owner_fk'
       announceDate column:'ann_announce_date'
         expiryDate column:'ann_expiry_date'
               code column:'ann_code'
        description column:'ann_description'
  }

  static constraints = {
             owner(nullable:false, blank:false)
      announceDate(nullable:false, blank:false)
        expiryDate(nullable:true, blank:false)
              code(nullable:true, blank:false)
       description(nullable:true, blank:false)

  }
}
