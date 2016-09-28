package com.ub.cse601.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.ub.cse601.sql.OLAPQueries;

public class CentralBiostarWindow {

	private JFrame frame;
	private JTextField clusterId;
	private JTextField probeId;
	private JTextField mesureUnitId;
	private JTextField goId;
	private JTextField txtQueryOutput;
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

	/**
	 * Create the application.
	 */
	public CentralBiostarWindow() {
		this.msgBox = new JLabel("Initializing app....");
		try {
			olapQueryClient = new OLAPQueries();
			initialize();
			this.msgBox = new JLabel("App initialized...Execute Queries Now");

		} catch (Exception ex) {
			msgBox.setText("Exception: " + ex.getMessage());
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

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_panel_1
												.createSequentialGroup().addGap(59).addGroup(gl_panel_1
														.createParallelGroup(Alignment.LEADING).addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGroup(gl_panel_1
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(queryLabel)
																				.addComponent(diseaseLabel)
																				.addComponent(clstrLabel)
																				.addComponent(probeLabel)
																				.addComponent(mUnitLabel).addComponent(
																						goIdLabel,
																						GroupLayout.PREFERRED_SIZE, 81,
																						GroupLayout.PREFERRED_SIZE))
																		.addGap(96)
																		.addGroup(gl_panel_1
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(goId,
																						GroupLayout.DEFAULT_SIZE, 209,
																						Short.MAX_VALUE)
																				.addComponent(probeId, 209, 209,
																						Short.MAX_VALUE)
																				.addComponent(clusterId, 209, 209,
																						Short.MAX_VALUE)
																				.addComponent(
																						queryCombo, 0, 209,
																						Short.MAX_VALUE)
																				.addComponent(diseaseCombo, 0,
																						209, Short.MAX_VALUE)
																				.addGroup(gl_panel_1
																						.createSequentialGroup()
																						.addComponent(resetBtn,
																								GroupLayout.PREFERRED_SIZE,
																								96,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(executeBtn,
																								GroupLayout.PREFERRED_SIZE,
																								106,
																								GroupLayout.PREFERRED_SIZE))
																				.addComponent(mesureUnitId,
																						GroupLayout.DEFAULT_SIZE, 209,
																						Short.MAX_VALUE))
																		.addGap(837))
														.addGroup(gl_panel_1
																.createParallelGroup(Alignment.TRAILING, false)
																.addComponent(msgBox, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(scrollPane, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 849,
																		Short.MAX_VALUE)))
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
				.addGap(40).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
				.addGap(84).addComponent(msgBox).addContainerGap(61, Short.MAX_VALUE)));

		txtQueryOutput = new JTextField();
		txtQueryOutput.setText("Query Output");
		scrollPane.setColumnHeaderView(txtQueryOutput);
		txtQueryOutput.setColumns(10);
		panel_1.setLayout(gl_panel_1);

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

		// initialize combo boxes
		queryCombo.insertItemAt("Query 1", 0);
		queryCombo.insertItemAt("Query 2", 2);
		queryCombo.insertItemAt("Query 3", 3);
		queryCombo.insertItemAt("Query 4", 4);
		queryCombo.insertItemAt("Query 5", 5);
		queryCombo.insertItemAt("Query 6", 6);
		diseaseCombo.insertItemAt("All", 0);

		Iterator<Entry<Integer, String>> diseaseMapItr = olapQueryClient.allDiseaseList().entrySet().iterator();
		while (diseaseMapItr.hasNext()) {
			Entry<Integer, String> entry = diseaseMapItr.next();
			diseaseCombo.insertItemAt(entry.getValue(), entry.getKey());

		}

		// show frame
		this.frame.setVisible(true);
	}
}
