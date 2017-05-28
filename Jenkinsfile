node {
   def mvnHome
   stage('Clone') {
     git(url: 'https://github.com/naotawool/learning_java.git', branch: 'feature/pipeline', poll: true)

     // Get the Maven tool.
     // ** NOTE: This 'M3' Maven tool must be configured
     // **       in the global configuration.
     mvnHome = tool 'M3'
   }
   stage('Build') {
     bat "set MS_HOME="
     bat "echo %JAVA_HOME%"
     bat "echo %PATH%"
     bat "${mvnHome}\\bin\\mvn.bat clean test"
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