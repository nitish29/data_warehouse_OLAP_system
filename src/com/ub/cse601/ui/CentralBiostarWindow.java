package com.ub.cse601.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
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
import javax.swing.table.DefaultTableModel;

import com.ub.cse601.sql.OLAPQueries;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.ScrollPaneConstants;

public class CentralBiostarWindow {

	private JFrame frame;
	private JTextField clusterId;
	private JTextField probeId;
	private JTextField mesureUnitId;
	private JTextField goId;
	private JComboBox queryCombo;
	private JLabel queryLabel;
	private JLabel diseaseLabel;
	private JLabel probeLabel;
	private JLabel mUnitLabel;
	private JComboBox diseaseCombo;
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
	private JComboBox dis1;
	private JLabel lblAnd;
	private JComboBox dis2;
	private JLabel avgCorrLbl;
	private JLabel statsLabel;
	private JComboBox statistics;

	/**
	 * Create the application.
	 */
	public CentralBiostarWindow() {
		this.msgBox = new JLabel("Initializing app....");
		try {
			olapQueryClient = new OLAPQueries();
			initialize();
			msgBox.setText("App initialized...Execute Queries Now");

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Part 2", null, panel_1, null);

		msgBox = new JLabel("");

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
				} catch (Exception ex) {
					msgBox.setText("Exception: " + ex);
				}
			}
		});
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Part 3", null, panel_2, null);

		JLabel lblDisease_1 = new JLabel("Disease");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap().addComponent(lblDisease_1).addContainerGap(359, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap().addComponent(lblDisease_1).addContainerGap(194, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		// populate disease combo
		dis1.insertItemAt("Select", 0);
		dis2.insertItemAt("Select", 0);
		Iterator<Entry<Integer, String>> diseaseMapItr = olapQueryClient.allDiseaseList().entrySet().iterator();
		while (diseaseMapItr.hasNext()) {
			Entry<Integer, String> entry = diseaseMapItr.next();
			diseaseCombo.insertItemAt(entry.getValue(), entry.getKey());
			dis1.insertItemAt(entry.getValue(), entry.getKey());
			dis2.insertItemAt(entry.getValue(), entry.getKey());

		}
		// show frame
		this.frame.setVisible(true);
	}

	public void onChangeQuery() {
		String query = queryCombo.getSelectedItem().toString();
		switch (query) {
		case QUERY_1:
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
			break;
		case QUERY_2:
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
			break;
		case QUERY_3:
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
			break;
		case QUERY_4:
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
			dis1.setVisible(false);
			dis2.setVisible(false);
			lblAnd.setVisible(false);
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
			dis1.setSelectedIndex(0);
			dis2.setSelectedIndex(0);
			lblAnd.setVisible(true);
			break;
		default:
			break;
		}

	}

	private void executeQuery(String queryType) throws SQLException {
		switch (queryType) {
		case QUERY_1:
			msgBox.setText("Executing Query...");
			String diseaseName = diseaseCombo.getSelectedIndex() == 0 ? "" : diseaseCombo.getSelectedItem().toString();
			Object[] queryResult = olapQueryClient.queryForTumorALLPatients(diseaseName, null);
			List<String[]> results = (ArrayList<String[]>) queryResult[2];
			Object[][] data = OLAPUtilities.convertListToArray(results);
			DefaultTableModel model = new DefaultTableModel(data, (String[]) queryResult[1]);
			resultTable.setModel(model);
			resultCount.setText("Result Count: " + queryResult[0]);
			msgBox.setText("Query Executed Successfully");
			break;
		case QUERY_2:
			break;
		case QUERY_3:
			break;
		case QUERY_4:
		case QUERY_5:
			break;
		case QUERY_6:
			break;
		}
	}
}
