import jetbrains.NotNull;

public class Bola {
    private static int contador=0;  // Nº total de bolas creadas
    private int numero;             // >=1, AUTO, NO MODIFICABLE
    private Color color;            // NO NULO

    public Bola(Color color) {
        setNumero(++contador);
        setColor(color);
    }

    public static Bola generar() {
        Color[] colores=Color.values();
        int r=(int)(Math.random()*colores.length);
        return new Bola(colores[r]);
    }

    public Color getColor() {
        return color;
    }

    public int getNumero() {
        return numero;
    }

    public void setColor(@NotNull Color color) {
        this.color = color;
    }

    private void setNumero(int numero) {
        assert numero>=1: String.format("El número debe ser >=1 [numero=%d]", numero);
        this.numero = numero;
    }

    public static int totalBolas() {
        return contador;
    }



    @Override
    public String toString() {
        return "%d %s".formatted(numero, color);
    }
}
