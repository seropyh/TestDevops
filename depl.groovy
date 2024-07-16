pipeline{
    agent any
    stages {
        stage ("install helm"){
        steps {
        script {
        sh "ls -la"
        sh "chmod 777 ./helm"
def remote = [:]
    remote.name = 'test'
    remote.host = '192.168.49.1'
    remote.user = 'osv'
    remote.password = 'osv'
    remote.allowAnyHosts = true
    remote.fileTransfer = 'scp'
    stage('Remote SSH') {
      sshCommand remote: remote, command: "ls -lrt"
      sshCommand remote: remote, command: "for i in {1..5}; do echo -n \"Loop \$i \"; date ; sleep 1; done"
    }
    stage ('copy chart to remote server'){
      //writeFile file: './nginx/nginx/*', text: 'ls -lrt'
      
      sshPut remote: remote, from: './nginx/nginx/', into: '~/tchart/'
    }
}

}}}
}
//        sh "ssh -i /root/.ssh/rsa_id -n -f osv@192.168.49.1 date"
	//sh "./helm install  nginx  ./nginx/nginx"
/*    stage(" execute Ansible") {
           steps {
           ANSIBLE_HOST_KEY_CHECKING=False ansible-playbook -i ./ansible/hosts.ini ./ansible/playbook/nginxdepl.yml -u ubuntu

        /*        ansiblePlaybook (
                    installation: 'ansible', //Name of the Ansible tool which you had provided in Jenkins Tools configuration
                    inventory: './ansible/hosts', //Ansible inventory file path
                    playbook: './ansible/playbook/nginxdepl.yml' //Name along with the path of the ansible playbook
                )
            }            
            } */ 
    
            
    /*    stage("deploy") {
            agent {
                kubernetes {
                    cloud 'kubernetes'
                    label 'nginx'   
                  }               }
            steps {
    //        container('jnlp'){
                //    dir("helm"){
        //                sh "./helm install  nginx  ./nginx"
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
        //     }    //        }
    //  //  }     //    }
            
 
