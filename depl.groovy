pipeline {
    agent  any

    stages {
        stage('Подготовка') {
            steps {
    		script{
                sh "python --version"
		sh "ls -la"
		}            
            }
        }
        stage('Сборка') {
            steps {
    	    
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
}