import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class listView extends JFrame {
    private List<String> items = new ArrayList<>();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list = new JList<>(model);

    public listView() {
        // Agregar algunos elementos a la lista
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");

        // Actualizar la lista con los elementos iniciales
        updateList();

        // Añadir la lista al panel de contenido
        add(new JScrollPane(list), BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateList() {
        model.clear();
        for (String item : items) {
            model.addElement(item);
        }
    }

    public void addItem(String item) {
        items.add(item);
        updateList();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new listView());
    }
}
