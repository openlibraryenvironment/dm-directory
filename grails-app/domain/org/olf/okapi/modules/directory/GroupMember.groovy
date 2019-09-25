package org.olf.okapi.modules.directory

import com.k_int.web.toolkit.custprops.CustomProperties
import com.k_int.web.toolkit.databinding.BindUsingWhenRef
import com.k_int.web.toolkit.refdata.Defaults
import com.k_int.web.toolkit.refdata.RefdataValue
import com.k_int.web.toolkit.tags.Tag

import grails.gorm.MultiTenant


// Called when data binding wants to bind a variable of type DirectoryEntry to any domain
// class. obj will be an instance of that class, propName will be the property name which has
// type DirectoryEntry and source will be the source map.
@BindUsingWhenRef({ obj, propName, source, isCollection = false ->
  CustomBinders.bindGroupMember(obj, propName, source, isCollection)
})
class GroupMember  implements MultiTenant<GroupMember>,CustomProperties  {

  String id
  DirectoryEntry memberOrg

  static graphql = true

  static hasMany = [
  ]

  static mappedBy = [
  ]

  static belongsTo = [
    groupOrg: DirectoryEntry
  ]

  static mapping = {
                 id column:'gm_id', generator: 'uuid2', length:36
           groupOrg column:'gm_group_fk'
          memberOrg column:'gm_member_fk'
  }

  static constraints = {
              groupOrg(nullable:false, blank:false)
             memberOrg(nullable:false, blank:false)
  }

}
