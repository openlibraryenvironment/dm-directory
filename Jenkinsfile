#!groovy

podTemplate(
  containers:[
    containerTemplate(name: 'jdk11',                image:'adoptopenjdk:11-jdk-openj9',   ttyEnabled:true, command:'cat')
  ],
  volumes: [
  ])
{
  node(POD_LABEL) {

    // See https://www.jenkins.io/doc/pipeline/steps/pipeline-utility-steps/
    // https://www.jenkins.io/doc/pipeline/steps/
    // https://github.com/jenkinsci/nexus-artifact-uploader-plugin
  
    stage ('checkout') {
      checkout scm
      props = readProperties file: './gradle.properties'
      app_version = props.appVersion
      deploy_cfg = null;
      semantic_version_components = app_version.toString().split('\\.')
      is_snapshot = app_version.contains('SNAPSHOT')
      println("Got props: asString:${props} appVersion:${props.appVersion}/${props['appVersion']}/${semantic_version_components}");
      sh 'echo branch:$BRANCH_NAME'
      sh 'echo commit:$checkout_details.GIT_COMMIT'
    }
  
    stage ('check') {
      container('jdk11') {
        echo 'Hello, JDK'
        sh 'java -version'
        sh './gradlew --version'
      }
    }
  
    stage ('build') {
      container('jdk11') {
        sh './gradlew --no-daemon --console=plain clean build generatePomFileForMavenPublication'
        sh 'ls ./build/libs'
      }
    }
  
    stage ('publish') {
        def target_repository = null;
        println("Props: ${props}");
        def release_files = findFiles(glob: '**/dm-directory-*.*.*.jar')
        println("Release Files: ${release_files}");
        if ( release_files.size() == 1 ) {
          if ( release_files[0].name.contains('SNAPSHOT') ) {
            target_repository='snapshots';
          }
          else {
            target_repository='releases';
          }
        }
  
        if ( target_repository != null ) {
          println("Publish ${release_files[0].path} with version ${props.appVersion} to ${target_repository}");
          nexusArtifactUploader(
            nexusVersion: 'nexus3',
            protocol: 'https',
            nexusUrl: 'nexus.k-int.com',
            groupId: 'org.olf.reshare.dm.directory',
            version: props.appVersion,
            repository: target_repository,
            credentialsId: 'kinexus',
            artifacts: [
                [artifactId: 'dm-directory', classifier: '', file: release_files[0].path, type: 'jar'],
                [artifactId: 'dm-directory', type: "pom", file: 'build/publications/maven/pom-default.xml'] ]
          )
        }
    }
  }

  stage ('Remove old builds') {
    //keep 3 builds per branch
    properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '3', numToKeepStr: '3']]]);
  }

}
