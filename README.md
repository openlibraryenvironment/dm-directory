# DM-Directory

## What is this?

[Some say](https://blog.christianposta.com/microservices/the-hardest-part-about-microservices-data/) that the hardest thing about microservices is your data. Thats before we hit the [Entity Service Anit-Pattern](https://www.michaelnygard.com/blog/2017/12/the-entity-service-antipattern/).

mod-directory is an authoritative source of business service directory information - it can be used to find addresses, electronic service endpoints and to look up what method of communication a given party wishes to be used in a given context. This is crucially important for applications like resource sharing, where an institution will elect a specific protocol from a choice of many for their request handling. This choice implies specific configuration and settings needed in the context of the institution and the protocol. mod-directory exists to help solve that problem.

The challenge is that we want microservices to be independently deployable, and self contained resilient units. We certainly want to avoid runtime dependencies to other services in order to prevent the recursive-call-storm that means an app performs sub-millisecond in it's test harness, and takes hours in production. Event streams are the order of the day for solving this problem - and kafka is an ideal choice if you want to have new modules come along, consume the event stream to date, and be up and running.

But. you just want to use the directory API in your code - you certainly don't want to have to re-code all the storage structures. If you only require a very specific subset of the directory info, then coding your own local storage for directory events is probably the right way to go. But if you want the full glory of the directory, don't want runtime service dependencies, and don't want to re-implement the domain model - then dm-directory can help.

dm-directory is an internal storage schema for representing directory information inside a microservice. It's actually the storage model for mod-directory, but it's separated out so that if you want access to the full directory schema in your microservice, you can drop this library in, and instantly be able to connect the entities to your app-specific domain model within the boundary of your microservice. For ILL - this means that there is a direct linkage between a patron request and the directory entry of a supplying institution but only within the bounded context of the resource sharing module.

## Watch out

For reasons, we have had to seriously adjust the conventional behaviour of hibernate in order to make the natural IDs of an entry in mod-directory the same as it's appearence in other modules. Ideally we would not do this - and would use a synthetic identifier any place we needed to reference an entry. This single requirement is responsible for 90% of the pain and dirt in this module. I can only apologize for making what should have been simple and elegant into a rats nest.

# Release procedure

GitHub actions publishes this to the Github Packages repository. To trigger publishing an artifact, create and publish a release in github.

# Legacy

This process used to rely upon init.gradle in ~/.gradle having the right nexus config

The project was created using

    grails create-plugin dm-directory --profile=rest-api-plugin


To build

    grails package-plugin


To upload plugin

    grails publish-plugin

