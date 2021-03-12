package ParalelismoDistribuido;

import java.io.Serializable;

public class Matrix implements Serializable {
    double[][] matriz;

    public Matrix(double[][] matriz) { this.matriz=matriz; }

    public double[][] getMatriz() { return matriz; }
}
