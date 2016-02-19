package org.axp.singleton.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.axp.singleton.Weather;
import org.axp.singleton.WeatherController;
import org.axp.singleton.event.WeatherAvailableEvent;
import org.axp.singleton.event.WeatherChangeEvent;

/**
 * The UI front end for the weather app. No business logic here.
 */
public class WeatherUI extends JFrame implements ChangeListener {
	private static final long serialVersionUID = 1426625161412730462L;
	private JLabel weatherDesc = label( " ", 14 );
	private JLabel currTemp = label( "???°C", 20 );
	private JLabel minTemp = label( "???°C", 20 );
	private JLabel maxTemp = label( "???°C", 20 );
	private JLabel humidity = label( "??%", 20 );
	private JLabel windSpeed = label( "??? mps", 20 );

	public WeatherUI( WeatherController controller ) {
		super( "Scottish weather" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		controller.setChangeListener( this );

		JButton loadButton = new JButton( "Get weather data" );
		loadButton.setActionCommand( "load" );
		loadButton.addActionListener( controller );
		add( loadButton );
		
		setSize( 220, 270 );
	}
	
	private void setOutWeatherPanel( WeatherController controller, String[] cityNames ) {
		JComboBox<String> dropDown = new JComboBox<>( cityNames );
		dropDown.addItemListener( controller );
		dropDown.setBorder( BorderFactory.createEmptyBorder( 0, 0, 8, 0 ) );
		dropDown.setSelectedIndex( -1 );
		
		TabularPanel weatherPanel = new TabularPanel( 0, 5 );
		weatherPanel.addRow( 2, dropDown );
		weatherPanel.addRow( new JLabel( "Current weather" ), this.weatherDesc );
		weatherPanel.addRow( new JLabel( "Current temperature" ), this.currTemp );
		weatherPanel.addRow( new JLabel( "Min temperature" ), this.minTemp );
		weatherPanel.addRow( new JLabel( "Max temperature" ), this.maxTemp );
		weatherPanel.addRow( new JLabel( "Humidity" ), this.humidity );
		weatherPanel.addRow( new JLabel( "Wind speed" ), this.windSpeed );
		weatherPanel.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
		
		getContentPane().removeAll();
		add( weatherPanel );
		pack();
	}
	
	private static JLabel label( String text, float size ) {
		JLabel label = new JLabel( text );
		label.setFont( label.getFont().deriveFont( size ) );
		return label;
	}

	@Override
	public void stateChanged( ChangeEvent evt ) {
		if ( evt instanceof WeatherChangeEvent ) {
			WeatherChangeEvent weatherChange = (WeatherChangeEvent) evt;
			Weather weather = weatherChange.getNewWeather();

			this.weatherDesc.setText( weather.getDescription() );
			this.currTemp.setText( weather.getCurrTemp() + "°C" );
			this.minTemp.setText( weather.getMinTemp() + "°C" );
			this.maxTemp.setText( weather.getMaxTemp() + "°C" );
			this.humidity.setText( weather.getHumidity() + "%" );
			this.windSpeed.setText( weather.getWindSpeed() + " mps" );
		}
		else if ( evt instanceof WeatherAvailableEvent ) {
			WeatherAvailableEvent weatherAvailable = (WeatherAvailableEvent) evt;
			setOutWeatherPanel( weatherAvailable.getSource(), weatherAvailable.getAvailableLocations() );
		}
		else {
			throw new IllegalArgumentException( "Don't know how to deal with change" );
		}
	}
}
