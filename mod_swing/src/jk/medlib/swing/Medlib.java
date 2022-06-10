/*
 * Medlib.java
 *
 * Created on __DATE__, __TIME__
 */

package jk.medlib.swing;

import java.util.List;

import javax.swing.UIManager;

import jk.frame.FrameLoader;
import jk.medlib.fs.events.LoadFoldersEvent;
import jk.medlib.media.events.GetMediaFilesEvent;
import jk.medlib.media.events.GetMediaTreeEvent;
import jk.medlib.media.types.MediaCategory;

public class Medlib extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	/** Creates new form Medlib 
	 * @throws Exception */
	public Medlib() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jSplitPane = new javax.swing.JSplitPane();
		jSplitRight = new javax.swing.JScrollPane();
		jTable = new javax.swing.JTable();
		jSplitLeft = new javax.swing.JScrollPane();
		jPanelCats = new javax.swing.JPanel();
		jTogglePlaylists = new javax.swing.JToggleButton();
		jPanelPlaylists = new javax.swing.JPanel();
		jTreePlaylists = new javax.swing.JTree();
		jPanelErrors = new javax.swing.JPanel();
		jTreeErrors = new javax.swing.JTree();
		jToggleErrors = new javax.swing.JToggleButton();
		jTextSearch = new javax.swing.JTextField();
		jButtonSearch = new javax.swing.JButton();
		jSeparator = new javax.swing.JSeparator();
		jButtonNewPls = new javax.swing.JButton();
		jButtonSavePls = new javax.swing.JButton();
		jButtonDeletePls = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Media Library");
		setBackground(new java.awt.Color(255, 255, 255));

		jSplitPane.setBackground(new java.awt.Color(255, 255, 255));
		jSplitPane.setDividerLocation(200);

		jSplitRight.setBackground(new java.awt.Color(255, 255, 255));

		jTable.setAutoCreateRowSorter(true);
		jTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null } },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jTable.setAutoscrolls(false);
		jTable.setShowHorizontalLines(false);
		jTable.setShowVerticalLines(false);
		jSplitRight.setViewportView(jTable);

		jSplitPane.setRightComponent(jSplitRight);

		jSplitLeft.setPreferredSize(new java.awt.Dimension(50, 100));

		jPanelCats.setBackground(new java.awt.Color(255, 255, 255));
		jPanelCats.setMinimumSize(new java.awt.Dimension(50, 0));

		jTogglePlaylists.setFont(new java.awt.Font("Segoe UI", 1, 12));
		jTogglePlaylists.setText("Playlists");
		jTogglePlaylists
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jTogglePlaylists.setMaximumSize(new java.awt.Dimension(100, 25));
		jTogglePlaylists.setMinimumSize(new java.awt.Dimension(100, 25));
		jTogglePlaylists.setPreferredSize(new java.awt.Dimension(100, 25));
		jPanelPlaylists.setVisible(jTogglePlaylists.isSelected());
		jTogglePlaylists.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTogglePlaylistsActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanelPlaylistsLayout = new javax.swing.GroupLayout(
				jPanelPlaylists);
		jPanelPlaylists.setLayout(jPanelPlaylistsLayout);
		jPanelPlaylistsLayout.setHorizontalGroup(jPanelPlaylistsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTreePlaylists,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 197,
						Short.MAX_VALUE));
		jPanelPlaylistsLayout.setVerticalGroup(jPanelPlaylistsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTreePlaylists,
						javax.swing.GroupLayout.DEFAULT_SIZE, 92,
						Short.MAX_VALUE));

		javax.swing.GroupLayout jPanelErrorsLayout = new javax.swing.GroupLayout(
				jPanelErrors);
		jPanelErrors.setLayout(jPanelErrorsLayout);
		jPanelErrorsLayout.setHorizontalGroup(jPanelErrorsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTreeErrors,
						javax.swing.GroupLayout.DEFAULT_SIZE, 197,
						Short.MAX_VALUE));
		jPanelErrorsLayout.setVerticalGroup(jPanelErrorsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTreeErrors,
						javax.swing.GroupLayout.DEFAULT_SIZE, 107,
						Short.MAX_VALUE));

		jToggleErrors.setText("Errors");
		jToggleErrors.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jToggleErrors.setMaximumSize(new java.awt.Dimension(100, 25));
		jToggleErrors.setMinimumSize(new java.awt.Dimension(100, 25));
		jToggleErrors.setPreferredSize(new java.awt.Dimension(100, 25));
		jPanelErrors.setVisible(jToggleErrors.isSelected());
		jToggleErrors.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleErrorsActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanelCatsLayout = new javax.swing.GroupLayout(
				jPanelCats);
		jPanelCats.setLayout(jPanelCatsLayout);
		jPanelCatsLayout.setHorizontalGroup(jPanelCatsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTogglePlaylists,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 197,
						Short.MAX_VALUE)
				.addComponent(jPanelPlaylists,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jToggleErrors,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 197,
						Short.MAX_VALUE)
				.addComponent(jPanelErrors,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanelCatsLayout
				.setVerticalGroup(jPanelCatsLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelCatsLayout
										.createSequentialGroup()
										.addComponent(
												jTogglePlaylists,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelPlaylists,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jToggleErrors,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelErrors,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(157, Short.MAX_VALUE)));

		jSplitLeft.setViewportView(jPanelCats);

		jSplitPane.setLeftComponent(jSplitLeft);

		jTextSearch.setText("Search...");

		jButtonSearch.setText("Search");

		jSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);
		jSeparator.setMaximumSize(new java.awt.Dimension(50, 10));

		jButtonNewPls.setText("New Playlist");

		jButtonSavePls.setText("Save Playlist");

		jButtonDeletePls.setText("Delete Playlist");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jTextSearch,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										199,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButtonSearch)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButtonNewPls)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButtonSavePls)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButtonDeletePls)
								.addContainerGap(172, Short.MAX_VALUE))
				.addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE,
						794, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(jSplitPane)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(
																		jButtonNewPls)
																.addComponent(
																		jButtonSavePls)
																.addComponent(
																		jButtonDeletePls))
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
																.addComponent(
																		jSeparator,
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		jTextSearch,
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(
																		jButtonSearch,
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		24,
																		Short.MAX_VALUE)))
								.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jTogglePlaylistsActionPerformed(java.awt.event.ActionEvent evt) {
		jPanelPlaylists.setVisible(jTogglePlaylists.isSelected());
	}

	private void jToggleErrorsActionPerformed(java.awt.event.ActionEvent evt) {
		jPanelErrors.setVisible(jToggleErrors.isSelected());
	}

	public static void main(String args[]) {
		try {
			//TODO: show loader

			new FrameLoader().load();
			new LoadFoldersEvent().trigger();
			GetMediaTreeEvent treeEvent = new GetMediaTreeEvent();
			treeEvent.trigger();
			List<MediaCategory> roots = treeEvent.getRoots();
			for (MediaCategory root : roots) {
				System.out.println();
				printTree(root);
			}

			//TODO: load app

			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new Medlib().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			//TODO: close loader
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonDeletePls;
	private javax.swing.JButton jButtonNewPls;
	private javax.swing.JButton jButtonSavePls;
	private javax.swing.JButton jButtonSearch;
	private javax.swing.JPanel jPanelCats;
	private javax.swing.JPanel jPanelErrors;
	private javax.swing.JPanel jPanelPlaylists;
	private javax.swing.JSeparator jSeparator;
	private javax.swing.JScrollPane jSplitLeft;
	private javax.swing.JSplitPane jSplitPane;
	private javax.swing.JScrollPane jSplitRight;
	private javax.swing.JTable jTable;
	private javax.swing.JTextField jTextSearch;
	private javax.swing.JToggleButton jToggleErrors;
	private javax.swing.JToggleButton jTogglePlaylists;
	private javax.swing.JTree jTreeErrors;
	private javax.swing.JTree jTreePlaylists;

	// End of variables declaration//GEN-END:variables

	private static void printTree(MediaCategory cat) {
		try {
			GetMediaFilesEvent files = new GetMediaFilesEvent(cat);
			files.trigger();

			int size = 0;

			if (files.getMediaFiles() != null)
				size = files.getMediaFiles().size();

			System.out.println(cat.getCategoryPath() + " (" + size + ") - "
					+ cat.getChildren().size() + " children");
			for (MediaCategory current : cat.getChildren())
				printTree(current);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}