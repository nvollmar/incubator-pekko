/*
 * Copyright (C) 2009-2021 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.persistence.state

import scala.reflect.ClassTag

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import akka.actor.ClassicActorSystemProvider
import akka.actor.ExtendedActorSystem
import akka.actor.Extension
import akka.actor.ExtensionId
import akka.actor.ExtensionIdProvider
import akka.annotation.InternalApi
import akka.persistence.PersistencePlugin
import akka.persistence.PluginProvider
import akka.persistence.state.scaladsl.DurableStateStore
import akka.util.unused

/**
 * Persistence extension for queries.
 */
object DurableStateStoreRegistry extends ExtensionId[DurableStateStoreRegistry] with ExtensionIdProvider {

  override def get(system: ActorSystem): DurableStateStoreRegistry = super.get(system)
  override def get(system: ClassicActorSystemProvider): DurableStateStoreRegistry = super.get(system)

  def createExtension(system: ExtendedActorSystem): DurableStateStoreRegistry = new DurableStateStoreRegistry(system)

  def lookup: DurableStateStoreRegistry.type = DurableStateStoreRegistry

  @InternalApi
  private[akka] val pluginProvider
      : PluginProvider[DurableStateStoreProvider, DurableStateStore[_], javadsl.DurableStateStore[_]] =
    new PluginProvider[DurableStateStoreProvider, scaladsl.DurableStateStore[_], javadsl.DurableStateStore[_]] {
      override def scalaDsl(t: DurableStateStoreProvider): DurableStateStore[_] = t.scaladslDurableStateStore()
      override def javaDsl(t: DurableStateStoreProvider): javadsl.DurableStateStore[_] = t.javadslDurableStateStore()
    }

}

class DurableStateStoreRegistry(system: ExtendedActorSystem)
    extends PersistencePlugin[scaladsl.DurableStateStore[_], javadsl.DurableStateStore[_], DurableStateStoreProvider](
      system)(ClassTag(classOf[DurableStateStoreProvider]), DurableStateStoreRegistry.pluginProvider)
    with Extension {

  /**
   * Scala API: Returns the [[akka.persistence.state.scaladsl.DurableStateStore]] specified by the given
   * read journal configuration entry.
   *
   * The provided durableStateStorePluginConfig will be used to configure the journal plugin instead of the actor system
   * config.
   */
  final def durableStateStoreFor[T <: scaladsl.DurableStateStore[_]](
      durableStateStorePluginId: String,
      durableStateStorePluginConfig: Config): T =
    pluginFor(durableStateStorePluginId, durableStateStorePluginConfig).scaladslPlugin.asInstanceOf[T]

  /**
   * Scala API: Returns the [[akka.persistence.state.scaladsl.DurableStateStore]] specified by the given
   * read journal configuration entry.
   */
  final def durableStateStoreFor[T <: scaladsl.DurableStateStore[_]](durableStateStorePluginId: String): T =
    durableStateStoreFor(durableStateStorePluginId, ConfigFactory.empty)

  /**
   * Java API: Returns the [[akka.persistence.state.javadsl.DurableStateStore]] specified by the given
   * read journal configuration entry.
   */
  final def getDurableStateStoreFor[T <: javadsl.DurableStateStore[_]](
      @unused clazz: Class[T], // FIXME generic Class could be problematic in Java
      durableStateStorePluginId: String,
      durableStateStorePluginConfig: Config): T =
    pluginFor(durableStateStorePluginId, durableStateStorePluginConfig).javadslPlugin.asInstanceOf[T]

  final def getDurableStateStoreFor[T <: javadsl.DurableStateStore[_]](
      clazz: Class[T],
      durableStateStorePluginId: String): T =
    getDurableStateStoreFor[T](clazz, durableStateStorePluginId, ConfigFactory.empty())

}
