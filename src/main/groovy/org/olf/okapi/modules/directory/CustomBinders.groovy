package org.olf.okapi.modules.directory

import com.k_int.web.toolkit.databinding.BindUsingWhenRef

import grails.web.databinding.DataBindingUtils
import groovy.util.logging.Slf4j

@Slf4j
class CustomBinders {
  
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
      }
      else if ( ( data.symbol != null ) && ( data.authority != null ) ) {
  
        def qr = Symbol.executeQuery('select s from Symbol as s where s.symbol=:s and s.authority.symbol=:a',[s:data.symbol, a:data.authority])
  
        if ( qr.size() == 1 )
          val = qr.get(0)
  
        if ( val == null ) {
          log.debug ("Create new symbol entry, ${data} - prop=${propName}, source=${source}, source.id=${source?.id}")
          // val = new Symbol(data)
          // Try recursively calling the bind here to do the right thing
          val = new Symbol()
  
          if ( propName == 'symbols' ) {
            log.debug("Add new symbol to entry")
            obj.addToSymbols(val)
            // val.owner = obj
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

          if ( propName == 'services' ) {
            log.debug ("Add new SA to services list")
            obj.addToServices(val)
            // source.accountHolder = obj
          }
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
    
    log.debug ("DirectoryEntry::@BindUsingWhenRef(${obj} ${source} ${propName})")

    // this isn't right when we a processing a property which is a collection
    def data = isCollection ? source : source[propName]

    // If the data is asking for null binding then ensure we return here.
    if (data == null) {
      return null
    }

    DirectoryEntry val = null
    if ( data instanceof Map ) {
      if ( data.id ) {
        log.debug ("ID supplied for Directory entry - read it")
        val = DirectoryEntry.read(data.id)
      }
      else if ( data.slug != null ) {
        log.debug ("Looking up directory entry by slug ${data.slug}")
        val = DirectoryEntry.findBySlug(data.slug)
        if ( val == null ) {
          log.debug ("Create new directory entry, ${data} - prop=${propName}, source=${source}, source.id=${source?.id}")
          val = new DirectoryEntry(name:data.name, slug:data.slug)
          if ( propName == 'units' ) {
            log.debug ("Add new directory entry to parent units")
            obj.addToUnits(val)
            // source.parent = obj
          }
        }
      }
    }
    else {
      log.debug("Data is instanceof ${data?.class?.name} - skip")
    }

    log.debug ("DirectoryEntry::@BindUsingWhenRef completed, returning ${val}")
    if ( val ) {
      DataBindingUtils.bindObjectToInstance(val, data)
    }

    val
  }
}
