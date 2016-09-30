package com.ub.cse601.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OLAPQueries {

	public DBClient dbClient;
	public Connection conn;

	public OLAPQueries() {

		dbClient = new DBClient();
		conn = dbClient.getConn();

	}

	public Object[] queryForTumorALLPatients(String diseaseName, String patientId) throws SQLException {

		PreparedStatement stmt = null;
		int count = 0;
		List<String[]> queryOutput = null;
		String[] columnNames = null;
		Object[] output = null;

		try {
			output = new Object[3];
			queryOutput = new ArrayList<String[]>();
			StringBuilder query = new StringBuilder();
			query.append(
					"select cf.P_ID as PATIENT_ID, ds.name as DISEASE_NAME, ds.type as DISEASE_TYPE, ds.description as DISEASE_DESC from clinical_fact cf, disease ds where cf.DS_ID=ds.DS_ID");
			if (diseaseName != null && diseaseName.length() > 0) {
				query.append(" AND ds.name=?");
			}

			query.append(" and ds.type='leukemia' and ds.description='tumor' order by cf.P_ID ASC");

			stmt = conn.prepareStatement(query.toString());
			if (diseaseName != null && diseaseName.length() > 0) {
				stmt.setString(1, diseaseName);
			}
			ResultSet rs = stmt.executeQuery();
			int colCount = rs.getMetaData().getColumnCount();
			columnNames = new String[colCount];

			for (int i = 0; i < colCount; i++) {
				columnNames[i] = rs.getMetaData().getColumnLabel(i + 1);
			}

			while (rs.next()) {
				String[] row = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					row[i] = rs.getString(i + 1);
				}
				queryOutput.add(row);
				count++;
			}
			output[0] = count;
			output[1] = columnNames;
			output[2] = queryOutput;
		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}
		return output;

	}

	public Object[] q2drugsForPatientsWithTumor(String disease, String dsType, String diseaseDsc) throws SQLException {

		// ArrayList<String> drugList = new ArrayList<String>();

		PreparedStatement stmt = null;

		int count = 0;
		List<String[]> queryOutput = null;
		String[] columnNames = null;
		Object[] output = null;

		try {

			output = new Object[3];
			queryOutput = new ArrayList<String[]>();
			StringBuffer query = new StringBuffer();
			query.append(
					"select drug.dr_id as DRUG_ID, drug.name as DRUG_NAME, drug.type as DRUG_TYPE,disease.name as DISEASE_NAME, disease.type DISEASE_TYPE, disease.DESCRIPTION as DISEASE_DESCRIPTION from drug, clinical_fact,disease where clinical_fact.DR_ID=drug.DR_ID AND clinical_fact.DS_ID=disease.DS_ID");
			if (disease != null && disease.length() > 0) {
				query.append(" AND disease.name=?");
			}
			if (dsType != null && dsType.length() > 0) {
				query.append(" AND disease.type=?");
			}
			if (diseaseDsc != null && diseaseDsc.length() > 0) {
				query.append(" AND disease.description=?");
			}
			query.append(" order by drug.type ASC");
			stmt = conn.prepareStatement(query.toString());
			if (disease != null && disease.length() > 0) {
				stmt.setString(1, disease);
			}
			if (dsType != null && dsType.length() > 0) {
				stmt.setString(2, dsType);
			}
			if (diseaseDsc != null && diseaseDsc.length() > 0) {
				stmt.setString(3, diseaseDsc);
			}
			ResultSet rs = stmt.executeQuery();
			int colCount = rs.getMetaData().getColumnCount();
			columnNames = new String[colCount];

			for (int i = 0; i < colCount; i++) {
				columnNames[i] = rs.getMetaData().getColumnLabel(i + 1);
			}

			/*
			 * while (rs.next()) {
			 * 
			 * String drugType = rs.getString("TYPE"); String[] drugData =
			 * drugType.split(" "); drugList.add(drugData[2]);
			 * System.out.println("drugtype" + drugType + "drugdata" +
			 * drugData);
			 * 
			 * }
			 */

			while (rs.next()) {
				String[] row = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					row[i] = rs.getString(i + 1);
				}
				queryOutput.add(row);
				count++;
			}
			output[0] = count;
			output[1] = columnNames;
			output[2] = queryOutput;

		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}

		// return drugList;
		return output;

	}

	public Object[] expforALLpatientsquery3(String diseasename, String clid, String muid) throws SQLException {

		// ArrayList<String> expList = new ArrayList<String>();

		Statement stmt = null;

		int count = 0;
		List<String[]> queryOutput = null;
		String[] columnNames = null;
		Object[] output = null;

		try {

			output = new Object[3];
			queryOutput = new ArrayList<String[]>();
			StringBuffer sb = new StringBuffer();
			String dropv1query = "begin execute immediate 'drop view q3clidprobe'; exception when others then null; end;";
			String createv1query = null;
			String createv2query = null;
			String createv4query = null;

			if (clid != null && clid.length() > 0) {

				createv1query = "create view q3clidprobe as " + "select gene_fact.U_ID, gene_fact.CL_ID, probe.PB_ID "
						+ "from gene_fact, probe " + "where gene_fact.U_ID=probe.U_ID AND gene_fact.CL_ID=" +clid;
			}

			else {
				createv1query = "create view q3clidprobe as " + "select gene_fact.U_ID, gene_fact.CL_ID, probe.PB_ID "
						+ "from gene_fact, probe " + "where gene_fact.U_ID=probe.U_ID";
			}
			String selectv1query = "select * from q3clidprobe";

			String dropv2query = "begin execute immediate 'drop view q3clidmuid'; exception when others then null; end;";

			if (muid != null && muid.length() > 0) {
				createv2query = "create view q3clidmuid as "
						+ "select q3clidprobe.CL_ID, microarray_fact.S_ID, microarray_fact.MU_ID, microarray_fact.EXP "
						+ "from q3clidprobe, microarray_fact "
						+ "where q3clidprobe.pb_id=microarray_fact.PB_ID AND MICROARRAY_FACT.MU_ID=" + muid;
			} else {
				createv2query = "create view q3clidmuid as "
						+ "select q3clidprobe.CL_ID, microarray_fact.S_ID, microarray_fact.MU_ID, microarray_fact.EXP "
						+ "from q3clidprobe, microarray_fact " + "where q3clidprobe.pb_id=microarray_fact.PB_ID";
			}

			String selectv2query = "select * from q3clidmuid";

			String dropv3query = "begin execute immediate 'drop view q3clidmuidpid'; exception when others then null; end;";
			String createv3query = "create view q3clidmuidpid as "
					+ "select q3clidmuid.CL_ID, q3clidmuid.MU_ID, q3clidmuid.exp, clinical_fact.P_ID "
					+ "from q3clidmuid, CLINICAL_FACT " + "where q3clidmuid.S_ID=clinical_fact.S_ID";
			String selectv3query = "select * from q3clidmuidpid";

			String dropv4query = "begin execute immediate 'drop view q3pidall'; exception when others then null; end;";
			if (diseasename != null && diseasename.length() > 0) {
				createv4query = "create view q3pidall as " + "select clinical_fact.p_id, disease.ds_id, disease.name "
						+ "from clinical_fact, disease " + "where clinical_fact.ds_id = disease.DS_ID and disease.name="
						+ "'"+diseasename+"'";
			} else {
				createv4query = "create view q3pidall as " + "select clinical_fact.p_id, disease.ds_id, disease.name "
						+ "from clinical_fact, disease " + "where clinical_fact.ds_id = disease.DS_ID";
			}
			String selectv4query = "select * from q3pidall";

			String dropv5query = "begin execute immediate 'drop view q3final'; exception when others then null; end;";
			String createv5query = "create view q3final as "
					+ "select q3clidmuidpid.cl_id, q3clidmuidpid.mu_id, q3clidmuidpid.exp, q3clidmuidpid.p_id, q3pidall.name "
					+ "from q3clidmuidpid, q3pidall " + "where q3clidmuidpid.P_ID = q3pidall.P_ID";
			String selectv5query = "select * from q3final";

			// String dropq = "drop view if exists v225";

			int queryval;
			stmt = conn.createStatement();
			queryval = stmt.executeUpdate(dropv1query);
			queryval = stmt.executeUpdate(createv1query);
			queryval = stmt.executeUpdate(dropv2query);
			queryval = stmt.executeUpdate(createv2query);
			queryval = stmt.executeUpdate(dropv3query);
			queryval = stmt.executeUpdate(createv3query);
			queryval = stmt.executeUpdate(dropv4query);
			queryval = stmt.executeUpdate(createv4query);
			queryval = stmt.executeUpdate(dropv5query);
			queryval = stmt.executeUpdate(createv5query);
			ResultSet rs = stmt.executeQuery(selectv5query);

			int colCount = rs.getMetaData().getColumnCount();
			columnNames = new String[colCount];

			for (int i = 0; i < colCount; i++) {
				columnNames[i] = rs.getMetaData().getColumnLabel(i + 1);
			}

			/*
			 * while (rs.next()) {
			 * 
			 * String probeexp = rs.getString("exp"); //String[] drugData =
			 * drugType.split(" "); expList.add(probeexp);
			 * System.out.println("probe" + probeexp);
			 * 
			 * }
			 */

			while (rs.next()) {
				String[] row = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					row[i] = rs.getString(i + 1);
				}
				queryOutput.add(row);
				count++;
			}
			output[0] = count;
			output[1] = columnNames;
			output[2] = queryOutput;

		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}

		// return expList;
		return output;

	}


    public Object[] tstatALLpatientsquery4(String diseasename, String goid) throws SQLException {


        Statement stmt = null;

        int count = 0;
        List<String[]> queryOutput = null;
        String[] columnNames = null;
        Object[] output = null;

        try {

            output = new Object[3];
            queryOutput = new ArrayList<String[]>();
            StringBuffer sb = new StringBuffer();


            String dropv1query = "begin execute immediate 'drop view q4v1'; exception when others then null; end;";
            String createv1query = null;

            if (goid != null && goid.length() > 0) {

                createv1query = "create view q4v1 as " + "select gene_fact.U_ID, gene_fact.GO_ID, probe.PB_ID "
                        + "from gene_fact, probe " + "where gene_fact.U_ID=probe.U_ID AND gene_fact.GO_ID=" +12502;
            }

            else {
                createv1query = "create view q4v1 as " + "select gene_fact.U_ID, gene_fact.GO_ID, probe.PB_ID "
                        + "from gene_fact, probe " + "where gene_fact.U_ID=probe.U_ID";
            }
            String selectv1query = "select * from q4v1";

            String dropv2query = "begin execute immediate 'drop view q4v2'; exception when others then null; end;";

            String createv2query = "create view q4v2 as "
                    + "select q4v1.U_ID,q4v1.GO_ID,q4v1.PB_ID, microarray_fact.S_ID, microarray_fact.EXP "
                    + "from q4v1, microarray_fact " + "where q4v1.pb_id=microarray_fact.PB_ID";

            String selectv2query = "select * from q4v2";


            String dropv3query = "begin execute immediate 'drop view q4vsidexp'; exception when others then null; end;";
            String createv3query = "create view q4vsidexp as "
                    + "select q4v2.U_ID, q4v2.GO_ID, q4v2.PB_ID, q4v2.S_ID, q4v2.exp, clinical_fact.P_ID "
                    + "from q4v2, CLINICAL_FACT " + "where q4v2.S_ID=clinical_fact.S_ID";

            String selectv3query = "select * from q4vsidexp";


            String dropv4query = "begin execute immediate 'drop view q4dispid'; exception when others then null; end;";

            String createv4query = "create view q4dispid as " + "select clinical_fact.p_id, disease.name "
                    + "from clinical_fact, disease " + "where clinical_fact.ds_id = disease.DS_ID";

            String selectv4query = "select * from q4dispid";


            String dropv5query = "begin execute immediate 'drop view q4final'; exception when others then null; end;";
            String createv5query = "create view q4final as "
                    + "select q4vsidexp.go_id, q4vsidexp.pb_id, q4vsidexp.exp, q4vsidexp.p_id, q4dispid.name "
                    + "from q4vsidexp, q4dispid " + "where q4vsidexp.P_ID = q4dispid.P_ID";
            String selectv5query = "select * from q4final";


            String dropv6query = "begin execute immediate 'drop view q4ttest'; exception when others then null; end;";
            String createv6query = "create view q4ttest as "
                    + "q4final.pb_id, q4final.exp, q4final.p_id, q4final.go_id, q4final.name, "
                    + "case when q4final.name = '"+diseasename+"' then 0 else 1 end as diseaseval "
                    + "from q4final";
            String selectv6query = "select * from q4ttest";


            String selectv7query = "select pb_id, "
                    + "stats_t_test_indep(diseaseval,exp, 'STATISTIC', 0)t_observed, "
                    + "stats_t_test_indep(diseaseval,exp)two_sided_p_value "
                    + "from q4ttest "
                    + "group by PB_ID";

            int queryval;
            stmt = conn.createStatement();
            queryval = stmt.executeUpdate(dropv1query);
            queryval = stmt.executeUpdate(createv1query);
            queryval = stmt.executeUpdate(dropv2query);
            queryval = stmt.executeUpdate(createv2query);
            queryval = stmt.executeUpdate(dropv3query);
            queryval = stmt.executeUpdate(createv3query);
            queryval = stmt.executeUpdate(dropv4query);
            queryval = stmt.executeUpdate(createv4query);
            queryval = stmt.executeUpdate(dropv5query);
            queryval = stmt.executeUpdate(createv5query);
            queryval = stmt.executeUpdate(dropv6query);
            queryval = stmt.executeUpdate(createv6query);
            ResultSet rs = stmt.executeQuery(selectv7query);

            int colCount = rs.getMetaData().getColumnCount();
            columnNames = new String[colCount];

            for (int i = 0; i < colCount; i++) {
                columnNames[i] = rs.getMetaData().getColumnLabel(i + 1);
            }

            while (rs.next()) {
                String[] row = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    row[i] = rs.getString(i + 1);
                }
                queryOutput.add(row);
                count++;
            }
            output[0] = count;
            output[1] = columnNames;
            output[2] = queryOutput;

        } catch (SQLException e) {

            printSQLException(e);

        } finally {

            if (stmt != null) {

                stmt.close();

            }

        }

        return output;

    }


    public Object[] fstatALLAMLpatientsquery5(String diseasename1,String diseasename2, String goid) throws SQLException {


        Statement stmt = null;

        int count = 0;
        List<String[]> queryOutput = null;
        String[] columnNames = null;
        Object[] output = null;

        try {

            output = new Object[3];
            queryOutput = new ArrayList<String[]>();
            StringBuffer sb = new StringBuffer();


            String dropv1query = "begin execute immediate 'drop view q5v1'; exception when others then null; end;";
            String createv1query = null;

            if (goid != null && goid.length() > 0) {

                createv1query = "create view q5v1 as " + "select gene_fact.U_ID, gene_fact.GO_ID, probe.PB_ID "
                        + "from gene_fact, probe " + "where gene_fact.U_ID=probe.U_ID AND gene_fact.GO_ID=" +7154;
            }

            else {
                createv1query = "create view q5v1 as " + "select gene_fact.U_ID, gene_fact.GO_ID, probe.PB_ID "
                        + "from gene_fact, probe " + "where gene_fact.U_ID=probe.U_ID";
            }
            String selectv1query = "select * from q5v1";

            String dropv2query = "begin execute immediate 'drop view q5v2'; exception when others then null; end;";

            String createv2query = "create view q5v2 as "
                    + "select q5v1.U_ID,q5v1.GO_ID,q5v1.PB_ID, microarray_fact.S_ID, microarray_fact.EXP "
                    + "from q5v1, microarray_fact " + "where q5v1.pb_id=microarray_fact.PB_ID";

            String selectv2query = "select * from q5v2";


            String dropv3query = "begin execute immediate 'drop view q4vsidexp'; exception when others then null; end;";
            String createv3query = "create view q5vsidexp as "
                    + "select q5v2.U_ID, q5v2.GO_ID, q5v2.PB_ID, q5v2.S_ID, q5v2.exp, clinical_fact.P_ID "
                    + "from q5v2, CLINICAL_FACT " + "where q5v2.S_ID=clinical_fact.S_ID";

            String selectv3query = "select * from q5vsidexp";


            String dropv4query = "begin execute immediate 'drop view q5dispid'; exception when others then null; end;";

            String createv4query = "create view q5dispid as " + "select clinical_fact.p_id, disease.name "
                    + "from clinical_fact, disease " + "where clinical_fact.ds_id = disease.DS_ID";

            String selectv4query = "select * from q5dispid";


            String dropv5query = "begin execute immediate 'drop view q5final'; exception when others then null; end;";
            String createv5query = "create view q5final as "
                    + "select q5vsidexp.go_id, q5vsidexp.pb_id, q5vsidexp.exp, q5vsidexp.p_id, q5dispid.name "
                    + "from q5vsidexp, q5dispid " + "where q5vsidexp.P_ID = q5dispid.P_ID";
            String selectv5query = "select * from q5final";


            String dropv6query = "begin execute immediate 'drop view q5ftest'; exception when others then null; end;";
            String createv6query = "create view q5ftest as "
                    + "q5final.pb_id, q5final.exp, q5final.p_id, q5final.go_id, q5final.name, "
                    + "case when q5final.name = '"+diseasename1+"' then 0 else 1 end as diseaseval "
                    + "from q5final "
                    + "where q5final.name = '"+diseasename1+"' or q5final.name = '"+diseasename2+"'";
            String selectv6query = "select * from q5ftest";
            

            String selectv7query = "select pb_id, "
                    + "stats_f_test(diseaseval,exp, 'STATISTIC', 0)f_observed, "
                    + "stats_f_test(diseaseval,exp)two_sided_p_value "
                    + "from q5ftest "
                    + "group by PB_ID";

            int queryval;
            stmt = conn.createStatement();
            queryval = stmt.executeUpdate(dropv1query);
            queryval = stmt.executeUpdate(createv1query);
            queryval = stmt.executeUpdate(dropv2query);
            queryval = stmt.executeUpdate(createv2query);
            queryval = stmt.executeUpdate(dropv3query);
            queryval = stmt.executeUpdate(createv3query);
            queryval = stmt.executeUpdate(dropv4query);
            queryval = stmt.executeUpdate(createv4query);
            queryval = stmt.executeUpdate(dropv5query);
            queryval = stmt.executeUpdate(createv5query);
            queryval = stmt.executeUpdate(dropv6query);
            queryval = stmt.executeUpdate(createv6query);
            ResultSet rs = stmt.executeQuery(selectv7query);

            int colCount = rs.getMetaData().getColumnCount();
            columnNames = new String[colCount];

            for (int i = 0; i < colCount; i++) {
                columnNames[i] = rs.getMetaData().getColumnLabel(i + 1);
            }

            while (rs.next()) {
                String[] row = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    row[i] = rs.getString(i + 1);
                }
                queryOutput.add(row);
                count++;
            }
            output[0] = count;
            output[1] = columnNames;
            output[2] = queryOutput;

        } catch (SQLException e) {

            printSQLException(e);

        } finally {

            if (stmt != null) {

                stmt.close();

            }

        }

        return output;

    }


	public Map<Integer, String> allDiseaseList() throws SQLException {

		Map<Integer, String> diseaseMap = new HashMap<Integer, String>();

		Statement stmt = null;

		try {

			String query = "select disease.NAME, disease.DS_ID from disease order by disease.DS_ID ASC";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String name = rs.getString("NAME");
				Integer id = rs.getInt("DS_ID");
				diseaseMap.put(id, name);

			}

		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}

		return diseaseMap;

	}

	/*
	 * public static void main(String[] args){
	 * 
	 * Statement stmt = null;
	 * 
	 * String query =
	 * "select * from clinical_fact, disease where clinical_fact.DS_ID=disease.DS_ID AND disease.name='ALL' and disease.type='leukemia' and disease.description='tumor'"
	 * ;
	 * 
	 * try { stmt = conn.createStatement(); ResultSet rs =
	 * stmt.executeQuery(query);
	 * 
	 * int count = 0;
	 * 
	 * while (rs.next()) {
	 * 
	 * count++;
	 * 
	 * }
	 * 
	 * System.out.println("Count:" + count);
	 * 
	 * } catch (SQLException e ) { e.printStackTrace(); }
	 *//*
		 * finally { if (stmt != null) { stmt.close(); } }
		 *//*
		 * 
		 * 
		 * 
		 * 
		 * }
		 */

	// Helper functions -- referenced from oracle Website

	public static void printSQLException(SQLException ex) {

		for (Throwable e : ex) {

			if (e instanceof SQLException) {

				if (ignoreSQLException(((SQLException) e).getSQLState()) == false) {

					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());

					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());

					System.err.println("Message: " + e.getMessage());

					Throwable t = ex.getCause();
					while (t != null) {
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
