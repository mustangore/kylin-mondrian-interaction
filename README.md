# Kylin und Mondrain Interaction

Includes patches and jars for Kylin interaction with Mondrian and Saiku.

## Pre-installation Requirements
You should be able to run Kylin, to build and create a [Kylin](http://kylin.incubator.apache.org/) Cube successfully.
Also you will need a Mondrian 4 Schema, which describes your OLAP Cube in Kylin.
In our case we use Cloudera 5.4 Quickstart VM [[Download](http://www.cloudera.com/content/cloudera/en/downloads/quickstart_vms/cdh-5-4-x.html)].

## Kylin, Mondrian and Saiku Interaction

1. Download [Kylins JDBC](kylin/kylin-jdbc-1.0-incubating.jar) or download [Kylin Binaries](http://kylin.incubator.apache.org/download/) and extract the archive to get the current Kylin JDBC.
2. Download current [Saiku Community Edition](http://community.meteorite.bi/?cedownload).
3. Download compiled Mondrian 4.4 (Lagunitas) jar with Kylin dialect added (see [Mondrian](/mondrian) folder) or apply [our patch](mondrian/add-kylin-dialect.patch) to Mondrian and compile your own Mondrian 4.4 Snapshot.
4. Download newer [Commons HTTPClient](saiku/commons-httpclient-3.1.jar).
5. Extract Saiku (path is now called _saiku_root_path_).
6. Go to folder _saiku_path_/tomcat/webapps/saiku/WEB-INF/lib/
  * add Kylins JDBC jar
  * add the compiled Mondrian 4.4 jar with Kylin dialect
  * add newer Commons HTTPClient jar
  * remove old Mondrian jar
  * remove old Commons HTTPClient jar
7. Start Saiku (_sh start-saiku.sh_)
8. Go to http://localhost:8080/
  * User: admin
  * Password: admin
9. Click on Admin Console (big A in navigation bar)
10. Upload your Mondrian Schema
11. Add a new Data Source:
  * Name: Kylin Data Source
  * Connection Type: Mondrian
  * URL: jdbc:kylin://{your_kylin_url}:7070/{your_project_name}
  * Schema: {your_mondrian_schema}
  * JDBC Driver: org.apache.kylin.jdbc.Driver
  * Username: {your_kylin_username} (Standard: admin)
  * Password: {your_kylin_password} (Standard: KYLIN)

Have fun!

## Kylin and Mondrian with Java Application

Take a look into [Example Folder](/mondrian).
