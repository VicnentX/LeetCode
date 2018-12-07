import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class Populate {
    public static void main(String[] args) {
        Populate gen = new Populate();
        gen.execute(args);
        System.out.println("Finished");
    }


    public void execute(String[] args) {
        Connection conn = null;
        try {
            System.out.println("DB server connecting...");
            conn = openConnect();
            System.out.println("DB server connection successfully");
            for (int i = 0; i < args.length; i++) {
                if (args[i].contains("user")) {
                    publishUserData(conn, args[i]);
                } else {
                    populateData(conn, args[i]);
                }
            }
        } catch (SQLException e) {
            System.err.println("[Error]: Errors occurs when connecting to the database server: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("[Error]: Cannot find the database driver");
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            closeConnect(conn);
        }
    }

    private void populateData(Connection conn, String fName) throws SQLException, IOException {
        Statement stmt = conn.createStatement();
        FileReader fReader = new FileReader(fName);
        BufferedReader bufReader = new BufferedReader(fReader);

        String tableName = fName.replaceFirst(".dat", "");
        System.out.println("[Info]: Deleting previous " + tableName);
        stmt.executeUpdate("DELETE FROM " + tableName);
        System.out.println("[Info]: Inserting Data " + tableName);
        String tuple = bufReader.readLine(); // ignore first line
        int fieldLen = tuple.split("\t").length;
        if (tableName.equals("movie_locations")) {
            fieldLen = 5;
        }
        while ((tuple = bufReader.readLine()) != null) {
            String[] fields = tuple.split("\t");
            String prefix = "";
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < fieldLen; i++) {
                String attr = " ";
                if (i < fields.length && fields[i] != null) {
                    attr = fields[i].replaceAll("'", "''");
                }
                if (attr.equals("\\N")) {
                    attr = "";
                }

                sb.append(prefix + "'" + attr + "'");
                prefix = ", ";
            }
            stmt.executeUpdate("INSERT INTO " + tableName + " VALUES (" + sb.toString() + ")");
        }

        fReader.close();
        stmt.close();
    }


    private void publishUserData(Connection conn, String fName) throws SQLException, IOException {
        Statement stmt = conn.createStatement();
        FileReader fReader = new FileReader(fName);
        BufferedReader bufReader = new BufferedReader(fReader);

        String tableName = fName.replaceFirst(".dat", "");
        boolean timestamp = false;
        if (tableName.contains("-timestamps")) {
            System.out.print("[Info]: " + tableName + "share table with ");
            timestamp = true;
            tableName = tableName.replaceFirst("-timestamps", "");
            System.out.println(tableName);
        }

        System.out.println("[Info]: Deleting previous tuplies from " + tableName);
        stmt.executeUpdate("DELETE FROM " + tableName);
        System.out.println("[Info]: Inserting Data into " + tableName);
        String tuple = bufReader.readLine();
        int fieldLen = 3;
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?,?,?)");
        while ((tuple = bufReader.readLine()) != null) {
            String[] fields = tuple.split("\t");
            for (int i = 0; i < fieldLen; i++) {
                pstmt.setString(i + 1, fields[i]);
            }
            StringBuilder sb = new StringBuilder();
            if (!timestamp) {
                sb.append(fields[5] + "-" + fields[4] + "-" + fields[3] + " ");
                sb.append(fields[6] + ":" + fields[7] + ":" + fields[8]);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                Date date = new Date(Long.parseLong(fields[3]));
                sb.append(sdf.format(date));
            }
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(sb.toString()));
            pstmt.executeUpdate();
        }
        fReader.close();
        pstmt.close();
        stmt.close();
    }

    private Connection openConnect() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String host = "localhost";
        String port = "1521";
        String dbName = "orcl"; // Win: xe, MAC: orcl
        String uName = "bao";
        String pWord = "bao";

        // Construct JDBC URL
        String dbURL = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + dbName;
        return DriverManager.getConnection(dbURL, uName, pWord);
    }


    private void closeConnect(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("[Error]: Cannot close Oracle DB connection: " + e.getMessage());
        }
    }
}
