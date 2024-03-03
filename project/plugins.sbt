/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, which was derived from Akka.
 */

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "2.1.5")

addSbtPlugin("com.lightbend.sbt" % "sbt-java-formatter" % "0.7.0")
addSbtPlugin("com.lightbend.sbt" % "sbt-bill-of-materials" % "1.0.2")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.11.1")
addSbtPlugin("com.github.sbt" % "sbt-osgi" % "0.10.0")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.1.3")
addSbtPlugin("com.github.sbt" % "sbt-unidoc" % "0.5.0")
addSbtPlugin("com.thoughtworks.sbt-api-mappings" % "sbt-api-mappings" % "3.0.2")
addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.4.7")
addSbtPlugin("com.github.sbt" % "sbt-boilerplate" % "0.7.0")

addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.10.0")
addSbtPlugin("com.github.sbt" % "sbt-pull-request-validator" % "2.0.0")
addSbtPlugin("net.bzzt" % "sbt-reproducible-builds" % "0.32")

addSbtPlugin("com.github.sbt" % "sbt-dynver" % "5.0.1")
addSbtPlugin("com.github.pjfanning" % "sbt-source-dist" % "0.1.12")
addSbtPlugin("com.github.pjfanning" % "sbt-pekko-build" % "0.3.3")
addSbtPlugin("com.github.reibitto" % "sbt-welcome" % "0.4.0")
addSbtPlugin("com.github.sbt" % "sbt-license-report" % "1.6.1")
addSbtPlugin("io.github.roiocam" % "sbt-depend-walker" % "0.1.1")

// We have to deliberately use older versions of sbt-paradox because current Pekko sbt build
// only loads on JDK 1.8 so we need to bring in older versions of parboiled which support JDK 1.8
addSbtPlugin(("org.apache.pekko" % "pekko-sbt-paradox" % "1.0.0").excludeAll(
  "com.lightbend.paradox", "sbt-paradox",
  "com.lightbend.paradox" % "sbt-paradox-apidoc",
  "com.lightbend.paradox" % "sbt-paradox-project-info"))
addSbtPlugin(("com.lightbend.paradox" % "sbt-paradox" % "0.9.2").force().exclude("com.typesafe.sbt", "sbt-web"))
addSbtPlugin(("com.lightbend.paradox" % "sbt-paradox-apidoc" % "0.10.1").force().exclude("com.typesafe.sbt", "sbt-web"))
addSbtPlugin(("com.lightbend.paradox" % "sbt-paradox-project-info" % "2.0.0").force()
  .exclude("com.typesafe.sbt", "sbt-web"))
addSbtPlugin("com.github.sbt" % "sbt-web" % "1.5.4") // sbt-paradox 0.9.2 depends on old sbt-web 1.4.x, but we want a newer version
