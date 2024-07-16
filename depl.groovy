pipeline{
    agent any
    stages {
    
    stage('Remote SSH') {
      steps {
      def remote = [:]
	remote.name = 'test'
	remote.host = '192.168.49.1'
	remote.user = 'osv'
	remote.password = 'osv'
	remote.allowAnyHosts = true
	remote.fileTransfer = 'scp'
          }}
    stage ('copy chart to remote server'){
      steps {
      sshPut remote: remote, from: './nginx/nginx/', into: '~/tchart/'
    }}
    stage ('install  chart nginx to remote server'){
     steps {
      sshCommand remote: remote, command: "helm install -n  nsnginx nginx01 /home/osv/tchart/nginx/"
      sshRemove remote: remote, path: "/home/osv/tchart/nginx/"
}    
}

}
}