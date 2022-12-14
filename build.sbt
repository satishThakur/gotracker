import Dependencies.Libraries

val scala3Version = "3.2.0"
idePackagePrefix := Some("com.satish.tracker")

lazy val root = (project in file("."))
  .settings(
    name := "gotracker",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    resolvers += Resolver.sonatypeRepo("snapshots"),
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
      Libraries.circeExtras,
      Libraries.circeRefined,
    ),
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
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

