
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
<<<<<<< HEAD
agent any  
=======
pipeline {    
>>>>>>> b30914d (install helm pod)
stages {
        stage("deploy") {
	agent {
                kubernetes {
                    cloud 'kubernetes'
<<<<<<< HEAD
                    label 'jenkins'
                }
            }	
=======
                    label 'cd'
                    sh "echo Hello helm"
                }    
            }
>>>>>>> b30914d (install helm pod)

        
            steps {
<<<<<<< HEAD
		    script {
		sh "echo Hello world"	
		}
                container('jenkins') {
=======
                container('nsnginx') {
>>>>>>> b30914d (install helm pod)
                    dir("helm") {
                        
                        sh "echo 'Simple helm install!'"
                        sh "wget https://get.helm.sh/helm-v3.2.4-linux-amd64.tar.gz"
                        sh "tar zxfv helm-v3.2.4-linux-amd64.tar.gz"
                        sh "cp linux-amd64/helm ."

                        sh "echo 'upgrade app!'"
                        sh "./helm upgrade --instaODll --wait app ./app"
                    }
                }
            }
        }
    }
}    
