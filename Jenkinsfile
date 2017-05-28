pipeline {
  agent any
  stages {
    stage('Clone') {
      steps {
        git(url: 'https://github.com/naotawool/learning_java.git', branch: 'feature/pipeline', poll: true)
      }
    }
    stage('Build') {
      steps {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn test"
      }
    }
    stage('Test Results') {
      steps {
        parallel(
          "Test Results": {
            junit(testResults: 'build/test-results/test/TEST-*.xml', healthScaleFactor: 1)

          },
          "Coverage Results": {
            jacoco(execPattern: '**/**.exec', classPattern: '**/classes', sourcePattern: '**/src/main/java')

          },
          "FindBugs Results": {
            findbugs(pattern: 'build/findbugsReports/main.xml')

          }
        )
      }
    }
  }
}