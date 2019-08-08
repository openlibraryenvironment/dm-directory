

This is a grails plugin which defines the domain model for the directory service. It is created as a separate entity
so that it may be bundled into multiple different services.

Different services will likely consume an event stream that allows services to pick and choose the data they need in
order to operate.

This plugin contains prebuilt migration files that can be included -- HOWEVER -- it also includes the migration files for
tookit components like custprops and tags. If your module also makes use of those components your module migrations will
likely clash with the migrations in this file.  THEREFORE you are advised to not use the migrations from this plugin,
but create a set of migrations specifically for your module, based on the classes in the dependent libraries. This will
mean your module migrations file are built specifically for the set of dependencies your module defines and each dependent
class will only be defined once. This does mean you will have to generate migrations in each module using this plugin
as it changes.


Created using
grails create-plugin dm-directory --profile=rest-api-plugin


To build

grails package-plugin

To upload plugin
grails publish-plugin
