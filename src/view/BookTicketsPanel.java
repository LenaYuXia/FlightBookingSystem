package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import entity.Ticket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class BookTicketsPanel extends JPanel {

	private JTabbedPane tabbedPanel = new JTabbedPane();

	public BookTicketsPanel() {
		initMainPanel(1); // need chang
	}

	public BookTicketsPanel(int iSeatTpe) {
		initMainPanel(iSeatTpe);
	}

	public void initMainPanel(int iSeatTpe) {
		this.setLayout(new BorderLayout());

		String ticket1 = "Ticket 1";
		BookATicketPanel bookATicketPanel = new BookATicketPanel(iSeatTpe);
		tabbedPanel.addTab(ticket1, bookATicketPanel);
		addCloseActionToTab(ticket1, bookATicketPanel);

		JPanel panel = new JPanel();
		panel.setBounds(0, 354, 450, 33);
		bookATicketPanel.add(panel);

		JButton btnAddmorecustomer = new JButton("Add a New Ticket");
		panel.add(btnAddmorecustomer);
		btnAddmorecustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookATicketPanel bookATicketPanelTemp = new BookATicketPanel(iSeatTpe);
				String title = "Ticket " + (tabbedPanel.getTabCount() + 1);
				tabbedPanel.addTab(title, bookATicketPanelTemp);
				addCloseActionToTab(title, bookATicketPanelTemp);
			}
		});
		btnAddmorecustomer.setSize(40, 10);

		JButton btnNext = new JButton("              Next              ");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame confirmFrame = new JFrame();
				BookingConfirmPanel msf = new BookingConfirmPanel(getTickets());
				confirmFrame.getContentPane().add(msf, BorderLayout.CENTER);
				confirmFrame.setSize(600, 500);
				confirmFrame.setVisible(true);
			}
		});
		panel.add(btnNext);

		this.add(tabbedPanel, BorderLayout.CENTER);

	}

	// init ticket to do
	public List<Ticket> getTickets() {
		tabbedPanel.getTabCount();
		return null;
	}

	// init ticket to do
	public void initTickets(List<Ticket> tickets) {
		tabbedPanel.getTabCount();

	}

	private void addCloseActionToTab(String title, BookATicketPanel bookATicketPanelTemp) {
		int index = tabbedPanel.indexOfTab(title);
		JPanel pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(title);
		JLabel btnClose = new JLabel("    x");
		btnClose.setToolTipText("Delect " + title);
		btnClose.setForeground(Color.red);
		btnClose.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;

		pnlTab.add(lblTitle, gbc);

		GridBagConstraints gbc1 = new GridBagConstraints();
		pnlTab.add(btnClose, gbc1);

		tabbedPanel.setTabComponentAt(index, pnlTab);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (bookATicketPanelTemp != null) {
					tabbedPanel.remove(bookATicketPanelTemp);
				}

			}

		});

	}

	public class MyCloseActionHandler implements ActionListener {

		public void actionPerformed(ActionEvent evt) {
			Component selected = tabbedPanel.getSelectedComponent();
			if (selected != null) {
				tabbedPanel.remove(selected);
			}

		}

	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		BookTicketsPanel msf = new BookTicketsPanel(1);
		jFrame.getContentPane().add(msf, BorderLayout.CENTER);
		jFrame.setSize(600, 500);
		jFrame.setVisible(true);
	}

}