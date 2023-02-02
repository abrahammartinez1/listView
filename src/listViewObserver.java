import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class listViewObserver extends JFrame implements Observer {
    private List<String> items = new ArrayList<>();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list = new JList<>(model);
    private JTextField textField = new JTextField(20);
    private JButton addButton = new JButton("Add");

    public listViewObserver() {
        // Hacer que la lista sea observable
        ObservableList observableList = new ObservableList(items);
        observableList.addObserver(this);

        // Agregar un listener al botón de agregar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar el elemento introducido por el usuario a la lista
                String item = textField.getText();
                observableList.addItem(item);
                textField.setText("");
            }
        });

        // Añadir los componentes al panel de contenido
        JPanel form = new JPanel();
        form.add(textField);
        form.add(addButton);
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        // Actualizar la lista cuando se notifique un cambio
        updateList();
    }

    private void updateList() {
        model.clear();
        for (String item : items) {
            model.addElement(item);
        }
    }

    private static class ObservableList extends Observable {
        private List<String> items;

        public ObservableList(List<String> items) {
            this.items = items;
        }

        public void addItem(String item) {
            items.add(item);
            setChanged();
            notifyObservers();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new listViewObserver());
    }
}
