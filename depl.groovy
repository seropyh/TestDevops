
/*    agent  any

    stages {
        stage('Подготовка') {
            steps {
    		script{
		sh "ls -la"
		}
            }
        }
        stage('Сборка') {
            steps {
    	    echo 'Проводим сборку'
            }
        }
        stage('Тестирование') {
            steps {
                echo 'Тестируем нашу сборку'
            }
        }
        stage('Развертывание') {
            steps {
                echo 'Переносим код в рабочую среду или создаем артефакт'
            }
        }
    }
    */
pipeline {
    agent none
    stages {
        stage ("install helm"){
        steps {
        script {
        sh "curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3"
        sh "chmod 700 get_helm.sh"
        sh"./get_helm.sh"
        }
        }
        }
        stage("deploy") {
            agent {
                kubernetes {
                    cloud 'kubernetes'
                    label 'nginx'
                
                }
            
            steps {
                container('jenkins') {
                    dir("helm") {
                        sh "echo 'Simple helm install!'"
                        sh "wget https://get.helm.sh/helm-v3.2.4-linux-amd64.tar.gz"
                        sh "tar zxfv helm-v3.2.4-linux-amd64.tar.gz"
                        sh "cp linux-amd64/helm ."

                        sh "echo 'upgrade app!'"
                        sh "./helm upgrade --install --wait app ./app"
                    }
                }
            }
        }
    }
}    
