
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class GameFrame extends javax.swing.JFrame {
    

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameFrame.class.getName());
    
    String[] words;
    
    int[] x ;
    int[] y ;
   
    int score = 0;
    int mistakes = 0;
    static int highScore;
    long startTime;

    boolean[] visible;
    
    javax.swing.Timer timer;
    
    /**
     * Creates new form GameFrame
     */
    public GameFrame() {
        initComponents();
        
        java.awt.EventQueue.invokeLater(() -> {
            typeField.requestFocusInWindow();
        });
        
        GamePanel panel = new GamePanel();
        panel.setBounds(0, 0, 740, 658);

        jPanel1.setLayout(null);
        jPanel1.add(panel);
        
        scoreField.setText("0");
        mistakeField.setText("0");
        
        setLocationRelativeTo(null);
        
    }
    
    private String currentDifficulty;
    
    public void startGame(String difficulty) {
        
        switch (difficulty){
            case "EASY" -> words = new String[]{
                    "RUBY", "PHP", "STAR", "BEAR", "SHOE",
                    "CAKE", "BREAD", "APPLE", "CHAIR", "TABLE",
                    "WIND", "SMILE", "LAUGH", "DARK", "YELLOW",
                    "JUMP", "SWIM", "DANCE", "DREAM", "CAR"
                };
                
            case "MEDIUM" -> words = new String[]{
                "METHODS", "PROGRAM", "STATIC", "OBJECT", "CULTURE",
                "TIGER", "POSITIVE", "DART", "FLAME", "EAGLE",
                "ANSWER", "FROST", "SWORD", "CLASS", "BLOSSOM",
                "EIFFEL", "ENGINEER", "OCEAN", "COMPUTER", "SCIENCE",
            };
            
            case "HARD" -> words = new String[]{
                "PROGRAMMING", "STRAWBERRY", "VARIABLES", "RECONCILIATION", "CIRCUMFERENCE",
                "SUNSHINE", "JAVASCRIPT", "ENCAPSULATION", "PROCEDURAL", "ABSTRACTION",
                "ARCHEOLOGY", "BUREAUCRACY", "POLYMORPHISM", "RELATIONSHIP", "PRELLELOGRAM",
                "UNIVERSE", "INHERITANCE", "JUSTIFICATION", "TECHNOLOGY", "IRRESPONSIBILTY",
            };
            
            default -> words = new String[]{"HELLO", "JAVA", "CODE", "GAME", "MOUSE"};
            }
        
        this.currentDifficulty = difficulty; 
        
        //this are the positions of those words
        x = new int[words.length];
        y = new int[words.length];
        visible = new boolean[words.length];
        
        java.util.Random rand = new java.util.Random();
        for (int n = 0; n<words.length; n++){
            x[n] = 50 + rand.nextInt(550);
            y[n] = -(50 + (n * 80));
            visible[n] = true;
        }
        
        //reset values
        score = 0;
        mistakes = 0;
        scoreField.setText("0");
        mistakeField.setText("0");
 
        startTime = System.currentTimeMillis();
        
        //fall speed based on mode chosen
        int speed;
        speed = switch (difficulty) {
            case "EASY" -> 2;
            case "MEDIUM" -> 4;
            case "HARD" -> 6;
            default -> 3;
        };
        
                
        timer = new javax.swing.Timer(30, (ActionEvent e) -> {
            for (int i=0; i<y.length; i++){
                
                if (visible[i]){
                    y[i] += speed;
                    
                    if (y[i] > 600){
                        visible[i] = false;
                        
                        mistakes++;
                        mistakeField.setText(String.valueOf(mistakes));
                        
                        if (mistakes >= 5){
                            
                            timer.stop();
                            
                            long endTime = System.currentTimeMillis();
                            long survivedMilliseconds = endTime - startTime;
                            long survivedSeconds = survivedMilliseconds / 1000;

                            long minutes = survivedSeconds / 60;
                            long seconds = survivedSeconds % 60;

                            String timeFormatted = minutes + ":" + String.format("%02d", seconds);
                            
                            resultsFrame rf = new resultsFrame(score, mistakes, highScore, timeFormatted);
                            rf.setVisible(true);
                            
                            dispose();
                        }
                        
                    }
                }

            }
            respawnWords();
            repaint();
        });
        
        startTime = System.currentTimeMillis();
        
        timer.start();
    }
    
    private void respawnWords(){
        boolean allGone = true;
        for (boolean v : visible) {
            if (v) {
                allGone = false;
                break;
            }
        }
        
        if (allGone){
            java.util.Random rand = new java.util.Random();
            for (int i=0; i<words.length; i++){
                x[i] = 50 + rand.nextInt(550);
                y[i] = -(50 + (i * 80));
                visible[i] = true;
            }
        }
    }
    
    class GamePanel extends JPanel{
    
        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            setOpaque(false);
            
            if (words == null) return;

            g.setFont(new Font("Arial", Font.BOLD, 20));

            for(int i=0; i<words.length; i++){
                if(visible[i]){
                    g.drawString(words[i], x[i], y[i]);
                }
            }
        }
    }
    
    private javax.swing.Timer gameTimer;
    
   public void pauseGame() {
    if (timer != null) timer.stop();
    }

    public void resumeGame() {
    if (timer != null) timer.start();
    }  
    
    public void restartGame() {
    // reset your game state here
        timer.stop();
        timer.start();
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
        typeField = new javax.swing.JTextField();
        scoreLabel = new javax.swing.JLabel();
        scoreField = new javax.swing.JTextField();
        mistakeLabel = new javax.swing.JLabel();
        mistakeField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        currentHighScore = new javax.swing.JLabel();
        currentHighScoreField = new javax.swing.JTextField();
        pauseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(162, 188, 224));

        typeField.setBackground(new java.awt.Color(255, 255, 255));
        typeField.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        typeField.setForeground(new java.awt.Color(0, 0, 0));
        typeField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        typeField.addActionListener(this::typeFieldActionPerformed);

        scoreLabel.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(0, 51, 51));
        scoreLabel.setText("Score:");

        scoreField.setBackground(new java.awt.Color(153, 255, 204));
        scoreField.setFont(new java.awt.Font("MS UI Gothic", 3, 18)); // NOI18N
        scoreField.addActionListener(this::scoreFieldActionPerformed);

        mistakeLabel.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        mistakeLabel.setForeground(new java.awt.Color(0, 51, 51));
        mistakeLabel.setText("Mistakes:");

        mistakeField.setBackground(new java.awt.Color(153, 255, 204));
        mistakeField.setFont(new java.awt.Font("MS UI Gothic", 3, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(162, 188, 224));

        currentHighScore.setFont(new java.awt.Font("MS UI Gothic", 1, 20)); // NOI18N
        currentHighScore.setForeground(new java.awt.Color(0, 0, 0));
        currentHighScore.setText("Highest Score:");

        currentHighScoreField.setBackground(new java.awt.Color(162, 188, 224));
        currentHighScoreField.setFont(new java.awt.Font("MS UI Gothic", 0, 19)); // NOI18N
        currentHighScoreField.setForeground(new java.awt.Color(0, 0, 0));
        currentHighScoreField.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentHighScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentHighScoreField, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentHighScore)
                    .addComponent(currentHighScoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pauseButton.setBackground(new java.awt.Color(204, 204, 255));
        pauseButton.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        pauseButton.setForeground(new java.awt.Color(0, 0, 0));
        pauseButton.setText("⛯");
        pauseButton.addActionListener(this::pauseButtonActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(scoreLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(mistakeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mistakeField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(402, 402, 402)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scoreField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scoreLabel)
                    .addComponent(mistakeLabel)
                    .addComponent(mistakeField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void typeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeFieldActionPerformed
        // TODO add your handling code here:
        String typed = typeField.getText().trim().toUpperCase();
        boolean correct = false;
        
        for (int i=0; i<words.length; i++){
            if(visible[i] && typed.equals(words[i])){
                
                visible[i] = false; //this will make the word disappear.
                score++;
                
                if (score > highScore) {
                    highScore = score;
                
                }
                
                currentHighScoreField.setText(String.valueOf(highScore));
                
                scoreField.setText(String.valueOf(score));
                
                correct = true;
                break;
            }
        }
        
        if (!correct){
            mistakes++;
            mistakeField.setText(String.valueOf(mistakes));
        }
        typeField.setText("");
        repaint();
    }//GEN-LAST:event_typeFieldActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        // TODO add your handling code here:
        if (timer != null) {
            timer.stop(); // stop words from falling
        }
        pauseFrame pf = new pauseFrame(this, currentDifficulty);
        pf.setVisible(true);
        
        java.awt.EventQueue.invokeLater(() -> {
            typeField.requestFocusInWindow();
        });
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void scoreFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scoreFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GameFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentHighScore;
    private javax.swing.JTextField currentHighScoreField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField mistakeField;
    private javax.swing.JLabel mistakeLabel;
    private javax.swing.JButton pauseButton;
    private javax.swing.JTextField scoreField;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JTextField typeField;
    // End of variables declaration//GEN-END:variables
}
