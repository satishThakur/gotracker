import Dependencies.Libraries

val scala3Version = "3.2.1"
lazy val root = (project in file("modules/core"))
  .settings(
    name := "gotracker",
    version := "0.1.0-SNAPSHOT",
    assembly / mainClass := Some("tracker.Main"),

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
      Libraries.logback,
      Libraries.ciris,
      Libraries.jwtCore,
    ),
  )

ThisBuild / assemblyMergeStrategy := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case PathList("module-info.class") => MergeStrategy.discard
  case PathList("META-INF", "versions", xs@_, "module-info.class") => MergeStrategy.discard
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

