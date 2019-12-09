import java.sql.*;
import java.util.*;

public class Lab4 {
	
	private Connection Connect() {
		Connection conn = null;
        try {
            // db parameters
            String url = /*Put your URL here*/;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
	}
	
	public void transactions() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		
		try {
			Connection conn = this.Connect();
			Statement st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		int option = 0;
		while(option != 10) {
			MainMenu();
			option = sc.nextInt();
			if(option > 10) {
				System.out.println("Invalid Option.");
				MainMenu();
			}
			if(option == 1) {
				System.out.print("Enter Start Location: ");
				String ans = sc.nextLine();
				System.out.print("Enter Destination: ");
				String ans2 = sc.nextLine();
				System.out.print("Enter Date: ");
				String ans3 = sc.nextLine();
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "SELECT * "
							+ "FROM ActualTripStopInfo "
							+ "WHERE TripNumber =" + ans + "AND ScheduledStartTime =" 
							+ ans2 + "AND StopNumber =" + ans3 + ";";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 2) {
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 3) {
				System.out.print("Insert Trip Number: ");
				int num = sc.nextInt();
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "SELECT * FROM TripStopInfo" +
								"WHERE TripNumber =" + num + ";";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 4) {
				System.out.print("Enter Driver Name: ");
				String name = sc.nextLine();
				System.out.print("Enter Date");
				String date = sc.nextLine();
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "SELECT *"
								+ "FROM TripOffering"
								+ "WHERE DriverName =" + name + "AND Date =" + date + ";";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 5) {
				System.out.print("Add Driver Name: ");
				String name = sc.nextLine();
				System.out.print("Enter Driver Phone Number: ");
				String pNum = sc.nextLine();
				
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "INSERT INTO Driver VALUES(" + name + "," + pNum + ")";
					st.executeUpdate(sql);
					System.out.println("Insertion complete.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 6) {
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 7) {
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 8) {
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 9) {
				System.out.print("Which trip would you like to add? ");
				int ans = sc.nextInt();
				String ans2 = sc.nextLine();
				int ans3 = sc.nextInt();
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "INSERT INTO ActualTripInfo "
							+ "VALUES(" + ans + "," + ans2 + "," + ans3 + ")";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 10) {
				System.exit(0);
			}
		}
		
	}
	
	public void MainMenu() {
		System.out.println("Welcome to the Pomona Transit System!");
		System.out.println("Please select an option:");
		System.out.println("(1): Schedules for a certain Start Point");
		System.out.println("(2): Edit the Schedule");
		System.out.println("(3): Display Stops of a Trip");
		System.out.println("(4): Display Schedule of a Driver");
		System.out.println("(5): Add a Driver");
		System.out.println("(6): Add a Bus");
		System.out.println("(7): Delete a Driver");
		System.out.println("(8): Delete a Bus");
		System.out.println("(9): Find Actual Trip Info");
		System.out.println("(10): Exit the Database");
	}

}
