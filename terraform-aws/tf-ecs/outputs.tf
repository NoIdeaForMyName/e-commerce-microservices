output "services_ports" {
  description = "Names and associated ports of microservices working inside ECS ecommerce task"
  value = [
    for task in jsondecode(aws_ecs_task_definition.ecommerce_task.container_definitions) : {
      name = task.name
      port = task.portMappings[0].hostPort
    }
  ]
  sensitive = true
}
