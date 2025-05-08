locals {
  application_name = "ecommerce_microservices_fargate"
  launch_type      = "FARGATE"
}

resource "aws_ecs_cluster" "ecommerce_cluster" {
  name = local.application_name
}

resource "aws_ecs_service" "ecommerce_service" {
  name             = "ecommerce_spring_app"
  cluster          = aws_ecs_cluster.ecommerce_cluster.arn
  launch_type      = local.launch_type
  platform_version = "LATEST"

  deployment_maximum_percent         = 200
  deployment_minimum_healthy_percent = 0
  desired_count                      = 1

  task_definition = "${aws_ecs_task_definition.ecommerce_task.family}:${aws_ecs_task_definition.ecommerce_task.revision}"

  network_configuration {
    assign_public_ip = true
    security_groups  = [aws_security_group.ecs_sg.id]
    subnets          = data.aws_subnets.microservices_subnets.ids
  }
}

resource "aws_security_group" "ecs_sg" {
  name   = "allow_microservices_connection_ecs_sg"
  vpc_id = data.aws_vpc.microservices_vpc.id

  ingress {
    from_port   = 8080
    to_port     = 8084
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # for DockerHub connection
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
