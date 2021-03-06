package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JPanel base = new JPanel();


	public QuerryFrame(DataLager data) {

		this.data = data;

		
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
	
	private void noBookingFound() {
		JOptionPane.showMessageDialog(this, "No Booking found, check youre Booking number",
				"information", JOptionPane.INFORMATION_MESSAGE);
	}

	class BookingNumberSearchListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {
			NumberFormat formatter = new DecimalFormat("#0.00"); 
			textArea.setText("");
			

			String number= textField.getText();

			BookingInfo booking = data.getBookingByNumber(number); 

			if(booking != null) {
				
				StringBuilder output = new StringBuilder(); 
				
				List<Ticket>tickets = booking.getTickets();
				
				int filghtId = tickets.get(0).getSeat().getFlightId();
				List<Flight>flights = data.getFlights().stream().filter(f -> f.getFlightId() == filghtId).collect(Collectors.toList());
				
				output.append("\n");
				output.append(" ");
				output.append(flights.get(0).getFlightName());
				output.append(" ");
				output.append(flights.get(0).getDepartureTime());
				output.append("\n");

				for(Ticket t: tickets) {

					output.append("\n");
					output.append(" ");
					output.append(t.getCustomer().getFirstName());
					output.append(" ");
					output.append(t.getCustomer().getLastName());
					output.append("\n");
					output.append(" Seat number: ");
					output.append(t.getSeat().getSeatNumber());
					

					List<Food>meal = t.getFoods();

					if(!meal.isEmpty()) {
						output.append("\n Meal Orderd:\n");
						meal.forEach(f -> output.append(" "+ f.getFoodName() + "\n"));
					}
					else {
						output.append("\n No meal ordered\n");
					}

					output.append(" Ticket price: ");
					output.append(formatter.format(t.getTicketPrice()));
					output.append("\n");
				}

				output.append("\n Total price of booking: ");
				output.append(formatter.format(booking.getBookingPrice()));
				
				textArea.append(output.toString());
			}
			else {
				
				noBookingFound();
			
		}

		
			
		}

	}


}

