package app;


import Utils.Arquivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor.costa
 */
public class Programa extends javax.swing.JFrame {

    public static int[] pai;

    public int procura(int x) {
        if (pai[x] == x) {
            return x;
        }
        return pai[x];
    }

    //Método que recebe o vetor de arestas e calcula o peso máximo
    public int agm(Aresta a[], int vertice, int aresta) {
        //Ordenando as arestas
        Arrays.sort(a);
        pai = new int[vertice + 1];
        //Instanciando o vetor pai
        for (int j = 1; j <= vertice; j++) {
            pai[j] = j;
        }

        int cont = vertice + 1, pesoMax = 0;
        int teste = 0, x1, y1;
        Aresta resp[] = new Aresta[aresta];

        //Verifica cada aresta
        for (int i = aresta - 1; i > 0; i--) {
            //Condição para verificar as aresta até o número de vértices -1
            if (teste != vertice - 1) {
                //Usa um outro método para verificar o pai de cada vértice
                if (procura(a[i].x) != procura(a[i].y)) {
                    //Concatena o peso na variável
                    pesoMax += a[i].peso;
                    resp[teste] = a[i];

                    x1 = pai[a[i].x];
                    y1 = pai[a[i].y];

                    pai[a[i].x] = cont;
                    pai[a[i].y] = cont;

                    //Percorre todo o vetor pai atualizando o conteúdo
                    for (int j = 0; j < pai.length; j++) {
                        if (pai[j] == x1 || pai[j] == y1) {
                            pai[a[i].x] = cont;
                            pai[a[i].y] = cont;
                        }
                    }
                    cont++; //usado para controle a atualização do vetor pai
                }
                teste++; //usado para controlar a quantidade de aresta que será usada
            } else {
                break;
            }
        }

        //Exibe numa simples janela o peso máximo
        //JOptionPane.showMessageDialog(null, "Peso Máximo da árvore: " + pesoMax);
        //Fecha o programa ecerrando a execução
        //dispose();
        return pesoMax;

    }

    /**
     * Creates new form Programa
     */
    public Programa() {
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Árvore Geradora Máxima");

        jButton1.setText("...");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel2.setText("Escolha o arquivo de configuração do grafo");

        jButton2.setText("Executar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        String caminho = null;

        JFileChooser abrir = new JFileChooser();
        int retorno = abrir.showOpenDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            caminho = abrir.getSelectedFile().getAbsolutePath();
            //Verificar se é txt.
            if (caminho.endsWith(".txt")) {
                jTextField1.setText(caminho);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de arquivo inválido. Selecione um arquivo .txt!");
            }

        }

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        Aresta vetor[] = null; //Vetor vazio para receber os dados que seram lidos do arquivo
        String arquivoVazio = null;

        //Verifica se foi selecionado algum arquivo.
        if (jTextField1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado. Por favor escolha um arquivo.");

        } else {

            try {
                arquivoVazio = Arquivo.getFirstLine(jTextField1.getText().trim()); //Verifica se o arquivo está vazio
            } catch (IOException ex) {
                Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!Arquivo.AreYouHere(jTextField1.getText())) { //Verifica se o arquivo informado existe
                JOptionPane.showMessageDialog(null, "O arquivo não existe!");

            } else if (arquivoVazio == null) { //Continuação: Verifica se o arquivo está vazio
                JOptionPane.showMessageDialog(null, "O arquivo está vazio!");
            } else {
                try {

                    BufferedReader br = new BufferedReader(new FileReader(jTextField1.getText()));

                    int Nteste = Integer.parseInt(br.readLine()); //Lê a primeira linha do arquivo e armazena a quantidade de testes

                    String linha; //Variável para armazenar linha a linha do arquivo.
                    int o = 0, d = 0, p = 0; //Variáveis auxiliares para armazenar origem, destino e peso da aresta

                    //Somente para formatar o texto de exibição para o usuário sobre a quantidade de grafos contidos no arquivo
                    if (Nteste == 1) {
                        JOptionPane.showMessageDialog(null, "Foi identificado " + Nteste + " grafo no arquivo.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Foram identificados " + Nteste + " grafos no arquivo.");
                    }

                    //Loop para varrer o arquivo conforme a quantidade de teste
                    for (int i = 0; i < Nteste; i++) {
                        linha = br.readLine();
                        if (linha == null) {
                            continue;
                        }

                        int vertice = Integer.parseInt(String.valueOf(linha.charAt(0))); //Armazena a quantidade de vértices do grafo
                        int aresta = Integer.parseInt(String.valueOf(linha.charAt(2))); //Armazena a quantidade de arestas do grafo.
                        vetor = new Aresta[aresta]; //Cria um vetor conforme o tamanho das arestas

                        //Loop para ler linha al inha do arquivo
                        for (int j = 0; j < aresta; j++) {
                            linha = br.readLine();
                            int[] result = Arquivo.getIntArray2(linha);
                            o = result[0];
                            d = result[1];
                            p = result[2];
                            //É captura a primeira posição da linha lida e convertida para inteiro                        

                            vetor[j] = new Aresta(o, d, p);//Cria uma nova aresta a armazena ela no vetor de arestas
                        }

                        //Chama o método para calcular o peso das arestas e o retorno é armazenado na variável resutlado
                        int resultado = agm(vetor, vertice, aresta);

                        //Formata o resusltado exibindo o peso máximo das arestas do grafo
                        JOptionPane.showMessageDialog(null, "Peso máximo do " + (i + 1) + "º grafo = " + resultado);
                    }

                    //Feche o buffer de leitura do arquivo
                    br.close();

                } catch (Exception ex) {
                    Logger.getLogger(Programa.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_jButton2MouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Programa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Programa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Programa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Programa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Programa().setVisible(true);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}