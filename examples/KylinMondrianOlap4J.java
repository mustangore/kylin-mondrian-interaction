package org.mustangore.kylin;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapStatement;
import org.olap4j.layout.RectangularCellSetFormatter;

public class KylinMondrianOlap4J {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Class.forName("mondrian.olap4j.MondrianOlap4jDriver");

    Connection connection = DriverManager.getConnection(
        "jdbc:mondrian:"
      + "Jdbc=jdbc:kylin://{YOUR_URL}:7070/{YOUR_PROJECT_NAME}};"
      + "JdbcDrivers=org.apache.kylin.jdbc.Driver;"
      + "JdbcUser={YOUR_USER};"               // Default: admin
      + "JdbcPassword={YOUR_PASSWORD};"       // Default: KYLIN
      + "Catalog=file:/absolute/path/to/your/mondrianSchema.xml;");

    // We are dealing with an OLAP connection. we must unwrap it.
    final OlapConnection olapConnection = connection.unwrap(OlapConnection.class);

    // Prepare a statement.
    final OlapStatement olapStatement = olapConnection.createStatement();

    // We use the utility formatter.
    RectangularCellSetFormatter formatter = new RectangularCellSetFormatter(false);

    // Your MDX Statement
    String mdxStatement = "{YOUR_MDX_QUERY}";

    CellSet cellSet = olapStatement.executeOlapQuery(mdxStatement);

    // Print out.
    PrintWriter writer = new PrintWriter(System.out);
    formatter.format(cellSet, writer);
    writer.flush();
  }
}
