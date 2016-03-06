package projeto01;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import projeto01.Movimento;

/**
 * Represents a node of the Tree class. The Node is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree.
 */
public class Node implements Cloneable {
    final int MOVEU_CIMA     = 1;
    final int MOVEU_BAIXO    = 2;
    final int MOVEU_ESQUERDA = 3;
    final int MOVEU_DIREITA  = 4;
    
    public Integer[] posicoes;
    public List<Node> children;
    private int level = 0;
    private Node parent;
    private String Movimento;

    public String getMovimento() {
        return Movimento;
    }

    public void setMovimento(String Movimento) {
        this.Movimento = Movimento;
    }
    
    public void setMovimento(int Movimento) {
        if(Movimento == MOVEU_BAIXO){
            this.Movimento = "Mover para baixo";
        }
        else if(Movimento == MOVEU_CIMA){
            this.Movimento = "Mover para cima";
        }
        else if(Movimento == MOVEU_DIREITA){
            this.Movimento = "Mover para direita";
        }
        else if(Movimento == MOVEU_ESQUERDA){
            this.Movimento = "Mover para esquerda";
        }
        else{
            this.Movimento = "Movimento inválido";
        }
    }

    public int getLevel() {
        return level;
    }

    public int getValorHeuristica() {
        return ValorHeuristica;
    }
    private Boolean Aberto = true;
    private int ValorHeuristica;
 
    public Node() {
        super();
        parent = null;
        children = null;
    }

    public Boolean getAberto() {
        return Aberto;
    }

    public void Fechar(){
        this.Aberto = false;
    }
    
    public Node(Integer[] posicoes) {
        this.posicoes = posicoes;
        parent = null;
        children = null;
        this.CalcularHeuristica();
    }

    public Integer[] getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Integer[] posicoes) {
        this.posicoes = posicoes;
        this.CalcularHeuristica();
    }    
     
    /**
     * Return the children of Node. The Tree is represented by a single
     * root Node whose children are represented by a List<Node>. Each of
     * these Node elements in the List can have children. The getChildren()
     * method will return the children of a Node.
     * @return the children of Node
     */
    public List<Node> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node>();
        }
        return this.children;
    }
 
    /**
     * Sets the children of a Node object. See docs for getChildren() for
     * more information.
     * @param children the List<Node> to set.
     */
    public void setChildren(List<Node> children) {
        this.children = children;
    }
 
    /**
     * Returns the number of immediate children of this Node.
     * @return the number of immediate children.
     */
    public int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }
     
    /**
     * Adds a child to the list of children for this Node. The addition of
     * the first child will create a new List<Node>.
     * @param child a Node object to set.
     */
    public void addChild(Node child) {
        if (children == null) {
            children = new ArrayList<Node>();
        }
        children.add(child);
        child.level = this.level + 1;
        child.parent = this;
    }
     
    /**
     * Inserts a Node at the specified position in the child list. Will     * throw an ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the Node object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void insertChildAt(int index, Node child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChild(child);
            return;
        } else {
            children.get(index); //just to throw the exception, and stop here
            children.add(index, child);
        }
    }
     
    /**
     * Remove the Node element at index index of the List<Node>.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }
    
    public void setParent( Node parent ) {
        this.parent = parent;
        this.level = parent.level + 1;
    }
    
    public Node getParent() {
        return parent;
    }
    
    @Override
    public String toString() {
        return posicoes.toString();
    }
     
    /**
     * Mesmo método da classe node do backup, só que adaptado pra essa 
     * parada das 8 peças comparando só as posições..
     * @param obj é o outro node pra fazer a comparação
     * @return 
     */
    public boolean equals( Object obj ) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Node other = (Node) obj;
      if (posicoes == null) {
        if (other.posicoes != null) return false;
      } 
      //else if (Arrays.equals(posicoes, other.posicoes)) 
      //aparentemente esse equals não compara os valores do vetor..
      //então vamo fazer esse if grande pra caralho..
      else if((posicoes[0] == other.posicoes[0])&&
              (posicoes[1] == other.posicoes[1])&&
              (posicoes[2] == other.posicoes[2])&&
              (posicoes[3] == other.posicoes[3])&&
              (posicoes[4] == other.posicoes[4])&&
              (posicoes[5] == other.posicoes[5])&&
              (posicoes[6] == other.posicoes[6])&&
              (posicoes[7] == other.posicoes[7])&&
              (posicoes[8] == other.posicoes[8])){
        return true;
      }
    return false;
    }
 
    /**
     * Calcula o valorzinho lá pra ordenar depois..
     * @param PosicaoFinal como as peças deveriam estar
     */
    public void CalcularHeuristica(){
        this.ValorHeuristica = 0;
        for(int i = 0; i <= 8; i++) {
            switch(i){
                case 0:{
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 3;}  
                    break;
                }
                case 1:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 2;} 
                    break;                           
                }
                case 2:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 4;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 3;}
                    break;
                }
                case 3:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 3;}
                    break;
                }
                case 4:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 1;}
                    break;
                }
                case 5:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 2;}
                    break;
                }
                case 6:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 4;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 1;}
                    break;
                }
                case 7:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 1;}
                    break;
                }
                case 8:{
                    if (this.posicoes[i] == 1) { this.ValorHeuristica += 4;}
                    if (this.posicoes[i] == 2) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 3) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 4) { this.ValorHeuristica += 3;}
                    if (this.posicoes[i] == 5) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 6) { this.ValorHeuristica += 1;}
                    if (this.posicoes[i] == 7) { this.ValorHeuristica += 2;}
                    if (this.posicoes[i] == 8) { this.ValorHeuristica += 1;}  
                    break;                         
                }
            }  
        }
    }
    
    public void Caminho(){
        System.out.println(Caminho(this));
    } 
    
    private String Caminho(Node node){
        if((node.parent != null)&&(node.Movimento != null)){
            return Caminho(node.parent) + " \n " + node.getMovimento();
        }
        return "";
    }
}