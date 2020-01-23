	package gameClient;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.HashMap;

	/**
 * This class represents a simple example of using MySQL Data-Base.
 * Use this example for writing solution. 
 * @author boaz.benmoshe
 *
 */
public class SimpleDB {
	public static final String jdbcUrl="jdbc:mysql://db-mysql-ams3-67328-do-user-4468260-0.db.ondigitalocean.com:25060/oop?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
	public static final String jdbcUser="student";
	public static final String jdbcUserPassword="OOP2020student";
	private static Connection connection = null;
	private static Statement statement = null;
	private static int[][] table = {{0, 145, 290}, {1, 450, 580}, {3, 750, 580},
									{5, 570, 500}, {9, 510, 580}, {11, 1050, 580},
									{13, 310, 580}, {16, 235, 290}, {19, 250, 580},
									{20, 200, 290}, {23, 1000, 1140}};


		/**
	 * Simple main for demonstrating the use of the Data-base
	 * @param args
	 */
	public static void main(String[] args) {
			int id1 = 0;  // "real" existing ID & KML
			int id2 = 316316249;
			int level = 1;//1,2,3
			printLog(id2);
			//allUsers();
			String kml1 = getKML(id2,level);
			System.out.println("***** KML1 file example: ******");
			System.out.println(kml1);
		}
	/** simply prints all the games as played by the users (in the database).
	 * 
	 */
		public static void printLog(int id) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = 
						DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
				Statement statement = connection.createStatement();
				String allCustomersQuery = "SELECT * FROM Logs where userID="+id;
			
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
				int ind =0;
				while(resultSet.next())
				{
					System.out.println(ind+") Id: " + resultSet.getInt("UserID")+", level: "+resultSet.getInt("levelID")+", score: "+resultSet.getInt("score")+", moves: "+resultSet.getInt("moves")+", time: "+resultSet.getDate("time"));
					ind++;
				}
				resultSet.close();
				statement.close();		
				connection.close();		
			}
			
			catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("Vendor Error: " + sqle.getErrorCode());
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	/**
	 * this function returns the KML string as stored in the database (userID, level);
	 * @param id
	 * @param level
	 * @return
	 */
			public static String getKML(int id, int level) {
				String ans = null;
				String allCustomersQuery = "SELECT * FROM Users where userID="+id+";";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);		
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(allCustomersQuery);
					if(resultSet!=null && resultSet.next()) {
						ans = resultSet.getString("kml_"+level);
					}
				}
				catch (SQLException sqle) {
					System.out.println("SQLException: " + sqle.getMessage());
					System.out.println("Vendor Error: " + sqle.getErrorCode());
				}
				
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return ans;
			}
		public static int allUsers() {
			int ans = 0;
			String allCustomersQuery = "SELECT * FROM Users;";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = 
						DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);		
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
				while(resultSet.next()) {
					System.out.println("Id: " + resultSet.getInt("UserID")+", max_level:"+resultSet.getInt("levelNum"));
					ans++;
				}
				resultSet.close();
				statement.close();		
				connection.close();
			}
			catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("Vendor Error: " + sqle.getErrorCode());
			}
			
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return ans;
		}


/*******************************************************************************************/
	public static int getNumOfGames(int id) {
		try {
			int counter = 0;
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Logs WHERE UserID=" + id +"  ; ";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				counter++;

			}
			return counter;

		} catch (Exception e){
			e.printStackTrace();
		}
		return -1;
	}


	public static int getLevel(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			Statement statement = connection.createStatement();
			boolean bool = true;
			int level = 0;
			int x = 0;
			while (bool == true) {
				String allCustomersQuery = "SELECT * FROM Logs WHERE UserID=" +
											id + " AND score >=" + table[x][1] +
											" AND levelID=" + table[x][0] +
											" AND moves<=" + table[x][2] + "  ; ";
				ResultSet resultSet = statement.executeQuery(allCustomersQuery);
				while (resultSet.next()) {
					if (resultSet.getInt("UserID") == id)
						level = table[x][0];
					else
						bool = false;
				}
				if (x < table.length - 1)
					x++;
				else
					bool = false;
			}
			return level;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	public static HashMap<Integer, String> getMyScores(int id) {
		String query = "SELECT * FROM Logs as logs inner join (" + "SELECT max(score) as score, levelID FROM Logs"
				+ " where userID = " + id + " group by levelID" + ") as groupedLogs"
				+ " on logs.levelID = groupedLogs.levelID and logs.score = groupedLogs.score" + " where userID = " + id
				+ " order by logs.levelID asc";
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<Integer, String> myResaults = new HashMap<>();
		try {
			while (resultSet.next()) {
				String value = "" + resultSet.getInt("userID") + "," + resultSet.getInt("levelID") + ","
						+ resultSet.getInt("score") + "," + resultSet.getInt("moves") + "," + resultSet.getDate("time");
				myResaults.put(resultSet.getInt("levelID"), value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			resultSet.close();
			connection.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myResaults;
	}

		public static HashMap<Integer, String> getBestScores(int id) {
			String query = "SELECT * FROM Logs as logs inner join ("
					+ "SELECT max(score) as score, levelID, userID FROM Logs" + "	group by levelID,userID"
					+ ") as groupedLogs"
					+ " on logs.userID = groupedLogs.userID and logs.levelID = groupedLogs.levelID and logs.score = groupedLogs.score"
					+ " order by logs.userID desc,logs.levelID asc";
			ResultSet resultSet = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcUserPassword);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			HashMap<Integer, String> bestResaults = new HashMap<>();
			try {
				while (resultSet.next()) {
					String value = "" + resultSet.getInt("userID") + "," + resultSet.getInt("levelID") + ","
							+ resultSet.getInt("score") + "," + resultSet.getInt("moves") + "," + resultSet.getDate("time");
					bestResaults.put(Integer.parseInt(resultSet.getInt("userID") + "," + resultSet.getInt("levelID")), value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				resultSet.close();
				connection.close();
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bestResaults;
		}


}
		
