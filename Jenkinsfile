pipeline {
    agent any
    options { quietPeriod(600) }
    environment {
        IMAGE_NAME = 'reg.supremind.info/bsg-app-2/test-assets/supre_test/supre-test:1.0-0621'
        IMAGE_PREFIX = ''
    }
    stages {
         stage('Build/Test') {
             steps {
                 configFileProvider([configFile(fileId: 'supretest-maven', targetLocation: 'settings.xml')]) {
                     sh '''
 //                        export JAVA_HOME=/opt/jdk-11
  //                       export CLASSPATH=$JAVA_HOME/lib:$CLASSPATH
 //                        export PATH=$JAVA_HOME/bin:/opt/mvnd/bin:$PATH
                         java -version
                         mvn clean package -DskipAntRunForJenkins --settings ./settings.xml
  //                       mkdir -p backend/target/dependency && (cd backend/target/dependency; jar -xf ../*.jar)
                     '''
                 }
             }
         }
        stage('Docker build & push') {
            steps {
             configFileProvider([configFile(fileId: 'Dockerfile', variable: 'DOCKERFILE_PATH')]) {

                sh '''
                 cd ${WORKSPACE} && docker  build  -t ${IMAGE_NAME} -f $DOCKERFILE_PATH' --push
                '''
            }
        }

    }
}
}
