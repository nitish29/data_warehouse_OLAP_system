package com.ub.cse601.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ub.cse601.sql.OLAPQueries;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CentralBiostarWindow {

	private JFrame frame;
	private JTextField clusterId;
	private JTextField probeId;
	private JTextField mesureUnitId;
	private JTextField goId;
	private JComboBox<String> queryCombo;
	private JLabel queryLabel;
	private JLabel diseaseLabel;
	private JLabel probeLabel;
	private JLabel mUnitLabel;
	private JComboBox<String> diseaseCombo;
	private JLabel clstrLabel;
	private JLabel goIdLabel;
	private JButton resetBtn;
	private JButton executeBtn;
	private JLabel msgBox;
	private OLAPQueries olapQueryClient;
	private JPanel panel_3;
	private JLabel lblQueryResults;
	private JScrollPane scrollPane;
	private JTable resultTable;
	private JLabel resultCount;
	private static final String QUERY_1 = "Query 1";
	private static final String QUERY_2 = "Query 2";
	private static final String QUERY_3 = "Query 3";
	private static final String QUERY_4 = "Query 4";
	private static final String QUERY_5 = "Query 5";
	private static final String QUERY_6 = "Query 6";
	private JComboBox<String> dis1;
	private JLabel lblAnd;
	private JComboBox<String> dis2;
	private JLabel avgCorrLbl;
	private JLabel statsLabel;
	private JComboBox<String> statistics;
	private JLabel pIdLbl;
	private JTextField pIdText;
	private JLabel kdQuery;
	JComboBox kdCombo;
	JLabel kdDisLbl;
	JComboBox kdDisCombo;
	JLabel thrsValue;
	JTextField thrsText;
	private JPanel panel_4;

	/**
	 * Create the application.
	 */
	public CentralBiostarWindow() {
		try {
			olapQueryClient = new OLAPQueries();
			initialize();
			msgBox.setText("App initialized...Execute Queries Now");
			UIDefaults defaults = UIManager.getLookAndFeelDefaults();
			if (defaults.get("Table.alternateRowColor") == null)
				defaults.put("Table.alternateRowColor", new Color(255, 228, 225));

		} catch (Exception ex) {
			msgBox.setText("Exception: " + ex);
			System.out.println("Exception: " + ex);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void resetFields() {
		queryCombo.setSelectedIndex(0);
		diseaseCombo.setSelectedIndex(0);
		clusterId.setText("");
		goId.setText("");
		mesureUnitId.setText("");
		probeId.setText("");
	}

	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setSize(1200, 800);
		// frame.setBounds(100, 100, 450, 300);
		msgBox = new JLabel("Initializing app....");
		// show frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Part 2-OLAP Operations", null, panel_1, null);

		queryLabel = new JLabel("Query");

		queryCombo = new JComboBox();

		diseaseLabel = new JLabel("Disease");

		probeLabel = new JLabel("Probe Id");

		mUnitLabel = new JLabel("MesureUnit ID");

		clstrLabel = new JLabel("Cluster Id");

		goIdLabel = new JLabel("GoId");

		diseaseCombo = new JComboBox();

		clusterId = new JTextField();
		clusterId.setColumns(10);

		probeId = new JTextField();
		probeId.setColumns(10);

		mesureUnitId = new JTextField();
		mesureUnitId.setColumns(10);

		goId = new JTextField();
		goId.setColumns(10);

		resetBtn = new JButton("Reset");

		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFields();
			}
		});

		// queryCombo.add

		executeBtn = new JButton("Execute");

		panel_3 = new JPanel();

		statsLabel = new JLabel("Statistics");

		statistics = new JComboBox();
		statistics.insertItemAt("T Statistics", 0);
		statistics.insertItemAt("F Statistics", 1);

		dis1 = new JComboBox();

		lblAnd = new JLabel("AND");

		dis2 = new JComboBox();

		avgCorrLbl = new JLabel("Avg Correlation Between");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(59)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(msgBox)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 948, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(queryLabel)
										.addComponent(diseaseLabel).addComponent(clstrLabel).addComponent(probeLabel)
										.addComponent(mUnitLabel)
										.addComponent(goIdLabel, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(statsLabel).addComponent(avgCorrLbl))
								.addGap(96)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(statistics, 0, 214, Short.MAX_VALUE)
										.addComponent(goId, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(probeId, 209, 214, Short.MAX_VALUE)
										.addComponent(clusterId, 209, 214, Short.MAX_VALUE)
										.addComponent(queryCombo, 0, 214, Short.MAX_VALUE)
										.addComponent(diseaseCombo, 0, 214, Short.MAX_VALUE)
										.addComponent(mesureUnitId, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(resetBtn, GroupLayout.PREFERRED_SIZE, 96,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(executeBtn,
														GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(dis1, 0, 87, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblAnd, GroupLayout.PREFERRED_SIZE, 27,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(dis2,
														GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
								.addGap(837)))
				.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(37)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(queryLabel).addComponent(
						queryCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(diseaseLabel).addComponent(
						diseaseCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(clstrLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(clusterId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(probeLabel).addComponent(
						probeId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(
								mUnitLabel)
						.addComponent(mesureUnitId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(goId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(statistics,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(goIdLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(statsLabel)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(dis1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(dis2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(avgCorrLbl).addComponent(lblAnd))
				.addGap(28)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(resetBtn)
						.addComponent(executeBtn))
				.addGap(31).addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(msgBox).addContainerGap(49, Short.MAX_VALUE)));

		lblQueryResults = new JLabel("Query Results");
		lblQueryResults.setHorizontalAlignment(SwingConstants.LEFT);

		scrollPane = new JScrollPane();

		resultCount = new JLabel();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup().addComponent(resultCount).addContainerGap())
				.addGroup(gl_panel_3.createSequentialGroup().addComponent(lblQueryResults).addContainerGap()));
		gl_panel_3
				.setVerticalGroup(
						gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup().addGap(7).addComponent(lblQueryResults)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(resultCount)
										.addContainerGap(28, Short.MAX_VALUE)));

		resultTable = new JTable();
		scrollPane.setViewportView(resultTable);
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);

		// initialize combo boxes
		queryCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				onChangeQuery();

			}
		});
		queryCombo.insertItemAt("Query 1", 0);
		queryCombo.insertItemAt("Query 2", 1);
		queryCombo.insertItemAt("Query 3", 2);
		queryCombo.insertItemAt("Query 4", 3);
		queryCombo.insertItemAt("Query 5", 4);
		queryCombo.insertItemAt("Query 6", 5);
		queryCombo.setSelectedIndex(0);
		diseaseCombo.insertItemAt("All", 0);
		diseaseCombo.setSelectedIndex(0);
		executeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String queryType = queryCombo.getSelectedItem().toString();
				try {
					msgBox.setText("Executing Query...");
					executeQuery(queryType);
					msgBox.setText("Query executed successfully...");
				} catch (Exception ex) {
					msgBox.setText("Exception: " + ex);
				}
			}
		});
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Part 3-Knowledge Discovery", null, panel_2, null);

		kdQuery = new JLabel("Find What");

		kdCombo = new JComboBox();

		kdDisLbl = new JLabel("Disease");

		kdDisCombo = new JComboBox();

		thrsValue = new JLabel("Threshold P-Value");

		thrsText = new JTextField();

		pIdLbl = new JLabel("Patient Id");

		pIdText = new JTextField();
		pIdText.setColumns(10);

		panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Knowledge Discovery",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(199, 21, 133)));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(33)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(kdQuery)
								.addComponent(kdDisLbl).addComponent(thrsValue).addComponent(pIdLbl))
						.addGap(128)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false).addComponent(pIdText)
								.addComponent(thrsText, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(
										kdDisCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(kdCombo, 0, 261, Short.MAX_VALUE))
						.addGap(80).addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(210, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addGap(26)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(kdQuery)
										.addComponent(kdCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(30)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addComponent(kdDisLbl)
										.addComponent(kdDisCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(31)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(thrsValue)
										.addComponent(thrsText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(29)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(pIdLbl)
										.addComponent(pIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(519, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		// populate disease combo
		Iterator<Entry<Integer, String>> diseaseMapItr = olapQueryClient.allDiseaseList().entrySet().iterator();
		while (diseaseMapItr.hasNext()) {
			Entry<Integer, String> entry = diseaseMapItr.next();
			diseaseCombo.insertItemAt(entry.getValue(), entry.getKey());
			dis1.insertItemAt(entry.getValue(), entry.getKey() - 1);
			dis2.insertItemAt(entry.getValue(), entry.getKey() - 1);
			kdDisCombo.insertItemAt(entry.getValue(), entry.getKey() - 1);

		}
		populateKDQueries(kdCombo);
		kdDisCombo.setSelectedIndex(1);
	}

	public void populateKDQueries(JComboBox kdComboBox) {
		kdComboBox.insertItemAt("T-Statistics", 0);
		kdComboBox.insertItemAt("F-Statistics", 1);
		kdComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "T-Statistics", "F-Statistics",
				"Informative Genes", "Correlation of Informative Genes" }));
	}

	public void onChangeQuery() {
		String query = queryCombo.getSelectedItem().toString();
		switch (query) {
		case QUERY_1:
			diseaseCombo.setVisible(true);
			clusterId.setVisible(false);
			clstrLabel.setVisible(false);
			probeId.setVisible(false);
			probeLabel.setVisible(false);
			mUnitLabel.setVisible(false);
			mesureUnitId.setVisible(false);
			goId.setVisible(false);
			goIdLabel.setVisible(false);
			statsLabel.setVisible(false);
			statistics.setVisible(false);
			avgCorrLbl.setVisible(false);
			dis1.setVisible(false);
			dis2.setVisible(false);
			lblAnd.setVisible(false);
			diseaseLabel.setVisible(true);
			break;
		case QUERY_2:
			diseaseCombo.setVisible(true);
			clusterId.setVisible(false);
			clstrLabel.setVisible(false);
			probeId.setVisible(false);
			probeLabel.setVisible(false);
			mUnitLabel.setVisible(false);
			mesureUnitId.setVisible(false);
			goId.setVisible(false);
			goIdLabel.setVisible(false);
			statsLabel.setVisible(false);
			statistics.setVisible(false);
			avgCorrLbl.setVisible(false);
			dis1.setVisible(false);
			dis2.setVisible(false);
			lblAnd.setVisible(false);
			diseaseLabel.setVisible(true);
			break;
		case QUERY_3:
			diseaseCombo.setVisible(true);
			clusterId.setVisible(true);
			clstrLabel.setVisible(true);
			mUnitLabel.setVisible(true);
			mesureUnitId.setVisible(true);
			statsLabel.setVisible(false);
			statistics.setVisible(false);
			avgCorrLbl.setVisible(false);
			dis1.setVisible(false);
			dis2.setVisible(false);
			lblAnd.setVisible(false);
			diseaseLabel.setVisible(true);
			break;
		case QUERY_4:
			diseaseCombo.setVisible(true);
			clusterId.setVisible(false);
			clstrLabel.setVisible(false);
			probeId.setVisible(false);
			probeLabel.setVisible(false);
			mUnitLabel.setVisible(false);
			mesureUnitId.setVisible(false);
			goId.setVisible(true);
			goIdLabel.setVisible(true);
			statsLabel.setVisible(false);
			statistics.setVisible(false);
			avgCorrLbl.setVisible(false);
			dis1.setVisible(false);
			dis2.setVisible(false);
			lblAnd.setVisible(false);
			statistics.setVisible(true);
			statsLabel.setVisible(true);
			statistics.setSelectedIndex(0);
			statistics.setEditable(false);
			diseaseLabel.setVisible(true);
			break;
		case QUERY_5:
			clusterId.setVisible(false);
			clstrLabel.setVisible(false);
			probeId.setVisible(false);
			probeLabel.setVisible(false);
			mUnitLabel.setVisible(false);
			mesureUnitId.setVisible(false);
			goId.setVisible(true);
			goIdLabel.setVisible(true);
			statistics.setVisible(true);
			statsLabel.setVisible(true);
			statistics.setSelectedIndex(1);
			statistics.setEditable(false);
			avgCorrLbl.setVisible(false);
			dis1.setVisible(true);
			dis2.setVisible(true);
			lblAnd.setVisible(false);
			diseaseCombo.setVisible(false);
			diseaseLabel.setVisible(false);
			avgCorrLbl.setVisible(true);
			lblAnd.setVisible(true);
			dis1.setSelectedIndex(1);
			dis2.setSelectedIndex(2);
			break;
		case QUERY_6:
			clusterId.setVisible(false);
			clstrLabel.setVisible(false);
			probeId.setVisible(false);
			probeLabel.setVisible(false);
			mUnitLabel.setVisible(false);
			mesureUnitId.setVisible(false);
			goId.setVisible(true);
			goIdLabel.setVisible(true);
			avgCorrLbl.setVisible(true);
			dis1.setVisible(true);
			dis2.setVisible(true);
			dis1.setSelectedIndex(1);
			dis2.setSelectedIndex(2);
			lblAnd.setVisible(true);
			diseaseLabel.setVisible(false);
			diseaseCombo.setVisible(false);

			break;
		default:
			break;
		}

	}

	private void executeQuery(String queryType) throws SQLException {
		String diseaseName = diseaseCombo.getSelectedItem() == null || diseaseCombo.getSelectedItem() == "All" ? ""
				: diseaseCombo.getSelectedItem().toString();
		String disease1 = dis1.getSelectedItem() == null ? "" : dis1.getSelectedItem().toString();
		String disease2 = dis2.getSelectedItem() == null ? "" : dis2.getSelectedItem().toString();
		List<String[]> rawQueryResults = null;
		Object[][] queryData = null;
		DefaultTableModel model = null;
		Object[] queryResult = null;
		String go_id = goId.getText();
		switch (queryType) {
		case QUERY_1:
			queryResult = olapQueryClient.queryForTumorALLPatients(diseaseName, null);
			populateTable(queryResult);
			break;
		case QUERY_2:
			String dsType = "";
			String dsDsc = "";
			queryResult = olapQueryClient.q2drugsForPatientsWithTumor(diseaseName, dsType, dsDsc);
			populateTable(queryResult);
			break;
		case QUERY_3:
			String clId = clusterId.getText();
			String muId = mesureUnitId.getText();
			queryResult = olapQueryClient.expforALLpatientsquery3(diseaseName, clId, muId);
			populateTable(queryResult);
			break;
		case QUERY_4:
			queryResult = olapQueryClient.tstatALLpatientsquery4(diseaseName, go_id);
			populateTable(queryResult);
			break;
		case QUERY_5:
			String goid = goId.getText();
			queryResult = olapQueryClient.fstatALLAMLpatientsquery5(disease1, disease2, goid);
			populateTable(queryResult);
			break;
		case QUERY_6:
			//queryResult = olapQueryClient.findInformativeGenes("ALL");
			//populateTable(queryResult);
			break;
		}
	}

	private void populateTable(Object[] queryResult) {
		List<String[]> rawQueryResults = (ArrayList<String[]>) queryResult[2];
		Object[][] queryData = OLAPUtilities.convertListToArray(rawQueryResults);
		DefaultTableModel model = new DefaultTableModel(queryData, (String[]) queryResult[1]);
		resultTable.setModel(model);
		resultCount.setText("Result Count: " + queryResult[0]);

	}
}
