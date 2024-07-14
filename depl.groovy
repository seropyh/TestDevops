pipeline{
    agent any
    stages {
        stage ("install helm"){
        steps {
        script {
        sh "ls -la"
        sh "chmod 777 ./helm"
        sh "./helm"
        }   }   }
        stage("deploy") {
            agent {
                kubernetes {
                    cloud 'kubernetes'
                    label 'nginx'   
                  }               }
            steps {

            container('jnlp'){
                //    dir("helm"){
                        sh "./helm install  nginx  ./nginx"
                    
                    /*    sh "echo 'Simple helm install!'"
                        sh "wget https://get.helm.sh/helm-v3.2.4-linux-amd64.tar.gz"
                        sh "tar zxfv helm-v3.2.4-linux-amd64.tar.gz"
	                sh "cp linux-amd64/helm ."
                        sh "./helm repo list"
                        sh "ls -la"
    sh "./helm install  nginx  ./nginx"                         
                        sh "echo 'upgrade app!'"
                        sh "./helm upgrade 01--install --wait app ./app" 
                         
            */   

        //     }
            }
        }    }    }
 }
