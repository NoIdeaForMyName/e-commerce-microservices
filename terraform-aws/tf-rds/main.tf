provider "aws" {
  region = "us-east-1"
}

data "aws_vpc" "microservices_vpc" {
  id = var.vpc_id
}


resource "aws_db_instance" "microservices_db" {
  identifier             = "microservicesdb"
  instance_class         = "db.t4g.micro"
  allocated_storage      = 5
  engine                 = "postgres"
  engine_version         = "17.2"
  username               = var.db_username
  password               = var.db_password
  publicly_accessible    = false
  skip_final_snapshot    = true
  vpc_security_group_ids = [aws_security_group.rds_sg.id]
}


resource "aws_security_group" "rds_sg" {
  name   = "allow_db_connection_rds_sg"
  vpc_id = data.aws_vpc.microservices_vpc.id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["172.31.0.0/16"]
  }
}
