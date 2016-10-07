package com.ub.cse601.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalDouble;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.inference.TTest;

import com.ub.cse601.sql.OLAPQueries;

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
	private static final String QUERY_7 = "Informative Genes";
	private static final String QUERY_8 = "Classify Patient";
	private static final String QUERY_9 = "T-Statistics";
	private static final String QUERY_10 = "F-Statistics";

	private JComboBox<String> dis1;
	private JLabel lblAnd;
	private JComboBox<String> dis2;
	private JLabel avgCorrLbl;
	private JLabel statsLabel;
	private JComboBox<String> statistics;
	private JLabel pIdLbl;
	private JLabel kdQuery;
	JComboBox kdCombo;
	JLabel kdDisLbl;
	JComboBox kdDisCombo;
	JLabel thrsValue;
	JTextField thrsText;
	JLabel errorMsg;
	JLabel queryDesc;
	JComboBox pIdCombo;
	JPanel kdPanel;
	JScrollPane kdTable;
	JLabel kdQResCnt;
	JButton kdReset;
	JButton kdExecute;
	private JLabel chlkBoxLbl;
	private JCheckBox dis1Chk;
	private JCheckBox dis2Chk;
	private JCheckBox dis3Chk;
	private JCheckBox dis4Chk;
	private JCheckBox dis5Chk;
	private JCheckBox dis6Chk;
	private JLabel queryTime;
	private JComboBox disDscCombo;
	JLabel disType;
	JLabel disDesc;
	JComboBox disTypCombo;

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
		// show frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);
		// frame.setBounds(100, 100, 450, 300);
		msgBox = new JLabel("Initializing app....");

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
		statistics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (queryCombo.getSelectedItem().equals(QUERY_5)) {
					avgCorrLbl.setText(statistics.getSelectedItem().toString() + " Between");
				}

			}
		});

		dis1 = new JComboBox();

		lblAnd = new JLabel("AND");

		dis2 = new JComboBox();

		avgCorrLbl = new JLabel("Avg Correlation Between");

		errorMsg = new JLabel("");

		queryDesc = new JLabel("");
		queryDesc.setForeground(Color.BLUE);

		chlkBoxLbl = new JLabel("Between");

		dis1Chk = new JCheckBox("ALL");

		dis2Chk = new JCheckBox("AML");

		dis3Chk = new JCheckBox("Breast tumor");

		dis4Chk = new JCheckBox("Colon tumor");

		dis5Chk = new JCheckBox("Giloblastome");

		dis6Chk = new JCheckBox("Flu");

		disTypCombo = new JComboBox();
		disType = new JLabel("Type");
		populateDiseaseTypeCombo();
		disDscCombo = new JComboBox();
		populateDiseaseDescCombo();
		disDesc = new JLabel("Description");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
								gl_panel_1
										.createSequentialGroup().addGap(
												59)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_1.createSequentialGroup()
														.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
																.addComponent(queryLabel).addComponent(diseaseLabel)
																.addComponent(clstrLabel).addComponent(probeLabel)
																.addComponent(mUnitLabel)
																.addComponent(goIdLabel, GroupLayout.PREFERRED_SIZE, 81,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(statsLabel)
																.addComponent(avgCorrLbl))
														.addGap(96)
														.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
																.addComponent(statistics, 0, 220, Short.MAX_VALUE)
																.addComponent(goId, GroupLayout.DEFAULT_SIZE, 220,
																		Short.MAX_VALUE)
																.addComponent(probeId, 209, 220, Short.MAX_VALUE)
																.addComponent(clusterId, 209, 220, Short.MAX_VALUE)
																.addComponent(queryCombo, 0, 220, Short.MAX_VALUE)
																.addComponent(diseaseCombo, 0, 220, Short.MAX_VALUE)
																.addComponent(mesureUnitId, GroupLayout.DEFAULT_SIZE,
																		220, Short.MAX_VALUE)
																.addGroup(gl_panel_1.createSequentialGroup()
																		.addComponent(resetBtn,
																				GroupLayout.PREFERRED_SIZE, 96,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(executeBtn,
																				GroupLayout.PREFERRED_SIZE, 106,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED))
																.addGroup(gl_panel_1.createSequentialGroup()
																		.addComponent(dis1, 0, 93, Short.MAX_VALUE)
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(lblAnd,
																				GroupLayout.PREFERRED_SIZE, 27,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(dis2, GroupLayout.PREFERRED_SIZE,
																				83, GroupLayout.PREFERRED_SIZE)))
														.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel_1.createSequentialGroup().addGap(50)
																		.addComponent(disType).addGap(18)
																		.addComponent(disTypCombo,
																				GroupLayout.PREFERRED_SIZE, 147,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(disDesc)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(disDscCombo, 0, 157,
																				Short.MAX_VALUE)
																		.addGap(188))
																.addGroup(gl_panel_1.createSequentialGroup()
																		.addGroup(gl_panel_1
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_panel_1
																						.createSequentialGroup()
																						.addGap(37)
																						.addGroup(gl_panel_1
																								.createParallelGroup(
																										Alignment.LEADING)
																								.addComponent(errorMsg)
																								.addComponent(
																										queryDesc)))
																				.addGroup(gl_panel_1
																						.createSequentialGroup()
																						.addGap(96)
																						.addComponent(chlkBoxLbl)
																						.addGap(18)
																						.addGroup(gl_panel_1
																								.createParallelGroup(
																										Alignment.LEADING)
																								.addComponent(dis3Chk)
																								.addComponent(dis1Chk)
																								.addComponent(dis2Chk))
																						.addGap(38)
																						.addGroup(gl_panel_1
																								.createParallelGroup(
																										Alignment.LEADING)
																								.addComponent(dis5Chk)
																								.addComponent(dis6Chk)
																								.addComponent(
																										dis4Chk))))
																		.addPreferredGap(ComponentPlacement.RELATED, 86,
																				Short.MAX_VALUE))))
												.addComponent(msgBox).addComponent(panel_3, GroupLayout.PREFERRED_SIZE,
														948, GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		gl_panel_1
				.setVerticalGroup(
						gl_panel_1
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(
										gl_panel_1.createSequentialGroup().addGap(14).addComponent(errorMsg)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
														.addComponent(queryLabel)
														.addComponent(queryCombo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(queryDesc))
												.addPreferredGap(
														ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
														.addComponent(diseaseLabel)
														.addComponent(diseaseCombo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(disDscCombo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(disDesc)
														.addComponent(disTypCombo, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(disType))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
														.addComponent(clstrLabel, GroupLayout.PREFERRED_SIZE, 22,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(clusterId, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
														.addComponent(probeLabel).addComponent(probeId,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addComponent(mUnitLabel).addComponent(mesureUnitId,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1
																.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel_1.createSequentialGroup()
																		.addGroup(gl_panel_1
																				.createParallelGroup(Alignment.BASELINE)
																				.addComponent(goId,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(dis1Chk))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(statistics,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_panel_1.createSequentialGroup()
																		.addComponent(goIdLabel)
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(statsLabel))
																.addComponent(chlkBoxLbl, Alignment.TRAILING))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(gl_panel_1
																		.createParallelGroup(Alignment.BASELINE)
																		.addComponent(dis1, GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(dis2, GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(avgCorrLbl).addComponent(lblAnd))
																.addGap(36)
																.addGroup(gl_panel_1
																		.createParallelGroup(Alignment.BASELINE)
																		.addComponent(executeBtn)
																		.addComponent(resetBtn)))
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(dis4Chk)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addGroup(gl_panel_1
																		.createParallelGroup(Alignment.BASELINE)
																		.addComponent(dis2Chk).addComponent(dis6Chk))
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addGroup(gl_panel_1
																		.createParallelGroup(Alignment.BASELINE)
																		.addComponent(dis3Chk).addComponent(dis5Chk))))
												.addGap(18)
												.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 294,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(msgBox)
												.addContainerGap(80, Short.MAX_VALUE)));

		lblQueryResults = new JLabel("Query Results");
		lblQueryResults.setHorizontalAlignment(SwingConstants.LEFT);

		scrollPane = new JScrollPane();

		resultCount = new JLabel();

		queryTime = new JLabel("");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup().addComponent(resultCount).addContainerGap())
				.addGroup(gl_panel_3.createSequentialGroup().addComponent(lblQueryResults)
						.addPreferredGap(ComponentPlacement.RELATED, 743, Short.MAX_VALUE).addComponent(queryTime,
								GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addGap(7)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE).addComponent(lblQueryResults)
								.addComponent(queryTime, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(resultCount).addGap(28)));

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
		diseaseCombo.insertItemAt("Select All", 0);
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
		errorMsg.setVisible(false);
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Part 3-Knowledge Discovery", null, panel_2, null);

		kdQuery = new JLabel("Find What");

		kdCombo = new JComboBox();
		kdCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				onChangeKDCombo();
			}
		});

		kdDisLbl = new JLabel("Disease");

		kdDisCombo = new JComboBox();

		thrsValue = new JLabel("Threshold P-Value");

		thrsText = new JTextField();

		pIdLbl = new JLabel("Patient Id");

		pIdCombo = new JComboBox();
		pIdCombo.setModel(new DefaultComboBoxModel(new String[] { "P1", "P2", "P3", "P4", "P5", "P6" }));

		JLabel label = new JLabel("App initialized...Execute Queries Now");

		kdPanel = new JPanel();

		kdTable = new JScrollPane();

		kdQResCnt = new JLabel();

		JLabel label_2 = new JLabel("Query Results");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_kdPanel = new GroupLayout(kdPanel);
		gl_kdPanel.setHorizontalGroup(gl_kdPanel.createParallelGroup(Alignment.LEADING).addGap(0, 948, Short.MAX_VALUE)
				.addComponent(kdTable, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
				.addGroup(gl_kdPanel.createSequentialGroup().addComponent(kdQResCnt).addContainerGap())
				.addGroup(gl_kdPanel.createSequentialGroup().addComponent(label_2).addContainerGap()));
		gl_kdPanel
				.setVerticalGroup(
						gl_kdPanel.createParallelGroup(Alignment.LEADING).addGap(0, 294, Short.MAX_VALUE)
								.addGroup(gl_kdPanel.createSequentialGroup().addGap(7).addComponent(label_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(kdTable, GroupLayout.PREFERRED_SIZE, 229,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(kdQResCnt)
										.addContainerGap(28, Short.MAX_VALUE)));
		kdPanel.setLayout(gl_kdPanel);

		kdReset = new JButton("Reset");
		kdReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetPanel2Flds();

			}

		});

		kdExecute = new JButton("Execute");
		kdExecute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String queryType = kdCombo.getSelectedItem().toString();
				try {
					executeQuery(queryType);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Knowledge discovery",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(199, 21, 133)));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(33).addGroup(gl_panel_2.createParallelGroup(
						Alignment.LEADING, false)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2
								.createSequentialGroup()
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.LEADING).addComponent(kdQuery)
										.addComponent(kdDisLbl).addComponent(thrsValue).addComponent(pIdLbl))
								.addGap(128)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
										.addComponent(pIdCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(thrsText, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(kdDisCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(kdCombo, 0, 261, Short.MAX_VALUE)
										.addGroup(gl_panel_2.createSequentialGroup()
												.addComponent(kdReset, GroupLayout.PREFERRED_SIZE, 96,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(kdExecute, GroupLayout.PREFERRED_SIZE, 106,
														GroupLayout.PREFERRED_SIZE)))
								.addGap(27)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE))
						.addComponent(kdPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(259, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addGap(
						26)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(kdQuery).addComponent(
								kdCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(pIdLbl).addComponent(
								pIdCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(kdReset)
								.addComponent(kdExecute)))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
				.addGap(27).addComponent(kdPanel, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
				.addGap(43).addComponent(label).addGap(85)));
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
		kdComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "T-Statistics", "F-Statistics", "Informative Genes", "Classify Patient" }));
	}

	public void onChangeQuery() {
		String query = queryCombo.getSelectedItem().toString();
		switch (query) {
		case QUERY_1:
			queryDesc.setText("Query Desc: Patients with a particular disease");
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
			errorMsg.setVisible(false);
			hideShowChkBoxes(false);
			disType.setVisible(true);
			disTypCombo.setVisible(true);
			disDscCombo.setVisible(true);
			disDesc.setVisible(true);
			break;
		case QUERY_2:
			queryDesc.setText("Query Desc: Drugs applied to patients with a particular disease");
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
			errorMsg.setVisible(false);
			hideShowChkBoxes(false);
			disType.setVisible(true);
			disTypCombo.setVisible(true);
			disDscCombo.setVisible(true);
			disDesc.setVisible(true);
			break;
		case QUERY_3:
			queryDesc.setText("Query Desc: Expression values of patients with a particulr disease");
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
			errorMsg.setVisible(false);
			hideShowChkBoxes(false);
			disType.setVisible(false);
			disTypCombo.setVisible(false);
			disDscCombo.setVisible(false);
			disDesc.setVisible(false);
			break;
		case QUERY_4:
			queryDesc.setText("Query Desc: T-Statistics of expression values betwwen two disease groups");
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
			errorMsg.setVisible(false);
			hideShowChkBoxes(false);
			disType.setVisible(false);
			disTypCombo.setVisible(false);
			disDscCombo.setVisible(false);
			disDesc.setVisible(false);
			break;
		case QUERY_5:
			queryDesc.setText("Query Desc: F-Statistics of expression values between two or more disease groups");
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
			statistics.setEnabled(false);
			statistics.setEditable(false);
			dis1.setVisible(false);
			dis2.setVisible(false);
			lblAnd.setVisible(false);
			diseaseCombo.setVisible(false);
			diseaseLabel.setVisible(false);
			avgCorrLbl.setVisible(false);
			lblAnd.setVisible(false);
			dis1.setSelectedIndex(1);
			dis2.setSelectedIndex(2);
			avgCorrLbl.setText(statistics.getSelectedItem() + " Between");
			errorMsg.setVisible(false);
			hideShowChkBoxes(true);
			chlkBoxLbl.setText(statistics.getSelectedItem() + " Between");
			disType.setVisible(false);
			disTypCombo.setVisible(false);
			disDscCombo.setVisible(false);
			disDesc.setVisible(false);
			break;
		case QUERY_6:
			queryDesc.setText("Query Desc: Avg. Correlation of expression values betwwen two disease groups");
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
			avgCorrLbl.setText("Avg. Correlation Between");
			errorMsg.setVisible(false);
			hideShowChkBoxes(false);
			disType.setVisible(false);
			disTypCombo.setVisible(false);
			disDscCombo.setVisible(false);
			disDesc.setVisible(false);
			statistics.setEnabled(false);
			break;
		default:
			break;
		}

	}

	public void onChangeKDCombo() {
		String query = kdCombo.getSelectedItem().toString();
		switch (query) {
		case QUERY_7:
			kdCombo.setVisible(true);
			kdDisCombo.setVisible(true);
			thrsText.setVisible(true);
			thrsValue.setVisible(true);
			kdDisLbl.setVisible(true);
			pIdLbl.setVisible(false);
			pIdCombo.setVisible(false);
			break;
		case QUERY_8:
			kdCombo.setVisible(true);
			kdDisCombo.setVisible(true);
			thrsText.setVisible(true);
			thrsValue.setVisible(true);
			kdDisLbl.setVisible(true);
			pIdLbl.setVisible(true);
			pIdCombo.setVisible(true);
			break;
		case QUERY_9:
			queryDesc.setText("Query Desc: Expression values of patients with a particulr disease");
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
			errorMsg.setVisible(false);
			hideShowChkBoxes(false);
			break;
		case QUERY_10:
			queryDesc.setText("Query Desc: T-Statistics of expression values betwwen two disease groups");
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
			errorMsg.setVisible(false);
			hideShowChkBoxes(false);
			break;

		default:
			break;
		}

	}

	private void executeQuery(String queryType) throws SQLException {
		String diseaseName = diseaseCombo.getSelectedItem() == null || diseaseCombo.getSelectedItem() == "Select All"
				? "" : diseaseCombo.getSelectedItem().toString();
		String disease1 = dis1.getSelectedItem() == null ? "" : dis1.getSelectedItem().toString();
		String disease2 = dis2.getSelectedItem() == null ? "" : dis2.getSelectedItem().toString();
		String disType = disTypCombo.getSelectedItem() == null || disTypCombo.getSelectedItem() == "All" ? ""
				: disTypCombo.getSelectedItem().toString();
		String disDsc = disDscCombo.getSelectedItem() == null || disDscCombo.getSelectedItem() == "All" ? ""
				: disDscCombo.getSelectedItem().toString();
		List<String[]> rawQueryResults = null;
		Object[][] queryData = null;
		DefaultTableModel model = null;
		Object[] queryResult = null;
		String goIdVal = goId.getText();
		String stats = statistics.getSelectedItem() == null ? "" : statistics.getSelectedItem().toString();
		long start = 0, end = 0;
		switch (queryType) {
		case QUERY_1:
			start = System.currentTimeMillis();
			queryResult = olapQueryClient.queryForTumorALLPatients(diseaseName, disType, disDsc);
			end = System.currentTimeMillis();
			populateTable(queryResult, resultTable, resultCount, start, end);
			break;
		case QUERY_2:
			start = System.currentTimeMillis();
			queryResult = olapQueryClient.q2drugsForPatientsWithTumor(diseaseName, disType, disDsc);
			end = System.currentTimeMillis();
			populateTable(queryResult, resultTable, resultCount, start, end);
			break;
		case QUERY_3:
			String clId = clusterId.getText();
			if (clId == null || clId.length() == 0) {
				errorMsg.setVisible(true);
				errorMsg.setText("!Cluster-id can't be empty.Please input Cluster-id");
				errorMsg.setForeground(Color.RED);
				break;
			}
			String muId = mesureUnitId.getText();
			start = System.currentTimeMillis();
			queryResult = olapQueryClient.expforALLpatientsquery3(diseaseName, clId, muId);
			end = System.currentTimeMillis();
			populateTable(queryResult, resultTable, resultCount, start, end);
			break;
		case QUERY_4:
			start = System.currentTimeMillis();
			queryResult = olapQueryClient.tstatALLpatientsquery4(diseaseName, goIdVal, stats);
			end = System.currentTimeMillis();
			populateTable(queryResult, resultTable, resultCount, start, end);
			break;
		case QUERY_5:
			if (goIdVal == null || goIdVal.length() == 0) {
				errorMsg.setVisible(true);
				errorMsg.setText("!GoId can't be empty.Please input GoId");
				errorMsg.setForeground(Color.RED);
				break;
			}
			List<String> selectedDiseases = new ArrayList<String>();
			String dis1 = dis1Chk.isSelected() ? dis1Chk.getText() : null;
			if (dis1 != null && dis1.length() > 0)
				selectedDiseases.add(dis1);
			String dis2 = dis2Chk.isSelected() ? dis2Chk.getText() : null;
			if (dis2 != null && dis2.length() > 0)
				selectedDiseases.add(dis2);
			String dis3 = dis3Chk.isSelected() ? dis3Chk.getText() : null;
			if (dis3 != null && dis3.length() > 0)
				selectedDiseases.add(dis3);
			String dis4 = dis4Chk.isSelected() ? dis4Chk.getText() : null;
			if (dis4 != null && dis4.length() > 0)
				selectedDiseases.add(dis4);
			String dis5 = dis5Chk.isSelected() ? dis5Chk.getText() : null;
			if (dis5 != null && dis5.length() > 0)
				selectedDiseases.add(dis5);
			String dis6 = dis6Chk.isSelected() ? dis6Chk.getText() : null;
			if (dis6 != null && dis6.length() > 0)
				selectedDiseases.add(dis6);
			start = System.currentTimeMillis();
			queryResult = olapQueryClient.fstatALLAMLpatientsquery5(goIdVal, selectedDiseases);
			end = System.currentTimeMillis();
			populateTable(queryResult, resultTable, resultCount, start, end);
			break;
		case QUERY_6:
			double avgCorrl = 0;
			if (goIdVal == null || goIdVal.length() == 0) {
				errorMsg.setVisible(true);
				errorMsg.setText("!GoId can't be empty.Please input GoId");
				errorMsg.setForeground(Color.RED);
				break;
			} else {
				errorMsg.setVisible(false);
				start = System.currentTimeMillis();
				Object[] expData = olapQueryClient.query6Correlation(disease1, disease2, goIdVal);
				end = System.currentTimeMillis();
				Map<Double, List<Double>> group1 = (Map<Double, List<Double>>) expData[0];
				Map<Double, List<Double>> group2 = (Map<Double, List<Double>>) expData[1];
				if (disease2 != null && !disease2.equals(disease1)) {
					avgCorrl = calculateAvgCorrelation(group1, group2);
					System.out.println(avgCorrl);
				} else {
					avgCorrl = calculateAvgCorrelation(group1, group1);
				}
			}
			String[] columnNames = new String[] { "DISEASE_GROUP_1", "DISEASE_GROUP_2", "AVG_CORRELATION" };
			List<String[]> corrlResult = new ArrayList<String[]>();
			corrlResult.add(new String[] { disease1, disease2, new Double(avgCorrl).toString() });
			Object[] queryResultObj = new Object[3];
			queryResultObj[0] = 1;
			queryResultObj[1] = columnNames;
			queryResultObj[2] = corrlResult;
			populateTable(queryResultObj, resultTable, resultCount, start, end);
			break;

		case QUERY_7:
			// Map<String, Double> map1 = new HashMap<>();
			// Map<String, Double> map2 = new HashMap<>();
			olapQueryClient.findInformativeGenes("ALL", 0.01);

			List<Double> list1 = new ArrayList<Double>();
			List<Double> list2 = new ArrayList<Double>();

			Double finalpvalue;

			Object[] expData = olapQueryClient.classifynewpatientcorrelation(disease1, 0.01, "P1");
			Map<Double, List<Double>> group1 = (Map<Double, List<Double>>) expData[0];
			Map<Double, List<Double>> group2 = (Map<Double, List<Double>>) expData[1];
			Map<String, List<Double>> group3 = (Map<String, List<Double>>) expData[2];

			list1 = findcorr(group1, group3);
			list2 = findcorr(group2, group3);

			double[] list1toarr = list1.stream().mapToDouble(Double::doubleValue).toArray();
			double[] list2toarr = list2.stream().mapToDouble(Double::doubleValue).toArray();

			finalpvalue = new TTest().homoscedasticTTest(list1toarr, list2toarr);

			System.out.print(finalpvalue);

			if (finalpvalue < 0.01) {
				System.out.print("New Patient has ALL");
			} else {
				System.out.print("New Patient does not have ALL");
			}

			/*
			 * String[] columnNames = new String[] { "DISEASE_GROUP_1",
			 * "DISEASE_GROUP_2", "AVG_CORRELATION" }; List<String[]>
			 * corrlResult = new ArrayList<String[]>(); corrlResult.add(new
			 * String[] { disease1, disease2, new Double(avgCorrl).toString()
			 * }); Object[] queryResultObj = new Object[3]; queryResultObj[0] =
			 * 1; queryResultObj[1] = columnNames; queryResultObj[2] =
			 * corrlResult; populateTable(queryResultObj);
			 */
			break;
		}
	}

	private List<Double> findcorr(Map<Double, List<Double>> group1, Map<String, List<Double>> group3) {

		List group1list = new ArrayList<Double>();

		List newplist = group3.entrySet().iterator().next().getValue();
		group1.forEach((Double d, List list) -> group1list.add(calculateCorrelation(list, newplist)));

		return group1list;
	}

	private void populateTable(Object[] queryResult, JTable table, JLabel resCount, long startTime, long endTime) {
		List<String[]> rawQueryResults = (ArrayList<String[]>) queryResult[2];
		Object[][] queryData = OLAPUtilities.convertListToArray(rawQueryResults);
		DefaultTableModel model = new DefaultTableModel(queryData, (String[]) queryResult[1]);
		table.setModel(model);
		resCount.setText("Result Count: " + queryResult[0]);
		queryTime.setText("Query took: " + (endTime - startTime) + " ms");

	}

	private double calculateAvgCorrelation(Map<Double, List<Double>> group1, Map<Double, List<Double>> group2) {
		List<Double> correlationList = new ArrayList<Double>();
		for (Entry<Double, List<Double>> p1Exp : group1.entrySet()) {
			for (Entry<Double, List<Double>> p2Exp : group2.entrySet()) {
				if (!p1Exp.getKey().equals(p2Exp.getKey())) {
					correlationList.add(new PearsonsCorrelation().correlation(
							p1Exp.getValue().stream().mapToDouble(Double::doubleValue).toArray(),
							p2Exp.getValue().stream().mapToDouble(Double::doubleValue).toArray()));
				}
			}
		}
		OptionalDouble mean = correlationList.stream().mapToDouble(Double::doubleValue).average();
		return mean.isPresent() ? mean.getAsDouble() : 0;

	}

	private double calculateCorrelation(List<Double> sample1, List<Double> sample2) {
		double[] sample1Arr = sample1.stream().mapToDouble(Double::doubleValue).toArray();
		double[] sample2Arr = sample2.stream().mapToDouble(Double::doubleValue).toArray();
		return new PearsonsCorrelation().correlation(sample1Arr, sample2Arr);
	}

	private void resetPanel2Flds() {
		kdCombo.setSelectedIndex(0);
		kdDisCombo.setSelectedIndex(0);
		pIdCombo.setSelectedIndex(0);
		thrsValue.setText("0.01");
		// TODO Auto-generated method stub

	}

	private void hideShowChkBoxes(boolean show) {
		chlkBoxLbl.setVisible(show);
		dis1Chk.setVisible(show);
		dis2Chk.setVisible(show);
		dis3Chk.setVisible(show);
		dis4Chk.setVisible(show);
		dis5Chk.setVisible(show);
		dis6Chk.setVisible(show);
	}

	private void populateDiseaseTypeCombo() {
		try {
			Map<Integer, String> disTypes = olapQueryClient.getAllDiseaseTypes();
			disTypCombo.insertItemAt("All", 0);
			for (Entry<Integer, String> entry : disTypes.entrySet()) {
				disTypCombo.insertItemAt(entry.getValue(), entry.getKey());
			}
			disTypCombo.setSelectedIndex(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	private void populateDiseaseDescCombo() {
		try {
			Map<Integer, String> disDesc = olapQueryClient.getAllDiseaseDesc();
			disDscCombo.insertItemAt("All", 0);
			for (Entry<Integer, String> entry : disDesc.entrySet()) {
				disDscCombo.insertItemAt(entry.getValue(), entry.getKey());
			}
			disDscCombo.setSelectedIndex(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// TODO Auto-generated method stub

	}
}
