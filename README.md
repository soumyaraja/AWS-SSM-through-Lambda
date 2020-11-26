# AWS-SSM-through-Lambda

## prerequisite
* create lambda fn in aws and provides required access to run ssm
* create ec2 instance and attach ssm role to ec2
* create trigger to run lamnbda function
* deploy code to lambda function

### Steps to execture by this ulitiy fn

* lambda fn to start ec2 instance, instanceid to be provided by user and can be store in ssm system parameter/security manager/lambda environmanr varable
* provides required delay to spin up ec2
* execute ssm run to remotely execute command in ec2
* if executable file stores in aws s3, copy the files in ec2 before running ssm command.




