
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
