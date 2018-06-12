
/**
 *
 * @author vitor.costa
 */
public class Aresta implements Comparable<Aresta> {

    int peso, x, y;

    public Aresta(int x, int y, int peso) {
        this.x = x;
        this.y = y;
        this.peso = peso;
        
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(Aresta o) {
        return this.peso - o.peso;
    }

    @Override
    public String toString() {
        return "Aresta{" + "Origem=" + getX() + ", Destino=" + getY() + ", Peso=" + getPeso() + '}' + "\n";
    }

   

}
