# spring_docker_thymeleaf
I am giving some step to install docker with mysql.

before doning all please do some before task

1. Install MAVEN On Host machine and test it (required)

2. Install CURL On Host and test it (Optional)

Open Docker quickstart Terminal

create new vm or enviornment

$docker-machine create --driver virtualbox dev

Where dev is name of VM

$docker-machine ls

TO see the dev VM

$docker-machine active

to see the active VM in which you are working 

$eval $(docker-machine env dev)

to go into dev machine

$docker-machine active

to check active machine again

$docker-machine ip dev

to get the ip address of docker-machine

Now Install Some default Images

$docker pull mysql:latest / $docker pull mysql:5.7 / $docker pull mysql

to install mySQl latest version

$docker pull java:8

to install java 8 

$docker images

to see the current images

$docker run --name demo-mysql -e MYSQL_ROOT_PASSWORD=1234 -p 3306:3306 -d mysql

How it will create demo-mysql container and start it on part 3306
-d option tell about the image from which you creating this container so always keep in mind container always create on the base of image so image is required

$docker ps 

to see running containers or process

$docker ps -a

to see all runing and non-runnig containers


Now we will deploy our spring boot application

GO to your application where you have Dockerfile

$mvn clean install

to create a new build

$docker build -t demo-spring .

to craete new image of your spring boot application

$docker images 

to check you images now you will see three images (mysql,java and demo-spring)

$docker run --name demo-spring-container --link demo-mysql -p 5000:5000 -d demo-spring

How your conatiner with mysql link created

$docker ps

You will see to conatiner for running

demo-mysql

demo-spring-container

$curl http://<conatiner machine ip>:5000/





