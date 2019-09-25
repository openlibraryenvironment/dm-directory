package org.olf.okapi.modules.directory

import grails.util.GrailsNameUtils
import grails.web.databinding.DataBindingUtils
import groovy.util.logging.Slf4j

@Slf4j
class CustomBinders {
  
  public static final bindNamingAuthority(final def obj, final String propName, final def source, final boolean isCollection) { 

    log.debug ("NamingAuthority::@BindUsingWhenRef ${obj} ${propName} ${source}")

    def data = isCollection ? source : source[propName]

    // If the data is asking for null binding then ensure we return here.
    if (data == null) {
      return null
    }

    NamingAuthority val = null

    if ( data instanceof Map ) {
      if ( data.id ) {
        val = NamingAuthority.read(data.id)
        if ( val == null ) {
          // it's possible that we are loading a copy of the data provided by mod-directory, in which case we want to have the
          // same IDs in the copy-to module as the source mod-directory system. If read(id) returned null it means that the
          // entry is not present yet - so create a new one with that ID
          val = new NamingAuthority(id:data.id, symbol:data.symbol);
        }
      }
      else if ( data.symbol ) {
        val = NamingAuthority.findBySymbol(data.symbol) ?: new NamingAuthority(symbol:data.symbol)
      }
    }
    else if ( data instanceof String ) {
      val = NamingAuthority.findBySymbol(data) ?: new NamingAuthority(symbol:data)
    }

    if ( val ) {
      if ( data instanceof Map ) {
        DataBindingUtils.bindObjectToInstance(val, data)
      }
    }

    val
  }
  
  public static final bindSymbol ( final def obj, final String propName, final def source, final boolean isCollection ) {
  
    log.debug ("Symbol::@BindUsingWhenRef ${obj} ${propName} ${source}")
  
    def data = isCollection ? source : source[propName]
  
    // If the data is asking for null binding then ensure we return here.
    if (data == null) {
      return null
    }
  
    Symbol val = null
  
    if ( data instanceof Map ) {
      if ( data.id ) {
        val = Symbol.read(data.id)
        if ( val == null ) {
          // it's possible that we are loading a copy of the data provided by mod-directory, in which case we want to have the
          // same IDs in the copy-to module as the source mod-directory system. If read(id) returned null it means that the
          // entry is not present yet - so create a new one with that ID
          val = new Symbol(id:data.id)
        }
      }
      else if ( ( data.symbol != null ) && ( data.authority != null ) ) {

        def qr = null;
        String authority_symbol = null;

        if ( data.authority instanceof String ) {
          authority_symbol = data.authority
          qr = Symbol.executeQuery('select s from Symbol as s where s.symbol=:s and s.authority.symbol=:a',[s:data.symbol, a:data.authority])
        }
        else if ( data.authority.symbol != null ) {
          authority_symbol = data.authority.symbol
          qr = Symbol.executeQuery('select s from Symbol as s where s.symbol=:s and s.authority.symbol=:a',[s:data.symbol, a:data.authority.symbol])
        }
  
        if ( qr?.size() == 1 )
          val = qr.get(0)
        else {
          log.debug ("Create new symbol entry, ${data} - prop=${propName}, source=${source}, source.id=${source?.id}")
          // val = new Symbol(data)
          val = new Symbol()
        }
  
       
        // If we're binding in a colleciton property, only add the symbol if it's not already in the list
        if ( isCollection ) {
          log.debug("${propName} is a collection - add ${val} to it");

          // Check that this symbol is not already present
          if ( obj[propName].find { ( ( it.symbol == data.symbol ) && ( it.authority.symbol == authority_symbol ) ) } == null ) {
            log.debug("Can't locate a symbol ${data.symbol} in list for ${propName} Add it - class is ${obj[propName]?.class?.name}");

            obj."addTo${GrailsNameUtils.getClassName(propName)}" (val)
          }
          else {
            log.debug("found existing symbol - no action needed")
          }
        }

      }
    }
  
    if ( val ) {
      DataBindingUtils.bindObjectToInstance(val, data)
    }
  
    val
  }
   
  public static final bindServiceAccount(final def obj, final String propName, final def source, final boolean isCollection) {

    log.debug ("ServiceAccount::@BindUsingWhenRef ${obj} ${propName} ${source} ${isCollection}")


    def data = isCollection ? source : source[propName]

    // If the data is asking for null binding then ensure we return here.
    if (data == null) {
      return null
    }
    
    ServiceAccount val = null
    if ( data instanceof Map ) {
      if ( data.id ) {
        val = ServiceAccount.read(data.id)
        if ( val == null ) {
          // it's possible that we are loading a copy of the data provided by mod-directory, in which case we want to have the
          // same IDs in the copy-to module as the source mod-directory system. If read(id) returned null it means that the
          // entry is not present yet - so create a new one with that ID
          val = new ServiceAccount(id:data.id, slug:data.slug)
        }
      }
      else if ( data.slug ) {
        log.debug ("Lookup existing service account by slug ${data.slug}")
        val = ServiceAccount.findBySlug(data.slug)
        if ( val == null ) {
          log.debug ("Create new SA ${data} obj:${obj} id:${source?.id}...")
          // Create the account, but use cascade saving so that we get the ID of the parent
          // val = new ServiceAccount(data)

          // Try recursively calling the bind here to do the right thing
          val = new ServiceAccount(slug:data.slug)

          log.debug ("Add new SA to ${propName} list")
          obj."addTo${GrailsNameUtils.getClassName(propName)}" (val)
        }
      }
    }

    if ( val != null ) {
      log.debug ("Bind ServiceAccount properties using data ${val} ${data}")

      // We're going to try ONLY binding the service to see if thats what is causing the problem - this still gives us a NULL service
      // def dbr = DataBindingUtils.bindObjectToInstance(val, data, ['service'],Collections.emptyList(), null)

      def dbr = DataBindingUtils.bindObjectToInstance(val, data)

      log.debug ("Check value of service property After bind: ${val?.service} (owner=${val.accountHolder})- should not be null if ${data.service} is present - data binding result is ${dbr}")
    }
    else {
      log.debug ("-- val is null, can't merge ${data}")
    }

    val
  }

  public static final bindDirectoryEntry (final def obj, final String propName, final def source, final boolean isCollection) {
    
    log.debug ("DirectoryEntry::@BindUsingWhenRef(${obj} ${source} ${propName} ${isCollection})")

    // this isn't right when we a processing a property which is a collection
    def data = isCollection ? source : source[propName]

    // If the data is asking for null binding then ensure we return here.
    if (data == null) {
      log.debug("bindDirectoryEntry called with null date - return");
      return null
    }
    else {
      log.debug("Process data ${data}");
    }

    DirectoryEntry val = null

    try {
      if ( data instanceof Map ) {
        if ( data.id ) {
          log.debug ("ID supplied for Directory entry - read it")
          val = DirectoryEntry.read(data.id)
          if ( val == null ) {
            log.debug("Creating a new directory entry ${data}");
            // it's possible that we are loading a copy of the data provided by mod-directory, in which case we want to have the
            // same IDs in the copy-to module as the source mod-directory system. If read(id) returned null it means that the
            // entry is not present yet - so create a new one with that ID
            val = new DirectoryEntry(id:data.id, slug:data.slug, name:data.name)
            // Interim save
            val.save()
          }
        }
        else if ( data.slug != null ) {
          log.debug ("Looking up directory entry by slug ${data.slug}")
          val = DirectoryEntry.findBySlug(data.slug)
          if ( val == null ) {
            log.debug ("Create new directory entry, ${data} - prop=${propName}, source=${source}, source.id=${source?.id}")
            val = new DirectoryEntry(name:data.name, slug:data.slug)
            // Interim save
            val.save()

            if ( isCollection ) {
              log.debug ("${propName} is a collection - so add new directory entry to parent collection")
              obj."addTo${GrailsNameUtils.getClassName(propName)}" (val)
            }
          }
          else {
            log.debug("Located a directory entry for slug ${data.slug}(${val.id})");
          }
        }

        if ( val ) {
          log.debug("Binding data to instance ${val}");
          DataBindingUtils.bindObjectToInstance(val, data)
        }
      }
      else if ( data instanceof String ) {
        log.debug("Data is instanceof ${data?.class?.name} so try to look up a directory entry by slug: ${data}")

        // If we're in here, someone has referenced a directory entry using only the SLUG as a string. See if we can locate using that slug
        val = DirectoryEntry.findBySlug(data)
        if ( val == null ) {

          log.debug("Creating a new placeholder directory entry for slug ${data}");

          // A bit contentious - lets create a stub entry - maybe we're loading a consortial record and we want a placeholder
          // for a member library
          val = new DirectoryEntry(slug:data, name:data)
          // Interim save
          val.save()

          if ( !isCollection ) {
            // We're not a collection, so save won't cascade, lets save explicitly.
            val.save(flush:true, failOnError:true);
          }
        }
      }
      else {
        log.warn("Unhandled type ${data?.class?.name} for DirectoryEntry binding");
      }
    }
    catch (Exception e) {
      // log and rethrow
      log.error("unexpected error trying to process DirectryEntry",e);
      throw e;
    }

    log.debug ("DirectoryEntry::@BindUsingWhenRef completed, returning ${val}")
    val
  }

  public static final bindGroupMember (final def obj, final String propName, final def source, final boolean isCollection) {

    GroupMember val = null;

    log.debug ("GroupMember::@BindUsingWhenRef(${obj} ${source} ${propName} ${isCollection})")

    // this isn't right when we a processing a property which is a collection
    def data = isCollection ? source : source[propName]

    // If the data is asking for null binding then ensure we return here.
    if (data == null) {
      return null
    }

    if ( data instanceof Map ) {
      if ( data.id ) {
        val = GroupMember.read(data.id)
        if ( val == null ) {
          // it's possible that we are loading a copy of the data provided by mod-directory, in which case we want to have the
          // same IDs in the copy-to module as the source mod-directory system. If read(id) returned null it means that the
          // entry is not present yet - so create a new one with that ID
          val = new GroupMember(id:data.id)
        }
      }
      else if ( ( data.memberOrg != null ) && ( data.memberOrg instanceof String ) ) {
        log.debug("data.memberOrg != null (${data.memberOrg}) and it's a string..... so do special processing");
        // Look to see if we've already gt
        if ( isCollection ) {
          log.debug("processing group member, adding to a collection (${propName}) - see if we already have an entry for that");
          val = obj[propName]?.find { it.memberOrg?.slug == data.memberOrg }

          if ( val == null ) {
            log.debug("Create a new group member for ${data}");
            // Create a new group member, and rely on bindObjectToInstance to look up the member org and set it appropriately
            val = new GroupMember()
            // Add the member org to the parent
            obj."addTo${GrailsNameUtils.getClassName(propName)}" (val)
          }
        }
      }
    }
    else {
      log.debug("Data is instanceof ${data?.class?.name} - skip")
    }

    if ( val ) {
      DataBindingUtils.bindObjectToInstance(val, data)
    }

    log.debug ("GroupMember::@BindUsingWhenRef completed, returning ${val}")

    val;
  }
}
