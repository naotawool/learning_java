pipeline {
  agent any
  stages {
    stage('Clone') {
      steps {
        git(url: 'https://github.com/naotawool/learning_java.git', branch: '*/develop', poll: true)
      }
    }
    stage('Build') {
      steps {
        bat 'mvn build'
      }
    }
  }
}