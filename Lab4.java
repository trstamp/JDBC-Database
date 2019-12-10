import java.sql.*;
import java.util.*;

public class Lab4 {
	
	private Connection Connect() {
		Connection conn = null;
        try {
            // db parameters
            String url = /*Path to sqlite database*/;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;
            
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
        return null;
	}
	
	public void transactions() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		int option = 0;
		while(option != 10) {
			MainMenu();
			option = sc.nextInt();
			if(option > 10) {
				System.out.println("Invalid Option.");
				MainMenu();
				option = sc.nextInt();
			}
			if(option == 1) {
				System.out.println("Enter Start Location: ");
				String ans = sc.nextLine();
				System.out.println("\nEnter Destination: ");
				String ans2 = sc.nextLine();
				System.out.println("Enter Date: ");
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
				System.out.println("Enter Trip Number: ");
				int num = sc.nextInt();
				System.out.println("Would you like to add or delete this trip? ");
				String ans = sc.nextLine();
				if(ans == "add") {
					try {
						Connection conn = this.Connect();
						Statement st = conn.createStatement();
						String sql = "INSERT INTO TripOffering(TripNumber) VALUES(" + num + ");" ;
						st.executeUpdate(sql);
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else if(ans == "delete") {
					try {
						Connection conn = this.Connect();
						Statement st = conn.createStatement();
						String sql = "DELETE FROM TripOffering"
									+ "WHERE TripNumber =" + num + ";";
						st.executeUpdate(sql);
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else if(ans == "no") {
					System.out.println("Would you like to change the bus of this trip? ");
					String ans2 = sc.nextLine();
					if(ans2 == "yes") {
						System.out.println("Enter BusID: ");
						int num2 = sc.nextInt();
						String sql = "UPDATE TripOffering"
									+ "SET BusID =" + num2
									+ "WHERE TripNumber =" + num + ";";
					}
					else {
						MainMenu();
						option = sc.nextInt();
					}
				}
				else {
					System.out.println("Invalid option. ");
					MainMenu();
					option = sc.nextInt();
				}
			}
			else if(option == 3) {
				System.out.println("Insert Trip Number: ");
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
				System.out.println("Enter Driver Name: ");
				String name = sc.nextLine();
				System.out.println("Enter Date");
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
				System.out.println("Add Driver Name: ");
				String name = sc.nextLine();
				System.out.println("Enter Driver Phone Number: ");
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
				System.out.println("Add BusID: ");
				int ID = sc.nextInt();
				System.out.println("Add bus model: ");
				String mod = sc.nextLine();
				System.out.println("Add bus year: ");
				int year = sc.nextInt();
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "INSERT INTO Bus VALUES(" + ID + "," + mod + "," + year + ");";
					st.executeUpdate(sql);
					System.out.println("Insertion complete.");
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 7) {
				System.out.println("Enter Bus ID for deletion: ");
				int ID = sc.nextInt();
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "DELETE FROM Bus"
								+ "WHERE BusID =" + ID + ";";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 8) {
				System.out.println("Enter DriverName for deletion: ");
				String name = sc.nextLine();
				try {
					Connection conn = this.Connect();
					Statement st = conn.createStatement();
					String sql = "DELETE FROM Driver"
								+ "WHERE DriverName =" + name + ";";
					st.executeUpdate(sql);
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else if(option == 9) {
				System.out.println("Which trip would you like to add? ");
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
