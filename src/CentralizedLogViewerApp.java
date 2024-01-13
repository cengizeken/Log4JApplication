import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CentralizedLogViewerApp extends JFrame {

    private DefaultTableModel tableModel;
    private JTable logTable;

    public CentralizedLogViewerApp() {
        setTitle("Centralized Log Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // GUI components
        tableModel = new DefaultTableModel(new String[]{"Sınıf", "Hata Mesajı"}, 0);
        logTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(logTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton simulateErrorButton = new JButton("Hata Simülasyonu");
        simulateErrorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateErrors();
            }
        });

        getContentPane().add(simulateErrorButton, BorderLayout.SOUTH);

        // Log olaylarını işleme
        processLogEvents();


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopThread = true;
                // Diğer kapatma işlemleri
            }
        });
    }

    @Override
    public synchronized void addWindowListener(WindowListener l) {
    //TO DOfryryr
    }


    private void simulateErrors() {
        MyClass1 myClass1 = new MyClass1();
        MyClass2 myClass2 = new MyClass2();

        myClass1.doSomething();
        myClass2.doAnotherThing();
    }

    private volatile boolean stopThread = false;

    private void processLogEvents() {
        new Thread(() -> {
            while (!stopThread) {
                String logMessage = LogEventQueue.getInstance().poll();
                if (logMessage != null) {
                    SwingUtilities.invokeLater(() -> {
                        String[] parts = logMessage.split(": ");
                        tableModel.addRow(new Object[]{parts[0], parts[1]});
                        logTable.scrollRectToVisible(logTable.getCellRect(logTable.getRowCount() - 1, 0, true));
                    });
                }
            }
        }).start();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CentralizedLogViewerApp().setVisible(true));
    }
}
