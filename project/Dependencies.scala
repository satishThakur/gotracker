import sbt._

object Dependencies {
  object V {
    val cats             = "2.9.0"
    val catsEffect       = "3.4.4"
    val catsRedis        = "1.3.0"
    val catsRedisLog     = "1.3.0"
    val http4sVersion    = "1.0.0-M34"
    val circe            = "0.14.3"
    val fs2              = "3.4.0"
    val skunk            = "0.3.2"
    val munit            = "0.7.29"
    val weaver           = "0.8.1"
    val munitScalaCheck  = "0.7.29"
    val weaverScalaCheck = "0.8.1"
  }

  object Libraries {
    val cats             = "org.typelevel"       %% "cats-core"            % V.cats

    val catsEffect       = "org.typelevel"       %% "cats-effect"          % V.catsEffect

    val catsRedis        = "dev.profunktor"      %% "redis4cats-effects"   % V.catsRedis
    val catsRedisLog     = "dev.profunktor"      %% "redis4cats-log4cats"  % V.catsRedisLog

    val httpsDsl         = "org.http4s"          %% "http4s-dsl"           % V.http4sVersion
    val httpsServer      = "org.http4s"          %% "http4s-ember-server"  % V.http4sVersion
    val httpsClient      = "org.http4s"          %% "http4s-ember-client"  % V.http4sVersion
    val httpsCirce       = "org.http4s"          %% "http4s-circe"         % V.http4sVersion

    val circeCore        = "io.circe"            %% "circe-core"           % V.circe
    val circeParser      = "io.circe"            %% "circe-parser"         % V.circe
    val circeRefined     = "io.circe"            %% "circe-refined"        % V.circe

    val fs2Core          = "co.fs2"              %% "fs2-core"             % V.fs2
    val fs2IO            = "co.fs2"              %% "fs2-io"               % V.fs2
    val fs2Rs            = "co.fs2"              %% "fs2-reactive-streams" % V.fs2
    val fs2Scodec        = "co.fs2"              %% "fs2-scodec"           % V.fs2

    val skunkCore        = "org.tpolecat"        %% "skunk-core"           % V.skunk

    val munit            = "org.scalameta"       %% "munit"                % V.munit            % Test
    val munitScalaCheck  = "org.scalameta"       %% "munit-scalacheck"     % V.munitScalaCheck  % Test
    val weaver           = "com.disneystreaming" %% "weaver-cats"          % V.weaver           % Test
    val weaverScalaCheck = "com.disneystreaming" %% "weaver-scalacheck"    % V.weaverScalaCheck % Test
  }
}

