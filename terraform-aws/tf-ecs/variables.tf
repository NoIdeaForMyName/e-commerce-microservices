variable "vpc_id" {
  type = string
}

variable "kafka_url" {
  description = "Kafka server connection URL"
  type        = string
  sensitive   = true
}

variable "db_base_url" {
  description = "Database connection URL"
  type        = string
  sensitive   = true
}

variable "db_username" {
  description = "Database username"
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "Database password"
  type        = string
  sensitive   = true
}
