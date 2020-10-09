#!groovy

node {

  // See https://www.jenkins.io/doc/pipeline/steps/pipeline-utility-steps/
  // https://www.jenkins.io/doc/pipeline/steps/
  // https://github.com/jenkinsci/nexus-artifact-uploader-plugin

  stage ('checkout') {
    checkout scm
  }

  stage ('check') {
      echo 'Hello, JDK'
      sh 'java -version'
      sh './gradlew --version'
  }

  stage ('build') {
    sh './gradlew --no-daemon --console=plain clean build generatePomFileForMavenPublication'
    sh 'ls ./build/libs'
  }

  stage ('publish') {
      def props = readProperties file: 'gradle.properties'
      def target_repository = null;
      println("Props: ${props}");
      def release_files = findFiles(glob: '**/dm-directory-*.*.*.jar')
      println("Release Files: ${release_files}");
      if ( release_files.size() == 1 ) {
        // println("Release file : ${release_files[0].name}");
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

