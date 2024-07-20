Для использования чартов необходимо
1. На хостовой машине где будет развернут миникуб сгенерить ключевую пару:
  Создание ключей SSH 
  Проверка есть ли ключи 
  # ls -l ~/.ssh
  генерация ключей 
  # ssh-keygen -t rsa 
  вывод команды
  +---[RSA 3072]----+
  |                 |
  |        E        |
  |         .       |
  |        .    . . |
  | .      S.. . . .|
  |o . ..o. +o..oo .|
  |oO ....o. +ooo...|
  |%.=.  . .= = + + |
  |#Oo.   . .B.+ o  |
  +----[SHA256]-----+
  # cd ~/.ssh
  # cat id_rsa.pub
  Копируем и вставляем в гитхаб 
  профиль -> settings ->SSH and GPG keys 
  проверка 
  # ssh -T git@github.com
  добавляем в агент
  # ssh-add ~/.ssh/id_rsa
2. Включена ли виртуализация на VMBox
  включить виртуализацию в вм бокс (Windows)
  C:\Program Files\Oracle\VirtualBox
  VBoxManage.exe list vms
  VBoxManage.exe modifyvm "Jenkins slave_1_minikube" --nested-hw-virt on
3. Обновляем пвкеты на хост машине
  # sudo yum update
4. Устанавливаем Гит.
    подключаемся к этому репозиторию.
    выполняем команду
   # git pull 
5. Устанавливаем миникуб
   minicube
   сначала kubectl
   # curl -LO https://dl.k8s.io/release/`curl -LS https://dl.k8s.io/release/stable.txt`/bin/linux/amd64/kubectl
   # chmod +x ./kubectl  
   # sudo mv ./kubectl /usr/local/bin/kubectl
   или с помощью  пакета
   # sudo cat <<EOF > /etc/yum.repos.d/kubernetes.repo
   [kubernetes]
   name=Kubernetes
   baseurl=https://packages.cloud.google.com/yum/repos/kubernetes-el7-x86_64
   enabled=1
   gpgcheck=1
   repo_gpgcheck=1
   gpgkey=https://packages.cloud.google.com/yum/doc/rpm-package-key.gpg
   EOF
   # sudo yum install -y kubectl
   ---------------
   minikube +podman (если нужно)
   #curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \
   && chmod +x minikube
   #  sudo mkdir -p /usr/local/bin/
   #  sudo install minikube /usr/local/bin/
   #  sudo yum -y install podman
   Add your user to the 'sudoers' file: 'osv ALL=(ALL) NOPASSWD: /usr/bin/podman'
  # minikube start --driver=podman
  скачивается кубернетес запускается
  установка аддона дашборда
  # minikube addons enable helm-tiller
  # minikube addons enable ingress
6. Определяем ip minikube
   # minikube ip - сохраняем этот адрес  
7. Установка helm  на хост машину.
   # sudo yum install helm
8. Установка дженкинса из локального каталога скаченного из репозитория
   # helm install -n  jenkins jenkins01 /home/osv/helm_charts/git_devops/TestDevops/jenkins/
9. Jenkins будет отвечать на порту 3005 на ip minicube c п.6 
   У меня http://192.168.49.2:30005/
10. Скачать плагин SSH Pipeline Steps
11. Создать пайплайн и подключить его к репозиторию
    Dashboard - создать итем  - вводим имя пайплайна - pipeline -  ок 
    Настройки пайплайна:
    Заходим в пайплайн - настройки
    Внизу: Pipeline
    definition
    Выбираем pineline script from SCM
    Далее
    SCM - Git
    Repository URL - https://github.com/seropyh/TestDevops.git
    Credintials
    Add - Jenkins
    kind: username with ssh key
    username: .....
    private key  - enter directly  - add вставляем ключ ~/.ssh/id_rsa
    Branches to build - */main
    Script path depl.groovy
    


