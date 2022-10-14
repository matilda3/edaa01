package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.text.Position;

public class BookReaderController {
	
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(counter.getWordList());
		JList<Map.Entry<String, Integer>> listView = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(listView);
		 pane.add(scrollPane);
		 
		 JButton buttonA = new JButton("Alphabetic");
		 
		 JButton buttonFreq = new JButton("Frequency");
		 buttonFreq.addActionListener(event -> {
			 listModel.sort((item1, item2) -> {
					if(item2.getValue().equals(item1.getValue()) ){ 
						return item1.getKey().compareTo(item2.getKey());
					}else {
						return item2.getValue().compareTo(item1.getValue());
					}
				});
		 });
		buttonA.addActionListener(event -> {
			 listModel.sort((item1, item2) -> (item1.getKey().compareTo(item2.getKey())));
		 });
		 JPanel panel = new JPanel();
		 panel.add(buttonA);
		 panel.add(buttonFreq);
		 pane.add(panel, BorderLayout.SOUTH);
		 
		 JPanel searchPanel = new JPanel(new BorderLayout());
		 searchPanel.setLayout(new FlowLayout());
		 
		 JTextField textField = new JTextField(10);
		 textField.addActionListener(event -> {
			 String toFind = textField.getText().trim().toLowerCase();
			 for(int i = 0; i < listModel.getSize(); i++) {
				 if(listModel.getElementAt(i).getKey().equals(toFind)) {
					 listView.ensureIndexIsVisible(i);
					 listView.setSelectedIndex(i);
				 }else {
						UserInterface.prntMsg("Ordet finns inte");
					 }
			 }
		 });
		 
		 JButton searchButton = new JButton("Search");
		 searchButton.addActionListener(event -> {
			 String toFind = textField.getText().trim().toLowerCase();
			 for(int i = 0; i < listModel.getSize(); i++) {
				 if(listModel.getElementAt(i).getKey().equals(toFind)) {
					 listView.ensureIndexIsVisible(i);
					 listView.setSelectedIndex(i);
				 }else {
					UserInterface.prntMsg("Ordet finns inte");
				 }
			 }
		 });
		 
		 searchPanel.add(searchButton);
		 searchPanel.add(textField);
		 pane.add(searchPanel, BorderLayout.NORTH);
		 
		frame.pack();
		frame.setVisible(true);
		
	}

}
