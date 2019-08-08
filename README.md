

This is a grails plugin which defines the domain model for the directory service. It is created as a separate entity
so that it may be bundled into multiple different services.

Different services will likely consume an event stream that allows services to pick and choose the data they need in
order to operate.


Created using
grails create-plugin dm-directory --profile=rest-api-plugin


To build

grails package-plugin

To upload plugin
grails publish-plugin
