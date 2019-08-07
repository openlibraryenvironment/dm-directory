


Created using
grails create-plugin dm-directory --profile=rest-api-plugin


To upload plugin
gradle uploadArchives


Additional info
grails publish-plugin notes at
http://grails-plugins.github.io/grails-release/docs/manual/guide/single.html#repositories
grails.project.repos.default = "myRepo"
grails.project.repos.myRepo.url = "http://localhost:8081/repos"
grails.project.repos.myRepo.type = "maven"
grails.project.repos.myRepo.username = "admin"
grails.project.repos.myRepo.password = "password"
grails.project.repos.myRepo.portal = "grailsCentral"
