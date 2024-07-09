pipeline {
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
agent any  
stages {
        stage("deploy") {
	agent {
                kubernetes {
                    cloud 'kubernetes'
                    label 'cd'
                }
            }	

            steps {
		    script {
		sh "echo Hello world"	
		}
                container('jenkins') {
                    dir("helm") {
                        sh "echo 'Simple kubectl install!'"
                        sh "curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl"
                        sh "chmod +x ./kubectl"
                        sh "mv ./kubectl /usr/local/bin/kubectl"

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
