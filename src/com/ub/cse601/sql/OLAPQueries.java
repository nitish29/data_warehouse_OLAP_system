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
			String selectQuery;

			if (diseasename != null && diseasename.length() > 0) {

				selectQuery = "select cl_id, mu_id, exp, p_id, ds_name"
						+ " from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE"
						+ " where gene_fact.U_ID = probe.U_ID" + " and gene_fact.cl_id =" + clid
						+ " and probe.pb_id = microarray_fact.pb_id" + " and microarray_fact.mu_id = " + muid
						+ " and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID"
						+ " and PATIENTDISEASESAMPLE.DS_NAME ='" + diseasename + "'";

			} else {

				selectQuery = "select cl_id, mu_id, exp, p_id, ds_name"
						+ " from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE"
						+ " where gene_fact.U_ID = probe.U_ID" + " and gene_fact.cl_id =" + clid
						+ " and probe.pb_id = microarray_fact.pb_id" + " and microarray_fact.mu_id = " + muid
						+ " and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID";

			}

			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(selectQuery);

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

			String dropv1query = "begin execute immediate 'drop view q4optv1'; exception when others then null; end;";

			String createv1query = null;

			if (goid != null && goid.length() > 0) {

				createv1query = "create view q4optv1 as "
						+ "select go_id, microarray_fact.pb_id, exp, p_id,ds_name, case when ds_name = '" + diseasename
						+ "' then 0 else 1 end as diseaseval "
						+ "from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE " + "where gene_fact.GO_ID ="
						+ goid + " and gene_fact.U_ID = probe.U_ID " + "and probe.pb_id = microarray_fact.pb_id "
						+ "and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID";
			}

			String selectv2querygroup = "select pb_id, " + " stats_t_test_indep(diseaseval,exp, 'STATISTIC', 0)t_observed,"
					+ " stats_t_test_indep(diseaseval,exp)two_sided_p_value" + " from q4optv1" + " group by PB_ID";

			String selectv2queryungroup = "select " + " stats_t_test_indep(diseaseval,exp, 'STATISTIC', 0)t_observed,"
					+ " stats_t_test_indep(diseaseval,exp)two_sided_p_value" + " from q4ttest";

			stmt = conn.createStatement();

			// ResultSet rs = stmt.executeQuery(selectv2query);
			ResultSet rs = stmt.executeQuery(selectv2queryungroup);

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
			System.out.println("count" + count);
			output[1] = columnNames;
			output[2] = queryOutput;
			System.out.println(queryOutput);

		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}

		return output;

	}

	public Object[] fstatALLAMLpatientsquery5(String diseasename1, String diseasename2, String goid)
			throws SQLException {

		diseasename1 = "ALL";
		diseasename2 = "AML";

		Statement stmt = null;

		int count = 0;
		List<String[]> queryOutput = null;
		String[] columnNames = null;
		Object[] output = null;

		try {

			output = new Object[3];
			queryOutput = new ArrayList<String[]>();
			StringBuffer sb = new StringBuffer();

			String dropv1query = "begin execute immediate 'drop view q5optv1'; exception when others then null; end;";
			String createv1query = null;

			if (goid != null && goid.length() > 0) {

				createv1query = "create view q5optv1 as "
						+ "select go_id, microarray_fact.pb_id, exp, p_id,ds_name "
						+ "from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE " + "where gene_fact.GO_ID ="
						+ goid + " and gene_fact.U_ID = probe.U_ID " + "and probe.pb_id = microarray_fact.pb_id "
						+ "and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID "
						+ "and (patientdiseasesample.ds_name = 'ALL' "
                        + "or patientdiseasesample.ds_name = 'AML' "
                        + "or patientdiseasesample.ds_name = 'Breast tumor' "
                        + "or patientdiseasesample.ds_name = 'Colon tumor')";

			}

			String selectv2querygroupftest = "select pb_id, " + "stats_f_test(ds_name,exp, 'STATISTIC', 0)f_observed,"
					+ "stats_f_test(ds_name,exp)two_sided_p_value " + "from Q5OPTV1 " + "group by PB_ID";

            String selectv2queryungroupftest = "select " + "stats_f_test(ds_name,exp, 'STATISTIC', 0)f_observed,"
                    + "stats_f_test(ds_name,exp)two_sided_p_value " + "from Q5OPTV1";

			String selectv2querygroupanova = "select pb_id, " + "stats_one_way_anova(ds_name,exp, 'F_RATIO')f_ratio, "
					+ "stats_one_way_anova(ds_name,exp,'SIG')p_value " + "from q5optv1 " + "group by PB_ID";

            String selectv2queryungroupanova = "select " + "stats_one_way_anova(ds_name,exp, 'F_RATIO')f_ratio, "
                    + "stats_one_way_anova(ds_name,exp,'SIG')p_value " + "from q5optv1";

			int queryval;
			stmt = conn.createStatement();
			queryval = stmt.executeUpdate(dropv1query);
			queryval = stmt.executeUpdate(createv1query);

			// ResultSet rs = stmt.executeQuery(selectv5query);
			ResultSet rs = stmt.executeQuery(selectv2queryungroupanova);

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

	public Object[] query6Correlation(String diseasename1, String diseasename2, String goid) throws SQLException {

		Statement stmt = null;
		int count = 0;
		List<String[]> queryOutput = null;
		String[] columnNames = null;
		Object[] output = null;

		try {

			String dropv1query = "begin execute immediate 'drop view q6v1'; exception when others then null; end;";
			String createv1query = null;
            

			if (goid != null && goid.length() > 0) {

                createv1query = "create view q6optv1 as "
                        + "select go_id, microarray_fact.pb_id, exp, p_id,ds_name, case when ds_name = '" + diseasename1
                        + "' then 0 else 1 end as diseaseval "
                        + "from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE " + "where gene_fact.GO_ID ="
                        + goid + " and gene_fact.U_ID = probe.U_ID " + "and probe.pb_id = microarray_fact.pb_id "
                        + "and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID "
                        + "and (patientdiseasesample.ds_name = '" + diseasename1
                        + "' or patientdiseasesample.ds_name = '" + diseasename2 + "')";
			}

			String selectv1query = "select * from q6optv1";


			int queryval;

			stmt = conn.createStatement();
			queryval = stmt.executeUpdate(dropv1query);
			queryval = stmt.executeUpdate(createv1query);

			ResultSet rs = stmt.executeQuery(selectv1query);

			List<Double> expList1 = new ArrayList<Double>();
			List<Double> expList2 = new ArrayList<Double>();

			ArrayList<ArrayList<Double>> ALLPatientEXPList = new ArrayList<ArrayList<Double>>();

			// while (rs.next()) {
			//
			// if ( )
			//
			// }

			// int colCount = rs.getMetaData().getColumnCount();
			// columnNames = new String[colCount];
			//
			// for (int i = 0; i < colCount; i++) {
			// columnNames[i] = rs.getMetaData().getColumnLabel(i + 1);
			// }
			//
			// while (rs.next()) {
			// String[] row = new String[colCount];
			// for (int i = 0; i < colCount; i++) {
			// row[i] = rs.getString(i + 1);
			// }
			// queryOutput.add(row);
			// count++;
			// }
			// output[0] = count;
			// output[1] = columnNames;
			// output[2] = queryOutput;

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
