package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import data.DataLager;
import entity.BookingInfo;
import entity.Customer;
import entity.Flight;
import entity.Food;
import entity.Seat;
import entity.SeatStatus;
import entity.Ticket;

public class QuerryFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private DataLager data;


	public QuerryFrame(DataLager data) {

		this.data = data;

		JPanel base = new JPanel();
		add(base);
		
		base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));

		JPanel panelNorth = new JPanel();
		base.add(panelNorth);

		JLabel lblBookingNumber = new JLabel("Your booking number");
		panelNorth.add(lblBookingNumber);

		textField = new JTextField();
		panelNorth.add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new BookingNumberSearchListener());
		panelNorth.add(btnSearch);

		textArea = new JTextArea();
		base.add(textArea);

	}

	class BookingNumberSearchListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {

			textArea.setText("");
			StringBuilder output = new StringBuilder();

			String number= textField.getText();

			BookingInfo booking = data.getBookingByNumber(number); 

			if(booking != null) {

				List<Ticket>tickets = booking.getTickets();

				for(Ticket t: tickets) {

					output.append(t.getCustomer().getFirstName());
					output.append(" ");
					output.append(t.getCustomer().getLastName());
					output.append(" seatNumber: ");
					output.append(t.getSeat().getSeatNumber());
					output.append("\nMeal Orderd:\n");

					List<Food>meal = t.getFoods();

					if(meal != null) {
					meal.forEach(f -> output.append(f.getFoodName() + "\n"));
					}
					else {
						output.append("No meal ordered");
					}

					output.append(" Price: ");
					output.append(t.getTicketPrice());
				}

				output.append("\n\nTotal price of booking: ");
				output.append(booking.getBookingPrice());
			}

			textArea.append(output.toString());
		}


	}


}

