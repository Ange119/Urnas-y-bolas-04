import jetbrains.NotNull;

public class Urna {
    private static final int MIN_CAPACIDAD = 1;
    private Bola[] bolas ;

    public Urna( int capacidad ) {
        assert capacidad>= MIN_CAPACIDAD:
            "La capacidad de la urna debe ser >=&d [capacidad=%d]".formatted(MIN_CAPACIDAD, capacidad);
        bolas = new Bola[capacidad];
    }

    public int getCapacidad() {
        return bolas.length;
    }

    public void agregar(){
        agregar(Bola.generar());
    }

    public void agregar(@NotNull Bola bola){
        assert !estaLlena():
                "No es posible agregar la bola %s proque la urna esta llena ".formatted(bola);
        for (int i = 0; i < getCapacidad(); i++) {
            if (bolas[i] == null){
                bolas[i]=bola;
                return ;
            }
        }
    }

    public void agregar(@NotNull Color color){
        agregar(new Bola(color));
    }

    public boolean estaLlena(){
        for (int i = 0; i < getCapacidad(); i++) {
            if(bolas[i] == null){
                return false;
            }
        }
        return true ;
    }

    public boolean estaVacia(){
        for (int i = 0; i < getCapacidad(); i++) {
            if(bolas[i] != null){
                return false;
            }
        }
        return true ;
    }

    public void llenar(){
        while(!estaLlena()){
            agregar();
        }
    }

    public int numBolas(){
        int contador = 0 ;
        for (int i = 0; i < getCapacidad(); i++) {
            if (bolas[i]!=null){
                ++contador;
            }
        }
        return contador;
    }

    private Bola sacar(int pos){
        assert pos>=0 && pos<getCapacidad():
                "La posicion debe estar en el ranfo [%d, %d]".formatted(0,getCapacidad()-1);
        Bola aux = bolas[pos];
        bolas[pos]=null;
        return aux;
    }
    private Bola sacar(){
        assert !estaVacia():
                "No es posible sacar una bola aleatoria si la urna esta vacia";
        //posbolas almacena las posiciones de las bolas
       int [] posBolas = new int[numBolas()];
        for (int i = 0, j=0; i < getCapacidad(); i++) {
            if(bolas[i]!=null){
                posBolas[j++]=i;
            }
        }
        //generamos la posicion aleatoria
        int a =(int)(Math.random()*posBolas.length);
        return bolas[posBolas[a]];
    }

    public Bola sacar(@NotNull Color color){
        for (int i = 0; i < getCapacidad(); i++) {
            if(bolas[i]!=null && bolas[i].getColor().equals(color)){
                return sacar(i);
            }

        }
        return null;
    }

    public void vacias(){
        for (int i = 0; i < getCapacidad(); i++) {
            sacar(i);
        }
    }

    public String toString(){
        StringBuilder sb  =new StringBuilder();
        for (int i = 0; i < getCapacidad(); i++) {
            sb.append("|").append(bolas[i]);
        }
        sb.append("| Capacidad= ").append(getCapacidad());
        sb.append(" ");
        sb.append(estaVacia() ? "Vacia" : "");
        sb.append(estaLlena() ? "Llena " : "");
        return sb.toString();
    }

}
