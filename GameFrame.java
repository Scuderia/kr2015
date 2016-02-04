import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.Color;

public class GameFrame {

	private JFrame frame, snakeFrame;
	private JCheckBox gridOnOff;
	private JComboBox fontColor, snakeColor;
	private JButton start;
	private JSlider sliderSpeed, sliderScale, sliderWidth, sliderHeight;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame window = new GameFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u0417\u043C\u0435\u0439\u043A\u0430");
		frame.setResizable(false);
		frame.setBounds(100, 100, 560, 235);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(60, 179, 113));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel labelSpeed = new JLabel("\u0421\u043A\u043E\u0440\u043E\u0441\u0442\u044C");
		labelSpeed.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelSpeed.setBounds(30, 14, 70, 35);
		panel.add(labelSpeed);

		sliderSpeed = new JSlider();
		sliderSpeed.setForeground(new Color(0, 0, 0));
		sliderSpeed.setBackground(new Color(60, 179, 113));
		sliderSpeed.setMajorTickSpacing(1);
		sliderSpeed.setPaintTicks(true);
		sliderSpeed.setPaintLabels(true);
		sliderSpeed.setValue(7);
		sliderSpeed.setMaximum(14);
		sliderSpeed.setMinimum(1);
		sliderSpeed.setBounds(139, 14, 270, 44);
		panel.add(sliderSpeed);

		JLabel labelScale = new JLabel("\u041C\u0430\u0441\u0448\u0442\u0430\u0431");
		labelScale.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelScale.setBounds(30, 60, 70, 35);
		panel.add(labelScale);

		sliderScale = new JSlider();
		sliderScale.setBackground(new Color(60, 179, 113));
		sliderScale.setValue(32);
		sliderScale.setPaintTicks(true);
		sliderScale.setPaintLabels(true);
		sliderScale.setMinimum(10);
		sliderScale.setMaximum(32);
		sliderScale.setMajorTickSpacing(2);
		sliderScale.setBounds(139, 60, 270, 44);
		panel.add(sliderScale);

		JLabel labelWidth = new JLabel("\u0428\u0438\u0440\u0438\u043D\u0430 \u043F\u043E\u043B\u044F");
		labelWidth.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelWidth.setBounds(30, 106, 100, 35);
		panel.add(labelWidth);

		sliderWidth = new JSlider();
		sliderWidth.setBackground(new Color(60, 179, 113));
		sliderWidth.setValue(20);
		sliderWidth.setPaintTicks(true);
		sliderWidth.setPaintLabels(true);
		sliderWidth.setMinimum(10);
		sliderWidth.setMaximum(40);
		sliderWidth.setMajorTickSpacing(2);
		sliderWidth.setBounds(139, 106, 270, 44);
		panel.add(sliderWidth);

		JLabel labelHeight = new JLabel("\u0412\u044B\u0441\u043E\u0442\u0430 \u043F\u043E\u043B\u044F");
		labelHeight.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelHeight.setBounds(30, 152, 100, 35);
		panel.add(labelHeight);

		sliderHeight = new JSlider();
		sliderHeight.setBackground(new Color(60, 179, 113));
		sliderHeight.setValue(20);
		sliderHeight.setPaintTicks(true);
		sliderHeight.setPaintLabels(true);
		sliderHeight.setMinimum(10);
		sliderHeight.setMaximum(20);
		sliderHeight.setMajorTickSpacing(2);
		sliderHeight.setBounds(140, 152, 270, 44);
		panel.add(sliderHeight);

		gridOnOff = new JCheckBox("\u0412\u043A\u043B/\u0432\u044B\u043A\u043B \u0441\u0435\u0442\u043A\u0443");
		gridOnOff.setBackground(new Color(60, 179, 113));
		gridOnOff.setSelected(true);
		gridOnOff.setBounds(424, 21, 120, 23);
		panel.add(gridOnOff);

		JLabel labelSnakeColor = new JLabel("\u0426\u0432\u0435\u0442 \u0437\u043C\u0435\u0439\u043A\u0438");
		labelSnakeColor.setBounds(447, 51, 74, 14);
		panel.add(labelSnakeColor);

		String[] snakeColors = { "Зеленый", "Коричневый", "Красный" };
		snakeColor = new JComboBox(snakeColors);
		snakeColor.setBounds(424, 68, 120, 20);
		panel.add(snakeColor);

		JLabel labelFontColor = new JLabel("\u0426\u0432\u0435\u0442 \u0444\u043E\u043D\u0430");
		labelFontColor.setBounds(447, 114, 65, 14);
		panel.add(labelFontColor);

		String[] fontColors = { "Салатовый", "Желтый", "Серый" };
		fontColor = new JComboBox(fontColors);
		fontColor.setBackground(new Color(255, 255, 255));
		fontColor.setBounds(424, 131, 120, 20);
		panel.add(fontColor);

		start = new JButton("\u041D\u0430\u0447\u0430\u0442\u044C \u0438\u0433\u0440\u0443");
		start.setForeground(new Color(0, 0, 0));
		start.setBackground(new Color(178, 34, 34));
		start.setBounds(424, 162, 120, 30);
		panel.add(start);
		start.addActionListener(new ButtonListener());
	}

	private class WinListener implements WindowListener {

		public void windowActivated(WindowEvent e) {
		}

		public void windowClosed(WindowEvent e) {
			if (e.getWindow() == snakeFrame) {
				frame.setVisible(true);
			}
		}

		public void windowClosing(WindowEvent e) {
		}

		public void windowDeactivated(WindowEvent e) {
		}

		public void windowDeiconified(WindowEvent e) {
		}

		public void windowIconified(WindowEvent e) {
		}

		public void windowOpened(WindowEvent e) {
		}
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent aE) {
			if (aE.getSource() == start) {
				frame.setVisible(false);
				snakeFrame = new JFrame("Змейка");
				snakeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				snakeFrame.setResizable(false);
				snakeFrame.setSize(sliderWidth.getValue() * sliderScale.getValue() + 7,
						sliderHeight.getValue() * sliderScale.getValue() + 30 + sliderScale.getValue() * 2);
				snakeFrame.setLocationRelativeTo(null);
				snakeFrame.getContentPane()
						.add(new SnakeGame(sliderScale.getValue(), sliderWidth.getValue(), sliderHeight.getValue(),
								sliderSpeed.getValue(), gridOnOff.isSelected(), snakeColor.getSelectedIndex(),
								fontColor.getSelectedIndex()));
				snakeFrame.setVisible(true);
				snakeFrame.addWindowListener(new WinListener());
			}
		}
	}
}