package BarreiraSimples;

import java.util.concurrent.ThreadLocalRandom;

public class Funcionario {

    private final int codigo;
    private double salario;
    private double desc_renda;
    private double desc_inss;
    private double desc_prev_priv;
    private double desc_plan_hp;
    private double desc_total;
    private double salario_liquido;

    public Funcionario(int codigo){
        this.codigo = codigo;
        salario = ThreadLocalRandom.current().nextInt(1000, 5000 + 1);
        salario_liquido = salario;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getSalario() {
        return salario;
    }

    public double getDesc_renda() {
        return desc_renda;
    }

    public double getDesc_inss() {
        return desc_inss;
    }

    public double getDesc_prev_priv() {
        return desc_prev_priv;
    }

    public double getDesc_plan_hp() {
        return desc_plan_hp;
    }

    public double getDesc_total() {
        return desc_total;
    }

    public double getSalario_liquido() {
        return salario_liquido;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setDesc_renda(double desc_renda) {
        this.desc_renda = desc_renda;
    }

    public void setDesc_inss(double desc_inss) {
        this.desc_inss = desc_inss;
    }

    public void setDesc_prev_priv(double desc_prev_priv) {
        this.desc_prev_priv = desc_prev_priv;
    }

    public void setDesc_plan_hp(double desc_plan_hp) {
        this.desc_plan_hp = desc_plan_hp;
    }

    public void setDesc_total(double desc_total) {
        this.desc_total = desc_total;
    }

    public void setSalario_liquido(double salario_liquido) {
        this.salario_liquido = salario_liquido;
    }
}