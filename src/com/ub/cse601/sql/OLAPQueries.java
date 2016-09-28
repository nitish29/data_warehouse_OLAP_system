package com.ub.cse601.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OLAPQueries {


    public DBClient dbClient;
    public Connection conn;

    public OLAPQueries() {

        dbClient = new DBClient();
        conn = dbClient.getConn();

    }

    public int queryForTumorALLPatients ( String description, String type, String[] name ) throws SQLException {

        Statement stmt = null;
        int count = 0;

        try {

            String query = "select * from clinical_fact, disease where clinical_fact.DS_ID=disease.DS_ID AND disease.name='ALL' and disease.type='leukemia' and disease.description='tumor'";

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                count++;

            }


        } catch ( SQLException e ) {

            printSQLException(e);

        } finally {

            if ( stmt != null ) {

                stmt.close();

            }

        }
        return count;

    }

    public ArrayList<String> drugsForPatientsWithTumor( String description ) throws SQLException {

        ArrayList<String> drugList = new ArrayList<String>();

        Statement stmt = null;

        try {

            String query = "select drug.type,disease.DESCRIPTION from drug, clinical_fact,disease where clinical_fact.DR_ID=drug.DR_ID AND clinical_fact.DS_ID=disease.DS_ID AND disease.DESCRIPTION='tumor'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String drugType = rs.getString("TYPE");
                String[] drugData = drugType.split(" ");
                drugList.add(drugData[2]);

            }


        } catch ( SQLException e ) {

            printSQLException(e);

        } finally {

            if ( stmt != null ) {

                stmt.close();

            }

        }

        return drugList;

    }


    public ArrayList<String> allDiseaseList() throws SQLException {

        ArrayList<String> diseaseList = new ArrayList<String>();

        Statement stmt = null;

        try {

            String query = "select disease.NAME from disease";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String name = rs.getString("NAME");
                diseaseList.add(name);

            }


        } catch ( SQLException e ) {

            printSQLException(e);

        } finally {

            if ( stmt != null ) {

                stmt.close();

            }

        }

        return diseaseList;

    }


	
	/*public static void main(String[] args){

        Statement stmt = null;

        String query = "select * from clinical_fact, disease where clinical_fact.DS_ID=disease.DS_ID AND disease.name='ALL' and disease.type='leukemia' and disease.description='tumor'";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int count = 0;

            while (rs.next()) {

                count++;

            }

            System.out.println("Count:" + count);

        } catch (SQLException e ) {
            e.printStackTrace();
        } *//*finally {
            if (stmt != null) {
                stmt.close(); }
        }*//*




	}
    */

    // Helper functions -- referenced from oracle Website

    public static void printSQLException(SQLException ex) {

        for ( Throwable e : ex ) {

            if ( e instanceof SQLException ) {

                if ( ignoreSQLException (
                        ( (SQLException )e ).
                                getSQLState() ) == false ) {

                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " +
                            ((SQLException)e).getSQLState());

                    System.err.println("Error Code: " +
                            ((SQLException)e).getErrorCode());

                    System.err.println("Message: " + e.getMessage());

                    Throwable t = ex.getCause();
                    while(t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }

    public static boolean ignoreSQLException(String sqlState) {

        if (sqlState == null) {
            System.out.println("The SQL state is not defined!");
            return false;
        }

        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32"))
            return true;

        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55"))
            return true;

        return false;
    }
	
}
