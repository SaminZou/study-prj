package com.samin.designpattern.build.style2;

public class ProductionObj {

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    private ProductionObj(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public static class Builder {
        private String a;
        private String b = "b";
        private String c = "c";
        private String d;
        private String e = "d";

        public Builder(String a, String d) {
            this.a = a;
            this.d = d;
        }

        public Builder b(String b) {
            this.b = b;
            return this;
        }

        public Builder c(String c) {
            this.c = c;
            return this;
        }

        public Builder e(String e) {
            this.e = e;
            return this;
        }

        public ProductionObj build() {
            return new ProductionObj(this);
        }
    }

    @Override
    public String toString() {
        return "Production{"
                + "a='"
                + a
                + '\''
                + ", b='"
                + b
                + '\''
                + ", c='"
                + c
                + '\''
                + ", d='"
                + d
                + '\''
                + ", e='"
                + e
                + '\''
                + '}';
    }
}
