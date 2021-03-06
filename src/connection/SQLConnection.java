package connection;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
	protected static Connection connection;
	protected static Statement statement;
	protected static ResultSet set;
	
	protected static final class Tables {
		public static final String weather = "Weather";
		//TODO: ADD OTHER TABLES HERE
	}

	protected static Statement connect() throws ClassNotFoundException, SQLException {
		//TODO: ADD THIS 
		String dbname = "";
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:resources/" + dbname);
		statement = connection.createStatement();
		return statement;
	}
	
	protected static void create(String tableName) throws ClassNotFoundException, SQLException {
		statement.execute("CREATE TABLE if not exists " + tableName +
				//TODO: SET ADD THIS
				" id INTEGER PRIMARY KEY AUTOINCREMENT, ;");
	}
	
	protected static void insert(String tableName, String values) throws SQLException, ClassNotFoundException {
		//TODO: COMPLETE THIS
		statement.execute("INSERT INTO '" + tableName + "' () VALUES " + values + ";");
	}
	protected static void read(PrintStream ps, String tableName) throws ClassNotFoundException, SQLException {
		set = statement.executeQuery("SELECT * FROM " + tableName);
		while (set.next()) {
			ps.print("ID = " + set.getInt("id"));
			//TODO: PUT CODE HERE
			ps.println();
		}
	}
	
	
	protected static void delete(String tableName) throws ClassNotFoundException, SQLException {
		statement.execute("DROP TABLE '" + tableName + "';");
	}
	
	protected static void close() throws SQLException {
		if (!statement.isClosed())
			statement.close();
		if (!connection.isClosed())
			connection.close();
		if (set != null && !set.isClosed())
			set.close();
	}
}
