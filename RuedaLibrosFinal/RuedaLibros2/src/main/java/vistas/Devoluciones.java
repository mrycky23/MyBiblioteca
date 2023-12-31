/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas;

import com.mycompany.ruedalibros2.DAOBooksImpl;
import com.mycompany.ruedalibros2.DAOPrestamosImpl;
import com.mycompany.ruedalibros2.DAOUsersImpl;
import interfaces.DAOLibros;
import interfaces.DAOPrestamos;
import interfaces.DAOUsers;
import Utils.Utils;
import java.awt.Color;
import java.util.Date;
/**
 *
 * @author Usuario
 */
public class Devoluciones extends javax.swing.JPanel {
    private final int MAX_DAYS_RETURN = 5;
    private final int COST_DAY_SANC = 10;
    /**
     * Creates new form Devoluciones
     */
    public Devoluciones() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        libroIdTxt = new javax.swing.JTextField();
        folioTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnDevolver = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Devoluciones");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 21, 120, 40));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 15, 322));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Folio Usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Libro Id:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));
        jPanel1.add(libroIdTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 206, 33));

        folioTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folioTxtActionPerformed(evt);
            }
        });
        jPanel1.add(folioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 141, 206, 33));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reembolso.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 269, 231));

        btnDevolver.setBackground(new java.awt.Color(51, 102, 255));
        btnDevolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDevolver.setForeground(new java.awt.Color(255, 255, 255));
        btnDevolver.setText("Devolver");
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnDevolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 200, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void folioTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folioTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_folioTxtActionPerformed

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        String folio = folioTxt.getText();
        String bookId = libroIdTxt.getText();

        // Validaciones para los campos
        if (folio.isEmpty() || bookId.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            folioTxt.requestFocus();
            return;
        } else if (!Utils.isNumeric(folio) || !Utils.isNumeric(bookId)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Los campos Folio y el ID del libro deben ser números enteros. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            folioTxt.requestFocus();
            return;
        } else if (Integer.parseInt(folio) <= 0 || Integer.parseInt(bookId) <= 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Los campos Folio y el ID del libro deben ser mayor que 0. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            folioTxt.requestFocus();
            return;
        }

        try {
            DAOUsers daoUsers = new DAOUsersImpl();
            
            // Validamos existencia del usuario
            modelos.usuarios currentUser = daoUsers.getusuariobById(Integer.parseInt(folio));
            if (currentUser == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ningún usuario con ese folio. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                folioTxt.requestFocus();
                return;
            }
            
            DAOLibros daoBooks = new DAOBooksImpl();
            
            // Validamos existencia del libro
            modelos.Libros currentBook = daoBooks.getBookById(Integer.parseInt(bookId));
            if (currentBook == null){
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ningún libro con ese ID. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                libroIdTxt.requestFocus();
                return;
            }
            
            DAOPrestamos daoLendings = new DAOPrestamosImpl();
            
            // Validamos que el usuario tenga ese libro prestado.
            modelos.Prestamo currentLending = daoLendings.getLending(currentUser, currentBook);
            if (currentLending == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se ha podido encontrar el préstamo correspiendote a los datos ingresados. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
                libroIdTxt.requestFocus();
                return;
            }

            // Todo OK: Devolvemos libro.
            currentLending.setFecha_regreso(Utils.getFechaActual());
            daoLendings.modificar(currentLending);
            
            // Modificamos el libro sumandole 1 en disponibilidad.
            currentBook.setDisponible(currentBook.getDisponible() + 1);
            daoBooks.modificar(currentBook);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Libro ID: " + currentBook.getId() + " devuelto exitosamente por " + currentUser.getNombre() + ".\n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            folioTxt.setText("");
            libroIdTxt.setText("");
            
            // Verificamos sanciones
            int days = Utils.diferenciasDeFechas(Utils.stringToDate(currentLending.getFecha_salida()), new Date());
            if (days > MAX_DAYS_RETURN) {
                int daysDelayed = days - MAX_DAYS_RETURN;
                int sancMoney = daysDelayed * COST_DAY_SANC;
                
                // Aumentamos sanción del usuario y en dinero.
                currentUser.setSancion(currentUser.getSancion() + 1);
                currentUser.setSancion_dinero(currentUser.getSancion_dinero() + sancMoney);
                daoUsers.sancionar(currentUser);
                javax.swing.JOptionPane.showMessageDialog(this, "¡USUARIO SANCIONADO POR ENTREGA A DESTIEMPO! ($" + sancMoney + ") \n", "AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ocurrió un error al prestar el libro. \n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnDevolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDevolver;
    private javax.swing.JTextField folioTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField libroIdTxt;
    // End of variables declaration//GEN-END:variables
}
