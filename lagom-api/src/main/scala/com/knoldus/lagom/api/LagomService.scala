package com.knoldus.lagom.api

import com.lightbend.lagom.scaladsl.api._
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.typesafe.config.ConfigFactory

trait LagomService extends Service  {
  def rmcStorageProductTopic: Topic[DummyClass]

  private val config = ConfigFactory.load()
  private val rmcKafkaEnvironment = config.getString("oculus.environment.rmc")

  final override def descriptor: Descriptor = {
    import Service._
    named("rmc-storage-product-service").withTopics(
      topic("configData", this.rmcStorageProductTopic)(CompressedJsonSerializer[DummyClass])
    )
  }
}
