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
  CustomBinders.bindDirectoryEntry(obj, propName, source, isCollection)
})
class DirectoryEntry  implements MultiTenant<DirectoryEntry>,CustomProperties  {

  String id
  String name
  String slug
  String description
  String foafUrl
  String entryUrl

  // If the location corresponds to a location in a host LMS, record that
  // code here - "MAIN" is a common example
  String lmsLocationCode

  Long foafTimestamp

  DirectoryEntry parent

  /**
   * DirectoryEntries can be managed here, or just stored for reference. Managed entries can be accessed via
   * a foaf type service.
   */
  @Defaults(['Managed', 'Reference' ])
  RefdataValue status

  static graphql = true

  static hasMany = [
    tags:Tag,
    friends: FriendAssertion,
    units: DirectoryEntry,
    symbols: Symbol,
    services: ServiceAccount,
    announcements: Announcement,
    addresses: Address,
    members: GroupMember
  ]

  static mappedBy = [
    friends: 'owner',
    units: 'parent',
    symbols: 'owner',
    services: 'accountHolder',
    announcements: 'owner',
    addresses: 'owner',   
    members: 'groupOrg'
  ]

  static belongsTo = [
    parent: DirectoryEntry
  ]

  static mapping = {
                 id column:'de_id', generator: 'uuid2', length:36
               name column:'de_name'
               slug column:'de_slug'
        description column:'de_desc'
             parent column:'de_parent'
             status column:'de_status_fk'
            foafUrl column:'de_foaf_url'
           entryUrl column:'de_entry_url'
      foafTimestamp column:'de_foaf_timestamp'
    lmsLocationCode column:'de_lms_location_code'
              tags cascade:'save-update'
          services cascade:'save-update'
  }

  static constraints = {
              name(nullable:false, blank:false)
              slug(nullable:false, blank:false, unique:true)
        description(nullable:true, blank:false)
             parent(nullable:true, validator: { parent, obj ->
              return obj.checkParentTree(obj, obj)
            })
             status(nullable:true)
            foafUrl(nullable:true, blank:false)
           entryUrl(nullable:true, blank:false)
      foafTimestamp(nullable:true)
    lmsLocationCode(nullable:true)

  }

  /**
  * Search through the parent tree to find out if this directory entry appears as its own parent along the line
  * @return null if there are no parental loops, string saying "Cycle Detected" otherwise
  */
  public String checkParentTree(DirectoryEntry dirToCheck, DirectoryEntry dir) {
    println "========================================================================"
    println("Our directory entry to check against is: ${dirToCheck}")
    println("Our current directory entry is: ${dir}")
    if (dir.getParent() == null) {
      printf("%o has no parent", dir)
      return null
    } else {
      if (dirToCheck.id == dir.getParent().id) {
        println("${dir} has parent ${dirToCheck}, so there is a loop")
        return "Cycle detected"
      } else {
        println("No loop found yet, continuing")
        return checkParentTree(dirToCheck, dir.getParent())
      }
    }
    println "========================================================================"
  }

  /**
   * Search the symbols attached to this entry to see if we can find one in the nominated
   * namespace, if not and if there is a parent, try the parent
   */
  public String locateSymbolInNamespace(String ns) {
    String result = null;
    Symbol located_symbol = symbols.find { it.authority.symbol == ns }

    // If we managed to find a symbol in that namespace, job done, return it
    if ( located_symbol ) {
      result = located_symbol.symbol
    }
    else {
      // We didn't, does this entry have a parent?
      if ( parent ) {
        // Yes, see if the parent has a symbol matching that NS
        result = parent.locateSymbolInNamespace(ns);
      }
    }

    return result
  }

  public String getTagSummary() {
    String result = null;
    if ( tags )
      result = tags?.collect {it?.value?:'Missing Tag'}.join(', ')
    return result;
  }

  public String getSymbolSummary() {
    String result = null;
    if ( symbols )
      result = symbols?.collect {it?.authority?.symbol+':'+it?.symbol}.join(', ')
    return result;
  }

  public String getFullyQualifiedName() {
    String result = null;
    if ( parent ) {
      result = parent.getFullyQualifiedName() + ' / ' + name
    }
    else {
      result = name
    }
    return result;
  }

  public String toString() {
    return "DirectoryEntry ${name} (${id?:'Unsaved'})".toString()
  }

  public int hashCode() {
    "DirectoryEntry:${slug}".toString().hashCode()
  }

}
