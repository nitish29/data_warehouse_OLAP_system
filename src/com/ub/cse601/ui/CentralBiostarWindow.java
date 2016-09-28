package com.ub.cse601.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1.createParallelGroup(
						Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(59).addGroup(gl_panel_1
								.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 948, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(queryLabel).addComponent(diseaseLabel)
												.addComponent(clstrLabel).addComponent(probeLabel)
												.addComponent(mUnitLabel)
												.addComponent(goIdLabel, GroupLayout.PREFERRED_SIZE, 81,
														GroupLayout.PREFERRED_SIZE))
										.addGap(96)
										.addGroup(
												gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addComponent(goId, GroupLayout.DEFAULT_SIZE, 209,
																Short.MAX_VALUE)
														.addComponent(probeId, 209, 209, Short.MAX_VALUE)
														.addComponent(clusterId, 209, 209, Short.MAX_VALUE)
														.addComponent(queryCombo, 0, 209, Short.MAX_VALUE)
														.addComponent(diseaseCombo, 0, 209, Short.MAX_VALUE)
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(resetBtn, GroupLayout.PREFERRED_SIZE, 96,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(executeBtn, GroupLayout.PREFERRED_SIZE,
																		106, GroupLayout.PREFERRED_SIZE))
														.addComponent(mesureUnitId, GroupLayout.DEFAULT_SIZE, 209,
																Short.MAX_VALUE))
										.addGap(837))
								.addComponent(msgBox)).addContainerGap()));
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
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(mUnitLabel).addComponent(
						mesureUnitId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(goId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(25)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(resetBtn)
										.addComponent(executeBtn)))
						.addComponent(goIdLabel))
				.addGap(33).addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
				.addGap(65).addComponent(msgBox).addContainerGap(61, Short.MAX_VALUE)));

		lblQueryResults = new JLabel("Query Results");
		lblQueryResults.setHorizontalAlignment(SwingConstants.LEFT);

		scrollPane = new JScrollPane();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap().addComponent(lblQueryResults)
						.addContainerGap(857, Short.MAX_VALUE))
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE));
		gl_panel_3
				.setVerticalGroup(
						gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup().addGap(7).addComponent(lblQueryResults)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(35, Short.MAX_VALUE)));

		resultTable = new JTable();
		scrollPane.setViewportView(resultTable);
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);

		// initialize combo boxes
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
				try {
					String diseaseName = diseaseCombo.getSelectedItem().toString();
					Object[] queryResult = olapQueryClient.queryForTumorALLPatients(diseaseName, null);
					List<String[]> results = (ArrayList<String[]>) queryResult[2];
					Object[][] data = OLAPUtilities.convertListToArray(results);
					DefaultTableModel model = new DefaultTableModel(data, (String[]) queryResult[1]);
					resultTable.setModel(model);
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

		Iterator<Entry<Integer, String>> diseaseMapItr = olapQueryClient.allDiseaseList().entrySet().iterator();
		while (diseaseMapItr.hasNext()) {
			Entry<Integer, String> entry = diseaseMapItr.next();
			diseaseCombo.insertItemAt(entry.getValue(), entry.getKey());

		}
		// show frame
		this.frame.setVisible(true);
	}
}
