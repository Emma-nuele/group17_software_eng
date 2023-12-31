/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator.developed;

public class Number {
    
    private double re;
    private double im;
    
    public Number(double re, double im){
        
        this.re = re;
        this.im = im;
                
    }

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public double getIm() {
        return im;
    }

    public void setIm(double im) {
        this.im = im;
    }

    @Override
    public String toString() {
        
        String posIm= "";
        String posRe= "";
        
        if(im>=0)
            posIm="+";
        if(re>=0)
            posRe="+";
        return posRe + re + posIm + im + "j";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true; 
        if(obj==null || getClass() != obj.getClass())
            return false; 
        Number other = (Number) obj; 
        return Double.compare(other.re, re) == 0 && Double.compare(other.im, im) ==0; 
         
    }
    
    
}
