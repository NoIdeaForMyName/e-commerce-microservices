resource "aws_ecs_task_definition" "ecommerce_task" {
  container_definitions = jsonencode([
    {
      name      = "order-service"
      image     = "docker.io/noideaformyname/order-service-image:latest"
      essential = true
      environment = [
        {
          name  = "KAFKA_URL"
          value = var.kafka_url
        },
        {
          name  = "DB_URL"
          value = "${var.db_base_url}/order_db"
        },
        {
          name  = "DB_USERNAME"
          value = var.db_username
        },
        {
          name  = "DB_PASSWORD"
          value = var.db_password
        }
      ]
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
    },
    {
      name      = "client-service"
      image     = "docker.io/noideaformyname/client-service-image:latest"
      essential = true
      environment = [
        {
          name  = "KAFKA_URL"
          value = var.kafka_url
        },
        {
          name  = "DB_URL"
          value = "${var.db_base_url}/client_db"
        },
        {
          name  = "DB_USERNAME"
          value = var.db_username
        },
        {
          name  = "DB_PASSWORD"
          value = var.db_password
        }
      ]
      portMappings = [
        {
          containerPort = 8081
          hostPort      = 8081
        }
      ]
    },
    {
      name      = "inventory-service"
      image     = "docker.io/noideaformyname/inventory-service-image:latest"
      essential = true
      environment = [
        {
          name  = "KAFKA_URL"
          value = var.kafka_url
        },
        {
          name  = "DB_URL"
          value = "${var.db_base_url}/inventory_db"
        },
        {
          name  = "DB_USERNAME"
          value = var.db_username
        },
        {
          name  = "DB_PASSWORD"
          value = var.db_password
        }
      ]
      portMappings = [
        {
          containerPort = 8082
          hostPort      = 8082
        }
      ]
    },
    {
      name      = "payment-service"
      image     = "docker.io/noideaformyname/payment-service-image:latest"
      essential = true
      environment = [
        {
          name  = "KAFKA_URL"
          value = var.kafka_url
        },
        {
          name  = "DB_URL"
          value = "${var.db_base_url}/payment_db"
        },
        {
          name  = "DB_USERNAME"
          value = var.db_username
        },
        {
          name  = "DB_PASSWORD"
          value = var.db_password
        }
      ]
      portMappings = [
        {
          containerPort = 8083
          hostPort      = 8083
        }
      ]
    },
    {
      name      = "shipping-service"
      image     = "docker.io/noideaformyname/shipping-service-image:latest"
      essential = true
      environment = [
        {
          name  = "KAFKA_URL"
          value = var.kafka_url
        },
        {
          name  = "DB_URL"
          value = "${var.db_base_url}/shipping_db"
        },
        {
          name  = "DB_USERNAME"
          value = var.db_username
        },
        {
          name  = "DB_PASSWORD"
          value = var.db_password
        }
      ]
      portMappings = [
        {
          containerPort = 8084
          hostPort      = 8084
        }
      ]
    }
  ])

  family                   = local.application_name
  requires_compatibilities = [local.launch_type]
  network_mode             = "awsvpc"

  cpu    = 2048
  memory = 5120

  runtime_platform {
    operating_system_family = "LINUX"
    cpu_architecture        = "X86_64"
  }

}
