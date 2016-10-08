package com.ub.cse601.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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

	public Object[] queryForTumorALLPatients(String diseaseName, String diseaseType, String diseaseDesc)
			throws SQLException {

		Statement stmt = null;
		int count = 0;
		List<String[]> queryOutput = null;
		String[] columnNames = null;
		Object[] output = null;

		try {
			output = new Object[3];
			queryOutput = new ArrayList<String[]>();
			StringBuilder query = new StringBuilder();
			query.append(
					"select cf.P_ID as PATIENT_ID, ds.name as DISEASE_NAME, ds.type as DISEASE_TYPE, ds.description as DISEASE_DESC "
							+ "from clinical_fact cf, disease ds where cf.DS_ID=ds.DS_ID");
			if (diseaseName != null && diseaseName.length() > 0) {
				query.append(" AND ds.name='" + diseaseName + "'");
			}
			if (diseaseType != null && diseaseType.length() > 0) {
				query.append(" AND ds.type='" + diseaseType + "'");
			}
			if (diseaseDesc != null && diseaseDesc.length() > 0) {
				query.append(" AND ds.description='" + diseaseDesc + "'");
			}

			// query.append(" and ds.type='leukemia' and ds.description='tumor'
			// order by cf.P_ID ASC");
			query.append(" order by cf.P_ID ASC");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query.toString());
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

		Statement stmt = null;

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
				query.append(" AND disease.name='" + disease + "'");
			}
			if (dsType != null && dsType.length() > 0) {
				query.append(" AND disease.type='" + dsType + "'");
			}
			if (diseaseDsc != null && diseaseDsc.length() > 0) {
				query.append(" AND disease.description='" + diseaseDsc + "'");
			}
			query.append(" order by drug.type ASC");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query.toString());
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
			StringBuffer selectQuery = new StringBuffer();

			selectQuery.append("select cl_id, mu_id, exp, p_id, ds_name"
					+ " from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE"
					+ " where gene_fact.U_ID = probe.U_ID" + " and probe.pb_id = microarray_fact.pb_id"
					+ " and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID ");

			if (diseasename != null && diseasename.length() > 0) {
				selectQuery.append(" and PATIENTDISEASESAMPLE.DS_NAME ='" + diseasename + "'");
			}

			if (clid != null && clid.length() > 0) {

				selectQuery.append(" and gene_fact.cl_id =" + clid);
			}
			if (muid != null && muid.length() > 0) {

				selectQuery.append(" and microarray_fact.mu_id = " + muid);
			}

			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(selectQuery.toString());

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

	public Object[] tstatALLpatientsquery4(String diseasename, String goid, String stats) throws SQLException {

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
						+ "'" + " then 0 else 1 end as diseaseval "
						+ "from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE " + "where gene_fact.GO_ID ="
						+ goid + " and gene_fact.U_ID = probe.U_ID " + "and probe.pb_id = microarray_fact.pb_id "
						+ "and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID";
			}
			else{
				createv1query = "create view q4optv1 as "
						+ "select go_id, microarray_fact.pb_id, exp, p_id,ds_name, case when ds_name = '" + diseasename
						+ "'" + " then 0 else 1 end as diseaseval "
						+ "from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE " + "where "
						+ " gene_fact.U_ID = probe.U_ID " + "and probe.pb_id = microarray_fact.pb_id "
						+ "and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID";
			}

			String selectv2querygroup = "select pb_id, "
					+ " stats_t_test_indep(diseaseval,exp, 'STATISTIC', 0)t_observed,"
					+ " stats_t_test_indep(diseaseval,exp)two_sided_p_value" + " from q4optv1" + " group by PB_ID";

			String selectv2queryungroup = "select " + "stats_t_test_indep(diseaseval,exp, 'STATISTIC', 0)t_observed,"
					+ " stats_t_test_indep(diseaseval,exp)two_sided_p_value" + " from q4ttest";

			String selectv2querygroupftest = "select pb_id, "
					+ "stats_f_test(diseaseval,exp, 'STATISTIC', 0)f_observed,"
					+ "stats_f_test(diseaseval,exp)two_sided_p_value " + "from q4ttest " + "group by PB_ID";

			String selectv2queryungroupftest = "select " + "stats_f_test(diseaseval,exp, 'STATISTIC', 0)f_observed,"
					+ "stats_f_test(diseaseval,exp)two_sided_p_value " + "from q4ttest";

			int queryval;
			stmt = conn.createStatement();
			queryval = stmt.executeUpdate(dropv1query);
			queryval = stmt.executeUpdate(createv1query);

			ResultSet rs = null;

			if (stats != null && stats.length() > 0) {
				if (stats.equals("T Statistics")) {
					rs = stmt.executeQuery(selectv2queryungroup);
				} else if (stats.equals("F Statistics")) {
					rs = stmt.executeQuery(selectv2queryungroupftest);
				}
			}

			// ResultSet rs = stmt.executeQuery(selectv2querygroup);
			// ResultSet rs = stmt.executeQuery(selectv2queryungroup);
			// ResultSet rs = stmt.executeQuery(selectv2querygroupftest);
			// ResultSet rs = stmt.executeQuery(selectv2queryungroupftest);

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

	public Object[] fstatALLAMLpatientsquery5(String goid, List<String> diseases) throws SQLException {

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
			StringBuffer createv1query = new StringBuffer();

			if (goid != null && goid.length() > 0) {

				createv1query.append("create view q5optv1 as "
						+ "select go_id, microarray_fact.pb_id, exp, p_id,ds_name "
						+ "from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE " + "where gene_fact.GO_ID ="
						+ goid + " and gene_fact.U_ID = probe.U_ID " + "and probe.pb_id = microarray_fact.pb_id "
						+ "and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID ");
				if (diseases.size() > 0) {
					createv1query.append("and (");
					for (int i = 0; i < diseases.size(); i++) {
						if (i == 0)
							createv1query.append("patientdiseasesample.ds_name = '" + diseases.get(i) + "'");
						else
							createv1query.append(" or patientdiseasesample.ds_name = '" + diseases.get(i) + "'");
					}
					createv1query.append(")");
				}

			}

			String selectv2querygroupanova = "select pb_id, " + "stats_one_way_anova(ds_name,exp, 'F_RATIO')f_ratio, "
					+ "stats_one_way_anova(ds_name,exp,'SIG')p_value " + "from q5optv1 " + "group by PB_ID";

			String selectv2queryungroupanova = "select " + "stats_one_way_anova(ds_name,exp, 'F_RATIO')f_ratio, "
					+ "stats_one_way_anova(ds_name,exp,'SIG')p_value " + "from q5optv1";

			int queryval;
			stmt = conn.createStatement();
			queryval = stmt.executeUpdate(dropv1query);
			queryval = stmt.executeUpdate(createv1query.toString());

			// ResultSet rs = stmt.executeQuery(selectv5query);
			ResultSet rs = stmt.executeQuery(selectv2queryungroupanova);

			int colCount = rs.getMetaData().getColumnCount();
			columnNames = new String[colCount];

			for (int i = 0; i < colCount; i++) {
				columnNames[i] = rs.getMetaData().getColumnLabel(i + 1);
			}

			DecimalFormat df = new DecimalFormat("#.###");

			while (rs.next()) {
				String[] row = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					row[i] = df.format(rs.getDouble(i + 1));
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

	public Object[] findInformativeGenes(String diseasename, double pvalue) throws SQLException {

		Statement stmt = null;

		int count = 0;
		List<String[]> queryOutput = null;
		String[] columnNames = null;
		Object[] output = null;

		try {

			output = new Object[3];
			queryOutput = new ArrayList<String[]>();
			StringBuffer sb = new StringBuffer();

			String dropv1query = "begin execute immediate 'drop view part3v1'; exception when others then null; end;";
			String createv1query = null;

			createv1query = "create view part3v1 as " + "select gene.U_id, exp, p_id, ds_name, case when ds_name = '"
					+ diseasename + "' then 0 else 1 end as diseaseval "
					+ "from gene, probe, microarray_fact, PATIENTDISEASESAMPLE " + "where gene.U_ID = probe.U_ID "
					+ "and probe.pb_id = microarray_fact.pb_id "
					+ "and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID";

			String selectv1query = "select * from part3v1";

			String dropv2query = "begin execute immediate 'drop view part3v2'; exception when others then null; end;";

			String createv2query = "create view part3v2 as " + "select u_id, "
					+ "stats_t_test_indep(diseaseval,exp, 'STATISTIC', 0)t_observed, "
					+ "stats_t_test_indep(diseaseval,exp)two_sided_p_value " + "from part3v1 " + "group by u_id";

			String selectv2query = "select * from part3v2";

			String dropv3query = "begin execute immediate 'drop view part3v3'; exception when others then null; end;";

			/*String createv3query = "create view part3v3 as " + "select u_id, t_observed, two_sided_p_value, "
					+ "case when two_sided_p_value < " + pvalue
					+ " then 'informative' else 'non-informative' end as genestat, " + "case when two_sided_p_value < "
					+ pvalue + " then 1 else 0 end as infogene " + "from part3v2 " + "order by genestat";
					*/

            String createv3query = "create view part3v3 as " + "select u_id as informative_genes, t_observed, two_sided_p_value "
                    + "from part3v2 "
                    + "where two_sided_p_value < "+pvalue
                    + " order by u_id";

			String selectFinalQuery = "select * from part3v3";

			int queryval;
			stmt = conn.createStatement();
			queryval = stmt.executeUpdate(dropv1query);
			queryval = stmt.executeUpdate(createv1query);
			queryval = stmt.executeUpdate(dropv2query);
			queryval = stmt.executeUpdate(createv2query);
			queryval = stmt.executeUpdate(dropv3query);
			queryval = stmt.executeUpdate(createv3query);

			ResultSet rs = stmt.executeQuery(selectFinalQuery);

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

	public Object[] classifynewpatientcorrelation(String diseasename, double pvalue, String newpatient)
			throws SQLException {

		Statement stmt = null;
		Map<Double, List<Double>> dis1PatientExPList = null;
		Map<Double, List<Double>> dis2PatientExPList = null;
		Map<String, List<Double>> dis3PatientExPList = null;
		Object[] expData = null;

		int count = 0;
		List<String[]> queryOutput = null;
		String[] columnNames = null;
		Object[] output = null;

		try {

			output = new Object[3];
			queryOutput = new ArrayList<String[]>();
			StringBuffer sb = new StringBuffer();

			String dropv1query = "begin execute immediate 'drop view part3v4'; exception when others then null; end;";
			String createv1query = null;

			createv1query = "create view part3v4 as " + "select part3v2.u_id, ds_name, exp, p_id "
					+ "from part3v2, part3v1 " + "where part3v1.u_id = part3v2.U_ID "
					+ "and part3v2.two_sided_p_value < " + pvalue + " and ds_name = '" + diseasename + "' "
					+ "order by p_id, u_id";

			String selectv1query = "select * from part3v4";

			String dropv2query = "begin execute immediate 'drop view part3v5'; exception when others then null; end;";

			String createv2query = "create view part3v5 as " + "select unique(testpatients.u_id), " + newpatient
					+ " as " + newpatient + "_exp " + "from testpatients, part3v4 "
					+ "where part3v4.u_id = testpatients.u_id " + "order by u_id";

			String selectv2query = "select * from part3v5";

			String dropv3query = "begin execute immediate 'drop view part3v6'; exception when others then null; end;";
			String createv3query = null;

			createv3query = "create view part3v6 as " + "select part3v2.u_id, ds_name, exp, p_id "
					+ "from part3v2, part3v1 " + "where part3v1.u_id = part3v2.U_ID "
					+ "and part3v2.two_sided_p_value < " + pvalue + " and ds_name != '" + diseasename + "' "
					+ "order by p_id, u_id";

			String selectv3query = "select * from part3v6";

			int queryval;
			stmt = conn.createStatement();

			queryval = stmt.executeUpdate(dropv1query);
			queryval = stmt.executeUpdate(createv1query);

			queryval = stmt.executeUpdate(dropv2query);
			queryval = stmt.executeUpdate(createv2query);

			queryval = stmt.executeUpdate(dropv3query);
			queryval = stmt.executeUpdate(createv3query);

			dis1PatientExPList = new HashMap<Double, List<Double>>();
			dis2PatientExPList = new HashMap<Double, List<Double>>();
			dis3PatientExPList = new HashMap<String, List<Double>>();

			ResultSet rs = stmt.executeQuery(selectv1query);

			while (rs.next()) {
				Double pId = rs.getDouble("p_id");
				Double expValue = rs.getDouble("EXP");
				if (dis1PatientExPList.get(pId) == null) {
					List<Double> expList = new ArrayList<Double>();
					expList.add(expValue);
					dis1PatientExPList.put(pId, expList);
				} else {
					dis1PatientExPList.get(pId).add(expValue);
				}
			}

			rs = stmt.executeQuery(selectv3query);

			while (rs.next()) {
				Double pId = rs.getDouble("p_id");
				Double expValue = rs.getDouble("EXP");
				if (dis2PatientExPList.get(pId) == null) {
					List<Double> expList = new ArrayList<Double>();
					expList.add(expValue);
					dis2PatientExPList.put(pId, expList);
				} else {
					dis2PatientExPList.get(pId).add(expValue);
				}
			}

			if (newpatient != null) {
				rs = stmt.executeQuery(selectv2query);
				String pId = newpatient + "_exp";
				while (rs.next()) {
					Double expValue = rs.getDouble(newpatient + "_exp");
					if (dis3PatientExPList.get(pId) == null) {
						List<Double> expList = new ArrayList<Double>();
						expList.add(expValue);
						dis3PatientExPList.put(pId, expList);
					} else {
						dis3PatientExPList.get(pId).add(expValue);
					}
				}

			}

			expData = new Object[3];
			expData[0] = dis1PatientExPList;
			expData[1] = dis2PatientExPList;
			expData[2] = dis3PatientExPList;

			/*
			 * int colCount = rs.getMetaData().getColumnCount(); columnNames =
			 * new String[colCount];
			 * 
			 * for (int i = 0; i < colCount; i++) { columnNames[i] =
			 * rs.getMetaData().getColumnLabel(i + 1); }
			 * 
			 * while (rs.next()) { String[] row = new String[colCount]; for (int
			 * i = 0; i < colCount; i++) { row[i] = rs.getString(i + 1); }
			 * queryOutput.add(row); count++; } output[0] = count; output[1] =
			 * columnNames; output[2] = queryOutput;
			 */

		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}

		return expData;

	}

	public Object[] query6Correlation(String disease1, String disease2, String goId) throws SQLException {

		Statement stmt = null;
		Map<Double, List<Double>> dis1PatientExPList = null;
		Map<Double, List<Double>> dis2PatientExPList = null;
		Object[] expData = null;

		try {

			String dropv1query = "begin execute immediate 'drop view q6optv1'; exception when others then null; end;";
			StringBuffer createv1query = new StringBuffer();
			createv1query.append("create view q6optv1 as "
					+ "select PATIENTDISEASESAMPLE.S_ID, go_id, microarray_fact.pb_id, exp, p_id,ds_name, case when ds_name = '"
					+ disease1 + "' then 0 else 1 end as diseaseval "
					+ "from gene_fact, probe, microarray_fact, PATIENTDISEASESAMPLE where gene_fact.U_ID = probe.U_ID "
					+ "and probe.pb_id = microarray_fact.pb_id "
					+ "and microarray_fact.s_id = PATIENTDISEASESAMPLE.S_ID ");
			if (goId != null && goId.length() > 0) {
				createv1query.append("and gene_fact.GO_ID = " + goId);
			}
			if (disease2 != null && !disease2.equals(disease1)) {
				createv1query.append(" and (patientdiseasesample.ds_name = '" + disease1
						+ "' or patientdiseasesample.ds_name = '" + disease2 + "')");
			} else {
				createv1query.append(" and patientdiseasesample.ds_name = '" + disease1 + "'");
			}

			String selectv1query = "select P_ID, S_ID, PB_ID, EXP from q6optv1 where DISEASEVAl=0 order by P_ID, S_ID, PB_ID ASC";

			int queryval;

			stmt = conn.createStatement();
			queryval = stmt.executeUpdate(dropv1query);
			queryval = stmt.executeUpdate(createv1query.toString());

			ResultSet rs = stmt.executeQuery(selectv1query);

			dis1PatientExPList = new HashMap<Double, List<Double>>();
			dis2PatientExPList = new HashMap<Double, List<Double>>();

			while (rs.next()) {
				Double pId = rs.getDouble("P_ID");
				Double expValue = rs.getDouble("EXP");
				if (dis1PatientExPList.get(pId) == null) {
					List<Double> expList = new ArrayList<Double>();
					expList.add(expValue);
					dis1PatientExPList.put(pId, expList);
				} else {
					dis1PatientExPList.get(pId).add(expValue);
				}
			}
			if (disease2 != null && !disease2.equals(disease1)) {
				String selectv2query = "select P_ID, S_ID, PB_ID, EXP from q6optv1 where DISEASEVAl=1 order by P_ID, S_ID, PB_ID ASC";
				rs = stmt.executeQuery(selectv2query);
				while (rs.next()) {
					Double pId = rs.getDouble("P_ID");
					Double expValue = rs.getDouble("EXP");
					if (dis2PatientExPList.get(pId) == null) {
						List<Double> expList = new ArrayList<Double>();
						expList.add(expValue);
						dis2PatientExPList.put(pId, expList);
					} else {
						dis2PatientExPList.get(pId).add(expValue);
					}
				}

			}
			expData = new Object[2];
			expData[0] = dis1PatientExPList;
			expData[1] = dis2PatientExPList;

		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}

		return expData;

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
		return sqlState.equalsIgnoreCase("42Y55");

	}

	public Map<Integer, String> getAllDiseaseTypes() throws SQLException {
		Map<Integer, String> diseaseTypes = new HashMap<Integer, String>();

		Statement stmt = null;

		try {
			int count = 1;
			String query = "select distinct(disease.type) as DS_TYPE from disease order by DS_TYPE ASC";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String type = rs.getString("DS_TYPE");
				Integer id = count++;
				diseaseTypes.put(id, type);

			}

		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}

		return diseaseTypes;

	}

	public Map<Integer, String> getAllDiseaseDesc() throws SQLException {
		Map<Integer, String> diseaseDescMap = new HashMap<Integer, String>();

		Statement stmt = null;

		try {
			int count = 1;
			String query = "select distinct(disease.description) as DS_DESC from disease order by DS_DESC ASC";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String type = rs.getString("DS_DESC");
				Integer id = count++;
				diseaseDescMap.put(id, type);

			}

		} catch (SQLException e) {

			printSQLException(e);

		} finally {

			if (stmt != null) {

				stmt.close();

			}

		}

		return diseaseDescMap;

	}

}
