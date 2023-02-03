import Dependencies.Libraries

val scala3Version = "3.2.1"

lazy val root = (project in file("modules/core"))
  .settings(
    name := "gotracker",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    resolvers ++= Resolver.sonatypeOssRepos("snapshots"),
    scalacOptions ++= List("-feature", "-deprecation", "-Ykind-projector:underscores", "-source:future"),

    libraryDependencies ++= Seq(
      Libraries.cats,
      Libraries.catsEffect,
      Libraries.httpsDsl,
      Libraries.httpsServer,
      Libraries.httpsClient,
      Libraries.httpsCirce,
      Libraries.circeCore,
      Libraries.circeParser,
      Libraries.circeRefined,
      Libraries.munit,
      Libraries.munitScalaCheck,
      Libraries.weaver,
      Libraries.weaverScalaCheck,
      Libraries.log4cats,
    ),
  )

ThisBuild / assemblyMergeStrategy := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

