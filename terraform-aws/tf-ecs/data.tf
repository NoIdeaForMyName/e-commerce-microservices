data "aws_vpc" "microservices_vpc" {
  id = var.vpc_id
}

data "aws_subnets" "microservices_subnets" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.microservices_vpc.id]
  }
}
